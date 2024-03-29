package wuwei.japan_core.cius;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Node;

/**
 * デジタルインボイスのXMLインスタンス文書を読み取り、Tidy dataを格納しているCSVファイルを出力する.
 */
public class Invoice2csv {
	static boolean TRACE = false;
	static String PROCESSING = null;
	
	static String IN_XML  = null;
	static String OUT_CSV = null;
	static String CHARSET = "UTF-8";
	
	static String DOCUMENT_CURRENCY_CODE_ID = "JBT-091"; /*文書通貨コードのID*/
	static String TAX_CURRENCY_CODE_ID      = "JBT-090"; /*税通貨コードnoID*/
	static String DOCUMENT_CURRENCY         = null; /*文書通貨コード*/
	static String TAX_CURRENCY              = null; /*税通貨コード*/
	
	static String INVOICE_ID = "JBT-019";
	static String INVOICE_NUMBER;

	/**
	 * Tidy dataテーブルの行を指定する索引データ
	 */
	static TreeMap<Integer/* sort */,Integer/* seq */> boughMap = new TreeMap<>();
	
	/**
	 * Tidy dataテーブル全体についてのTidy dataテーブルの行を指定する索引データのリスト
	 */
	static ArrayList<TreeMap<Integer, Integer>> boughMapList = new ArrayList<>();
	
	/**
	 * Tidy dataテーブル作成用の2次元リストの行
	 */
	static TreeMap<Integer, String> rowMap = new TreeMap<>();
	
	/**
	 * Tidy dataテーブル作成用の2次元リスト　Tidy dataテーブルは、FileHandler/tidyData
	 */
	static TreeMap<String, TreeMap<Integer, String>> rowMapList = new TreeMap<>();

	/**
	 * デジタルインボイス(XML)をCSVに変換する。
 	 * The application's entry point
	 * @param args an array of command-line arguments for the application
	 * last updated 2023-02-24
	 */
	public static void main(String[] args) {
//		PROCESSING = args[0]+" SEMANTICS";		
//		IN_XML     = args[1];
//		OUT_CSV    = args[2];
//		if (4==args.length && "T".equals(args[3]))
//			TRACE = true;
		TRACE                  = true;
		PROCESSING             = "SME-COMMON SEMANTICS";
		IN_XML                 = "data/xml/Example1_SME.xml";
		OUT_CSV                = "data/csv/Example1_SME.csv";
//		PROCESSING             = "JP-PINT SEMANTICS";	
//		IN_XML                 = "data/xml/Example1.xml";
//		OUT_CSV                = "data/csv/Example1_PINT.csv";
//		IN_XML                 = "data/xml/Example5-AllowanceCharge.xml";
//		OUT_CSV                = "data/csv/Example5-AllowanceCharge_PINT.csv";
		FileHandler.TRACE      = TRACE;
		FileHandler.PROCESSING = PROCESSING;
		if (0==PROCESSING.indexOf("JP-PINT")) {
			FileHandler.CORE_CSV    = FileHandler.JP_PINT_CSV;
			FileHandler.XML_SKELTON = FileHandler.JP_PINT_XML_SKELTON;
		} else if (0==PROCESSING.indexOf("SME-COMMON")) {
			FileHandler.CORE_CSV    = FileHandler.SME_CSV;
			FileHandler.XML_SKELTON = FileHandler.SME_XML_SKELTON;
		} else {
			return;
		}
		processInvoice(IN_XML, OUT_CSV);
		if (TRACE) System.out.println("** END Invoice2csv "+IN_XML+" "+OUT_CSV+" **");
	}
	
	/**
	 * を読み込んで Tidy dataテーブルに展開し、CSVファイルに出力する.
	 * 
	 * @param in_xml デジタルインボイス（XMLインスタンス文書）.
	 * @param out_csv Tidy dataのCSV(RFC4180形式)ファイル.
	 */
	private static void processInvoice(String in_xml, String out_csv) {
		if (TRACE) System.out.println("\n** processInvoice("+in_xml+", "+out_csv+")");
		
		boughMap     = new TreeMap<Integer/*sort*/,Integer/*seq*/>();
		boughMapList = new ArrayList<TreeMap<Integer, Integer>>();
		rowMap       = new TreeMap<Integer, String>();
		rowMapList   = new TreeMap<String, TreeMap<Integer, String>>();
	
		FileHandler.parseBinding();
		
		try {
			FileHandler.parseInvoice(in_xml);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		FileHandler.nodeMap = FileHandler.parseDoc();
		
		for (Map.Entry<String, Binding> entry : FileHandler.bindingDict.entrySet()) {
			Binding binding = entry.getValue();
			Integer sort    = binding.getSemSort();
			String id = binding.getID();
			String card = binding.getCard();
			if (id.toLowerCase().matches("^jbg-.+$") &&
					card.matches("^.*n$") &&
					isMultiple(sort)) {
				FileHandler.multipleMap.put(sort, id);
			}
		}
		
		Binding binding = FileHandler.bindingDict.get(FileHandler.ROOT_ID);
		Integer sort = binding.getSemSort();
		boughMap.put(sort, 0);
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

		if (TRACE) System.out.println("-- END -- "+in_xml);
	}
	
	/**
	 * Tidy dataテーブル作成用の2次元リストrowMapListをTidy dataテーブル(FileHandler/tidyData)に変換する.
	 */
	private static void fillTable() {
		FileHandler.tidyData = new ArrayList<ArrayList<String>>();
		if (TRACE) System.out.println();

		FileHandler.header.add(FileHandler.ROOT_ID);
		// bough
		for (Map.Entry<Integer,String> multipleEntry : FileHandler.multipleMap.entrySet()) {
			String multipleID       = multipleEntry.getValue();
			Binding multipleBinding = FileHandler.bindingDict.get(multipleID);
			if (multipleID.toLowerCase().matches("^jbg-.+$") &&
					multipleBinding.isUsed()) {
				FileHandler.header.add(multipleID);
			}
		}
		// data
		for (Map.Entry<Integer,Binding> dataEntry : FileHandler.semBindingMap.entrySet()) {
			Integer dataSort    = dataEntry.getKey();
			Binding dataBinding = dataEntry.getValue();
			String dataID       = dataBinding.getID();
			if (1!=dataSort &&
					dataID.toLowerCase().matches("^jbt-.+$") &&
					dataBinding.isUsed() &&
					! FileHandler.header.contains(dataID)) {
				FileHandler.header.add(dataID);
			}
		}
		if (TRACE) System.out.println("* FileHandler.tidyData\n"+FileHandler.header.toString());
		for (Map.Entry<String, TreeMap<Integer, String>> entryRow : rowMapList.entrySet()) {
			ArrayList<String> record = new ArrayList<>();
			for (int i = 0; i < FileHandler.header.size(); i++) {
				record.add("");
			}
			// bough
			String rowMapKey = entryRow.getKey();
			String[] boughs = rowMapKey.split(" ");
			for (String bough : boughs) {
				String[] index       = bough.split("=");
				Integer boughSort    = Integer.parseInt(index[0]);
				Binding boughBinding = FileHandler.semBindingMap.get(boughSort);
				if (null!=boughBinding) {
					String boughID  = boughBinding.getID();
					String boughSeq = index[1];
					int boughIndex  = FileHandler.header.indexOf(boughID);
					if (boughIndex!=-1) {
						record.set(boughIndex, boughSeq);
					} else {
						if (TRACE) System.out.println(boughID+" NOT FOUND in the header");
					}
				}
			}
			// data
			TreeMap<Integer, String> rowMap = entryRow.getValue();
			for (Map.Entry<Integer, String> entry : rowMap.entrySet()) {
				Integer sort    = entry.getKey();
				String value    = entry.getValue();
				Binding binding = FileHandler.semBindingMap.get(sort);
				String id       = binding.getID();
				int dataIndex   = FileHandler.header.indexOf(id);
				if (dataIndex!=-1) {
					record.set(dataIndex, value);
				} else {
					if (TRACE) System.out.println(id+" NOT FOUND in the header");
				}
			}
			FileHandler.tidyData.add(record);
			if (TRACE) System.out.println(record.toString());
		}
	}

	/**
	 * 見つかった要素を Tidy dataテーブル作成用の2次元リストrowMapListに登録する.
	 * 
	 * @param semSort セマンティックモデルのセマンティックソート番号
	 * @param value 要素の値
	 * @param boughMap 
	 */
	private static void fillData (
			Integer semSort, 
			String value, 
			TreeMap<Integer, Integer> boughMap ) {
		Binding binding     = (Binding) FileHandler.semBindingMap.get(semSort);
		String id           = binding.getID();
		String businessTerm = binding.getBT();
		binding.setUsed(true);
		value = value.trim();
		if (TRACE) System.out.println("- 0 fillData boughMap = "+boughMap.toString()+id+" ("+semSort+") "+businessTerm+" = "+value);

		String rowMapKey = "";
		for (Map.Entry<Integer, Integer> entry : boughMap.entrySet()) {
			Integer boughSort = entry.getKey();
			Integer seq       = entry.getValue();
			rowMapKey += (boughSort+"="+seq+" ");
		}
		rowMap = new TreeMap<>();
		rowMapKey = rowMapKey.trim();
		if (rowMapList.containsKey(rowMapKey)) {
			rowMap = rowMapList.get(rowMapKey);
		}
		rowMap.put(semSort, value);
		rowMapList.put(rowMapKey, rowMap);
		if (INVOICE_ID.equals(id))
			INVOICE_NUMBER = value;
	}
	
	/**
	 * デジタルインボイスのXMLインスタンス文書を読み込み、セマンティックモデルの階層定義に従って親要素から子要素のXPathを使用して探し出し、その値をTidy data定義用の2次元リストに設定する。
	 * 
	 * @param parent 親のXML要素
	 * @param sort セマンティックモデル定義における親要素のセマンティックソート番号
	 * @param boughMap　要素が定義されたTidy dataの行に対応する索引
	 */
	private static void fillGroup (
			Node parent, 
			Integer sort, 
			TreeMap<Integer, Integer> boughMap ) {
		
		rowMap = new TreeMap<Integer, String>();
		
		// get child Nodes
		Binding binding     = FileHandler.semBindingMap.get(sort);
		String id           = binding.getID();
		String businessTerm = binding.getBT();

//		if ("JBG-010".equals(id))
//			System.out.println(id);
		TreeMap<Integer, List<Node>> childList = FileHandler.getChildren(parent, id);
		
		if (0==childList.size()) {
			if (TRACE) System.out.println("- 0 fillGroup boughMap="+boughMap.toString()+id+"(semSOrt="+sort+") "+businessTerm+" is Empty" );
			return;
		}
   	
		for (Integer childSort : childList.keySet()) {
			// childList includes both #text and @attribute
			Binding childBinding     = (Binding) FileHandler.semBindingMap.get(childSort);
			String childID           = childBinding.getID();
			String childBusinessTerm = childBinding.getBT();
			String childXPath        = childBinding.getXPath();
			int childLevel           = childBinding.getLevel();
			if (TRACE) System.out.println("- 1 fillGroup "+childID+"(semSort="+childSort+") "+childBusinessTerm+" XPath = "+childXPath);
//			if ("/Invoice/cac:PaymentMeans".equals(childXPath))
//				System.out.println(childXPath);
			List<Node> children = childList.get(childSort);			
			Integer countChildren = children.size();
			if (countChildren > 0) {
				for (int i = 0; i < countChildren; i++) {
					Node child = children.get(i);
					if (null!=child && childID.toLowerCase().matches("jbt-.+$")) {
						String value = null;
						value = child.getTextContent().trim();
						if (null != value && value.length() > 0) {
//							if (! "#text".equals(child.getNodeName()))
//								continue; // @attribute has already registered as a grand child in the procedure below if clause.
							if (TRACE) System.out.println("* 1 fillData child["+i+"]"+childID+"(semSOrt="+childSort+") "+child.getNodeName()+" = "+value);
							
							fillData(childSort, value, boughMap);
							
							if (FileHandler.semChildMap.containsKey(childSort)) {
								ArrayList<Integer> grandchildren = FileHandler.semChildMap.get(childSort);
								for (Integer grandchildSort : grandchildren) {
									Binding grandchildBiunding = (Binding) FileHandler.semBindingMap.get(grandchildSort);
									String grandchildID        = grandchildBiunding.getID();
									String grandchildBT        = grandchildBiunding.getBT();
									ParsedNode parsedNode      = FileHandler.nodeMap.get(grandchildSort);
									List<Node> grandchildNodes = parsedNode.nodes;
									for (int j = 0; j < grandchildNodes.size(); j++) {
										Node grandchild        = grandchildNodes.get(j);
										String grandchildName  = grandchild.getNodeName();
										String grandchildValue = grandchild.getTextContent().trim();
										if (TRACE) System.out.println("* 2 fillData boughMap"+boughMap.toString()+"child "+child.getNodeName()+" "+childID+
												" grandchild("+grandchildSort+")"+grandchildID+" level="+childLevel+" "+ childBusinessTerm+"->"+grandchildBT);
										if (TRACE) System.out.println("    grand child["+j+"]"+grandchildName+"="+grandchildValue);

										fillData(grandchildSort, grandchildValue, boughMap);
									}
								}
							}
						}
					} else {
						@SuppressWarnings("unchecked")
						TreeMap<Integer,Integer> boughMap1 = (TreeMap<Integer, Integer>) boughMap.clone();
						boolean is_multiple = isMultiple(childSort);
						if (is_multiple && countChildren > 1) {
							Integer lastkey   = boughMap1.lastKey();
							Integer lastvalue = boughMap1.get(lastkey);
							if (TRACE) System.out.print("    boughMap1 lastKey="+lastkey+" child is multiple level="+childLevel);
							if (childSort != lastkey) {
								if (boughMap.size() < childLevel + 1) {
									boughMap1.put(childSort, i);
								} else {
									boughMap1.pollLastEntry();
									boughMap1.put(childSort, i);
									boughMapList.remove(boughMapList.size() - 1);
								}
							} else if (countChildren > 1) {
								Integer lastvalue1 = lastvalue + 1;
								boughMap1.put(lastkey, lastvalue1);
							}
							boughMapList.add(boughMap1);
							if (TRACE) System.out.println("    UPDATED boughMapList="+boughMapList.toString()+" boughMap1="+boughMap1.toString());
						}
						if (TRACE) System.out.println("* fillGroup "+childID+" boughMapList="+boughMapList.toString()+" boughMap1"+boughMap1.toString()+" child(semSort="+childSort+") level="+childLevel+" "+ businessTerm+" -> "+childBusinessTerm);

						fillGroup(child, childSort, boughMap1);
					}
				}
			}
		}
	}

	/**
	 * セマンティックソート番号 semSortで指定された要素がXML DOM Documentに複数あるか判定する.<br>
	 * なお、FileHandler.MULTIPLE_IDで指定された要素も、複数と判定する.
	 * 
	 * @param semSort セマンティックソート番号.
	 * 
	 * @return multiple 複数であればtrue.　存在しないか1件しかなければfalse.
	 */
	@SuppressWarnings("unlikely-arg-type")
	private static boolean isMultiple(Integer semSort) {
		boolean multiple  = false;
		Binding binding   = FileHandler.semBindingMap.get(semSort);
		String id         = binding.getID();
		String xPath      = binding.getXPath();
		xPath             = FileHandler.stripSelector(xPath);
		List<Node> founds = FileHandler.getXPath(FileHandler.root, xPath);
		if (null!=founds && founds.size() > 1) {
			multiple = true;
		} else if (Arrays.asList(FileHandler.MULTIPLE_ID).contains(id.toLowerCase())) {
			multiple = true;
		}
		return multiple;
	}

}