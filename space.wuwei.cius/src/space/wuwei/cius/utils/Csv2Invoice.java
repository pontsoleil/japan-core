package space.wuwei.cius.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
//import java.io.StringReader;
import java.util.ArrayList;
//import java.util.Dictionary;
import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Hashtable;
import java.util.Map;
//import java.util.Arrays;

//import javax.xml.XMLConstants;
//import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

public class Csv2Invoice {
	
	static Map<String,Object> bindingDict = new HashMap<>();
	static ArrayList<Object> bindingList = new ArrayList<>();
	static Document doc = null;
	static XPath xpath = null;
	
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skeleton.xml";
	
	static String nsURIcac = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2";
	static String nsURIcbc = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2";
	
	public static void main(String[] args) {
		NodeList nodes = null;
		NodeList children = null;
		Node node = null;
		Node child = null;
		int nodesLength = 0;
		
		parseBinding(JP_PINT_CSV);
		parseSkeleton(JP_PINT_XML_SKELTON);
		
		Node root = doc.getFirstChild();

		// cbc:ID
		appendElementNS((Element)root, nsURIcbc, "cbc:ID", "abc-123");
		
		// cac:taxTotal
		Element taxTotal = appendElementNS((Element)root, nsURIcac, "cac:taxTotal", "");
		Element taxAmount = appendElementNS(taxTotal, nsURIcbc, "cbc:TaxAmount", "3000");
		taxAmount.setAttribute("currencyID","JPY");
		Element taxSubTotal = appendElementNS(taxTotal, nsURIcac, "cac:TaxSubTotal", "");
		Element taxableAmount = appendElementNS(taxSubTotal, nsURIcbc, "cbc:TaxableAmount", "30000");
		taxableAmount.setAttribute("currencyID","JPY");
		Element taxAmount_Taxcategory = appendElementNS(taxSubTotal, nsURIcbc, "cbc:TaxAmount", "3000");		
		taxAmount_Taxcategory.setAttribute("currencyID","JPY");
		
		systemOutXML(doc);
		
//		nodes = xpathEvaluate(doc,"/*/cbc:DocumentCurrencyCode/text()");
//		
//	    String documentCurrencyCode = nodes.item(0).getNodeValue();
//	    System.out.println(documentCurrencyCode);
	    
		// cac:TaxSubtotal
		
	    nodes = xpathEvaluate(doc,"/*/cac:TaxTotal[cbc:TaxAmount/@currencyID=/*/cbc:DocumentCurrencyCode/text()]/cac:TaxSubtotal");
	    nodesLength = nodes.getLength();
	    for (int i = 0; i < nodesLength; i++) {
	      node = nodes.item(i);
		  children = xpathEvaluate(node,"./cbc:TaxableAmount/text()");
		  child = children.item(0);
		  System.out.println(child.getNodeValue());	  
		  children = xpathEvaluate(node,"./cbc:TaxAmount/text()");
		  child = children.item(0);
		  System.out.println(child.getNodeValue());
		  children = xpathEvaluate(node,"./cac:TaxCategory/cbc:ID/text()");
		  child = children.item(0);
		  System.out.println(child.getNodeValue());	
		  children = xpathEvaluate(node,"./cac:TaxCategory/cbc:Percent/text()");
		  child = children.item(0);
		  System.out.println(child.getNodeValue());
	    }
		
//		for (int i=0; i < bindingList.size(); i++) {
//			Object b = bindingList.get(i);
////			String id = b.get("id");
//			System.out.println(b.toString());	
//		}
		System.out.println("end");
	}

	private static Element appendElementNS(Element root, String nsURI, String qname, String content) {
		Element e = doc.createElementNS(nsURI, qname);
		if (content!="") {
			e.setTextContent(content);
		}
		root.appendChild(e);
		return e;
	}

	private static void systemOutXML(Document doc)  {
		try {
			// Use a Transformer for output
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer;
		    transformer = tFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (TransformerException | TransformerFactoryConfigurationError e1) {
			e1.printStackTrace();
		}
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
		try(BufferedReader fileReader = new BufferedReader(new FileReader(fname)))
		{	
		  String line = "";
		  line = fileReader.readLine();
		  String[] headers = line.split(",");
		  //Read the file line by line
		  while ((line = fileReader.readLine()) != null) {
		    //Get all tokens available in line
		    String[] tokens = line.split(",");
//		    //Verify tokens
//		    System.out.println(Arrays.toString(tokens));
			Map<String,String> binding = new HashMap<String, String>();
		    for (int i = 0; i < headers.length; i++) {
		    	binding.put(headers[i], tokens[i]);
		    }
		    String id = binding.get("id");
		    if (id!="") {
		    	bindingDict.put(id, binding);
		    	bindingList.add(binding);
		    } 
		  }
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
	}

	private static void parseSkeleton(String skeleton) {
		try {
		    //Build DOM
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true); // never forget this!
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    //Parse XML file
		    doc = builder.parse(new FileInputStream(new File(skeleton)));
		    //Create XPath
		    XPathFactory xpathfactory = XPathFactory.newInstance();
		    xpath = xpathfactory.newXPath();
		    xpath.setNamespaceContext(new NamespaceResolver(doc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
