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
//import java.util.Map.Entry;
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
    	String IN_XML = "data/xml/Example0.xml";
    	
		parseBinding();
		doc = parseInvoice(IN_XML);
		parseDoc();
		
	    // ibg-23 TAX BREAKDOWN
		Binding ibg23Bindingt = bindingDict.get("ibg-23");
		Integer ibg23Sort = ibg23Bindingt.getSemSort();
		ArrayList<Integer> childSorts = childMap.get(ibg23Sort);
		for (Integer childSort : childSorts) {
			ParsedNode parsedNode = nodeMap.get(childSort);
			NodeList nodes = parsedNode.nodes;
			for (int i = 0; i < nodes.getLength(); i++) {
				String value = "";
				Node node = nodes.item(i);
				System.out.println(i+" "+node.getNodeName()+" "+node.getTextContent());
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
				}
			}
		}
	
	    // ibt-034-1 - Scheme identifier 
		Binding ibt34_1Binding = bindingDict.get("ibt-034-1");
		Integer ibg34_1Sort = ibt34_1Binding.getSemSort();
		ParsedNode parsedNode = nodeMap.get(ibg34_1Sort);
		NodeList nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
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
	    int nodesLength = nodes.getLength();
	    for (int i = 0; i < nodesLength; i++) {	      
	        Element node = (Element) nodes.item(i);
	        TreeMap<Integer, NodeList> childrenMap = getChildren(node, "ibg-23");
	        // Iterating HashMap through for loop
	        for (Integer sort : childrenMap.keySet()) {
	        	Binding binding = semBindingMap.get(sort);
	        	String id = binding.getID();
	        	String BT = binding.getBT();
	        	NodeList children = childrenMap.get(sort);
	        	Node child = children.item(0);
	        	if (null!=child) {
	        		System.out.println(id+" "+BT+" "+child.getNodeValue());
	        	} else {
	        		System.out.println(id+" "+BT+" N/A");
	        	}
	        }
	    }

	    // ibt-034-1 - Scheme identifier 
	    NodeList sellerElectronicAddressSchemeIdentifierAtts = getElements(FileHandler.root, "ibt-034-1");
	    Node sellerElectronicAddressSchemeIdentifierAtt = sellerElectronicAddressSchemeIdentifierAtts.item(0);
	    String sellerElectronicAddressSchemeIdentifier = sellerElectronicAddressSchemeIdentifierAtt.getNodeValue();
	    System.out.println("ibt-034-1 "+sellerElectronicAddressSchemeIdentifier);
	    
		// cbc:DocumentCurrencyCode
		NodeList documentCurrencyCodeEls = getElements(root, "ibt-005");//"/*/cbc:DocumentCurrencyCode/text()");
		Node documentCurrencyCodeEl = documentCurrencyCodeEls.item(0);
		String documentCurrencyCode = documentCurrencyCodeEl.getTextContent();
	    System.out.println("ibt-005 "+documentCurrencyCode);
	    
		// ibt-110 Invoice total TAX amount
	    NodeList invoiceTotalTaxAmountEl = getElements(FileHandler.root, "ibt-110");
	    String invoiceTotalTaxAmount = invoiceTotalTaxAmountEl.item(0).getTextContent();
	    System.out.println("ibt-110 "+invoiceTotalTaxAmount);
	    
    }
    
	public static void parseBinding() {
		try (BufferedReader fileReader = new BufferedReader(new FileReader(JP_PINT_CSV)))
		{
		  String line = "";
		  line = fileReader.readLine();
		  String[] headers = line.split(",");
		  Integer[] parents = new Integer[10];
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
		    String BusinessTerm = binding.getBT();
		    Integer semSort = binding.getSemSort();
		    Integer synSort = binding.getSynSort();
		    String l = binding.getLevel();
		    
		    System.out.println(id+" "+l+" "+BusinessTerm);
		    
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
				    for (Integer child_semSort: children) {
				    	parentMap.put(child_semSort,parent_semSort);
				    }
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
	
	public static void parseDoc() {
		for (Integer semSort: semBindingMap.keySet()) {
			Binding binding = semBindingMap.get(semSort);
			String xPath = binding.getXPath();
			NodeList nodes = getXPath(root, xPath);
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
		NodeList nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String value = node.getTextContent();
			nodeValueMap.put(i, value);
			System.out.println(i+" "+node.getNodeName()+" "+value);
		}
		return nodeValueMap;
	}
	
	public static NodeList getElements(Element parent, String id) {
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replaceAll("/Invoice/", "/*/");
		xpath = xpath.replaceAll("/ubl:Invoice/", "/*/");
		if (null==parent) {
			System.out.println("- FileHaldler.getElements parent null");
			return null;
		}
		xpath = xpath.replace("/Invoice/", "/*/");
		xpath = xpath.replace("/ubl:Invoice/", "/*/");
		if (id.matches("^ibt-[0-9]+$")) {
			xpath += "/text()";
		}
		
		NodeList nodes = xpathEvaluate(parent, xpath);
		
		return nodes;
	}
	
	public static NodeList getXPath(Element parent, String xpath) {
		xpath = xpath.replaceAll("/Invoice/", "/*/");
		if (null==parent) {
			System.out.println("- FileHaldler.getXPath parent null");
			return null;
		}
		NodeList nodes = xpathEvaluate(parent, xpath);
		return nodes;
	}
	
	public static Element appendElementNS (
			Element parent,
			String nsURI, 
			String prefix, 
			String qname, 
			String value, 
			HashMap<String, String> attrMap ) {
		System.out.println("- FileHaldler.appendElementNS "+prefix+":"+qname);
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
	
	public static TreeMap<Integer, NodeList> getChildren(Node e, String id) {
		TreeMap<Integer, NodeList> childList = new TreeMap<>();	
		Integer semSort = ((Binding) bindingDict.get(id)).getSemSort();
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
//		String parent_xpath;
//		if (semSort > 1000) {
//			Integer parent_semSort = parentMap.get(semSort);
//			Binding parent_binding = semBindingMap.get(parent_semSort);
//			parent_xpath = parent_binding.getXPath();
//			if (! parent_xpath.equals("/Invoice") && ! parent_xpath.equals("/ubl:Invoice")) {
//				xpath = xpath.replace(parent_xpath, ".");
//			}
//		}
		
		ArrayList<Integer> children = childMap.get(semSort);
		for (Integer sort: children) {
			Binding child_binding = (Binding) semBindingMap.get(sort);
			String childID =  child_binding.getID();
			String child_datatype = child_binding.getDatatype();
			String child_xpath = child_binding.getXPath();
			if (! xpath.equals("/Invoice") && ! xpath.equals("/ubl:Invoice")) {
				child_xpath = child_xpath.replace(xpath, ".");
			}
			child_xpath = child_xpath.replace("/Invoice/", "/*/");
			child_xpath = child_xpath.replace("/ubl:Invoice/", "/*/");
			
			if (childID.matches("^ibt-.*$") && ! "String".equals(child_datatype)) {
				child_xpath += "/text()";
			}
			
			NodeList nodes = xpathEvaluate(e, child_xpath);
			
			if (nodes.getLength() > 0) {
				childList.put(sort, nodes);
			}
		}
		return childList;
	}

	public static NodeList xpathEvaluate(Node node, String xPath) {
		XPathExpression expr = null;
		Object result;
		try {
			xPath = xPath.replace("/Invoice/", "/*/");
			xPath = xPath.replace("/ubl:Invoice/", "/*/");
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
		System.out.println("FileHandler.csvFileRead " + filename + " " + charset);
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
