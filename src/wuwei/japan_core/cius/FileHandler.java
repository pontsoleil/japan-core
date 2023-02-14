package wuwei.japan_core.cius;

import java.io.BufferedReader;
//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.Set;
import java.util.List;
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
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import wuwei.japan_core.utils.CSV;
import wuwei.japan_core.utils.NamespaceResolver;

/**
 * 物理ファイルとの入出力とその為のデータ変換を制御するクラス
 */
public class FileHandler {
	static String CORE_CSV                   = "CIUS/data/base/core_japan.csv";
	static String JP_PINT_CSV                = "CIUS/data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON        = "CIUS/data/base/jp_pint_skeleton.xml";
	static String SME_XML_SKELTON            = "CIUS/data/base/sme_skeleton.xml";
	
	static String PROCESSING;
	
	static List<String> jp_pint_XPath0       = new ArrayList<>();
	static List<String> jp_pint_XPath        = new ArrayList<>();
	
	public static Document doc               = null;
	public static XPath xpath                = null;
	public static Document agDoc             = null;
	public static XPath agXpath              = null;
	public static Element root               = null;
	public static String ROOT_ID             = "JBG-00";
	public static String PINT_ROOT_ID        = "ibg-00";
	public static String SME_ROOT_ID         = "JBG-00";
	public static String[] MULTIPLE_ID       = {"JBG-01","JBG-02","JBG-03","JBG-16","JBG-18","JBG-32","JBG-39","JBG-43","JBG-44","JBG-45","JBG-47","JBG-21","JBG-35","JBG-36","JBG-37","JBG-38","JBG-26","JBG-27","JBG-28","JBG-29","JBG-30","JBG-33","JBG-34","JBG-35","JBG-36","JBG-31","JBG-32","JBG-34","JBG-35","JBG-38","JBG-41","JBG-46","JBG-47","JBG-48"};
	public static String[] PINT_MULTIPLE_ID  = {"ibg-20", "ibg-21", "ibg-23", "ibg-25","ibg-27", "ibg-28"};
	public static String[] SME_MULTIPLE_ID   = {"ICL2","ICL3","ICL4","ICL43","ICL45","ICL31","ICL36","ICL40","ICL41","ICL42","ICL45","ICL47","ICL58","ICL59","ICL60","ICL61","ICL56","ICL69","ICL55","ICL62","ICL62","ICL67","ICL67","ICL73","ICL74","ICL91","ICL84","ICL77","ICL85","ICL86","ICL87"};
	public static HashMap<String, String> nsURIMap = null;
	
	/**
	 * Tidy dataテーブルの見出し行
	 */
	public static ArrayList<String> header   = new ArrayList<>();
	/**
	 * Tidy dataテーブル
	 */
    public static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();
    
	public static Map<String/*id*/,	Binding> bindingDict = new HashMap<>();
	public static TreeMap<Integer/*semSort*/, Binding> semBindingMap = new TreeMap<>();
	static TreeMap<Integer/*synSort*/, Binding> synBindingMap = new TreeMap<>();
	public static TreeMap<Integer/*parent semSort*/, ArrayList<Integer/*child semSort*/>> childMap = new TreeMap<>();
	public static TreeMap<Integer/*child semSort*/, Integer/*parent semSort*/> parentMap = new TreeMap<>();
	public static TreeMap<Integer/*semSort*/, String/*id*/> multipleMap = new TreeMap<>();
    public static TreeMap<Integer/*semSort*/, ParsedNode> nodeMap = new TreeMap<>();

    public static List<String> xPathCounter = new ArrayList<>();

    /**
     * 制御処理のテストを単体でテストする関数
     * 
     * @param args 使用しない
     */
    public static void main(String[] args) 
    {
    	String IN_XML = "data/xml/Example1.xml";
    	
		parseBinding();
		parseInvoice(IN_XML);
		parseDoc();
		
		List<Node> nodes;
		ParsedNode parsedNode;
		
	    // ibt-0373 - INVOICING PERIOD Start date 
		Binding ibt73Binding = bindingDict.get("ibt-073");
		Integer ibt73Sort = ibt73Binding.getSemSort();
		parsedNode = nodeMap.get(ibt73Sort);
		nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			System.out.println(i+" "+node.getNodeName()+" "+node.getTextContent());
		}
		
		// ibt-024 Specification identifier
		Binding ibt24Binding = bindingDict.get("ibt-024");
		Integer ibt24Sort = ibt24Binding.getSemSort();
		parsedNode = nodeMap.get(ibt24Sort);
		nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			System.out.println("ibt-024 Specification identifier "+i+" "+node.getNodeName()+" "+node.getTextContent());
		}		
		
	    // ibg-23 TAX BREAKDOWN
		Binding ibg23Bindingt = bindingDict.get("ibg-23");
		Integer ibg23Sort = ibg23Bindingt.getSemSort();
		ArrayList<Integer> childSorts = childMap.get(ibg23Sort);
		for (Integer childSort : childSorts) {
			parsedNode = nodeMap.get(childSort);
			nodes = parsedNode.nodes;
			for (int i = 0; i < nodes.size(); i++) {
				String value = "";
				Node node = nodes.get(i);
				System.out.print(i+" "+node.getNodeName()+" "+node.getTextContent());
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
				} else {
					System.out.println("");
				}
			}
		}
	
	    // ibt-034-1 - Scheme identifier 
		Binding ibt34_1Binding = bindingDict.get("ibt-034-1");
		Integer ibt34_1Sort = ibt34_1Binding.getSemSort();
		parsedNode = nodeMap.get(ibt34_1Sort);
		nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
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
	    int nodesLength = nodes.size();
	    for (int i = 0; i < nodesLength; i++) {	      
	        Element node = (Element) nodes.get(i);
	        
	        TreeMap<Integer, List<Node>> childrenMap = getChildren(node, "ibg-23");
	        
	        // Iterating HashMap through for loop
	        for (Integer sort : childrenMap.keySet()) {
	        	Binding binding = semBindingMap.get(sort);
	        	String id = binding.getID();
	        	String BT = binding.getBT();
	        	List<Node> children = childrenMap.get(sort);
	        	Node child = children.get(0);
	        	if (null!=child) {
	        		System.out.println(id+" "+BT+" "+child.getNodeValue());
	        	} else {
	        		System.out.println(id+" "+BT+" N/A");
	        	}
	        }
	    }

	    // ibt-034-1 - Scheme identifier 
	    List<Node> sellerEASAttrs = getElements(FileHandler.root, "ibt-034-1");
	    if (sellerEASAttrs.size() > 0) {
	    	Node sellerEASAttr = sellerEASAttrs.get(0);
	    	String sellerElectronicAddressSchemeIdentifier = sellerEASAttr.getNodeValue();
	    	System.out.println("ibt-034-1 "+sellerElectronicAddressSchemeIdentifier);
	    }
	    
		// cbc:DocumentCurrencyCode
		List<Node> documentCurrencyCodeEls = getElements(root, "ibt-005");//"/*/cbc:DocumentCurrencyCode/text()");
		Node documentCurrencyCodeEl = documentCurrencyCodeEls.get(0);
		String documentCurrencyCode = documentCurrencyCodeEl.getTextContent();
	    System.out.println("ibt-005 "+documentCurrencyCode);
	    
		// ibt-110 Invoice total TAX amount
	    List<Node> invoiceTotalTaxAmountEl = getElements(FileHandler.root, "ibt-110");
	    String invoiceTotalTaxAmount = invoiceTotalTaxAmountEl.get(0).getTextContent();
	    System.out.println("ibt-110 "+invoiceTotalTaxAmount);
	    
    }
    
	/**
	 * セマンティックモデル定義及びシンタクスバインディング定義シートを読み込み定義情報を次の広域変数に設定する。<br>
	 * Map&lt;String(id),Binding> bindingDict<br>
	 * TreeMap&lt;Integer(semSort), Binding> semBindingMap<br>
	 * TreeMap&lt;Integer(synSort), Binding> synBindingMap
	 */
    public static void parseBinding() 
	{
		System.out.println("-- parseBinding");

		Integer[] parents       = new Integer[10];
		Binding[] bindingParent = new Binding[10];
		ArrayList<ArrayList<String>> binding_data = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(JP_PINT_CSV);
			binding_data = CSV.readFile(fileInputStream, "UTF-8");
			ArrayList<String> headers = binding_data.get(0);
			for (int n=1; n < binding_data.size(); n++) {
				ArrayList<String> cells = binding_data.get(n);
				// semSort,id,card,level,businessTerm,desc,dataType,businessTerm_ja,desc_ja,synSort,element,synDatatype,xPath,occur
				// 1       2  3    4     5            6    7        8               9       10      11      12          13    14				
				Binding binding = new Binding(0, "", "", "", "", "", 0, "", "");
				for (int i = 0; i < headers.size(); i++) {
					String key = headers.get(i);
					if (0==i) {
						key	= key.replace("\uFEFF", "");
					}
					String value = cells.get(i);
					Integer order = -1;
					switch (key) {
					case "semSort":
						order = Integer.parseInt(value);
						binding.setSemSort(order);
						break;
					case "id":
						binding.setID(value);
						break;
					case "card":
						binding.setCard(value);
						break;
					case "level":
						binding.setLevel(value);
						break;
					case "businessTerm":
						binding.setBT(value);
						break;
					case "dataType":
						binding.setDatatype(value);
						break;
					case "synSort":
						if (value.matches("^[0-9]+$"))
							order = Integer.parseInt(value);
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
				Integer semSort = binding.getSemSort();
				Integer synSort = binding.getSynSort();
//				System.out.println("- FileHandler.parseBinding "+binding.getID()+" "+binding.getXPath());
				bindingDict.put(id, binding);
				semBindingMap.put(semSort, binding);
				synBindingMap.put(synSort, binding);
			}
			
			for (Entry<Integer, Binding> entry : semBindingMap.entrySet()) {
				Integer semSort = entry.getKey();
				Binding binding = entry.getValue();
				String l = binding.getLevel();
				Integer level = Integer.parseInt(l);
				parents[level] = semSort;
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
					childMap.put(parent_semSort, children);
					for (Integer child_semSort: children) {
						parentMap.put(child_semSort, parent_semSort);
					}
				}
			}
			  
			for (Entry<Integer, Binding> entry : semBindingMap.entrySet()) {
				Binding binding = entry.getValue();
				String id = binding.getID();
				String xPath = binding.getXPath();
				String strippedXPath = stripSelector(xPath);
				int idx = strippedXPath.lastIndexOf("/");
				String additionalXPath = "";
				if (idx >= 0) {
					additionalXPath = strippedXPath.substring(0, idx);
				}
				String l = binding.getLevel();
				Integer level = Integer.parseInt(l);
				bindingParent[level] = binding;
				if (level > 0) {
					int parent_level = level - 1;
					Binding parentBinding = bindingParent[parent_level];
					String parentID = parentBinding.getID();
					String parentXPath = parentBinding.getXPath();
					String strippedParentXPath = stripSelector(parentXPath);
//					System.out.println("- FileHandler.parseBinding check additional XPath "+parentID+"->"+id);
					if (additionalXPath.length() > 0 &&
							strippedParentXPath.indexOf(additionalXPath) < 0 &&
							additionalXPath.indexOf(strippedParentXPath) < 0) {
						additionalXPath = resumeSelector(additionalXPath, xPath);
						System.out.println(id+" "+xPath+" "+parentID+" "+parentXPath+"\n    ADDED parent XPath: "+parentXPath+" additional Xpath: "+additionalXPath);
						
						parentBinding.addAdditionalXPath(additionalXPath);
					} else if (idx > 0 && xPath.length() > 0 &&
							strippedParentXPath.indexOf(xPath) < 0 &&
							xPath.indexOf(strippedParentXPath) < 0) {
						additionalXPath = xPath;
						System.out.println(id+" "+xPath+" "+parentID+" "+parentXPath+"\n    ADDED parent XPath: "+parentXPath+" additional Xpath: "+additionalXPath);
						
						parentBinding.addAdditionalXPath(additionalXPath);
					}
				}
			}
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
	}
	
//	private static int countChar(char ch, String str) {
//		int count = 0;
//		for (int i = 0; i < str.length(); i++) {
//		    if (str.charAt(i) == ch) {
//		        count++;
//		    }
//		}
//		return count;
//	}
	
	/**
	 * 変換元のデジタルインボイスを読み込み、XML DOM及び名前空間を指定したXPathが使用できるように<br>
	 * XML DOM Document: doc, ルート要素(Element: root)及びxpath(XPath: xpath)を広域変数に設定する。
	 * 
	 * @param xmlfile 変換元のデジタルインボイス
	 */
	public static void parseInvoice(String xmlfile) 
	{
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
			root = (Element) doc.getChildNodes().item(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * semSortをキーにバインディング定義のXPathを検索し、それ使用してdocに定義されている要素を取り出して作成したTreeMapであるnodeMapを作成する。
	 * 
	 * @return nodeMap semSortをキーとするTreeMap&lt;Integer(semSort) ParsedNod&gt;
	 * 
	 */
	public static TreeMap<Integer, ParsedNode> parseDoc() {
		TreeMap<Integer/*semSort*/, ParsedNode> nodeMap = new TreeMap<>();
		for (Integer semSort: semBindingMap.keySet()) {
			Binding binding = semBindingMap.get(semSort);
			String xPath = binding.getXPath();
			List<Node> nodes = getXPath(root, xPath);
			if (nodes.size() > 0) {
				binding.setUsed(true);
			}
			ParsedNode parsedNode = new ParsedNode(binding, nodes);
			nodeMap.put(semSort, parsedNode);
		}
		return nodeMap;
	}
	
	/**
	 * デジタルインボイスのルート要素のみが定義されたスキーマファイルJP_PINT_XML_SKELTONを読み込んで名前空間を定義する。
	 */
	public static void parseSkeleton() 
	{
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
	
	/**
	 * セマンティックモデルのidから、バインディング情報のsemSortを検索し、その値を用いてnodeMapから該当するNodeの値を取り出してnodeValueMapを作成する。
	 * 
	 * @param id セマンティックモデルのid
	 * @return nodeValueMap TreeMap&lt;Integer(順序番号), String(Nodeの値)&gt;
	 */
	public static TreeMap<Integer, String> getNodeValues(String id) 
	{
		TreeMap<Integer, String> nodeValueMap = new TreeMap<>();
		Binding binding = bindingDict.get(id);
		Integer sort = binding.getSemSort();
		
		ParsedNode parsedNode = nodeMap.get(sort);
		
		List<Node> nodes = parsedNode.nodes;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			String value = node.getTextContent().trim();
			nodeValueMap.put(i, value);
//			System.out.println(i+" "+node.getNodeName()+" "+value);
		}
		return nodeValueMap;
	}
	
	/**
	 * 子要素のidからバインディング定義のXPathを取り出してそれを使用して親要素の下位にある子要素を探し出し、その結果をリストとして返す。
	 * 
	 * @param parent　親要素
	 * @param id　子要素のid
	 * @return nodes XPathに該当する要素のリスト List&lt;Node&gt; nodes
	 */
	public static List<Node> getElements(Element parent, String id) 
	{
		Binding binding = (Binding) bindingDict.get(id);
		String xpath = binding.getXPath();
		xpath = xpath.replaceAll("/Invoice", "/*");
		xpath = xpath.replaceAll("/ubl:Invoice", "/*");
		if (null==parent) {
			System.out.println("- FileHaldler.getElements parent null");
			return null;
		}
		if (id.toLowerCase().matches("^ibt-.+$")) {
			xpath += "/text()";
		}
		List<Node> nodes = getXPathNodes(parent, xpath);
		return nodes;
	}
	
	/**
	 * 親要素から指定されたXPathに該当する要素を探す。
	 * 
	 * @param parent 親要素
	 * @param xpath 親要素からのXPath
	 * @return
	 */
	public static List<Node> getXPath(Element parent, String xpath) 
	{
		xpath = xpath.replaceAll("/(Invoice|ubl:Invoice)/", "/*/");
		if (null==parent) {
			System.out.println("- FileHaldler.getXPath parent null");
			return null;
		}
		List<Node> nodes = getXPathNodes(parent, xpath);
		return nodes;
	}

	/**
	 * セマンティックモデルに基づいて親要素のidから次の方法で子要素を探す。
	 * <ul>
	 * <li>親要素から子要素のXPathに基づいて指定されたXML要素を探す.</li>
	 * <li>及びモデルでは定義されていないが子要素のXPathから追加定義定義されたXPathに該当するXML要素を探す.</li>
	 * </ul>
	 * 
	 * @param parent 親要素
	 * @param parent_id 親要素のid
	 * @return 親要素のsemSortをキーとし、子要素(Node)のリストを値とするTreeMap
	 */
	public static TreeMap<Integer, List<Node>> getChildren(Node parent, String parent_id) 
	{
		TreeMap<Integer, List<Node>> childList = new TreeMap<>();	
		Binding binding = (Binding) bindingDict.get(parent_id);
		Integer semSort = binding.getSemSort();
		String xpath = binding.getXPath();
		System.out.println("-- getChildren "+parent_id+"("+semSort+")"+xpath);

		ArrayList<Integer> children = childMap.get(semSort);
		
		if (null!=children) {
			for (Integer sort: children) {
				Binding child_binding = (Binding) semBindingMap.get(sort);
				String childID        =  child_binding.getID();
				String child_datatype = child_binding.getDatatype();
				String child_xpath    = child_binding.getXPath();
				Set<String> additionalXPath =
						child_binding.getAdditionalXPath();
				child_xpath = checkChildXPath(xpath, childID, child_datatype, child_xpath);
				
				List<Node> nodes = getXPathNodes(parent, child_xpath);
				
				if (null!=additionalXPath) {
					for(Iterator<String> iterator = additionalXPath.iterator(); iterator.hasNext(); ) {
						String additional_xpath     = iterator.next();
						List<Node> additional_nodes = getXPathNodes(parent, additional_xpath);
						nodes.addAll(additional_nodes);
					}
				}
				
				if (nodes.size() > 0)
					childList.put(sort, nodes);
			}
		}
		return childList;
	}

	/**
	 * 必要に応じて /text() を追加して、ルート要素からの子要素の相対的な XPath を求める。<br>
	 * Append /text() where necessary to find the relative XPath of child elements from the parent element.
	 * 
	 * @param xpath 親要素のXPath / XPath of the parent Node.
	 * @param childID 子要素のid /　id of the child Node.
	 * @param child_datatype 子要素のおデータ型 / Data type of the child Node.
	 * @param child_xpath 子要素のXPath / XPath of the child Node.
	 * @return　更新された子要素のXPath / Replaced XPath of the child Node.
	 */
	private static String checkChildXPath(
			String xpath, 
			String childID, 
			String child_datatype, 
			String child_xpath ) 
	{
		if (! xpath.equals("/Invoice") && ! xpath.equals("/ubl:Invoice")) {
			child_xpath = child_xpath.replace(xpath, ".");
		}
		// replace root element in the selector with /*
		child_xpath = child_xpath.replace("/Invoice/", "/*/");
		child_xpath = child_xpath.replace("/ubl:Invoice/", "/*/");
		if (childID.toLowerCase().matches("^ibt-.*$") &&
				! "String".equals(child_datatype)) {
			child_xpath += "/text()";
		}
		return child_xpath;
	}

	/**
	 * 親のXML要素から指定されたXPathにあるXML要素を探し見つかった要素を返す.
	 * 
	 * @param parent 親のXML要素
	 * @param xPath 親のXML要素からのXPath
	 * @return XPathで指定された位置にある子のXML要素のリスト
	 */
	public static List<Node> getXPathNodes(
			Node parent, 
			String xPath ) 
	{
		XPathExpression expr = null;
		NodeList result;
		List<Node> nodeList = new ArrayList<>();
		try {
			xPath = xPath.replace("/Invoice", "/*");
			xPath = xPath.replace("/ubl:Invoice", "/*");
			expr = xpath.compile(xPath);
			result = (NodeList) expr.evaluate(parent, XPathConstants.NODESET);
			nodeList = asList(result);	
			// https://stackoverflow.com/questions/50509663/convert-nodelist-to-list-in-java
			final int len = nodeList.size();
		    List<Node> nodes = new ArrayList<>();
		    for (int i = 0; i < len; i++) {
		      final Node node = nodeList.get(i);
		      if (node.getNodeType() == Node.ELEMENT_NODE ||
		    		  node.getNodeType() == Node.TEXT_NODE ||
		    		  node.getNodeType() == Node.ATTRIBUTE_NODE) {
		    	  nodes.add((Node) node);
		      }
		      // Ignore other node types.
		    }
		    return nodes;	
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	// https://stackoverflow.com/questions/19589231/can-i-iterate-through-a-nodelist-using-for-each-in-java
	/**
	 * XML要素のNodeListをXML要素のリストList&lt;Node&gt;に変換する.
	 * @param nodes XML要素のNodeList
	 * @return 変換されたXML要素のリスト List&lt;Node&gt;
	 */
	public static List<Node> asList(NodeList nodes) 
	{	    
		return nodes.getLength()==0
				? Collections.<Node>emptyList()
				: new NodeListWrapper(nodes);
	}
	/**
	 * XML要素のNodeListをXML要素のリストList&lt;Node&gt;に変換するためのリストのラッパークラス.<br>
	 * asList()関数で使用する.
	 */
	static final class NodeListWrapper extends AbstractList<Node>	  
	implements RandomAccess 
	{	    
		private final NodeList list;
	    NodeListWrapper(NodeList l) {
	      list=l;
	    }
	    public Node get(int index) {
	      return list.item(index);
	    }
	    public int size() {
	      return list.getLength();
	    }
	}
	
	/**
	 * XPathの文字列中に選択条件を指定するSelectorの文字列が含まれていればそのSelector文字列を返す.
	 * @param xPath XPathの文字列.
	 * @return 選択条件を指定するSelectorの文字列 [条件式]
	 */
	public static String extractSelector(String xPath) 
	{
		int start = xPath.indexOf("[");
		int last = xPath.lastIndexOf("]");
		String selector = "";
		if (start >= 0) {
			selector = xPath.substring(start, last+1);
		}
		return selector;
	}
	
	/**
	 * XPathの文字列中に選択条件を指定するSelectorの文字列が含まれていればそのSelector文字列を削除したXPath文字列を返す.
	 * @param xPath XPathの文字列.
	 * @return 選択条件を指定するSelectorの文字列 [条件式] を削除したXPath文字列.
	 */
	public static String stripSelector(String path) 
	{
		int start = path.indexOf("[");
		int last = path.lastIndexOf("]");
		if (start >= 0) {
			path = path.substring(0, start)+path.substring(last+1,path.length());	
		}
		return path;
	}
	
	/**
	 * 選択条件を指定するSelector文字列を削除したXPath文字列に、Selectorの文字列を含むXPathの文字列からSelectorを取り出し、そのSelectorをSelector文字列を削除したXPath文字列に戻してその文字列を返す.
	 * 
	 * @param strippedPath Selector文字列を削除したXPath文字列.
	 * @param path Selectorの文字列を含むXPathの文字列.
	 * @return resumedPath Selector文字列を削除したXPath文字列にSelectorを戻した文字列.
	 */
	public static String resumeSelector(String strippedPath, String path) 
	{
		String resumedPath = strippedPath;
		if (strippedPath.indexOf("cac:AllowanceCharge") >= 0) {
			int start = path.indexOf("[");
			int last = path.lastIndexOf("]");
			if (start >= 0) {
				String selector = path.substring(start, last+1);
				String header = strippedPath.substring(0, start);
				if (start+1 > strippedPath.length()) {
					resumedPath = header+selector;
				} else {
					String trailer = strippedPath.substring(start+1, strippedPath.length());
					resumedPath = header+selector+"/"+trailer;
				}
			}
		}
		return resumedPath;	
	}

	/**
	 * XML DOM DocumentにXML要素を追加定義し、そのXML要素を親のXML要素の子要素として追加する.
	 * 
	 * @param parent 親のXML要素.
	 * @param nsURI XML要素の名前空間URI.
	 * @param prefix XML要素の名前空間を指定する接頭辞.
	 * @param qname XML要素の名前.
	 * @param value XML要素の値.
	 * @param attrMap　XML要素の属性.
	 * 
	 * @return element 
	 */
	public static Element appendElementNS (
			Element parent,
			String nsURI, 
			String prefix, 
			String qname, 
			String value, 
			HashMap<String, String> attrMap ) 
	{
		if (null==parent) {
			System.out.println("- FileHaldler.appendElementNS parent "+prefix+":"+qname+" is NULL return");
			return null;
		}
		try {
			if ("@".equals(qname.substring(0,1))) {
				String attrName = qname.substring(1, qname.length());
				
				Attr attribute = doc.createAttribute(attrName);
				
				attribute.setValue(value);
				parent.setAttributeNode(attribute);
				return parent;
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
	
	/**
	 * XML DOOM DocumentをXMLインスタンス文書ファイルに出力する.
	 * 
	 * @param doc XML DOOM Document
	 * @param filename XMLインスタンス文書ファイル名
	 * 
	 * @throws FileNotFoundException
	 * @throws TransformerException
	 * @throws TransformerFactoryConfigurationError
	 * @throws IOException
	 */
	public static void writeXML(
			Document doc, 
			String filename )
		throws
			FileNotFoundException,
			TransformerException,
			TransformerFactoryConfigurationError,
			IOException 
	{
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(doc);
	    FileOutputStream output = new FileOutputStream(filename);
		StreamResult result = new StreamResult(output);
        // pretty print XML
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");		    
		transformer.transform(source, result);
	}

	/**
	 * Tidy dataテーブルをCSVファイルに出力する.
	 * 
	 * @param filename CSVファイル名.
	 * @param charset 文字コード.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void csvFileWrite(
			String filename, 
			String charset )
		throws
			FileNotFoundException,
			IOException 
	{
		System.out.println("- FileHandler.csvFileWrite "+filename+" "+charset);
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		ArrayList<ArrayList<String>> data = new ArrayList<>();	
		// header
		data.add(header);
		// data
		for (ArrayList<String> row : tidyData) {
			data.add(row);
		}
		
		CSV.writeFile(fileOutputStream, data, charset);

        fileOutputStream.close();
	}

	/**
	 * CSVファイルから読み込んでTidy dataテーブルに展開する.
	 * 
	 * @param filename CSVファイル名.
	 * @param charset 文字コード.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void csvFileRead(
			String filename, 
			String charset )
		throws
			FileNotFoundException,
			IOException 
	{
		System.out.println("-- FileHandler.csvFileRead "+filename+" "+charset);
		FileInputStream fileInputStream = new FileInputStream(filename);
		
		ArrayList<ArrayList<String>> data = CSV.readFile(fileInputStream, charset);
		
		// header
		header = new ArrayList<String>();
		ArrayList<String> fields = data.get(0);
		for (String field : fields) {
			header.add(field);
		}
		// data
		tidyData = new ArrayList<ArrayList<String>>();
		for (int i = 1; i < data.size(); i++) {
			fields = data.get(i);
			tidyData.add(fields);
		}
        fileInputStream.close();
	}

	
	/**
	 * スキーマファイル schema を読み込んで名前空間を定義する。
	 * 
	 * @param schema
	 */
	public static List<String> parseSchema(String schema, String rootType) 
	{
		int countNodes,
			i,
			j;
		String 
			attrName,
			attrValue,
			namespace,
			schemaLocation,
			name,
			type,
			ref,
			unid,
			complexType;
		List<Node>
			nodes,
			elements;
		Node
			node,
			attribute,
			element;
		Comment comment;
		HashMap<String, String> namespaceMap = new HashMap<>();
		HashMap<String, String> importMap = new HashMap<>();
        NamedNodeMap attributes = null;
		String rootXPath = "/xsd:schema/xsd:complexType[@name='"+rootType+"']/xsd:sequence/xsd:element";
 	    try {
		    //Build DOM
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true); // never forget this!
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    //Parse XML file
		    File schemaFile = new File(schema);
		    String dirPath = schemaFile.getParent();
		    FileInputStream fis = new FileInputStream(schemaFile);
		    doc = builder.parse(fis);
		    //Create XPath
		    XPathFactory xpathfactory = XPathFactory.newInstance();
		    xpath = xpathfactory.newXPath();
		    xpath.setNamespaceContext(new NamespaceResolver(doc));
		    // parse schema
        	nodes = getXPathNodes(doc,"/xsd:schema");
	        node = nodes.get(0);
		    attributes = node.getAttributes();
        	namespaceMap = new HashMap<>();
        	for (j = 0; j < attributes.getLength(); j++) {
		 		attribute = attributes.item(j);
	            attrName = attribute.getNodeName();
	            attrValue = attribute.getNodeValue();
	            namespaceMap.put(attrName, attrValue);
	        }
        	String schemaNamespace = "";
        	if ("UBL"==PROCESSING) {
        		schemaNamespace = namespaceMap.get("xmlns:cac");
        	} else if ("CII"==PROCESSING) {
        		schemaNamespace = namespaceMap.get("xmlns:ram");
        	}
        	nodes = getXPathNodes(doc,"/xsd:schema/xsd:import");
	        countNodes = nodes.size();
	        if (countNodes > 0) {
	            for (i = 0; i < countNodes; i++) {
	            	node = nodes.get(i);
	            	namespace = "";
	            	schemaLocation = "";
	            	attributes = node.getAttributes();
	            	for (j = 0; j < attributes.getLength(); j++) {
	    		 		attribute = attributes.item(j);
	    	            attrName = attribute.getNodeName();
	    	            attrValue = attribute.getNodeValue();
	    	            if ("namespace"==attrName)
	    	            	namespace = attrValue;
	    	            else if ("schemaLocation"==attrName)
	    	            	schemaLocation = attrValue;
	    	        }
	            	importMap.put(namespace,schemaLocation);
	            }
	        }
		    // Parse XML file
	    	String agSchema = importMap.get(schemaNamespace);
		    fis = new FileInputStream(new File(dirPath,agSchema));
		    agDoc = builder.parse(fis);
		    // Create XPath
		    agXpath = xpathfactory.newXPath();
		    agXpath.setNamespaceContext(new NamespaceResolver(agDoc));
		    // get root XPath
		    nodes = getXPathNodes(doc,rootXPath);
	        countNodes = nodes.size();
	        if (countNodes > 0) {
	            for (i = 0; i < countNodes; i++) {
	            	node = nodes.get(i);
	            	attributes = node.getAttributes();
	            	if ("CII"==PROCESSING) {
		            	complexType = null;
		            	comment = null;
				        unid = null;
		            	for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
		            	    if (child.getNodeType() == Node.COMMENT_NODE) {
		            	        comment = (Comment) child;
		            	        unid = comment.getData();
		            	    }
		            	}
		            	if (null==comment) {
			        		List<Node> commentElements = getXPathNodes(node,"xsd:annotation/xsd:documentation/ccts:UniqueID");
			        		if (commentElements.size()>0) {
			        			Node commentNode = commentElements.get(0);
			        			unid = commentNode.getTextContent();
			        		}
		            	}
	            		name = attributes.getNamedItem("name").getNodeValue();
	            		type = attributes.getNamedItem("type").getNodeValue();
	            		if ("ram:".equals(type.substring(0,4))) {
		            		complexType = type.substring(4);
		            		type = null;
		            	}
		            	String _xPath = "rsm:"+name;
		            	xPathCounter.add(unid+" "+_xPath);
		            	System.out.println(xPathCounter.size()+" "+unid+" "+_xPath);
		            	if (complexType!=null) {
		            		checkComplexType(_xPath,complexType);
		            	}
	            	} else if ("UBL"==PROCESSING) {
	            		ref = attributes.getNamedItem("ref").getNodeValue();
	            		name = ref.substring(4);
	            		if (jp_pint_XPath.contains(ref)) {
		    		        xPathCounter.add(ref);
		    		        if ("ext:".equals(ref.substring(0,4)) ||
	    	            			"cbc:".equals(ref.substring(0,4))) {
	    	            		System.out.println(xPathCounter.size()+" "+ref);
	    	            	} else if ("cac:".equals(ref.substring(0,4))) {
		            			String elementXPath = "/xsd:schema/xsd:element[@name='"+name +"']";
		            			elements = getXPathNodes(agDoc,elementXPath);
		            			int countElements = elements.size();
		            		    if (countElements > 0) {
		            		    	element = elements.get(0);
		            		    	complexType = element.getAttributes().getNamedItem("type").getNodeValue();
		    		            	System.out.println(xPathCounter.size()+" "+ref);
		    		            	if (complexType!=null) {
		    		            		checkComplexType(ref,complexType);
		    		            	}
		            		    }
	    	            	}
	            		}
    	            }
	            }
	        }
	        System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return xPathCounter;
	}

	/**
	 * @param xPath
	 * @param parentType
	 */
	private static void checkComplexType(
			String xPath,
			String parentType) {
		int countNodes;
		int countElements,countChildElements;
		int i;
		String name = null;
		String ref = null;
		String type = null;
		String complexType = null;
		String unid = "";
		List<Node> nodes;
		List<Node> elements,childElements;
		Node node;
		Node element,childElement;
		NamedNodeMap attributes;
		String typeXPath = "/xsd:schema/xsd:complexType[@name='"+parentType +"']";
		nodes = getXPathNodes(agDoc,typeXPath);
	    countNodes = nodes.size();
	    if (countNodes > 0) {
        	node = nodes.get(0);
            elements = getXPathNodes(node,"xsd:sequence/xsd:element");
        	countElements = elements.size();
        	for (i = 0; i < countElements; i++) {
            	name = null;
            	type = null;
            	complexType = null;
	            Comment comment = null;
            	String _xPath = null;
            	element = elements.get(i);
            	attributes = element.getAttributes();
	            if ("CII"==PROCESSING) {
	            	for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
	            	    if (child.getNodeType() == Node.COMMENT_NODE) {
	            	        comment = (Comment) child;
	            	        unid = comment.getData();
	            	    }
	            	}
	            	if (null==comment) {
		        		List<Node> commentElements = getXPathNodes(element,"xsd:annotation/xsd:documentation/ccts:UniqueID");
		        		if (commentElements.size()>0) {
		        			Node commentNode = commentElements.get(0);
		        			unid = commentNode.getTextContent();
		        		}
	            	}
            		name = attributes.getNamedItem("name").getNodeValue();
            		type = attributes.getNamedItem("type").getNodeValue();
            		if ("ram:".equals(type.substring(0,4))) {
	            		complexType = type.substring(4);
	            		type = null;
	            	}
              		if (name.indexOf("ProcuringProject")>0) {
		            	System.out.println("\tProcuringProject:"+unid+" "+"/ram:"+name+" in "+xPath);
	            	} else if ((xPath+"/").indexOf("/ram:"+name+"/")>0) {
		            	System.out.println("\trecursive "+unid+" "+"/ram:"+name+" in "+xPath);
	            	} else {
		            	_xPath = xPath+"/ram:"+name;
		            	xPathCounter.add(unid+" "+_xPath);
		            	System.out.println(xPathCounter.size()+" "+unid+" "+_xPath);
		            	if (complexType!=null) {
		            		checkComplexType(_xPath,complexType);
		            	}
	            	}
            	} else if ("UBL"==PROCESSING) {
            		ref = attributes.getNamedItem("ref").getNodeValue();
            		if (null!=ref) {
            			if ("cac:".equals(ref.substring(0,4)) && (xPath+"/").indexOf(ref+"/")>0) {
    		            	System.out.println("\trecursive "+ref+" in "+xPath);
    	            	} else {
		            		name = ref.substring(4);
		            		_xPath = xPath+"/"+ref;
		            		if (jp_pint_XPath.contains(_xPath)) {
			    		        xPathCounter.add(_xPath);
			    		        System.out.println(xPathCounter.size()+" "+_xPath);
			    		        if ("cac:".equals(ref.substring(0,4))) {
			            			String elementXPath = "/xsd:schema/xsd:element[@name='"+name +"']";
			            			childElements = getXPathNodes(agDoc,elementXPath);
			            			countChildElements = childElements.size();
			            		    if (countChildElements > 0) {
			            		    	childElement = childElements.get(0);
			            		    	complexType = childElement.getAttributes().getNamedItem("type").getNodeValue();
			    		            	if (complexType!=null) {
			    		            		if (jp_pint_XPath.contains(_xPath)) {
				    		            		checkComplexType(_xPath,complexType);
			    		            		}
			    		            	}
			            		    }
				            	}
		            		}
    	            	}
            		}
            	}
	        }
	    }
	}
	
    public static void fillXPath(String infile) {
    	File file = new File(infile);
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String xPath;
    		while ((xPath = br.readLine()) != null) {
    			jp_pint_XPath0.add(xPath);
    			String path = xPath.replaceAll("\\[.+\\]","");
    			path = path.replaceAll("/Invoice/", "");
    			System.out.println(path);
    			jp_pint_XPath.add(path);
    		}
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void updateXPath() {
    	TreeMap<
    			Integer/*sequence*/,
    			String/*xpath*/>
    		pathCounter = new TreeMap<>();
    	TreeMap<
    			String/*selector*/,
    			TreeMap</*pathCounter*/
    				Integer/*sequence*/,
    				String/*xpath*/>>
    		selectorXPathCounter = new TreeMap<>();
    	TreeMap<
    			Integer/*sequence of base*/, 
    			TreeMap</*selectorXPathCounter*/
    				String/*selector*/,
    				TreeMap</*pathCounter*/
    					Integer/*sequence*/,
    					String/*xpath*/>>>
    		baseXPathCounter = new TreeMap<>();
    	int i;
		int last = 0;
    	Integer current=0;
    	int size = jp_pint_XPath0.size();
    	for (i = 0; i < size; i++) {
    		String xpath = jp_pint_XPath0.get(i);
    		if ("/Invoice".equals(xpath)) {
    			continue;
    		}
    		xpath = xpath.replace("/Invoice/","");
    		Integer index = 0;
    		String sequence = null;
    		String selector = null;
    		String base = null;
    		int start = xpath.indexOf("[");
    		int end = 0;
    		if (start > 0) {
    			end = xpath.indexOf("]")+1;
    		}
    		if (end > 0) {
    			base = xpath.substring(0,start);
    			selector = xpath.substring(start,end);
    			String _xpath = xpath.replaceAll("\\[.+\\]","");
	    		index = xPathCounter.indexOf(_xpath);
	    		if (-1==index) {
	    			continue;
	    		}
	    		if (xpath.indexOf("ChargeIndicator")>0) {
	    			System.out.println(index+" "+selector+" "+_xpath+"\n\t"+xpath);
	    		}
	    		current = index;
    		} else {
    			selector = "[]";
    			index = xPathCounter.indexOf(xpath);
				if ("cbc:".equals(xpath.substring(0,4))) {
					base = "Invoice";			
				} else if (xpath.length() >= 15 && "cac:InvoiceLine".equals(xpath.substring(0,15))) {
					base = "cac:InvoiceLine";
				} else if ("cac:".equals(xpath.substring(0,4))) {
					int loc = xpath.indexOf("/");
					if (loc > 0) {
						base = xpath.substring(0,loc);
					} else {
						base = xpath;
					}
				}
    		}
    		int seqBase = 0;
    		if (xPathCounter.contains(base)) {
    			seqBase = xPathCounter.indexOf(base);
    		} else {
    			seqBase = -1;
    		}    			
			if (baseXPathCounter.containsKey(seqBase)) {
				selectorXPathCounter = baseXPathCounter.get(seqBase);
			} else {
				selectorXPathCounter = new TreeMap<>();
			}
			if (selectorXPathCounter.containsKey(selector)) {
				pathCounter = selectorXPathCounter.get(selector);
			} else {
				pathCounter = new TreeMap<>();
			}
			pathCounter.put(index, xpath);
			System.out.println(pathCounter.toString());    					
			selectorXPathCounter.put(selector, pathCounter);
//			System.out.println(selectorXPathCounter.toString());    					
			baseXPathCounter.put(seqBase, selectorXPathCounter);
//			System.out.println(baseXPathCounter.toString());
    	}
    	
    	TreeMap<String,TreeMap<Integer, String>> _selectorXPathCounter = null;
    	TreeMap<Integer, String> _pathCounter = null;
    	TreeMap<String,TreeMap<Integer, String>> selectorXPathCounter0 = baseXPathCounter.get(-1);
    	TreeMap<Integer, String> pathCounter0 = selectorXPathCounter0.get("[]");
    	TreeMap<String,TreeMap<Integer, String>> selectorXPathCounter1 = baseXPathCounter.get(208);
    	TreeMap<Integer, String> pathCounter1 = selectorXPathCounter1.get("[]");
   	
    	List<String> _xPathCounter = new ArrayList<>();
     	String selector, path="";
    	for (Integer num = 0; num < xPathCounter.size(); num++) {
   			if (baseXPathCounter.containsKey(num)) {
    			_selectorXPathCounter = baseXPathCounter.get(num);
    			Iterator<String> itSel = _selectorXPathCounter.keySet().iterator();
            	while(itSel.hasNext()) {
            		selector = itSel.next();
    	        	_pathCounter = _selectorXPathCounter.get(selector);
    	        	Iterator<Integer> itSeq = _pathCounter.keySet().iterator();
    	        	while (itSeq.hasNext()) {
    	        		Integer n = itSeq.next();
    	        		if (-1==n) {
    	        			continue;
    	        		}
    	        		path = _pathCounter.get(n);
    	        		_xPathCounter.add(path);
    	        		System.out.println(_xPathCounter.size()+" "+num +" "+n+" "+path);
			    		int loc = jp_pint_XPath0.indexOf("/Invoice/"+path)+1;
			    		String _path = jp_pint_XPath0.get(loc).replaceAll("/Invoice/","");
			    		boolean isAttribute = _path.indexOf(path)==0 && "/@".equals("/"+_path.charAt(_path.lastIndexOf("/")+1));
			    		while (isAttribute) {
			    			_xPathCounter.add(_path);
					    	System.out.println("\t"+_path);
			    			if (loc>=jp_pint_XPath0.size()-1) {
			    				isAttribute = false;
			    			} else {
				    			_path = jp_pint_XPath0.get(loc++);
				    			isAttribute =  _path.indexOf(path)==0 && "/@".equals("/"+_path.charAt(_path.lastIndexOf("/")+1));
			    			}
			    		}
    	        	}
            	}
    		} else {
    			if (pathCounter0.containsKey(num)) { // Invoice
	    			path = pathCounter0.get(num);
	        		_xPathCounter.add(path);
	        		System.out.println(num +" "+path);
    			} else if (pathCounter1.containsKey(num)) { // InvoiceLine
	    			path = pathCounter1.get(num);
	        		_xPathCounter.add(path);
	        		System.out.println(_xPathCounter.size()+" "+num +" "+path);
    			}
    		}
    	}
    	xPathCounter = _xPathCounter;
//    	System.out.println(baseXPathCounter.toString());
    }
}