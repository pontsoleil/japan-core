package space.wuwei.cius.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Element;
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
    // CSV table
	public static ArrayList<String> header = new ArrayList<>();
	static TreeMap<Integer,String> indexMap = new TreeMap<>();
	static TreeMap<Integer,String> dataMap = new TreeMap<>();
    public static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();

	public static void main(String[] args) {
		
		FileHandler.parseBinding();
		FileHandler.doc = FileHandler.parseInvoice(IN_XML);
//		Element root = (Element) FileHandler.doc.getChildNodes().item(0);
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
        FileHandler.csvFileWrite(OUT_CSV, CHARSET);

		System.out.println("END");
	}
	
	private static void fillTable() {
		header.add("ibg-00");
		for (Map.Entry<Integer,String> multipleEntry : FileHandler.multipleMap.entrySet()) {
			String multipleID = multipleEntry.getValue();
			header.add(multipleID);
		}
		for (Map.Entry<Integer,Binding> dataEntry : FileHandler.semBindingMap.entrySet()) {
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
					Binding boughBinding = FileHandler.semBindingMap.get(boughSort);
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
				Binding binding = FileHandler.semBindingMap.get(sort);
				String id = binding.getID();
				int dataIndex = header.indexOf(id);
				record.set(dataIndex, value);
			}
			tidyData.add(record);
		}
		System.out.println(tidyData.toString());			
	}

	private static void fillData(Integer sort, String value, ArrayList<TreeMap<Integer,Integer>> boughMapList) {
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
				Binding boughBinding = FileHandler.semBindingMap.get(boughSort);
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
		Binding binding = FileHandler.semBindingMap.get(sort);
		String id = binding.getID();
		String businessTerm = binding.getBT();

		TreeMap<Integer, NodeList> childList = FileHandler.getChildren(parent,id);
		
		if (childList.size() == 0) {
			System.out.println("- fillGroup boughMapList=" + boughMapList.toString() + sort + ":" + id + " " + businessTerm + " is Empty");
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