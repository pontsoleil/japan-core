package space.wuwei.cius.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
//import java.io.StringReader;
import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Iterator;
//import java.util.Hashtable;
import java.util.Map;
//import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;
//import java.util.Arrays;
//import java.util.stream.Collectors;

//import javax.xml.XMLConstants;
//import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
//import org.xml.sax.InputSource;

public class Invoice2csv {
	
	static Document doc = null;
	static XPath xpath = null;
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skelton.xml";
	
	static Map<String/* id */,Binding> bindingDict = new HashMap<>();
	static TreeMap<Integer/* semSort */, Binding> semBindingMap = new TreeMap<>();
	static TreeMap<Integer/* synSort */, Binding> synBindingMap = new TreeMap<>();
	static TreeMap<Integer/* parent */, ArrayList<Integer/* child */>> childMap = new TreeMap<>();
	static TreeMap<Integer/* parent */, TreeMap<Integer/* child */, NodeList/* child */>> childNodeListMap = new TreeMap<>();
    static TreeMap<Integer/* semSort */, String/* id */> multipleMap = new TreeMap<>();
	// prepare CSV records
    static TreeMap<Integer/* sort */,Integer/* seq */> boughMap = new TreeMap<>();
    static ArrayList<TreeMap<Integer , Integer>> boughMapList = new ArrayList<>();
    static TreeMap<Integer,String> rowMap = new TreeMap<>();
    static ArrayList<TreeMap<Integer , String>> rowMapList = new ArrayList<>();

	public static void main(String[] args) {
		NodeList nodes = null;
		Node node = null;
		int nodesLength = 0;
		
		parseBinding(JP_PINT_CSV);
		doc = parseInvoice("data/xml/Example1.xml");
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
			String xPath = binding.getXPath();
			if (card.matches("...n")) {
				NodeList nodelist = getElements((Element) root, id);
				if (nodelist.getLength() > 1) {
					multipleMap.put(sort, id);
				}
			}
		}
	    
		Binding binding = bindingDict.get("ibg-00");
		Integer sort = binding.getSemSort();
		

//		bough.add(0);
		boughMap.put(1000,0);
		boughMapList.add(boughMap);
	    fillGroup(root, sort, boughMapList);
	    
		System.out.println("end");
	}

	private static void fillData(Integer sort, String value, ArrayList<TreeMap<Integer,Integer>> boughMapList) {
    	
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
            String card = childBinding.getCard();
           
            NodeList nodes = childList.get(childSort);
            
            Integer countNodes = nodes.getLength();
            if (countNodes > 0) {
	            for (Integer i = 0; i < countNodes;i++) {
	            	Node node = nodes.item(i);
		        	if (childID.matches("ibt-[0-9]+.*")) {
		        		try {
		        			String value = node.getTextContent();
			            	if (null==value || value.equals("")) {
//			            		System.out.println("- fillData boughMapList=" + boughMapList.toString() /*+ sort +":" + id + " "*/ + businessTerm + "->" /*+ childSort + ":" + childID + " "*/ + childBusinessTerm + "is Empty");
			            	} else {
//				            	System.out.println("* fillData boughMapList=" + boughMapList.toString() /*+ sort +":" + id + " "*/ + businessTerm + "->" /*+ childSort + ":" + childID + " "*/ + childBusinessTerm + " " + value );            	
				            	fillData(childSort, value, boughMapList);
			            	}
		        		} catch (Exception e) {
		        			System.out.println(e.toString());
		        		}
		            } else {
		            	boolean isKeyPresent = isMultiple(sort);
		                if (isKeyPresent) {
//		                		&& countNodes > 1) {
		                	TreeMap<Integer,Integer> lastBoughMap = boughMapList.get(boughMapList.size() - 1);
		                	Integer lastkey = lastBoughMap.lastKey();
		                	Integer lastvalue = lastBoughMap.get(lastkey);
			            	@SuppressWarnings("unchecked")
							TreeMap<Integer,Integer> boughMap1 = (TreeMap<Integer, Integer>) lastBoughMap.clone();
			            	if (sort!=lastkey) {
		                		boughMap1.put(sort, i);
		                		boughMapList.add(boughMap1);
			            	} else if (countNodes > 1) {
			            		lastvalue +=1;
			            		lastBoughMap.put(lastkey, lastvalue);
			            	}
	                		System.out.println(boughMapList.toString());
		                }
	                	System.out.println("* fillGroup " + " boughMapList=" + boughMapList.toString() /*+ sort +":" + id + " "*/ + businessTerm + "->" /*+ childSort + ":" + childID + " "*/ + childBusinessTerm);
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
	
	private static String getElementValue(Element parent, String id) {
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replace("/Invoice/", "/*/");
		xpath += "/text()";
		NodeList nodes = xpathEvaluate(parent,xpath);
		String nodeValue = "";
		if (nodes.getLength() > 0) {
			nodeValue = nodes.item(0).getNodeValue();
		}
	    return nodeValue;
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
		ArrayList<TreeMap<Integer, Node>> resultsMap = new ArrayList<>();
		Binding parent_binding = (Binding) bindingDict.get(id);
		String parent_xpath = parent_binding.getXPath();
		ArrayList<Integer> children = childMap.get(parent_sort);
		TreeMap<Integer, NodeList> childList = new TreeMap<>();	
		for (Integer sort: children) {
			String childID =  ((Binding) semBindingMap.get(sort)).getID();
			Binding binding = (Binding) bindingDict.get(childID);
			String xpath = binding.getXPath();
			xpath = xpath.replace(parent_xpath, ".");
			if (childID.matches("^ibt-.*")) {
				xpath += "/text()";
			}
			NodeList nodes = xpathEvaluate(e,xpath);
//			if (nodes.getLength() > 0) {
//				System.out.println("- getChildren " + sort + ":" + childID + nodes.item(0).toString() + " " + xpath);
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
		    		if (value.matches("[0-9]+")) {
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
		    		if (value.matches("[0-9]+")) {
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
		    if (l.matches("[0-9]+")) {
		    	level = Integer.parseInt(l);
			    parents[level] = sort;
		    	bindingDict.put(id, binding);
		    	semBindingMap.put(sort, binding);
			    if (level > 0) {
			    	int parent_level = level - 1;
				    Integer parent_sort = parents[parent_level];
//				    System.out.println(parent_sort + " " + sort);
				    ArrayList<Integer> children = null;
				    if (childMap.containsKey(parent_sort)) {
				    	children = childMap.get(parent_sort);
				    } else {
				    	children = new ArrayList<Integer>();
				    }
				    children.add(sort);
//				    System.out.println(children.toString());
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