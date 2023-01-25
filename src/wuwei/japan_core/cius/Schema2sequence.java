package wuwei.japan_core.cius;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Node;

import wuwei.japan_core.utils.CSV;

/**
 * デジタルインボイスのXMLインスタンス文書を読み取り、Tidy dataを格納しているCSVファイルを出力する.
 */
public class Schema2sequence {
	static String UBL_XML_SCHEMA = "UBL-2.1/xsd/maindoc/UBL-Invoice-2.1.xsd";
	static String UBL_ROOT_TYPE = "InvoiceType";
	static String UBL_OUT_CSV = "CIUS/data/csv/UBL-Invoice-2.1.csv";
	
	static String CII_XML_SCHEMA = "SMEinvoice/SME/schema/data/standard/SMEinvoice.xsd";
	static String CII_ROOT_TYPE = "SMEinvoiceType";
	static String CII_OUT_CSV = "CIUS/data/csv/SMEinvoice_ram.csv";
	
	static String JP_PINT_XPATH = "CIUS/data/base/JP_PINT_XPath.txt";
	
	static String PROCESSING;
	
	static String CHARSET = "UTF-8";

//	/**
//	 * XML SchemaのcomplexTyleのsequenceを指定する索引データ
//	 */
//    static HashMap<String/* complex */,String/* element */> sequenceMap = new HashMap<>();

	/**
 	 * mainでは変換処理を単体でテストする.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	PROCESSING = "UBL";
    	FileHandler.PROCESSING = PROCESSING;
    	if ("UBL"==PROCESSING) {
    		processSchema(UBL_XML_SCHEMA, UBL_ROOT_TYPE, UBL_OUT_CSV);
    	} else if ("CII"==PROCESSING) {
    		processSchema(CII_XML_SCHEMA, CII_ROOT_TYPE, CII_OUT_CSV);
    	}
		System.out.println("** END Schema2sequence**");
	}
    
	/**
	 * XML Schemaを読み込んで CSVファイルに出力する.
	 * 
	 * @param in_xml XMLスキーマ.
	 * @param root_type スキーマのルート型.
	 * @param out_csv complexTypeのXPathのsequenceのCSV(RFC4180形式)ファイル.
	 */
	private static void processSchema(String in_xml, String root_type, String out_csv) {
		String charset = "UTF-8";
//	    List<String> xPathCounter = FileHandler.xPathCounter;
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		
		System.out.println("** processInvoice\n\t"+in_xml+"\n\t"+root_type+"\n\t"+out_csv);

		try {
			FileHandler.fillXPath(JP_PINT_XPATH); // FileHandler.jp_pint_XPath
			
			FileHandler.parseSchema(in_xml,root_type); // FileHandler.xPathCounter
			
			FileHandler.updateXPath();
			
			System.out.println("- FileHandler.csvFileWrite " + out_csv + " " + charset);
			FileOutputStream fileOutputStream = new FileOutputStream(out_csv);
			// header
			ArrayList<String> header = null;
			if ("CII"==PROCESSING) {
				header = new ArrayList<>(Arrays.asList("seq", "UNID", "XPath"));
			} else if ("UBL"==PROCESSING) {
				header = new ArrayList<>(Arrays.asList("seq", "XPath"));
			}
			data.add(header);
			// row
			for (int i = 0; i < FileHandler.xPathCounter.size(); i++) {
				String strI = ""+i;
				String xPath = FileHandler.xPathCounter.get(i);
				ArrayList<String> row = null;
				if ("CII"==PROCESSING) {
					String unid = xPath.substring(0,10);
					xPath = xPath.substring(11);
					row = new ArrayList<>(Arrays.asList(strI, unid, xPath));
				} else if ("UBL"==PROCESSING) {
					row = new ArrayList<>(Arrays.asList(strI, xPath));
				}
				data.add(row);
			}
			CSV.writeFile(fileOutputStream, data, charset);
	        fileOutputStream.close();  
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}        
		System.out.println("-- END -- "+in_xml);
	}
}