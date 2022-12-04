package space.wuwei.cius.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

public class Invoice2csv {
	
	static Document doc = null;
	static XPath xpath = null;
	
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skelton.xml";
	static String IN_XML = "data/xml/Example1.xml";
	static String OUT_CSV = "data/csv/Example1.csv";

	static Map<String/* id */,Binding> bindingDict = new HashMap<>();
	static TreeMap<Integer/* semSort */, Binding> semBindingMap = new TreeMap<>();
	static TreeMap<Integer/* synSort */, Binding> synBindingMap = new TreeMap<>();
	static TreeMap<Integer/* parent */, ArrayList<Integer/* child */>> childMap = new TreeMap<>();
	static TreeMap<Integer/* parent */, TreeMap<Integer/* child */, NodeList/* child */>> childNodeListMap = new TreeMap<>();
    static TreeMap<Integer/* semSort */, String/* id */> multipleMap = new TreeMap<>();
	// prepare CSV records
    static TreeMap<Integer/* sort */,Integer/* seq */> boughMap = new TreeMap<>();
    static ArrayList<TreeMap<Integer, Integer>> boughMapList = new ArrayList<>();
    static TreeMap<Integer, String> rowMap = new TreeMap<>();
    static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();
    // CSV table
	static ArrayList<String> header = new ArrayList<>();
	static TreeMap<Integer,String> indexMap = new TreeMap<>();
	static TreeMap<Integer,String> dataMap = new TreeMap<>();
    static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();
    
	public static void main(String[] args) {
		parseBinding(JP_PINT_CSV);
		doc = parseInvoice(IN_XML);
		Element root = (Element) doc.getChildNodes().item(0);
//		// cbc:DocumentCurrencyCode
//		String documentCurrencyCode = getElementValue(root, "ibt-005");//"/*/cbc:DocumentCurrencyCode/text()");
//	    System.out.println(documentCurrencyCode);
//	    
//		// ibt-110 Invoice total TAX amount
//	    String invoiceTotalTaxAmount = getElementValue(root,"ibt-110");
//	    System.out.println(invoiceTotalTaxAmount);
//	    
//	    // ibg-23 TAX BREAKDOWN
//	    nodes = getElements(root,"ibg-23");
//	    nodesLength = nodes.getLength();
//	    for (int i = 0; i < nodesLength; i++) {	      
//	        node = nodes.item(i);
//	        TreeMap<Integer, Node> children = getChildren((Element) node, "ibg-23");
//	        // Iterating HashMap through for loop
//	        for (Map.Entry<Integer, Node> child : children.entrySet()) {
//	        	System.out.println(child.getKey() + " = " + ((Node) child.getValue()).getNodeValue());	  
//	        }
//	    }
		for (Map.Entry<String, Binding> entry : bindingDict.entrySet()) {
			Binding binding = entry.getValue();
			Integer sort = binding.getSemSort();
			String id = binding.getID();
			String card = binding.getCard();
			if (card.matches("^...n$")) {
				NodeList nodelist = getElements((Element) root, id);
				if (nodelist.getLength() > 1) {
					multipleMap.put(sort, id);
				}
			}
		}
	    
		Binding binding = bindingDict.get("ibg-00");
		Integer sort = binding.getSemSort();
		boughMap.put(1000,0);
		boughMapList.add(boughMap);
		
	    fillGroup(root, sort, boughMapList);   
	    fillTable();
        csvFileWrite(OUT_CSV,"UTF-8");

		System.out.println("END");
	}
	
	private static void fillTable() {
		header.add("ibg-00");
		for (Map.Entry<Integer,String> multipleEntry : multipleMap.entrySet()) {
			String multipleID = multipleEntry.getValue();
			header.add(multipleID);
		}
		for (Map.Entry<Integer,Binding> dataEntry : semBindingMap.entrySet()) {
			Binding dataBinding = dataEntry.getValue();
			if (dataBinding.isUsed()) {
				String dataID = dataBinding.getID();
				header.add(dataID);
			}
		}
		System.out.println(header.toString());
		for (Map.Entry<String, TreeMap<Integer, String>> entryRow : rowMapList.entrySet()) {
			ArrayList<String> record = new ArrayList<>();
			for (int i = 0; i < header.size(); i++) {
				record.add("");
			}
			// bough
			String rowMapListKey = entryRow.getKey();
			String[] boughs = rowMapListKey.split(",");
			for (String bough : boughs) {
				String[] indexes = bough.split(" ");
				for (String index : indexes) {
					List<String> data = Arrays.asList(index.split("="));
					Integer boughSort = Integer.parseInt(data.get(0));
					Binding boughBinding = semBindingMap.get(boughSort);
					String boughID = boughBinding.getID();
					String boughSeq = data.get(1);
					int boughIndex = header.indexOf(boughID);
					record.set(boughIndex, boughSeq);
				}
			}
			// data
			TreeMap<Integer, String> rowMap = entryRow.getValue();
			for (Map.Entry<Integer, String> entry : rowMap.entrySet()) {
				Integer sort = entry.getKey();
				String value = entry.getValue();
				Binding binding = semBindingMap.get(sort);
				String id = binding.getID();
				int dataIndex = header.indexOf(id);
				record.set(dataIndex, value);
			}
			tidyData.add(record);
		}
		System.out.println(tidyData.toString());			
	}

	private static void csvFileWrite(String filename, String charset) {
		System.out.println(filename + " " + charset);
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			Charset cs = Charset.forName(charset);
			OutputStreamWriter osw = new OutputStreamWriter(fo, cs);
			BufferedWriter bw = new BufferedWriter(osw);

			String headerLine = String.join(",", header);
			bw.write(headerLine);
            bw.write("\n");
            for(int i=0; i < tidyData.size(); i++) {
            	String line = String.join(",", tidyData.get(i));
                bw.write(line);
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static void fillData(Integer sort, String value, ArrayList<TreeMap<Integer,Integer>> boughMapList) {
		Binding binding = (Binding) semBindingMap.get(sort);
        String id = binding.getID();
		String businessTerm = binding.getBT();
		binding.setUsed(true);
		System.out.println("- fillData boughMapList=" + boughMapList.toString() + sort + ":" + id + " " + businessTerm + " " + value);

		String rowMapListKey = "";
		for (int i = 0; i < boughMapList.size(); i++) {
			TreeMap<Integer, Integer> boughMap = boughMapList.get(i);
			String rowMapKey = "";
			for (Map.Entry<Integer, Integer> entry : boughMap.entrySet()) {
				Integer boughSort = entry.getKey();
				Integer seq = entry.getValue();
				rowMapKey += (boughSort + "=" + seq + " ");
				Binding boughBinding = semBindingMap.get(boughSort);
				String boughID = boughBinding.getID();
				indexMap.put(boughSort, boughID);
			}
			rowMapKey = rowMapKey.trim();
			rowMapListKey += (rowMapKey + ",");
		}
		rowMapListKey = rowMapListKey.substring(0, rowMapListKey.length() - 1);
		System.out.println("|"+rowMapListKey+"|");
		
		TreeMap<Integer, String> rowMap = new TreeMap<>();
		if (rowMapList.containsKey(rowMapListKey)) {
			rowMap = rowMapList.get(rowMapListKey);
		}
		rowMap.put(sort, value);
		rowMapList.put(rowMapListKey, rowMap);
	}
	
	private static void fillGroup(Node parent, Integer sort, ArrayList<TreeMap<Integer, Integer>> boughMapList) {	
		// get child Nodes
		Binding binding = semBindingMap.get(sort);
		String id = binding.getID();
		String businessTerm = binding.getBT();

		TreeMap<Integer, NodeList> childList = getChildren(parent,id);
		
		if (childList.size() == 0) {
			System.out.println("- fillGroup boughMapList=" + boughMapList.toString() + sort + ":" + id + " " + businessTerm + " is Empty");
			return;
		}
   	
		for (Integer childSort : childList.keySet()) {
			Binding childBinding = (Binding) semBindingMap.get(childSort);
            String childID = childBinding.getID();
    		String childBusinessTerm = childBinding.getBT();
    		int childLevel = Integer.parseInt(childBinding.getLevel());
           
            NodeList nodes = childList.get(childSort);
            Integer countNodes = nodes.getLength();             
            if (countNodes > 0) {
            	if (childID.matches("^ibg-[0-9]+$")) {
					System.out.println("- fillGroup "+ childLevel +" childList " + childSort + ":" + childID + nodes.item(0).toString());
	            }
	            for (int i = 0; i < countNodes; i++) {
	            	Node node = nodes.item(i);
		        	if (childID.matches("^ibt-[0-9]+.*$")) {
	        			String value = node.getTextContent();
		            	if (!(null==value || value.equals(""))) {
			            	fillData(childSort, value, boughMapList);
		            	}
		            } else {
		            	boolean isKeyPresent = isMultiple(childSort);
		                if (isKeyPresent && countNodes > 1) {
		                	TreeMap<Integer,Integer> boughMap = boughMapList.get(boughMapList.size() - 1);
		                	Integer lastkey = boughMap.lastKey();
		                	Integer lastvalue = boughMap.get(lastkey);
			            	@SuppressWarnings("unchecked")
							TreeMap<Integer,Integer> boughMap1 = (TreeMap<Integer, Integer>) boughMap.clone();
			            	if (childSort != lastkey) {
			            		if (boughMap.size() < childLevel + 1) {
			                		boughMap1.put(childSort, i);
			                		boughMapList.add(boughMap1);
			            		} else {
			            			boughMap1.pollLastEntry();
			            			boughMap1.put(childSort, i);
			            			boughMapList.remove(boughMapList.size() - 1);
			            			boughMapList.add(boughMap1);
			            		}
			            	} else if (countNodes > 1) {
			            		lastvalue +=1;
			            		boughMap.put(lastkey, lastvalue);
			            	}
		                }
	                	System.out.println("* fillGroup "+ childLevel + " boughMapList=" + boughMapList.toString() /*+ sort +":" + id + " "*/ + businessTerm + "->" /*+ childSort + ":" + childID + " "*/ + childBusinessTerm);
	        			
	                	fillGroup(node, childSort, boughMapList);
		            }
	            }
            }
        }
	}

	private static boolean isMultiple(Integer sort) {
		// Get the iterator over the HashMap
		Iterator<Map.Entry<Integer, String>> iterator = multipleMap.entrySet().iterator();
		// flag to store result
		boolean isKeyPresent = false;
		// Iterate over the HashMap
		while (iterator.hasNext()) {
		    // Get the entry at this iteration
		    Map.Entry<Integer, String> entry = iterator.next();
		    // Check if this key of HashMap is the required key
		    if (sort == entry.getKey()) {
		        isKeyPresent = true;
		    }
		}
		return isKeyPresent;
	}
	
	private static NodeList getElements(Element parent, String id) {
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replaceAll("/Invoice/", "/*/");
		NodeList nodes = xpathEvaluate(parent, xpath);
		return nodes;
	}
	
	private static TreeMap<Integer, NodeList> getChildren(Node e, String id) {
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
	
	private static NodeList xpathEvaluate(Node node, String path) {
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

	private static void parseBinding(String fname) {
		try (BufferedReader fileReader = new BufferedReader(new FileReader(fname)))
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

	private static Document parseInvoice(String invoice) {
		try {
		    //Build DOM
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true); // never forget this!
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    doc = builder.parse(new FileInputStream(new File(invoice)));
		    //Create XPath
		    XPathFactory xpathfactory = XPathFactory.newInstance();
		    xpath = xpathfactory.newXPath();
		    xpath.setNamespaceContext(new NamespaceResolver(doc));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}