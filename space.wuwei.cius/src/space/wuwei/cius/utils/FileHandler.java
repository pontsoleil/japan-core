package space.wuwei.cius.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileHandler {
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skeleton.xml";
	
	public static Document doc = null;
	public static XPath xpath = null;
	public static Element root = null;
	public static String ROOT_ID = "ibg-00";
	public static HashMap<String,String> nsURIMap = null;
	
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
		    String[] tokens = line.split(",");
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
		    Integer semSort = binding.getSemSort();
		    Integer synSort = binding.getSynSort();
		    int level = 0;
		    if (l.matches("^[0-9]+$")) {
		    	level = Integer.parseInt(l);
			    parents[level] = semSort;
		    	bindingDict.put(id, binding);
		    	semBindingMap.put(semSort, binding);
		    	synBindingMap.put(synSort, binding);
		    	// fill semantic childMap
			    if (level > 0) {
			    	int parent_level = level - 1;
				    Integer parent_semSort = parents[parent_level];
				    ArrayList<Integer> children = null;
				    if (childMap.containsKey(parent_semSort)) {
				    	children = childMap.get(parent_semSort);
				    } else {
				    	children = new ArrayList<Integer>();
				    }
				    children.add(semSort);
				    childMap.put(parent_semSort,children);
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
		    // root
			root = (Element) FileHandler.doc.getChildNodes().item(0);   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static void parseSkeleton() {
		String skeleton = JP_PINT_XML_SKELTON;
		try {
		    //Build DOM
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true); // never forget this!
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    //Parse XML file
		    FileInputStream fis = new FileInputStream(new File(skeleton));
		    doc = builder.parse(fis);
		    //Create XPath
		    XPathFactory xpathfactory = XPathFactory.newInstance();
		    xpath = xpathfactory.newXPath();
		    xpath.setNamespaceContext(new NamespaceResolver(doc));
		    // root
		 	root = (Element) FileHandler.doc.getChildNodes().item(0);
		 	nsURIMap = new HashMap<String,String>();
		 	NamedNodeMap attributes = root.getAttributes();
		 	for (int i = 0; i < attributes.getLength(); i++) {
		 		Node attribute = attributes.item(i);
	            String name = attribute.getNodeName();
	            if ("xmlns".equals(name)) {
	            	name = "";
	            } else {
	            	name = name.replace("xmlns:","");
	            }
	            String value = attribute.getNodeValue();
	            nsURIMap.put(name, value);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static NodeList getElements(Element parent, String id) {
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replaceAll("/Invoice/", "/*/");
		NodeList nodes = xpathEvaluate(parent, xpath);
		return nodes;
	}
	
	public static Element appendElementNS(Element parent,
											String nsURI, 
											String prefix, 
											String qname, 
											String content, 
											HashMap<String, String> attrMap) {
		System.out.println("- FileHaldler.appendElementNS "+prefix+":"+qname);
		Element element = doc.createElementNS(nsURI, qname);
		// Set the desired namespace and prefix
		element.setPrefix(prefix);
		if (content!="") {
			element.setTextContent(content);
		}
		if (null!=attrMap) {
			for (Map.Entry<String, String> entry : attrMap.entrySet()) {
	           String name = entry.getKey();
	           String value = entry.getValue();
	           Attr attribute = doc.createAttribute(name);
	           attribute.setValue(value);
	           element.setAttributeNode(attribute);
	        }
		}
		parent.appendChild(element);
		return element;
	}
	
	public static TreeMap<Integer, NodeList> getChildren(Node e, String id) {
		Integer parent_semSort = ((Binding) bindingDict.get(id)).getSemSort();
		Binding parent_binding = (Binding) bindingDict.get(id);
		String parent_xpath = parent_binding.getXPath();
		ArrayList<Integer> children = childMap.get(parent_semSort);
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
			childList.put(sort, nodes);
		}
		return childList;
	}

	public static NodeList xpathEvaluate(Node node, String xPath) {
		XPathExpression expr = null;
		Object result;
		try {
			xPath = xPath.replace("/Invoice/", "/*/");
			expr = xpath.compile(xPath);
			result = expr.evaluate(node, XPathConstants.NODESET);
			return (NodeList) result; 
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeXML(Document doc, String filename)  {
		try {
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);
		    FileOutputStream output = new FileOutputStream(filename);
			StreamResult result = new StreamResult(output);
//		    StreamResult result = new StreamResult(System.out);
	         // pretty print XML
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");		    
			transformer.transform(source, result);
		} catch (FileNotFoundException | TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	public static void csvFileWrite(String filename, String charset) {
		System.out.println("FileHandler.csvFileWrite " + filename + " " + charset);
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

	public static void csvFileRead(String filename, String charset) {
		System.out.println("FileHandler.csvFileRead " + filename + " " + charset);
		Csv2Invoice.header = new ArrayList<String>();
		Csv2Invoice.tidyData = new ArrayList<ArrayList<String>>();
		try {
			FileInputStream fi = new FileInputStream(filename);
			Charset cs = Charset.forName(charset);
			InputStreamReader isw = new InputStreamReader(fi, cs);
			BufferedReader br = new BufferedReader(isw);
			// header
			String headerLine = br.readLine();
			String[] fields = headerLine.split(",");
			for (String field : fields) {
				Csv2Invoice.header.add(field);
			}
			// data
			String line;		
			while ((line = br.readLine()) != null) {
				fields = line.split(",");
				ArrayList<String> record = new ArrayList<String>();
				for (String field : fields) {
					record.add(field);
				}
				Csv2Invoice.tidyData.add(record);
			}
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
