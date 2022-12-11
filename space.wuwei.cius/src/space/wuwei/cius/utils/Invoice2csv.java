package space.wuwei.cius.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.Map.Entry;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Invoice2csv {
	static String IN_XML = "data/xml/Example1.xml";
	static String OUT_CSV = "data/csv/Example1.csv";
	static String CHARSET = "UTF-8";
	// prepare CSV records
    static TreeMap<Integer/* sort */,Integer/* seq */> boughMap = new TreeMap<>();
    static ArrayList<TreeMap<Integer, Integer>> boughMapList = new ArrayList<>();
    static TreeMap<Integer, String> rowMap = new TreeMap<>();
    static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();
    // semantic data
	static TreeMap<Integer,String> dataMap = new TreeMap<>();

	public static void main(String[] args) {
		 processInvoice("data/xml/Example1.xml", "data/csv/Example1.csv");
		 processInvoice("data/xml/Example2-TaxAcctCur.xml", "data/csv/Example2-TaxAcctCur.csv");
		 processInvoice("data/xml/Example3-SumInv1.xml", "data/csv/Example3-SumInv1.csv");
		 processInvoice("data/xml/Example4-SumInv2.xml", "data/csv/Example4-SumInv2.xsv");
		 processInvoice("data/xml/Example5-AllowanceCharge.xml", "data/csv/Example5-AllowanceCharge.csv");
		 processInvoice("data/xml/Example6-CorrInv.xml", "data/csv/Example6-CorrInv.csv");
		 processInvoice("data/xml/Example7-Return.Quan.xml", "data/csv/Example7-Return.Quan.csv");
		 processInvoice("data/xml/Example8-Return.ItPr.xml", "data/csv/Example8-Return.ItPr.csv");
		 System.out.println("** END **");
	}
	
	private static void processInvoice(String in_xml, String out_csv) {
		FileHandler.parseBinding();
		
		FileHandler.doc = FileHandler.parseInvoice(in_xml);
	    
		for (Map.Entry<String, Binding> entry : FileHandler.bindingDict.entrySet()) {
			Binding binding = entry.getValue();
			Integer sort = binding.getSemSort();
			String id = binding.getID();
			String card = binding.getCard();
			if (card.matches("^...n$")) {
				NodeList nodelist = FileHandler.getElements((Element) FileHandler.root, id);
				if (nodelist.getLength() > 1) {
					FileHandler.multipleMap.put(sort, id);
				}
			}
		}
	    
		Binding binding = FileHandler.bindingDict.get("ibg-00");
		Integer sort = binding.getSemSort();
		boughMap.put(1000,0);
		boughMapList.add(boughMap);
		
	    fillGroup(FileHandler.root, sort, boughMapList);
	    
	    fillTable();
	    
        FileHandler.csvFileWrite(out_csv, CHARSET);

		System.out.println("** END ** "+in_xml);
	}
	
	private static void fillTable() {
		FileHandler.header.add("ibg-00");
		for (Map.Entry<Integer,String> multipleEntry : FileHandler.multipleMap.entrySet()) {
			String multipleID = multipleEntry.getValue();
			FileHandler.header.add(multipleID);
		}
		for (Map.Entry<Integer,Binding> dataEntry : FileHandler.semBindingMap.entrySet()) {
			Binding dataBinding = dataEntry.getValue();
			if (dataBinding.isUsed()) {
				String dataID = dataBinding.getID();
				FileHandler.header.add(dataID);
			}
		}
//		System.out.println(FileHandler.header.toString());
		for (Map.Entry<String, TreeMap<Integer, String>> entryRow : rowMapList.entrySet()) {
			ArrayList<String> record = new ArrayList<>();
			for (int i = 0; i < FileHandler.header.size(); i++) {
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
					Binding boughBinding = FileHandler.semBindingMap.get(boughSort);
					String boughID = boughBinding.getID();
					String boughSeq = data.get(1);
					int boughIndex = FileHandler.header.indexOf(boughID);
					record.set(boughIndex, boughSeq);
				}
			}
			// data
			TreeMap<Integer, String> rowMap = entryRow.getValue();
			for (Map.Entry<Integer, String> entry : rowMap.entrySet()) {
				Integer sort = entry.getKey();
				String value = entry.getValue();
				Binding binding = FileHandler.semBindingMap.get(sort);
				String id = binding.getID();
				int dataIndex = FileHandler.header.indexOf(id);
				record.set(dataIndex, value);
			}
			FileHandler.tidyData.add(record);
		}
//		System.out.println(FileHandler.tidyData.toString());			
	}

	private static void fillData (
			Integer sort, 
			String value, 
			ArrayList<TreeMap<Integer,Integer>> boughMapList ) {
		Binding binding = (Binding) FileHandler.semBindingMap.get(sort);
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
				rowMapKey += (boughSort + "=" + seq + " ");
			}
			rowMapKey = rowMapKey.trim();
			rowMapListKey += (rowMapKey + ",");
		}
		rowMapListKey = rowMapListKey.substring(0, rowMapListKey.length() - 1);
//		System.out.println("|"+rowMapListKey+"|");
		TreeMap<Integer, String> rowMap = new TreeMap<>();
		if (rowMapList.containsKey(rowMapListKey)) {
			rowMap = rowMapList.get(rowMapListKey);
		}
		rowMap.put(sort, value);
		
		rowMapList.put(rowMapListKey, rowMap);
	}
	
	private static void fillGroup (
			Node parent, 
			Integer sort, 
			ArrayList<TreeMap<Integer, Integer>> boughMapList ) {	
		// get child Nodes
		Binding binding = FileHandler.semBindingMap.get(sort);
		String id = binding.getID();
		String businessTerm = binding.getBT();
//		String xPath = binding.getXPath();

		TreeMap<Integer, NodeList> childList = FileHandler.getChildren(parent, id);
		
		if (childList.size() > 0) {
			System.out.println("- fillGroup boughMapList=" + boughMapList.toString() + sort + ":" + id + " " + businessTerm );
		} else {
			System.out.println("- fillGroup boughMapList=" + boughMapList.toString() + sort + ":" + id + " " + businessTerm + " is Empty" );
			return;
		}
   	
		for (Integer childSort : childList.keySet()) {
			Binding childBinding = (Binding) FileHandler.semBindingMap.get(childSort);
            String childID = childBinding.getID();
    		String childBusinessTerm = childBinding.getBT();
    		int childLevel = Integer.parseInt(childBinding.getLevel());
           
            NodeList nodes = childList.get(childSort);
            
            Integer countNodes = nodes.getLength();             
            if (countNodes > 0) {
	            for (int i = 0; i < countNodes; i++) {
	            	Node node = nodes.item(i);
		        	if (childID.matches("^ibt-[0-9]+.*$")) {
	        			String value = node.getTextContent();
		            	if (!(null==value || value.equals(""))) {
		            		
			            	fillData(childSort, value, boughMapList);
			            	
		            	} else {	            		
		            		NamedNodeMap attributes = node.getAttributes();
		            		String attrNames[] = {"schemeID","currencyID","mimeCode","filename","listID","listVersionID"};
		            		for (int j = 0; j < attrNames.length; j++){
			            		String value1 = attributes.getNamedItem(attrNames[j]).getNodeValue();
			            		if (!(null==value1 || value1.equals(""))) {
			            			
			            			fillData(childSort, value1, boughMapList);
			            			
								}			            		
		            		}
		            	}
		            } else {
		            	boolean is_multiple = isMultiple(childSort);
		                if (is_multiple && countNodes > 1) {
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
	                	System.out.println("* fillGroup "+ childLevel + " boughMapList=" + boughMapList.toString() /*+ sort +":" + id + " "*/ + businessTerm + "->" /*+ childSort + ":" + childID + " "*/ + childBusinessTerm );

	                	fillGroup(node, childSort, boughMapList);
		            }
	            }
            }
        }
	}

	private static boolean isMultiple(Integer sort) {
		// Get the iterator over the HashMap
		Iterator<Map.Entry<Integer, String>> iterator = FileHandler.multipleMap.entrySet().iterator();
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

}