package space.wuwei.cius.utils;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    // CSV records
//    static TreeMap<Integer/* sort */,Integer/* seq */> boughMap = new TreeMap<>();
//    static ArrayList<TreeMap<Integer, Integer>> boughMapList = new ArrayList<>();
    static TreeMap<Integer, String> rowMap = new TreeMap<>();
    static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();
    // semantic data
	public static ArrayList<String> header = new ArrayList<>();
//	static TreeMap<Integer,String> dataMap = new TreeMap<>();
    public static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();
 	static TreeMap<Integer,String> indexMap = new TreeMap<>();
// 	static ArrayList<Element> parents = new ArrayList<>();
 	static class LevelElement {
 		public Integer seq;
 		public Integer sort;
 		public String xPath;
 		public Element element;
 		// constructor
 		LevelElement(Integer seq, Integer sort, String xPath, Element element) {
 			this.seq = seq;
 			this.sort = sort;
 			this.xPath = xPath;
 			this.element = element;
 		}
 	}
 	static ArrayList<LevelElement> boughElementList = new ArrayList<>();
 	static ArrayList<LevelElement> levelElementList = new ArrayList<>();
   
	public static void main(String[] args) {
		FileHandler.parseBinding();
		FileHandler.csvFileRead(IN_CSV, CHARSET);
		FileHandler.parseSkeleton();
		
		for (String id : header) {
			Binding binding = FileHandler.bindingDict.get(id);
			Integer sort = binding.getSynSort();
			indexMap.put(sort, id);
		}
		
		rowMapList = new TreeMap<>();
		System.out.println("- Csv2Invoice.main tidyData record");
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
		Element boughElement = null;
		LevelElement levelElement = null;
		boughElementList = new ArrayList<>();
		for (Map.Entry<String, TreeMap<Integer, String>> row : rowMapList.entrySet()) {
			String key = row.getKey();
			String[] boughs = key.split(",");
			for (int i = 0; i < boughs.length; i++) {
				String bough = boughs[i];
				String[] data = bough.split(" ");
				for (int j = 0; j < data.length; j++) {
					String ds = data[j];
					String[] d = ds.split("=");
					Integer boughSort = Integer.parseInt(d[0]);
					Integer boughSeq = Integer.parseInt(d[1]);
					Binding boughBinding = FileHandler.synBindingMap.get(boughSort);
					String id = boughBinding.getID();
					String xPath = boughBinding.getXPath();
					String value = "";
					HashMap<String,String> attributes = null;
					if (FileHandler.ROOT_ID.equals(id)) {
						boughElement = FileHandler.root;
						boughElement = FileHandler.root;						
						levelElement = new LevelElement(boughSeq, boughSort, xPath, boughElement);
						if (0==boughElementList.size()) {
							boughElementList.add(levelElement);
						}
						if (0==levelElementList.size()) {
							levelElementList.add(levelElement);
						}
					} else {
						boughElement = appendElementNS(j, boughSeq, boughSort, xPath, value, attributes);
						levelElement = new LevelElement(boughSeq, boughSort, xPath, boughElement);
						if (boughElementList.size() <= j) {
							boughElementList.add(levelElement);
						} else {
							boughElementList.set(j, levelElement);
						}
					}
				}
			}
			// row data
			TreeMap<Integer, String> rowMap = row.getValue();
			for (Map.Entry<Integer, String> entry : rowMap.entrySet()) {
				Integer synSort = entry.getKey();
				String value = entry.getValue();
				Binding binding = FileHandler.synBindingMap.get(synSort);
				 sort = binding.get
				String xPath = binding.getXPath();
//				NodeList results = FileHandler.xpathEvaluate(parent, xPath);
				if (0==results.getLength()) {
					HashMap<String,String> attributes = null;
					
					boughElement = appendElementNS(j, boughSeq, boughSort, xPath, value, attributes);
					levelElement = new LevelElement(boughSeq, boughSort, xPath, boughElement);
					if (boughElementList.size() <= j) {
						boughElementList.add(levelElement);
					} else {
						boughElementList.set(j, levelElement);
					}					
				}
				System.out.println("Pause2");
			}
		}
		System.out.println("Csv2Invoice.main\n" + boughElementList.toString());

		try (FileOutputStream output = new FileOutputStream(OUT_XML)) {
			WriteXmlDom.writeXml(FileHandler.doc, output);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		System.out.println("END");
	}

	private static Element appendElementNS(int i, 
											Integer boughSeq, 
											Integer boughSort, 
											String xPath,
											String value, 
											HashMap<String,String> attributes) {
		System.out.println("Csv2Invoice.appendElementNS " + boughSort + " " + boughSeq + " " + xPath);
		ArrayList<String> paths = splitPath(xPath);
		int depth = paths.size();
		boolean check = false;
		String path = "";
		if (paths.size() > 1) {
			path = paths.get(1);
			check = (1 == depth);
			Element element1 = createElement(FileHandler.root, path, boughSort, boughSeq, value, attributes, 1, depth);
			if (2 == paths.size()) {		
				return element1;
			} else {
				path = paths.get(2);
				Element element2 = createElement(element1, path, boughSort, boughSeq, value, attributes, 2, depth);
				if (3==paths.size()) {
					return element2;
				} else {
					path = paths.get(3);
					Element element3 = createElement(element2, path, boughSort, boughSeq, value, attributes, 3, depth);	
					if (4==paths.size()) {
						return element3;
					} else {
						path = paths.get(4);
						Element element4 = createElement(element3, path, boughSort, boughSeq, value, attributes, 4, depth);
						if (5==paths.size()) {
							return element4;
						} else {
							path = paths.get(5);
							Element element5 = createElement(element4, path, boughSort, boughSeq, value, attributes, 5, depth);
							if (6==paths.size()) {
								return element5;
							} else {
								path = paths.get(6);
								Element element6 = createElement(element5, path, boughSort, boughSeq, value, attributes, 6, depth);
								if (5==paths.size()) {
									return element6;
								} else {
									System.out.println("Csv2Invoice.appendElementNS appendElementNS XPath dpth is too deep 7.");
									return null;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	private static Element createElement(Element parent, 
											String path, 
											Integer boughSort, 
											Integer boughSeq,
											String value, 
											HashMap<String,String> attributes, 
											int level,
											int depth) {
		Element element = null;
		LevelElement levelElement = null;
		String cacValue = "";
		HashMap<String,String> cacAttributes = null;
		
		String selector = extractSelector(path);
		
		if (! ("".equals(selector))) {
			path = path.replace(selector, "");
		}
		
		String[] ename = path.split(":");
		String ns = ename[0];
		String nsURI = FileHandler.nsURIMap.get(ns);
		String qname = ename[1];
		
		boolean newElement = false;
		if (levelElementList.size() > level) {
			levelElement = levelElementList.get(level);
			if (levelElement.seq != boughSeq && level == (depth-1)) {
				newElement = true;
			}
		} else {
			newElement = true;
		}
		if (newElement) {
			if ("cac".equals(ns)) {
				value = cacValue;
				attributes = cacAttributes;
			}
			element = FileHandler.appendElementNS(parent, nsURI, ns, qname, cacValue, cacAttributes);
			levelElement = new LevelElement(boughSeq, boughSort, path, element);
		} else {
			levelElement = levelElementList.get(level);
			element = levelElement.element;
		}
		
		if (level == levelElementList.size()) {
			levelElementList.add(levelElement);
		} else {
			levelElementList.set(level, levelElement);
		}
		return element;
	}
	
	private static String extractSelector(String xPath) {
		int start = xPath.indexOf("[");
		int last = xPath.lastIndexOf("]");
		String selector = "";
		if (start >= 0) {
			selector = xPath.substring(start, last+1);
		}
		return selector;
	}

	private static ArrayList<String> splitPath(String xPath) {
		int start = xPath.indexOf("[");
		int last = xPath.lastIndexOf("]");
		String xPath1 = "";
		if (start >= 0) {
			String selector = xPath.substring(start, last+1);
			selector = selector.replaceAll("/","_");
			xPath1 = xPath.substring(0, start) + selector + xPath.substring(last+1,xPath.length());		
		} else {
			xPath1 = xPath;
		}
		ArrayList<String> paths = new ArrayList<>();
		if ("/".equals(xPath1.substring(0,1))) {
			xPath1 = xPath1.substring(1);
		}
		String[] paths0 = xPath1.split("/");
		for (int i = 0; i < paths0.length; i++) {
			String path = paths0[i];
			path = path.replaceAll("_","/");
			paths.add(path);
		}
		return paths;
	}

}
