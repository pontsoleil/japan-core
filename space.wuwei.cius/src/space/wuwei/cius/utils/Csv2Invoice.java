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
import java.util.TreeMap;

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
	static String OUT_XML = "data/xml/Example1out.xml";
	static String IN_CSV = "data/csv/Example1.csv";
	static String CHARSET = "UTF-8";
    // CSV table
	public static ArrayList<String> header = new ArrayList<>();
	static TreeMap<Integer,String> indexMap = new TreeMap<>();
	static TreeMap<Integer,String> dataMap = new TreeMap<>();
    public static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();
    // prepare CSV records
    static TreeMap<Integer/* sort */,Integer/* seq */> boughMap = new TreeMap<>();
    static ArrayList<TreeMap<Integer, Integer>> boughMapList = new ArrayList<>();
    static TreeMap<Integer, String> rowMap = new TreeMap<>();
    static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();

//	static String nsURIcac = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2";
//	static String nsURIcbc = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2";
	
	public static void main(String[] args) {
//		NodeList nodes = null;
//		NodeList children = null;
//		Node node = null;
//		Node child = null;
//		int nodesLength = 0;
		
		FileHandler.parseBinding();
		FileHandler.csvFileRead(IN_CSV, CHARSET);
		FileHandler.parseSkeleton();
		
		for (String id : header) {
			Binding binding = FileHandler.bindingDict.get(id);
			Integer sort = binding.getSynSort();
			indexMap.put(sort, id);
		}
		rowMapList = new TreeMap<>();
		for (ArrayList<String> record: tidyData) {
			System.out.println(record.toString());
			rowMap = new TreeMap<>();
			String key = "";
			for (int i = 0; i < record.size(); i++) {
				String field = record.get(i);
				if (""!=field) {
					String id = header.get(i);
					Binding binding = FileHandler.bindingDict.get(id);
					Integer sort = binding.getSynSort();
					if (id.matches("^ibg-[0-9]+$")) {
						key += sort+"="+field+" ";
					} else {
						rowMap.put(sort, field);
					}
				}
			}
			key = key.trim();
			rowMapList.put(key, rowMap);
		}
		System.out.println(rowMapList.toString());
		
		Element parent = null;
		for (Map.Entry<String, TreeMap<Integer, String>> row : rowMapList.entrySet()) {
			String key = row.getKey();
			String[] boughs = key.split(",");
			for (String bough : boughs) {
				String[] data = bough.split(" ");
				for (String ds : data) {
					String[] d = ds.split("=");
					Integer boughSort = Integer.parseInt(d[0]);
					Integer boughSeq = Integer.parseInt(d[1]);
					Binding boughBinding = FileHandler.synBindingMap.get(boughSort);
					String id = boughBinding.getID();
					String xPath = boughBinding.getXPath();
					if (FileHandler.ROOT_ID.equals(id)) {
						parent = FileHandler.root;
					} else {
						NodeList boughNodes = FileHandler.getElements(FileHandler.root, id);
						parent = (Element) boughNodes.item(boughSeq);
					}
					if (null==parent) {
						String value = "";
						HashMap<String,String> attributes = null;
						appendElementNS(FileHandler.root, value, xPath, attributes);
//						System.out.println("Pause");
					}
				}
			}
			
			TreeMap<Integer, String> rowMap = row.getValue();
			for (Map.Entry<Integer, String> entry : rowMap.entrySet()) {
				Integer synSort = entry.getKey();
				String value = entry.getValue();
				Binding binding = FileHandler.synBindingMap.get(synSort);
				String xPath = binding.getXPath();
				NodeList results = FileHandler.xpathEvaluate(parent, xPath);
				if (0==results.getLength()) {
					HashMap<String,String> attributes = null;
					appendElementNS(parent, value, xPath, attributes);
				}
				System.out.println("Pause2");
			}
		}
		
		System.out.println("END");
	}

	private static void appendElementNS(Element parent, String value, String xPath, HashMap<String,String> attributes) {
		System.out.println(xPath);
		int start = xPath.indexOf("[");
		int last = xPath.lastIndexOf("]");
		String selector = "";
		String xPath1 = "";
		if (start>=0) {
			selector = xPath.substring(start, last+1);
			xPath1 = xPath.substring(0, start) + xPath.substring(last+1,xPath.length());
		} else {
			xPath1 = xPath;
		}
		System.out.println(xPath1.substring(1));
		String[] paths = xPath1.substring(1).split("/");
		if (paths.length > 1) {
			Element parent1 = parent;
			String[] ename1 = paths[1].split(":");
			String ns1 = ename1[0];
			String nsURI1 = FileHandler.nsURIMap.get(ns1);
			String qname1 = ename1[1];
			if (2==paths.length) {
				FileHandler.appendElementNS(parent1, nsURI1, qname1, value, attributes);
			} else {
				Element parent2 = FileHandler.appendElementNS(parent1, nsURI1, qname1, null, null);
				String[] ename2 = paths[2].split(":");
				String ns2 = ename2[0];
				String nsURI2 = FileHandler.nsURIMap.get(ns2);
				String qname2 = ename2[1];
				if (3==paths.length) {
					FileHandler.appendElementNS(parent2, nsURI2, qname2, value, attributes);
				} else {
					Element parent3 = FileHandler.appendElementNS(parent2, nsURI2, qname2, null, null);
					String[] ename3 = paths[3].split(":");
					String ns3 = ename3[0];
					String nsURI3 = FileHandler.nsURIMap.get(ns3);
					String qname3 = ename3[1];
					if (4==paths.length) {
						FileHandler.appendElementNS(parent3, nsURI3, qname3, value, attributes);
					} else {
						Element parent4 = FileHandler.appendElementNS(parent3, nsURI3, qname3, null, null);
						String[] ename4 = paths[4].split(":");
						String ns4 = ename4[0];
						String nsURI4 = FileHandler.nsURIMap.get(ns4);
						String qname4 = ename4[1];
						if (5==paths.length) {
							FileHandler.appendElementNS(parent4, nsURI4, qname4, value, attributes);
						} else {
							Element parent5 = FileHandler.appendElementNS(parent4, nsURI4, qname4, null, null);
							String[] ename5 = paths[5].split(":");
							String ns5 = ename5[0];
							String nsURI5 = FileHandler.nsURIMap.get(ns5);
							String qname5 = ename5[1];
							if (6==paths.length) {
								FileHandler.appendElementNS(parent5, nsURI5, qname5, value, attributes);
							} else {
								Element parent6 = FileHandler.appendElementNS(parent5, nsURI5, qname5, null, null);
								String[] ename6 = paths[6].split(":");
								String ns6 = ename6[0];
								String nsURI6 = FileHandler.nsURIMap.get(ns6);
								String qname6 = ename6[1];
								if (5==paths.length) {
									FileHandler.appendElementNS(parent6, nsURI6, qname6, value, attributes);
								} else {
									System.out.println("appendElementNS XPath dpth is too deep 7.");
								}
							}
						}
					}
				}
			}
		}
	}


}
