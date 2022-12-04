package space.wuwei.cius.utils;

public class Binding {
	Integer semSort;
	String id;
	String level;
	String businessTerm;
	String card;
	String datatype;
	Integer synSort;
	String xPath;
	String occur;
	boolean isUsed;
	public Binding(Integer a, String b, String c, String d, String e, String f, Integer g, String h, String i) {
		semSort = a;
		id = b;
		level = c;
		businessTerm = d;
		card = e;
		datatype = f;
		synSort = g;
		xPath = h;
		occur = i;
		isUsed = false;
	}
	// getter
	public Integer getSemSort() { return semSort; }
	public String  getID() { return id; }
	public String  getLevel() { return level; }
	public String  getBT() { return businessTerm; }
	public String  getCard() { return card; }
	public String  getDatatype() { return datatype; }
	public Integer getSynSort() { return synSort; }
	public String  getXPath() { return xPath; }
	public String  getOccur() { return occur; }
	public boolean isUsed() { return isUsed; }
	// setter
	public Integer setSemSort(Integer x) { return semSort = x; }
	public String  setID(String x) { return id = x; }
	public String  setLevel(String x) { return level = x; }
	public String  setBT(String x) { return businessTerm = x; }
	public String  setCard(String x) { return card = x; }
	public String  setDatatype(String x) { return datatype = x; }
	public Integer setSynSort(Integer x) { return synSort = x; }
	public String  setXPath(String x) { return xPath = x; }
	public String  setOccur(String x) { return occur = x; }
	public boolean setUsed(boolean x) { return isUsed = x; }
}
