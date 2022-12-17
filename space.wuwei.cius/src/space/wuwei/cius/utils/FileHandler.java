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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.Set;
import java.util.List;
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
//import org.w3c.dom.NodeList;
//import org.w3c.dom.List<Node>;

public class FileHandler {
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skeleton.xml";
	
	public static Document doc = null;
	public static XPath xpath = null;
	public static Element root = null;
	public static String ROOT_ID = "ibg-00";
	public static HashMap<String, String> nsURIMap = null;
	
	public static ArrayList<String> header = new ArrayList<>();
    public static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();
    
	public static Map<
		String/* id */,
		Binding> bindingDict = new HashMap<>();
	public static TreeMap<
		Integer/* semSort */, 
		Binding> semBindingMap = new TreeMap<>();
	static TreeMap<
		Integer/* synSort */, 
		Binding> synBindingMap = new TreeMap<>();
	public static TreeMap<
		Integer/* parent semSort */, 
		ArrayList<Integer/* child semSort */>> childMap = new TreeMap<>();
	public static TreeMap<
		Integer/* child semSort */, 
		Integer/* parent semSort */> parentMap = new TreeMap<>();
	public static TreeMap<
    	Integer/* semSort */, 
    	String/* id */> multipleMap = new TreeMap<>();
    public static TreeMap<
    	Integer/* semSort */, 
    	ParsedNode> nodeMap = new TreeMap<>();

    public static void main(String[] args) {
    	String IN_XML = "data/xml/Example1.xml";
    	
		parseBinding();
		doc = parseInvoice(IN_XML);
		parseDoc();
		
		// ibt-024 Specification identifier
		Binding ibt24Binding = bindingDict.get("ibt-024");
		Integer ibt24Sort = ibt24Binding.getSemSort();
		ParsedNode parsedNode = nodeMap.get(ibt24Sort);
		List<Node> nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			System.out.println("ibt-024 Specification identifier "+i+" "+node.getNodeName()+" "+node.getTextContent());
		}		
		
	    // ibg-23 TAX BREAKDOWN
		Binding ibg23Bindingt = bindingDict.get("ibg-23");
		Integer ibg23Sort = ibg23Bindingt.getSemSort();
		ArrayList<Integer> childSorts = childMap.get(ibg23Sort);
		for (Integer childSort : childSorts) {
			parsedNode = nodeMap.get(childSort);
			nodes = parsedNode.nodes;
			for (int i = 0; i < nodes.size(); i++) {
				String value = "";
				Node node = nodes.get(i);
				System.out.print(i+" "+node.getNodeName()+" "+node.getTextContent());
				if (node.hasAttributes()) {
					NamedNodeMap attributes = node.getAttributes();
					int attrLength = attributes.getLength();
					for (int j = 0; j < attrLength; j++) {
						Node attribute = attributes.item(j);
				        String name = attribute.getNodeName();
				        if ("currencyID".equals(name)) {
				           value = attribute.getNodeValue();
				           System.out.println(" "+value);
				        }
					} 
				} else {
					System.out.println("");
				}
			}
		}
	
	    // ibt-034-1 - Scheme identifier 
		Binding ibt34_1Binding = bindingDict.get("ibt-034-1");
		Integer ibt34_1Sort = ibt34_1Binding.getSemSort();
		parsedNode = nodeMap.get(ibt34_1Sort);
		nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			System.out.println(i+" "+node.getNodeName()+" "+node.getTextContent());
		}
		
		// ibt-160 Item attribute name
		TreeMap<Integer, String> nodeValues = getNodeValues("ibt-160");
		for (int i = 0; i <nodeValues.size(); i++) {
			String value = nodeValues.get(i);
			System.out.println("ibt-160"+i+" "+value);
		}
		
		// ibg-23 TAX BREAKDOWN <cac:TaxSubtotal>
		nodes = getElements(root, "ibg-23");
	    int nodesLength = nodes.size();
	    for (int i = 0; i < nodesLength; i++) {	      
	        Element node = (Element) nodes.get(i);
	        
	        TreeMap<Integer, List<Node>> childrenMap = getChildren(node, "ibg-23");
	        
	        // Iterating HashMap through for loop
	        for (Integer sort : childrenMap.keySet()) {
	        	Binding binding = semBindingMap.get(sort);
	        	String id = binding.getID();
	        	String BT = binding.getBT();
	        	List<Node> children = childrenMap.get(sort);
	        	Node child = children.get(0);
	        	if (null!=child) {
	        		System.out.println(id+" "+BT+" "+child.getNodeValue());
	        	} else {
	        		System.out.println(id+" "+BT+" N/A");
	        	}
	        }
	    }

	    // ibt-034-1 - Scheme identifier 
	    List<Node> sellerEASAttrs = getElements(FileHandler.root, "ibt-034-1");
	    if (sellerEASAttrs.size() > 0) {
	    	Node sellerEASAttr = sellerEASAttrs.get(0);
	    	String sellerElectronicAddressSchemeIdentifier = sellerEASAttr.getNodeValue();
	    	System.out.println("ibt-034-1 "+sellerElectronicAddressSchemeIdentifier);
	    }
	    
		// cbc:DocumentCurrencyCode
		List<Node> documentCurrencyCodeEls = getElements(root, "ibt-005");//"/*/cbc:DocumentCurrencyCode/text()");
		Node documentCurrencyCodeEl = documentCurrencyCodeEls.get(0);
		String documentCurrencyCode = documentCurrencyCodeEl.getTextContent();
	    System.out.println("ibt-005 "+documentCurrencyCode);
	    
		// ibt-110 Invoice total TAX amount
	    List<Node> invoiceTotalTaxAmountEl = getElements(FileHandler.root, "ibt-110");
	    String invoiceTotalTaxAmount = invoiceTotalTaxAmountEl.get(0).getTextContent();
	    System.out.println("ibt-110 "+invoiceTotalTaxAmount);
	    
    }
    
	public static void parseBinding() {
		System.out.println("-- parseBinding");
		try (BufferedReader fileReader = new BufferedReader(new FileReader(JP_PINT_CSV)))
		{
			String line = "";
			line = fileReader.readLine();
			String[] headers = line.split(",");
			Integer[] parents = new Integer[10];
			Binding[] bindingParent = new Binding[10];
			//Read the file line by line
//			System.out.println(headers.toString());
			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(",");		
				// ﻿s﻿semSort,id,card,level,businessTerm,desc,dataType,businessTerm_ja,desc_ja,synSort,element,synDatatype,xPath,occur
				Binding binding = new Binding(0, "", "", "", "", "", 0, "", "");
				for (int i = 0; i < headers.length; i++) {
					String key = headers[i];
					if (0==i) {
						key	= key.replace("\uFEFF", "");
					}
					String value = tokens[i];
					Integer order = -1;
					switch (key) {
					case "semSort":
						order = Integer.parseInt(value);
						binding.setSemSort(order);
						break;
					case "id":
						binding.setID(value);
						break;
					case "card":
						binding.setCard(value);
						break;
					case "level":
						binding.setLevel(value);
						break;
					case "businessTerm":
						binding.setBT(value);
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
				Integer semSort = binding.getSemSort();
				Integer synSort = binding.getSynSort();
//				System.out.println("- FileHandler.parseBinding "+binding.getID()+" "+binding.getXPath());
				bindingDict.put(id, binding);
				semBindingMap.put(semSort, binding);
				synBindingMap.put(synSort, binding);
			}
			
			for (Entry<Integer, Binding> entry : semBindingMap.entrySet()) {
				Integer semSort = entry.getKey();
				Binding binding = entry.getValue();
				String id = binding.getID();
				String l = binding.getLevel();
				Integer level = Integer.parseInt(l);
				parents[level] = semSort;
				if (level > 0) {
					int parent_level = level - 1;
					Integer parent_semSort = parents[parent_level];
					Binding parent_binding = semBindingMap.get(parent_semSort);
					String parentID = parent_binding.getID();
					System.out.println("- FileHandler.parseBinding " + parentID + "->" + id);
					ArrayList<Integer> children = null;
					if (childMap.containsKey(parent_semSort)) {
						children = childMap.get(parent_semSort);
					} else {
						children = new ArrayList<Integer>();
					}
					children.add(semSort);
					childMap.put(parent_semSort, children);
					for (Integer child_semSort: children) {
						parentMap.put(child_semSort, parent_semSort);
					}
				}
			}
			  
			for (Entry<Integer, Binding> entry : semBindingMap.entrySet()) {
				Binding binding = entry.getValue();
				String id = binding.getID();
				String xPath = binding.getXPath();
				String strippedXPath = stripSelector(xPath);
				int idx = strippedXPath.lastIndexOf("/");
				String additionalXpath = "";
				if (idx >= 0) {
					additionalXpath = strippedXPath.substring(0, idx);
				}
				String l = binding.getLevel();
				Integer level = Integer.parseInt(l);
				bindingParent[level] = binding;
				if (level > 0) {
					int parent_level = level - 1;
					Binding parentBinding = bindingParent[parent_level];
					String parentID = parentBinding.getID();
					String parentXPath = parentBinding.getXPath();
					String strippedParentXPath = stripSelector(parentXPath);
					if (additionalXpath.length() > 0 &&
							strippedParentXPath.indexOf(additionalXpath) < 0 &&
							additionalXpath.indexOf(strippedParentXPath) < 0) {
						additionalXpath = resumeSelector(additionalXpath, xPath);
						parentBinding.addAdditionalXPath(additionalXpath);
						System.out.println(id+" "+xPath+" "+parentID+" "+parentXPath);
						System.out.println("    ADDED parent XPath: "+parentXPath+" additional Xpath: "+additionalXpath);
					}
				}
			}
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
	}
	
	private static int countChar(char ch, String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
		    if (str.charAt(i) == ch) {
		        count++;
		    }
		}
		return count;
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
	
	public static void parseDoc() {
		for (Integer semSort: semBindingMap.keySet()) {
			Binding binding = semBindingMap.get(semSort);
			String xPath = binding.getXPath();
			List<Node> nodes = getXPath(root, xPath);
			ParsedNode parsedNode = new ParsedNode(binding, nodes);
			nodeMap.put(semSort, parsedNode);
		}		
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
	
	public static TreeMap<Integer, String> getNodeValues(String id) {
		TreeMap<Integer, String> nodeValueMap = new TreeMap<>();
		Binding binding = bindingDict.get(id);
		Integer sort = binding.getSemSort();
		ParsedNode parsedNode = nodeMap.get(sort);
		List<Node> nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			String value = node.getTextContent().trim();
			nodeValueMap.put(i, value);
//			System.out.println(i+" "+node.getNodeName()+" "+value);
		}
		return nodeValueMap;
	}
	
	public static List<Node> getElements(Element parent, String id) {
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replaceAll("/Invoice", "/*");
		xpath = xpath.replaceAll("/ubl:Invoice", "/*");
		if (null==parent) {
			System.out.println("- FileHaldler.getElements parent null");
			return null;
		}
		if (id.matches("^ibt-[0-9]+$")) {
			xpath += "/text()";
		}
		List<Node> nodes = xpathEvaluate(parent, xpath);
		return nodes;
	}
	
	public static List<Node> getXPath(Element parent, String xpath) {
//		System.out.println("-- getXPath "+xpath);
		xpath = xpath.replaceAll("/Invoice/", "/*/");
		if (null==parent) {
			System.out.println("- FileHaldler.getXPath parent null");
			return null;
		}
		List<Node> nodes = xpathEvaluate(parent, xpath);
		return nodes;
	}

	public static TreeMap<Integer, List<Node>> getChildren(Node e, String id) {
		TreeMap<Integer, List<Node>> childList = new TreeMap<>();	
		Binding binding = (Binding) bindingDict.get(id);
		Integer semSort = binding.getSemSort();
		String xpath = binding.getXPath();
		System.out.println("-- getChildren "+id+"("+semSort+")"+xpath);

		ArrayList<Integer> children = childMap.get(semSort);
		
		if (null!=children) {
			for (Integer sort: children) {
				Binding child_binding = (Binding) semBindingMap.get(sort);
				String childID =  child_binding.getID();
				String child_datatype = child_binding.getDatatype();
				String child_xpath = child_binding.getXPath();
				Set<String> additionalXPath = child_binding.getAdditionalXPath();
				
				child_xpath = checkChildXPath(xpath, childID, child_datatype, child_xpath);
				
				List<Node> nodes = xpathEvaluate(e, child_xpath);
				
				if (null!=additionalXPath) {
					for(Iterator<String> iterator = additionalXPath.iterator(); iterator.hasNext(); ) {
						String additional_xpath = iterator.next();
						List<Node> additional_nodes = xpathEvaluate(e, additional_xpath);
						nodes.addAll(additional_nodes);
					}
				}
				
				if (nodes.size() > 0) {
					childList.put(sort, nodes);
//					System.out.print(child_xpath+" "+nodes.get(0));
				}
			}
		}
		return childList;
	}

	private static String checkChildXPath(String xpath, String childID, String child_datatype, String child_xpath) {
		if (! xpath.equals("/Invoice") && ! xpath.equals("/ubl:Invoice")) {
			child_xpath = child_xpath.replace(xpath, ".");
		}
		child_xpath = child_xpath.replace("/Invoice/", "/*/");
		child_xpath = child_xpath.replace("/ubl:Invoice/", "/*/");
		if (childID.matches("^ibt-.*$") && ! "String".equals(child_datatype)) {
			child_xpath += "/text()";
		}
		return child_xpath;
	}

	public static List<Node> xpathEvaluate(Node base, String xPath) {
		XPathExpression expr = null;
		NodeList result;
		List<Node> nodeList = new ArrayList<>();
		try {
			xPath = xPath.replace("/Invoice", "/*");
			xPath = xPath.replace("/ubl:Invoice", "/*");
			expr = xpath.compile(xPath);
			result = (NodeList) expr.evaluate(base, XPathConstants.NODESET);
//			if (result.getLength() > 0) {
//				System.out.print("-- xpathEvaluate "+xPath+" ");
//				System.out.println(result.getLength()+" "+result.item(0).toString());
//			}
			nodeList = asList(result);	
			// https://stackoverflow.com/questions/50509663/convert-nodelist-to-list-in-java
			final int len = nodeList.size();
		    List<Node> nodes = new ArrayList<>();
		    for (int i = 0; i < len; i++) {
		      final Node node = nodeList.get(i);
		      if (node.getNodeType() == Node.ELEMENT_NODE ||
		    		  node.getNodeType() == Node.TEXT_NODE ||
		    		  node.getNodeType() == Node.ATTRIBUTE_NODE) {
//		    	  System.out.println(node.toString());
		    	  nodes.add((Node) node);
		      }
		      // Ignore other node types.
		    }
		    return nodes;	
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// https://stackoverflow.com/questions/19589231/can-i-iterate-through-a-nodelist-using-for-each-in-java
	public static List<Node> asList(NodeList n) {	    
		return n.getLength()==0
				? Collections.<Node>emptyList()
				: new NodeListWrapper(n);
	}
	static final class NodeListWrapper extends AbstractList<Node>	  
	implements RandomAccess {	    
		private final NodeList list;
	    NodeListWrapper(NodeList l) {
	      list=l;
	    }
	    public Node get(int index) {
	      return list.item(index);
	    }
	    public int size() {
	      return list.getLength();
	    }
	}
	 
	public static String extractSelector(String xPath) {
		int start = xPath.indexOf("[");
		int last = xPath.lastIndexOf("]");
		String selector = "";
		if (start >= 0) {
			selector = xPath.substring(start, last+1);
		}
		return selector;
	}
	
	public static String stripSelector(String path) {
		int start = path.indexOf("[");
		int last = path.lastIndexOf("]");
		if (start >= 0) {
			path = path.substring(0, start) + path.substring(last+1,path.length());	
		}
		return path;
	}
	
	public static String resumeSelector(String strippedPath, String path) {
		String resumedPath = strippedPath;
		if (strippedPath.indexOf("cac:AllowanceCharge") >= 0) {
			int start = path.indexOf("[");
			int last = path.lastIndexOf("]");
			if (start >= 0) {
				String selector = path.substring(start, last+1);
				String header = strippedPath.substring(0, start);
				String trailer = strippedPath.substring(start+1, strippedPath.length());
				resumedPath = header + selector + "/" + trailer;
			}
		}
		return resumedPath;	
	}

	public static Element appendElementNS (
			Element parent,
			String nsURI, 
			String prefix, 
			String qname, 
			String value, 
			HashMap<String, String> attrMap ) {
//		System.out.println("- FileHaldler.appendElementNS "+prefix+":"+qname);
		try {
			if ("@".equals(qname.substring(0,1))) {
				String attrName = qname.substring(1, qname.length());
				Attr attribute = doc.createAttribute(attrName);
				attribute.setValue(value);
				parent.setAttributeNode(attribute);
				return null;
			} else {
				Element element = doc.createElementNS(nsURI, qname);
				element.setPrefix(prefix); // Set the desired namespace and prefix
				if (value!="") {
					element.setTextContent(value);					
				}
				if (null!=attrMap) {
					for (Map.Entry<String, String> entry : attrMap.entrySet()) {
			           String name = entry.getKey();
			           String attr = entry.getValue();
			           Attr attribute = doc.createAttribute(name);
			           attribute.setValue(attr);
			           element.setAttributeNode(attribute);
			        }
				}
				parent.appendChild(element);				
				return element;
			}
		} catch (Exception e) {
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
	        // pretty print XML
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");		    
			transformer.transform(source, result);
		} catch (FileNotFoundException | TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	public static void csvFileWrite(String filename, String charset) {
		System.out.println("- FileHandler.csvFileWrite " + filename + " " + charset);
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			Charset cs = Charset.forName(charset);
			OutputStreamWriter osw = new OutputStreamWriter(fo, cs);
			BufferedWriter bw = new BufferedWriter(osw);
			// header
			String headerLine = String.join(",", header);
			bw.write(headerLine);
            bw.write("\n");
            // data
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

	public static void csvFileRead(String filename, String charset) {
		System.out.println("-- FileHandler.csvFileRead " + filename + " " + charset);
		header = new ArrayList<String>();
		tidyData = new ArrayList<ArrayList<String>>();
		try {
			FileInputStream fi = new FileInputStream(filename);
			Charset cs = Charset.forName(charset);
			InputStreamReader isw = new InputStreamReader(fi, cs);
			BufferedReader br = new BufferedReader(isw);
			// header
			String headerLine = br.readLine();
			String[] fields = headerLine.split(",");
			for (String field : fields) {
				header.add(field);
			}
			// data
			String line;		
			while ((line = br.readLine()) != null) {
				fields = line.split(",");
				ArrayList<String> record = new ArrayList<String>();
				for (String field : fields) {
					record.add(field);
				}
				tidyData.add(record);
			}
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}