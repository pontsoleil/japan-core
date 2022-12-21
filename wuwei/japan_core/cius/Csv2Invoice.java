package wuwei.japan_core.cius;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import wuwei.japan_core.utils.WriteXmlDom;

public class Csv2Invoice {
	static String OUT_XML                   = "CIUS/data/xml/Example1_out.xml";
	static String IN_CSV                    = "CIUS/data/csv/Example1.csv";
	static String CHARSET                   = "UTF-8";
	static String DOCUMENT_CURRENCY_CODE_ID = "ibt-005";
	static String TAX_CURRENCY_CODE_ID      = "ibt-006";
	
    // CSV records
    static TreeMap<Integer, String> rowMap = new TreeMap<>();
    static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();
 	
    static class DataValue 
    {
    	public Integer seq;
 		public Integer sort;
 		public String xPath;
 		public String value;
 		public HashMap<String,String> attributes;
 		// constructor
 		DataValue(
 				Integer seq, 
 				Integer sort, 
 				String xPath, 
 				String value, 
 				HashMap<String,String> attributes) 
 		{
 			this.seq = seq;
 			this.sort = sort;
 			this.xPath = xPath;
 			this.value = value;
 			this.attributes = attributes;
 		}
    }
    
 	static class PathValue 
 	{
 		public String xPath;
 		public String value;

 		PathValue(String a, String b) {
 			xPath = a;
 			value = b;
 		}
 	}
 	static PathValue documentCurrencyCode = new PathValue(null, null);
 	static PathValue taxCurrencyCode = new PathValue(null, null);
   
	public static void main(String[] args) 
	{
//		processCSV("CIUS/data/csv/Example1.csv", "CIUS/data/xml/Example1_out.xml");
//		processCSV("CIUS/data/csv/Example2-TaxAcctCur.csv","CIUS/data/xml/Example2-TaxAcctCur_out.xml");
		processCSV("CIUS/data/csv/Example3-SumInv1.csv","CIUS/data/xml/Example3-SumInv1_out.xml");
//		processCSV("CIUS/data/csv/Example4-SumInv2.xsv","CIUS/data/xml/Example4-SumInv2_out.xml");
//		processCSV("CIUS/data/csv/Example5-AllowanceCharge0.csv","CIUS/data/xml/Example5-AllowanceCharge0_out.xml");
//		processCSV("CIUS/data/csv/Example5-AllowanceCharge.csv","CIUS/data/xml/Example5-AllowanceCharge_out.xml");
//		processCSV("CIUS/data/csv/Example6-CorrInv.csv","CIUS/data/xml/Example6-CorrInv_out.xml");
//		processCSV("CIUS/data/csv/Example7-Return.Quan.csv","CIUS/data/xml/Example7-Return.Quan_out.xml");
//		processCSV("CIUS/data/csv/Example8-Return.ItPr.csv","CIUS/data/xml/Example8-Return.ItPr_out.xml");
		System.out.println("** END **");
	}
	
	public static void processCSV(String in_csv, String out_xml) 
	{	
		FileHandler.parseBinding();
		
		try {
			FileHandler.csvFileRead(in_csv, CHARSET);
		} catch (FileNotFoundException e) {
			System.out.println("File not found "+in_csv);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileHandler.parseSkeleton();
					
		rowMapList = new TreeMap<>();
		
		System.out.println("- processCSV FileHandler.tidyData record");
		
		for (ArrayList<String> record: FileHandler.tidyData) {
			
			System.out.println(record.toString());
			
			rowMap = new TreeMap<>();
			String key = "";
			for (int i = 0; i < record.size(); i++) {
				String field = record.get(i);
				if (field.length() > 0) {
					String id = FileHandler.header.get(i);
					Binding binding = FileHandler.bindingDict.get(id);
					if (null==binding) {
						System.out.println("NULL");
						id = FileHandler.header.get(0).split(",")[i];
						binding = FileHandler.bindingDict.get(id);
					}
					Integer sort = binding.getSynSort();
					if (id.toLowerCase().matches("^ibg-.+$")) {
						key += sort+"="+field+" ";
					} else {
						rowMap.put(sort, field);
					}
					String xPath = binding.getXPath();
					if (DOCUMENT_CURRENCY_CODE_ID.equals(id)) {
						documentCurrencyCode.xPath = xPath;
						documentCurrencyCode.value = field;
					} else if (TAX_CURRENCY_CODE_ID.equals(id)) {
						taxCurrencyCode.xPath = xPath;
						taxCurrencyCode.value = field;
					}
				}
			}
			key = key.trim();
			rowMapList.put(key, rowMap);
		}
		
		Integer boughSort = null;
		Integer boughSeq = null;
		String id = null;
		String xPath = null;
		String value = "";
		HashMap<String,String> attributes = new HashMap<>();
		TreeMap<Integer/*synSort*/, String/*id*/> idMap = new TreeMap<>();
		
		int n = 0;		
		System.out.println(FileHandler.header);
		for (Map.Entry<String, TreeMap<Integer, String>> rowMap : rowMapList.entrySet()) {
			String key = rowMap.getKey();
			TreeMap<Integer, String> row = rowMapList.get(key);
			for (Integer synSort : row.keySet()) {
				Binding binding = FileHandler.synBindingMap.get(synSort);
				id = binding.getID();
				idMap.put(synSort, id);
			}
			n++;
		}
		
		int row_size = n;
		int col_size = idMap.size();
		DataValue[][] dataRedords = new DataValue[row_size][col_size];	
		
		int i = 0;
		for (Map.Entry<String, TreeMap<Integer, String>> rowMap : rowMapList.entrySet()) {
			String key = rowMap.getKey();
			String[] boughs = key.split(",");
			String bough    = boughs[boughs.length-1];
			String[] data   = bough.split(" ");
			String ds       = data[data.length-1];
			String[] d      = ds.split("=");
			boughSort = Integer.parseInt(d[0]);
			boughSeq  = Integer.parseInt(d[1]);
			TreeMap<Integer, String> row = rowMapList.get(key);
			for (Integer synSort : row.keySet()) {
				value = row.get(synSort);
				Binding binding = FileHandler.synBindingMap.get(synSort);
				xPath = binding.getXPath();
				String datatype = binding.getDatatype();
				attributes = new HashMap<>();
				if ("Amount".equals(datatype) || "Unit Price Amount".equals(datatype)) {
					if (xPath.length() > 0) {
						if (null!=taxCurrencyCode.xPath && xPath.indexOf(taxCurrencyCode.xPath)>=0) {
							attributes.put("currencyID", taxCurrencyCode.value);
						} else {
							attributes.put("currencyID", documentCurrencyCode.value);
						}
					}
				}
		        // Get key set of the TreeMap using keySet method
		        Set<Integer > keySet = idMap.keySet();
		        // Converting entrySet to ArrayList
		        List<Integer> entryList = new ArrayList<>(keySet);
		        int j = entryList.indexOf(synSort);
				System.out.println(i+" "+j);
				dataRedords[i][j] = new DataValue(boughSeq, boughSort, xPath, value, attributes);
			}
			i++;
		}
		
	    for (int y = 0; y < col_size; y++) {
	    	for (int x = 0; x < row_size; x++) {
	        	DataValue dataValue = dataRedords[x][y];
	        	if (null != dataValue) {
		        	boughSeq   = dataValue.seq;
		        	boughSort  = dataValue.sort;
		        	xPath      = dataValue.xPath;
		        	value      = dataValue.value;
		        	attributes = dataValue.attributes;
					appendElementNS(boughSeq, boughSort, xPath, value, attributes);
	        	}
	        }
	    }

		try (FileOutputStream output = new FileOutputStream(out_xml)) {
			WriteXmlDom.writeXml(FileHandler.doc, output);
		} catch (IOException eIO) {
		    eIO.printStackTrace();
		} catch (TransformerException eTE) {
			eTE.printStackTrace();
		}
		
		System.out.println("-- END -- "+out_xml);
	}

	private static Element appendElementNS (
			Integer boughSeq, 
			Integer boughSort, 
			String xPath,
			String value, 
			HashMap<String,String> attributes) 
	{
		if (']'==xPath.charAt(xPath.length()-1)) {
			return null;
		}
		value = value.trim();
		if (value.length() > 0) {
			System.out.println("* appendElementNS "+boughSort+"="+boughSeq+" "+xPath +"="+value);
		} else {
			System.out.println("* appendElementNS "+boughSort+"="+boughSeq+" "+xPath);
		}
		ArrayList<String> paths = splitPath(xPath);
		int depth = paths.size();
		Element element0 = FileHandler.root;
		Element element1, element2, element3, element4, element5, element6;
		Binding boughBinding = FileHandler.synBindingMap.get(boughSort);
		String boughXPath = boughBinding.getXPath();
		ArrayList<String> boughPaths = splitPath(boughXPath);
		int boughLevel = boughPaths.size()-1;	
		if (depth > 1) {
			element1 = fillLevelElement(1, depth, element0, paths.get(1), boughLevel, boughSort, boughSeq, value, attributes );
			if (2 == depth) {		
				return element1;
			} else {
				element2 = fillLevelElement(2, depth, element1, paths.get(2), boughLevel, boughSort, boughSeq, value, attributes );
				if (3==depth) {
					return element2;
				} else {
					element3 = fillLevelElement(3, depth, element2, paths.get(3), boughLevel, boughSort, boughSeq, value, attributes );
					if (4==depth) {
						return element3;
					} else {
						element4 = fillLevelElement(4, depth, element3, paths.get(4), boughLevel, boughSort, boughSeq, value, attributes );
						if (5==depth) {
							return element4;
						} else {
							element5 = fillLevelElement(5, depth, element4, paths.get(5), boughLevel, boughSort, boughSeq, value, attributes );
							if (6==depth) {
								return element5;
							} else {
								element6 = fillLevelElement(6, depth, element5, paths.get(6), boughLevel, boughSort, boughSeq, value, attributes );
								if (5==depth) {
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
			int     n, 
			int     depth,
			Element parent, 
			String  path, 
			int     boughLevel, 
			Integer boughSort, 
			Integer boughSeq,
			String  value,
			HashMap<String,String> attributes ) 
	{
		if (null==parent) {
			System.out.println("- fillLevelElement parent is NULL use root");
			parent = FileHandler.root;
		}
		String selector = FileHandler.extractSelector(path);
		
		List<Node> elements = FileHandler.getXPath(parent, path);
		
		Element element = null;
		try {
			System.out.print("- fillLevelElement getXPath returns "+elements.size()+" boughSeq="+boughSeq+" "+path);
			if (elements.size()==0 && ! path.matches("^cac:.*$")) {
				System.out.println("="+value);
			} else {
				System.out.println("");
			}
			if (0 == elements.size()) {
				element = createElement(parent, path, boughSort, 0, value, attributes, n, depth);
				if (selector.length() > 0) {
					System.out.println("    selector="+selector);
					defineSelector(element, selector, boughSort, boughSeq, n, depth);
				}
			} else {
				if (n == boughLevel) {
					if (boughSeq < elements.size()) {
						element = (Element) elements.get(boughSeq);
					} else {
						element = createElement(parent, path, boughSort, boughSeq, value, attributes, n, depth);
						if (selector.length() > 0) {
							System.out.println("    selector="+selector);
							defineSelector(element, selector, boughSort, boughSeq, n, depth);
						}
					}
				} else {
					element = (Element) elements.get(0);
				}
			}			
		} catch (Exception e) {
			System.out.println("xx fillLevelElement ERROR XPath:"+path+" element:"+element.toString());
			e.getStackTrace();
		}
		return element;
	}

	private static void defineSelector(
			Element element, 
			String selector, 
			Integer boughSort, 
			Integer boughSeq, 
			int n, 
			int depth ) 
	{
		if (0==selector.length()) {
			return;
		}
		selector = selector.substring(1,selector.length()-1);
		String[] params = selector.split("=");
		String selectorXPath = params[0];
		String selectorValue = params[1];
		if ("true()".equals(selectorValue)) {
			selectorValue = "true";
		} else if ("false()".equals(selectorValue)) {
			selectorValue = "false";
		} else {
			selectorValue = selectorValue.substring(1,selector.length()-1);
		}
		String[] paths = selectorXPath.split("/");
		HashMap<String,String> attrs = new HashMap<>();
		Element el = null;
		for (String sPath : paths) {
			if (sPath.matches("^cac:.*$")) {
				System.out.println("- defineSelector => createElement "+boughSort+"="+boughSeq+" "+sPath);
				el = createElement(element, sPath, boughSort, boughSeq, "", attrs, n, depth);
			} else {
				System.out.println("- defineSelector => createElement "+boughSort+"="+boughSeq+" "+sPath+" value="+selectorValue);
				createElement(el, sPath, boughSort, boughSeq, selectorValue, attrs, n, depth);
			}
		}
	}
	
	private static Element createElement (
			Element parent, 
			String path, 
			Integer boughSort, 
			Integer boughSeq,
			String value, 
			HashMap<String,String> attributes, 
			int level,
			int depth ) 
	{
		Element element = null;
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
			return element;
		}
	}

	private static ArrayList<String> splitPath (String xPath ) 
	{
		int start = xPath.indexOf("[");
		int last = xPath.lastIndexOf("]");
		String xPath1 = "";
		if (start >= 0) {
			String selector = xPath.substring(start, last+1);
			selector = selector.replaceAll("/","_");
			xPath1 = xPath.substring(0, start)+selector+xPath.substring(last+1,xPath.length());		
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
