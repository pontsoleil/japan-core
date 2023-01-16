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
public class Schema2sequence {
	static String XML_SCHEMA = "SMEinvoice/SME/schema/data/standard/ReusableAggregateBusinessInformationEntity_31p0.xsd";
	static String ROOT_TYPE = "SMEinvoiceType";
	static String OUT_CSV = "CIUS/data/csv/RAM.csv";
	static String CHARSET = "UTF-8";

	/**
	 * XML SchemaのcomplexTyleのsequenceを指定する索引データ
	 */
    static TreeMap<String/* complex */,String/* element */> sequenceMap = new TreeMap<>();

	/**
 	 * mainでは変換処理を単体でテストする.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
		 processSchema(XML_SCHEMA, ROOT_TYPE, OUT_CSV);
		 System.out.println("** END Schema2sequence**");
	}
	
	/**
	 * XML Schemaを読み込んで sequenceMapに展開し、CSVファイルに出力する.
	 * 
	 * @param in_xml XMLスキーマ.
	 * @param out_csv complexTypeのXPathのsequenceのCSV(RFC4180形式)ファイル.
	 */
	private static void processSchema(String in_xml, String root_type, String out_csv) {
		String root_xpath = "/xsd:schema/xsd:complexType[@name='"+root_type+"')]/xsd:sequence/xsd:element";
	    sequenceMap = new TreeMap<String/* complex */,String/* element */>();

		System.out.println("** processInvoice("+in_xml+"\n"+root_xpath+"\n"+out_csv+")");

		try {
			FileHandler.parseSchema(in_xml);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		List<Node> nodes =FileHandler.getXPathNodes(FileHandler.root, root_xpath);

        Integer countNodes = nodes.size();             
        if (countNodes > 0) {
            for (int i = 0; i < countNodes; i++) {
            	Node node = nodes.get(i);
    			String value = node.getTextContent().trim();
    			System.out.println(value);
            }
        }
	    
        try {
			FileHandler.csvFileWrite(out_csv, CHARSET);
        } catch (FileNotFoundException fnf) {
        	System.out.println("* File Not Found Exception "+out_csv);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("-- END -- "+in_xml);
	}
	
}