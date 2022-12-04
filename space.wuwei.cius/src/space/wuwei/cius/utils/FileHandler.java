package space.wuwei.cius.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileHandler {
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skelton.xml";
	
	public static Document doc = null;
	public static XPath xpath = null;
	
	public static Map<String/* id */,Binding> bindingDict = new HashMap<>();
	public static TreeMap<Integer/* semSort */, Binding> semBindingMap = new TreeMap<>();
	static TreeMap<Integer/* synSort */, Binding> synBindingMap = new TreeMap<>();
	public static TreeMap<Integer/* parent */, ArrayList<Integer/* child */>> childMap = new TreeMap<>();
	static TreeMap<Integer/* parent */, TreeMap<Integer/* child */, NodeList/* child */>> childNodeListMap = new TreeMap<>();
    public static TreeMap<Integer/* semSort */, String/* id */> multipleMap = new TreeMap<>();

	public static void parseBinding() {
		try (BufferedReader fileReader = new BufferedReader(new FileReader(JP_PINT_CSV)))
		{
		  String line = "";
		  line = fileReader.readLine();
		  String[] headers = line.split(",");
		  Integer[] parents = new Integer[6];
		  //Read the file line by line
		  while ((line = fileReader.readLine()) != null) {
		    //Get all tokens available in line
		    String[] tokens = line.split(",");
//		    //Verify tokens
//		    System.out.println(Arrays.toString(tokens));
		    Binding binding = new Binding(0, "", "", "", "", "", 0, "", "");
		    for (int i = 0; i < headers.length; i++) {
		    	String key = headers[i].replace("\uFEFF", "");
		    	String value = tokens[i];
		    	Integer order = -1;
		    	switch (key) {
		    	case "semSort":
		    		if (value.matches("^[0-9]+$")) {
		    			order = Integer.parseInt(value);
		    		}
		    		binding.setSemSort(order);
		    		break;
		    	case "id":
		    		binding.setID(value);
		    		break;
		    	case "level":
		    		binding.setLevel(value);
		    		break;
		    	case "businessTerm":
		    		binding.setBT(value);
		    		break;
		    	case "card":
		    		binding.setCard(value);
		    		break;
		    	case "dataType":
		    		binding.setDatatype(value);
		    		break;
		    	case "synSort":
		    		if (value.matches("^[0-9]+$")) {
		    			order = Integer.parseInt(value);
		    		}
		    		binding.setSynSort(order);
		    		break;
		    	case "xPath":
		    		binding.setXPath(value);
		    		break;
		    	case "occur":
		    		binding.setOccur(value);
		    	}
		    }
		    String id = binding.getID();
		    String l = binding.getLevel();
		    Integer sort = binding.getSemSort();
		    int level = 0;
		    if (l.matches("^[0-9]+$")) {
		    	level = Integer.parseInt(l);
			    parents[level] = sort;
		    	bindingDict.put(id, binding);
		    	semBindingMap.put(sort, binding);
			    if (level > 0) {
			    	int parent_level = level - 1;
				    Integer parent_sort = parents[parent_level];
				    ArrayList<Integer> children = null;
				    if (childMap.containsKey(parent_sort)) {
				    	children = childMap.get(parent_sort);
				    } else {
				    	children = new ArrayList<Integer>();
				    }
				    children.add(sort);
				    childMap.put(parent_sort,children);
			    }
			  }
		  }
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
	}

	public static Document parseInvoice(String xmlfile) {
		try {
		    //Build DOM
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true); // never forget this!
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    doc = builder.parse(new FileInputStream(new File(xmlfile)));
		    //Create XPath
		    XPathFactory xpathfactory = XPathFactory.newInstance();
		    xpath = xpathfactory.newXPath();
		    xpath.setNamespaceContext(new NamespaceResolver(doc));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static NodeList getElements(Element parent, String id) {
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replaceAll("/Invoice/", "/*/");
		NodeList nodes = xpathEvaluate(parent, xpath);
		return nodes;
	}
	
	public static TreeMap<Integer, NodeList> getChildren(Node e, String id) {
		Integer parent_sort = ((Binding) bindingDict.get(id)).getSemSort();
		Binding parent_binding = (Binding) bindingDict.get(id);
		String parent_xpath = parent_binding.getXPath();
		ArrayList<Integer> children = childMap.get(parent_sort);
		TreeMap<Integer, NodeList> childList = new TreeMap<>();	
		for (Integer sort: children) {
			String childID =  ((Binding) semBindingMap.get(sort)).getID();
			Binding binding = (Binding) bindingDict.get(childID);
			String xpath = binding.getXPath();
			if (! parent_xpath.equals("/Invoice")) {
				xpath = xpath.replace(parent_xpath, ".");
			}
			xpath = xpath.replace("/Invoice/", "/*/");
			if (childID.matches("^ibt-.*$")) {
				xpath += "/text()";
			}
			NodeList nodes = xpathEvaluate(e, xpath);
//			if (nodes.getLength() > 0) {
//				for (int i = 0; i < nodes.getLength(); i++) {
//	            	Node node = nodes.item(i);
//	            	System.out.println("- getChildren " + sort + ":" + childID + node.toString() + " " + xpath);
//				}
//			}
			childList.put(sort, nodes);
		}
		return childList;
	}

	public static NodeList xpathEvaluate(Node node, String path) {
		XPathExpression expr = null;
		Object result;
		try {
			expr = xpath.compile(path);
			result = expr.evaluate(node, XPathConstants.NODESET);
			return (NodeList) result; 
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void csvFileWrite(String filename, String charset) {
		System.out.println(filename + " " + charset);
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			Charset cs = Charset.forName(charset);
			OutputStreamWriter osw = new OutputStreamWriter(fo, cs);
			BufferedWriter bw = new BufferedWriter(osw);
			// header
			String headerLine = String.join(",", Invoice2csv.header);
			bw.write(headerLine);
            bw.write("\n");
            // data
            for(int i=0; i < Invoice2csv.tidyData.size(); i++) {
            	String line = String.join(",", Invoice2csv.tidyData.get(i));
                bw.write(line);
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
