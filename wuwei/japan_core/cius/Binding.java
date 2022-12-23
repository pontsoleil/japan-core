package wuwei.japan_core.cius;

import java.util.HashSet;
import java.util.Set;

/**
 * セマンティックモデル定義と構文バインディング定義のための情報を保持するクラス
 *
 */
public class Binding {
	Integer semSort;
	String id;
	String level;
	String businessTerm;
	String card;
	String datatype;
	Integer synSort;
	String xPath;
	Set<String/*XPath*/> additionalXPath;
	String occur;
	boolean isUsed;
	
	/**
	 * 
	 * @param semSort
	 * @param id
	 * @param level
	 * @param businessTerm
	 * @param card
	 * @param datatype
	 * @param synSort
	 * @param xPath
	 * @param occur
	 */
	public Binding(Integer semSort, String id, String level, String businessTerm, String card, String datatype, Integer synSort, String xPath, String occur) {
		this.semSort = semSort;
		this.id = id;
		this.level = level;
		this.businessTerm = businessTerm;
		this.card = card;
		this.datatype = datatype;
		this.synSort = synSort;
		this.xPath = xPath;
		this.occur = occur;
		this.additionalXPath = new HashSet<String>();
		this.isUsed = false;
	}
	
	// getter
	public Integer getSemSort() { return this.semSort; }
	public String  getID() { return this.id; }
	public String  getLevel() { return this.level; }
	public String  getBT() { return this.businessTerm; }
	public String  getCard() { return this.card; }
	public String  getDatatype() { return this.datatype; }
	public Integer getSynSort() { return this.synSort; }
	public String  getXPath() { return this.xPath; }
	public Set<String> getAdditionalXPath() { return this.additionalXPath; }
	public String  getOccur() { return this.occur; }
	public boolean isUsed() { return this.isUsed; }
	
	// setter
	public Integer setSemSort(Integer x) { return this.semSort = x; }
	public String  setID(String x) { return this.id = x; }
	public String  setLevel(String x) { return this.level = x; }
	public String  setBT(String x) { return this.businessTerm = x; }
	public String  setCard(String x) { return this.card = x; }
	public String  setDatatype(String x) { return this.datatype = x; }
	public Integer setSynSort(Integer x) { return this.synSort = x; }
	public String  setXPath(String x) { return this.xPath = x; }
	public Set<String> addAdditionalXPath(String x) {
		additionalXPath.add(x);
		return this.additionalXPath;
	}
	public String  setOccur(String x) { return this.occur = x; }
	public boolean setUsed(boolean x) { return this.isUsed = x; }
	
}
