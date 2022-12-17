package space.wuwei.cius.utils;
//TODO define selector related elements

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.transform.TransformerException;

//import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Csv2Invoice {
	static String OUT_XML = "data/xml/Example1_out.xml";
	static String IN_CSV = "data/csv/Example1.csv";
	static String CHARSET = "UTF-8";
	static String DOCUMENT_CURRENCY_CODE_ID = "ibt-005";
	static String TAX_CURRENCY_CODE_ID = "ibt-006";
	
    // CSV records
    static TreeMap<Integer, String> rowMap = new TreeMap<>();
    static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();
    // semantic data
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
 	static TreeMap<String, LevelElement> boughElementMap = new TreeMap<>();
 	static ArrayList<LevelElement> levelElementList = new ArrayList<>();
   
	public static void main(String[] args) {
		processCSV("data/csv/Example1.csv", "data/xml/Example1_out.xml");
//		processCSV("data/csv/Example2-TaxAcctCur.csv","data/xml/Example2-TaxAcctCur_out.xml");
//		processCSV("data/csv/Example3-SumInv1.csv","data/xml/Example3-SumInv1_out.xml");
//		processCSV("data/csv/Example4-SumInv2.xsv","data/xml/Example4-SumInv2_out.xml");
//		processCSV("data/csv/Example5-AllowanceCharge.csv","data/xml/Example5-AllowanceCharge_out.xml");
//		processCSV("data/csv/Example6-CorrInv.csv","data/xml/Example6-CorrInv_out.xml");
//		processCSV("data/csv/Example7-Return.Quan.csv","data/xml/Example7-Return.Quan_out.xml");
//		processCSV("data/csv/Example8-Return.ItPr.csv","data/xml/Example8-Return.ItPr_out.xml");
	}
	
	public static void processCSV(String in_csv, String out_xml) {
		String DocumentCurrencyCode = null;
		String TaxCurrencyCode = null;
		
		FileHandler.parseBinding();
		FileHandler.csvFileRead(in_csv, CHARSET);
		FileHandler.parseSkeleton();
					
		rowMapList = new TreeMap<>();
		System.out.println("- processCSV FileHandler.tidyData record");
		for (ArrayList<String> record: FileHandler.tidyData) {
			System.out.println(record.toString());
			rowMap = new TreeMap<>();
			String key = "";
			for (int i = 0; i < record.size(); i++) {
				String field = record.get(i);
				if (""!=field) {
					String id = FileHandler.header.get(i);
					Binding binding = FileHandler.bindingDict.get(id);
					Integer sort = binding.getSynSort();
					if (id.matches("^ibg-[0-9]+$")) {
						key += sort+"="+field+" ";
					} else {
						rowMap.put(sort, field);
					}
					if (DOCUMENT_CURRENCY_CODE_ID.equals(id)) { 
						DocumentCurrencyCode = field;
					} else if (TAX_CURRENCY_CODE_ID.equals(id)) {
						TaxCurrencyCode = field;
					}
				}
			}
			key = key.trim();
			rowMapList.put(key, rowMap);
		}
		
		Element boughElement = null;
		LevelElement levelElement = null;
		Integer boughSort = null;
		Integer boughSeq = null;
		String id = null;
		String xPath = null;
		String value = "";
		for (Map.Entry<String, TreeMap<Integer, String>> rowMap : rowMapList.entrySet()) {
			String key = rowMap.getKey();
			String[] boughs = key.split(",");
			for (int i = 0; i < boughs.length; i++) {
				String bough = boughs[i];
//				System.out.println(bough);
				String[] data = bough.split(" ");
				for (int j = 0; j < data.length; j++) {
					String ds = data[j];
					String[] d = ds.split("=");
					boughSort = Integer.parseInt(d[0]);
					boughSeq = Integer.parseInt(d[1]);
					Binding boughBinding = FileHandler.synBindingMap.get(boughSort);
					id = boughBinding.getID();
					xPath = boughBinding.getXPath();
					value = "";
					HashMap<String,String> attributes = null;
					if (FileHandler.ROOT_ID.equals(id)) {
						boughElement = FileHandler.root;
						levelElement = new LevelElement(boughSeq, boughSort, xPath, boughElement);
						if (0==levelElementList.size()) {
							levelElementList.add(levelElement);
						}
					} else {
						boughElement = appendElementNS(boughSeq, boughSort, xPath, value, attributes);
						levelElement = new LevelElement(boughSeq, boughSort, xPath, boughElement);
					}
					boughElementMap.put(boughSort+" "+boughSeq, levelElement);
				}
			}

			// row data
			String bough = boughs[boughs.length-1];
			String[] data = bough.split(" ");
			String ds = data[data.length-1];
			String[] d = ds.split("=");
			boughSort = Integer.parseInt(d[0]);
			boughSeq = Integer.parseInt(d[1]);
			String boughXPath = levelElement.xPath;
			System.out.println("- processCSV levelElement "+boughSort+" "+boughSeq+" xPath:"+boughXPath);
			TreeMap<Integer, String> row = rowMapList.get(key);
			for (Integer synSort : row.keySet()) {
				value = row.get(synSort);
				Binding binding = FileHandler.synBindingMap.get(synSort);
				xPath = binding.getXPath();
				HashMap<String,String> attributes = new HashMap<>();
				String datatype = binding.getDatatype();
				if ("Amount".equals(datatype) || "Unit Price Amount".equals(datatype)) {
					attributes.put("currencyID", DocumentCurrencyCode);
				}
				Element element = appendElementNS(boughSeq, boughSort, xPath, value, attributes);
				levelElement = new LevelElement(boughSeq, boughSort, xPath, element);
			}
		}
		System.out.println("- processCSV\n" + levelElementList.toString());

		try (FileOutputStream output = new FileOutputStream(out_xml)) {
			WriteXmlDom.writeXml(FileHandler.doc, output);
		} catch (IOException eIO) {
		    eIO.printStackTrace();
		} catch (TransformerException eTE) {
			eTE.printStackTrace();
		}
		
		System.out.println("** END **");
	}

	private static Element appendElementNS (
			Integer boughSeq, 
			Integer boughSort, 
			String xPath,
			String value, 
			HashMap<String,String> attributes) {
		if (value.length() > 0) {
		value = value.trim();
			System.out.println("- appendElementNS " + boughSort + "=" + boughSeq + " " + xPath +" value=" + value);
		} else {
			System.out.println("- appendElementNS " + boughSort + "=" + boughSeq + " " + xPath);
		}
		ArrayList<String> paths = splitPath(xPath);
		int depth = paths.size();
		Element element0 = FileHandler.root;
		Element element1, element2, element3, element4, element5, element6;
//		System.out.println(boughSort+" "+boughSeq);
		Binding boughBinding = FileHandler.synBindingMap.get(boughSort);
		String boughXPath = boughBinding.getXPath();
		ArrayList<String> boughPaths = splitPath(boughXPath);
		int boughLevel = boughPaths.size()-1;	
		if (depth > 1) {
			element1 = fillLevelElement(1, depth, element0, paths.get(1), boughLevel, boughSort, boughSeq, value, attributes );
			if (null==element1) {
				System.out.println("- appendElementNS element1 NULL");
			} else if (2 == depth) {		
				return element1;
			} else {
				element2 = fillLevelElement(2, depth, element1, paths.get(2), boughLevel, boughSort, boughSeq, value, attributes );
				if (null==element2) {
					System.out.println("- appendElementNS element2 NULL");
				} else if (3==depth) {
					return element2;
				} else {
					element3 = fillLevelElement(3, depth, element2, paths.get(3), boughLevel, boughSort, boughSeq, value, attributes );
					if (null==element3) {
						System.out.println("- appendElementNS element3 NULL");
					} else if (4==depth) {
						return element3;
					} else {
						element4 = fillLevelElement(4, depth, element3, paths.get(4), boughLevel, boughSort, boughSeq, value, attributes );
						if (null==element4) {
							System.out.println("- appendElementNS element4 NULL");
						} else if (5==depth) {
							return element4;
						} else {
							element5 = fillLevelElement(5, depth, element4, paths.get(5), boughLevel, boughSort, boughSeq, value, attributes );
							if (null==element5) {
								System.out.println("- appendElementNS elemen51 NULL");
							} else if (6==depth) {
								return element5;
							} else {
								element6 = fillLevelElement(6, depth, element5, paths.get(6), boughLevel, boughSort, boughSeq, value, attributes );
								if (null==element6) {
									System.out.println("- appendElementNS element6 NULL");
								} else if (5==depth) {
									return element6;
								} else {
									System.out.println("- appendElementNS appendElementNS XPath dpth is too deep 7.");
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
	
	private static Element fillLevelElement (
			int n, 
			int depth,
			Element parent, 
			String path, 
			int boughLevel, 
			Integer boughSort, 
			Integer boughSeq,
			String value,
			HashMap<String,String> attributes ) {
		String selector = FileHandler.extractSelector(path);
//		if (selector.length() > 0) {
//			System.out.println("Csv2Invoice.fillLevelElement selector="+selector);
//		}
		path = FileHandler.stripSelector(path);
		if (null==parent) {
			System.out.println("- fillLevelElement parent null");
			parent = FileHandler.root;
		}
		
		List<Node> elements = FileHandler.getXPath(parent, path);
		
		Element element = null;
		try {
			if (0 == elements.size()) {
				element = createElement(parent, path, boughSort, 0, value, attributes, n, depth);
			} else {
				if (n == boughLevel) {
					System.out.println("- fillLevelElement size="+elements.size()+" boughSeq="+boughSeq);
					if (boughSeq < elements.size()) {
						element = (Element) elements.get(boughSeq);
					} else {
						element = createElement(parent, path, boughSort, boughSeq, value, attributes, n, depth);
					}
				} else {
					element = (Element) elements.get(0);
				}
			}			
		} catch (Exception e) {
			System.out.println("- fillLevelElement "+elements.toString());
			e.getStackTrace();
		}
		return element;
	}
	
	private static Element createElement (
			Element parent, 
			String path, 
			Integer boughSort, 
			Integer boughSeq,
			String value, 
			HashMap<String,String> attributes, 
			int level,
			int depth ) {
		Element element = null;
		LevelElement levelElement = null;
		String cacValue = "";
		HashMap<String,String> cacAttributes = null;
				
		String path1 = FileHandler.stripSelector(path);
		String ns = null, nsURI = null, qname = null;
		if ("@".equals(path1.substring(0,1))) {
			qname = path1;
		} else {
			String[] ename = path1.split(":");
			ns = ename[0];
			nsURI = FileHandler.nsURIMap.get(ns);
			qname = ename[1];
		}
		
		if ("cac".equals(ns)) {
			value = cacValue;
			attributes = cacAttributes;
		}
		if ("@".equals(qname.substring(0,1))) {
			FileHandler.appendElementNS(parent, nsURI, ns, qname, value, attributes);
			return null;
		} else {
			element = FileHandler.appendElementNS(parent, nsURI, ns, qname, value, attributes);
			levelElement = new LevelElement(boughSeq, boughSort, path, element);
			if (level == levelElementList.size()) {
				levelElementList.add(levelElement);
			} else {
				levelElementList.set(level, levelElement);
			}
			return element;
		}
	}
	
//	private static String extractSelector(
//			String xPath ) {
//		int start = xPath.indexOf("[");
//		int last = xPath.lastIndexOf("]");
//		String selector = "";
//		if (start >= 0) {
//			selector = xPath.substring(start, last+1);
//		}
//		return selector;
//	}
//	
//	private static String stripSelector (
//			String path ) {
//		int start = path.indexOf("[");
//		int last = path.lastIndexOf("]");
//		if (start >= 0) {
//			path = path.substring(0, start) + path.substring(last+1,path.length());	
//		}
//		return path;
//	}

	private static ArrayList<String> splitPath (
			String xPath ) {
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