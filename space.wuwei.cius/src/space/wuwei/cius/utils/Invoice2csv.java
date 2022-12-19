package space.wuwei.cius.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Arrays;
//import java.util.Iterator;
import java.util.List;
// import java.util.List;
import java.util.Map;
//import java.util.Map.Entry;
import java.util.TreeMap;

//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;

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
//		 processInvoice("data/xml/Example0.xml", "data/csv/Example0.csv");
//		 processInvoice("data/xml/Example1.xml", "data/csv/Example1.csv");
//		 processInvoice("data/xml/Example2-TaxAcctCur.xml", "data/csv/Example2-TaxAcctCur.csv");
//		 processInvoice("data/xml/Example3-SumInv1.xml", "data/csv/Example3-SumInv1.csv");
//		 processInvoice("data/xml/Example4-SumInv2.xml", "data/csv/Example4-SumInv2.xsv");
		 processInvoice("data/xml/Example5-AllowanceCharge0.xml", "data/csv/Example5-AllowanceCharge0.csv");
//		 processInvoice("data/xml/Example5-AllowanceCharge.xml", "data/csv/Example5-AllowanceCharge.csv");
//		 processInvoice("data/xml/Example6-CorrInv.xml", "data/csv/Example6-CorrInv.csv");
//		 processInvoice("data/xml/Example7-Return.Quan.xml", "data/csv/Example7-Return.Quan.csv");
//		 processInvoice("data/xml/Example8-Return.ItPr.xml", "data/csv/Example8-Return.ItPr.csv");
		 System.out.println("** END **");
	}
	
	private static void processInvoice(String in_xml, String out_csv) {
		System.out.println("\n** processInvoice("+in_xml+", "+out_csv+")");
		
	    boughMap = new TreeMap<Integer/* sort */,Integer/* seq */>();
	    boughMapList = new ArrayList<TreeMap<Integer, Integer>>();
	    rowMap = new TreeMap<Integer, String>();
	    rowMapList = new TreeMap<String, TreeMap<Integer, String>>();
	
	    FileHandler.parseBinding();
		try {
			FileHandler.doc = FileHandler.parseInvoice(in_xml);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		FileHandler.parseDoc();
	    
		for (Map.Entry<String, Binding> entry : FileHandler.bindingDict.entrySet()) {
			Binding binding = entry.getValue();
			Integer sort = binding.getSemSort();
			String id = binding.getID();
			String card = binding.getCard();
//			String xPath = binding.getXPath();
			if (id.toLowerCase().matches("^ibg-.+$") && card.matches("^.*n$") && isMultiple(sort)) {
				FileHandler.multipleMap.put(sort, id);
			}
		}
	    
		Binding binding = FileHandler.bindingDict.get("ibg-00");
		Integer sort = binding.getSemSort();
		boughMap.put(1000,0);
		boughMapList.add(boughMap);
		
	    fillGroup(FileHandler.root, sort, boughMap);
	    
	    fillTable();
	    
        try {
			FileHandler.csvFileWrite(out_csv, CHARSET);
        } catch (FileNotFoundException fnf) {
        	System.out.println("* File Not Found Exception "+out_csv);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("-- END -- "+in_xml);
	}
	
	private static void fillTable() {
		FileHandler.tidyData = new ArrayList<ArrayList<String>>();
		System.out.println();

		FileHandler.header.add("ibg-00");
		// bough
		for (Map.Entry<Integer,String> multipleEntry : FileHandler.multipleMap.entrySet()) {
			String multipleID = multipleEntry.getValue();
			Binding multipleBinding = FileHandler.bindingDict.get(multipleID);
			if (multipleID.toLowerCase().matches("^ibg-.+$") && multipleBinding.isUsed()) {
				FileHandler.header.add(multipleID);
			}
		}
		// data
		for (Map.Entry<Integer,Binding> dataEntry : FileHandler.semBindingMap.entrySet()) {
			Integer dataSort = dataEntry.getKey();
			Binding dataBinding = dataEntry.getValue();
			String dataID = dataBinding.getID();
			if (1000!=dataSort && dataID.toLowerCase().matches("^ibt-.+$") && dataBinding.isUsed()) {
				FileHandler.header.add(dataID);
			}
		}
		System.out.println("* FileHandler.tidyData\n"+FileHandler.header.toString());
		for (Map.Entry<String, TreeMap<Integer, String>> entryRow : rowMapList.entrySet()) {
			ArrayList<String> record = new ArrayList<>();
			for (int i = 0; i < FileHandler.header.size(); i++) {
				record.add("");
			}
			// bough
			String rowMapKey = entryRow.getKey();
			String[] boughs = rowMapKey.split(" ");
			for (String bough : boughs) {
				String[] index = bough.split("=");
				Integer boughSort = Integer.parseInt(index[0]);
				Binding boughBinding = FileHandler.semBindingMap.get(boughSort);
				String boughID = boughBinding.getID();
				String boughSeq = index[1];
				int boughIndex = FileHandler.header.indexOf(boughID);
				if (boughIndex!=-1) {
					record.set(boughIndex, boughSeq);
				} else {
					System.out.println(boughID+" NOT FOUND in the header");
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
				if (dataIndex!=-1) {
					record.set(dataIndex, value);
				} else {
					System.out.println(id+" NOT FOUND in the header");
				}
			}
			FileHandler.tidyData.add(record);
			System.out.println(record.toString());
		}
	}

	private static void fillData (
			Integer sort, 
			String value, 
			TreeMap<Integer, Integer> boughMap ) {
		Binding binding = (Binding) FileHandler.semBindingMap.get(sort);
        String id = binding.getID();
		String businessTerm = binding.getBT();
		binding.setUsed(true);
		value = value.trim();
		System.out.println("- fill Data boughMap=" + boughMap.toString() + sort + "(" + id + ")" + businessTerm + ":" + value);

		String rowMapKey = "";
		for (Map.Entry<Integer, Integer> entry : boughMap.entrySet()) {
			Integer boughSort = entry.getKey();
			Integer seq = entry.getValue();
			rowMapKey += (boughSort + "=" + seq + " ");
		}
		rowMapKey = rowMapKey.trim();
		if (rowMapList.containsKey(rowMapKey)) {
			rowMap = rowMapList.get(rowMapKey);
		}
		rowMap.put(sort, value);
		rowMapList.put(rowMapKey, rowMap);
	}
	
	private static void fillGroup (
			Node parent, 
			Integer sort, 
			TreeMap<Integer, Integer> boughMap ) {
		
		rowMap = new TreeMap<Integer, String>();
		
		// get child Nodes
		Binding binding = FileHandler.semBindingMap.get(sort);
		String id = binding.getID();
		String businessTerm = binding.getBT();
//		String xPath = binding.getXPath();

		TreeMap<Integer, List<Node>> childList = FileHandler.getChildren(parent, id);
		
		if (0==childList.size()) {
			System.out.println("- fill Group boughMap=" + boughMap.toString() + sort + "(" + id + ")" + businessTerm + " is Empty" );
			return;
		}
   	
		for (Integer childSort : childList.keySet()) {
			Binding childBinding = (Binding) FileHandler.semBindingMap.get(childSort);
            String childID = childBinding.getID();
    		String childBusinessTerm = childBinding.getBT();
    		String childXPath = childBinding.getXPath();
    		int childLevel = Integer.parseInt(childBinding.getLevel());
			System.out.println("- fill Group " + childSort + "(" + childID + ")" + childBusinessTerm + " " +  childXPath);
          
            List<Node> children = childList.get(childSort);
            
            Integer countChildren = children.size();             
            if (countChildren > 0) {
	            for (int i = 0; i < countChildren; i++) {
	            	Node child = children.get(i);
		        	if (childID.matches("^ibt-[0-9]+.*$")) {
	        			String value = child.getTextContent().trim();
		            	if (null != value && value.length() > 0) {
		            		
			            	fillData(childSort, value, boughMap);
			            	
			        		if (FileHandler.childMap.containsKey(childSort)) {
			        			ArrayList<Integer> grandchildren = FileHandler.childMap.get(childSort);
			        			for (Integer grandchildSort : grandchildren) {
			        				Binding grandchildBiunding =  (Binding) FileHandler.semBindingMap.get(grandchildSort);
			        				String grandchildID = grandchildBiunding.getID();
			        				String grandchildBT = grandchildBiunding.getBT();
//			        				String grandchildXPath = grandchildBiunding.getXPath();
				                	System.out.println("* fill Group "+childID+" boughMapList="+boughMapList.toString()+" boughMap" + boughMap.toString() + " grandchild(" + grandchildSort + ")"+grandchildID+" level=" + childLevel + " " +  childBusinessTerm + "->" + grandchildBT);
				                	ParsedNode parsedNode = FileHandler.nodeMap.get(grandchildSort);
				                	List<Node> grandchildNodes = parsedNode.nodes;
				            		for (int j = 0; j < grandchildNodes.size(); j++) {
				            			Node grandchild = grandchildNodes.get(j);
				            			String grandchildName = grandchild.getNodeName();
				        				String grandchildValue = grandchild.getTextContent().trim();
				            			System.out.println("child["+i+"]"+child.getNodeName()+" "+child.getTextContent()+" grand child["+j+"]"+grandchildName+" "+grandchildValue);
				        				fillData(grandchildSort, grandchildValue, boughMap);
				            		}
			        			}
			        		}
		            	}
		            } else {
			        	@SuppressWarnings("unchecked")
						TreeMap<Integer,Integer> boughMap1 = (TreeMap<Integer, Integer>) boughMap.clone();
		            	boolean is_multiple = isMultiple(childSort);
		                if (is_multiple/* && countChildren > 1*/) {
		                	Integer lastkey = boughMap1.lastKey();
		                	Integer lastvalue = boughMap1.get(lastkey);
		                	System.out.print("    boughMap1 lastKey=" + lastkey + " child is multiple level=" + childLevel);
			            	if (childSort != lastkey) {
			            		if (boughMap.size() < childLevel + 1) {
			                		boughMap1.put(childSort, i);
			            		} else {
			            			boughMap1.pollLastEntry();
			            			boughMap1.put(childSort, i);
			            			boughMapList.remove(boughMapList.size() - 1);
			            		}
			            	} else /*if (countChildren > 1)*/ {
			            		Integer lastvalue1 = lastvalue + 1;
			            		boughMap1.put(lastkey, lastvalue1);
			            	}
			            	boughMapList.add(boughMap1);
			            	System.out.println(" UPDATED boughMapList=" + boughMapList.toString() + " boughMap1=" + boughMap1.toString());
		                }
	                	System.out.println("* fill Group "+childID+" boughMapList="+boughMapList.toString()+" boughMap1" + boughMap1.toString() + " child(" + childSort + ") level=" + childLevel + " " +  businessTerm + "->" + childBusinessTerm);
	                	fillGroup(child, childSort, boughMap1);
		            }
	            }
            }
        }
	}

	private static boolean isMultiple(Integer sort) {
		boolean multiple = false;
		Binding binding = FileHandler.semBindingMap.get(sort);
		String id = binding.getID();
		String xPath = binding.getXPath();
		xPath = FileHandler.stripSelector(xPath);
		List<Node> founds = FileHandler.getXPath(FileHandler.root, xPath);
		if (founds.size() > 1) {
			multiple = true;
		} else if (Arrays.asList(FileHandler.MULTIPLE_ID).contains(id.toLowerCase())) {
			multiple = true;
		}
		return multiple;
	}

}