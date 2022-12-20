package test.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.xpath.XPath;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import space.wuwei.cius.utils.Binding;
import space.wuwei.cius.utils.ParsedNode;

public class TestReadWrite {
	static String JP_PINT_CSV = "data/base/jp_pint.csv";
	static String JP_PINT_XML_SKELTON = "data/base/jp_pint_skeleton.xml";
	
	public static Document doc = null;
	public static XPath xpath = null;
	public static Element root = null;
	public static String ROOT_ID = "ibg-00";
	public static String[] MULTIPLE_ID = {"ibg-20", "ibg-21", "ibg-23", "ibg-25","ibg-27", "ibg-28"};
	public static HashMap<String, String> nsURIMap = null;
	
	public static ArrayList<String> header = new ArrayList<>();
    public static ArrayList<ArrayList<String>> tidyData = new ArrayList<>();
    
	public static Map<
		String/* id */,
		Binding> bindingDict = new HashMap<>();
	public static TreeMap<
		Integer/* semSort */, 
		Binding> semBindingMap = new TreeMap<>();
	static TreeMap<
		Integer/* synSort */, 
		Binding> synBindingMap = new TreeMap<>();
	public static TreeMap<
		Integer/* parent semSort */, 
		ArrayList<Integer/* child semSort */>> childMap = new TreeMap<>();
	public static TreeMap<
		Integer/* child semSort */, 
		Integer/* parent semSort */> parentMap = new TreeMap<>();
	public static TreeMap<
    	Integer/* semSort */, 
    	String/* id */> multipleMap = new TreeMap<>();
    public static TreeMap<
    	Integer/* semSort */, 
    	ParsedNode> nodeMap = new TreeMap<>();
    
	public static LinkedList<String> columns = new LinkedList<>();
	public static LinkedList<LinkedList<String>> data = new LinkedList<>();

    public static void main(String[] args) {
    	String IN_CSV = "data/csv/Example.csv";
    	String OUT_CSV = "data/csv/Example_out.csv";

    	try {
			csvFileRead(IN_CSV,"UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File not found "+IN_CSV);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
    		csvFileWrite(OUT_CSV,"UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File not found "+OUT_CSV);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	try {
			csvFileRead(OUT_CSV,"UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File not found "+OUT_CSV);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public static void csvFileWrite(String filename, String charset) throws FileNotFoundException, IOException {
		System.out.println("- csvFileWrite " + filename + " " + charset);
		FileOutputStream fo = new FileOutputStream(filename);
		Charset cs = Charset.forName(charset);
		OutputStreamWriter osw = new OutputStreamWriter(fo, cs);
		BufferedWriter bw = new BufferedWriter(osw);

		// data
		for (LinkedList<String> columns : data) {
			for (int i=0; i < columns.size(); i++) {
				String cell_value = columns.get(i);
				if (cell_value.indexOf("\"") != -1 || cell_value.indexOf(",") != -1) {
					cell_value = "\"" + cell_value.replaceAll("\"", "\"\"") + "\"";
				}
				bw.write(cell_value);
				if (i < columns.size() - 1) {
					bw.write(",");
				}
			}
			bw.write("\n");
		}

//		// header
//		for (String cell_value : header) {
//			if (cell_value.indexOf("\"") != -1 || cell_value.indexOf(",") != -1) {
//			    cell_value = "\""+cell_value.replaceAll("\"", "\"\"")+"\"";
//			}
//		}
//		String headerLine = String.join(",", header);
//		bw.write(headerLine);
//        bw.write("\n");
//        // data
//        for(int i=0; i < tidyData.size(); i++) {
//        	ArrayList<String> row = tidyData.get(i);
//    		for (String cell_value : row) {
//    			if (cell_value.indexOf("\"") != -1 || cell_value.indexOf(",") != -1) {
//    			    cell_value = "\""+cell_value.replaceAll("\"", "\"\"")+"\"";
//    			}
//    		}
//        	String line = String.join(",", row);
//            bw.write(line);
//            bw.write("\n");
//        }
		bw.close();
	}

	public static void csvFileRead(
			String filename, 
			String charset )
			throws
			FileNotFoundException,
			IOException
	{
		System.out.println("-- csvFileRead " + filename + " " + charset);
		header = new ArrayList<String>();
		tidyData = new ArrayList<ArrayList<String>>();
		FileInputStream fi = new FileInputStream(filename);
		
		read(fi,charset);
		
        fi.close();
	}

	
	
	// http://endeavour.cocolog-nifty.com/developer_room/2008/06/javacsv_793b.html
	//------------------------------------------------------------------
	/**
	 * CSVファイルの読み込み。
	 * @param stream 入力ストリーム。FileInputStream，ByteArrayInputStreamなど。
	 */
	public static void read(
			InputStream stream, 
			String charset )
	{
		InputStreamReader reader = null;
		BufferedReader buff = null;
		try {
			Charset cs = Charset.forName(charset);
			reader = new InputStreamReader(stream, cs);
			buff = new BufferedReader(reader);
			String record;
			int lineNum = 0;
			while ((record = buildRecord(buff)) != null) {
				lineNum++;
				if (record.length() <= 0)
					continue;
				if (record.startsWith("#"))
					continue;
				
				columns = splitRecord(record);
				
				if (columns.size() <= 0)
					continue;
				if (0 < columns.size()) {
					System.out.println("--"+lineNum);
					for (String cell : columns)
						System.out.print(cell+"|");
				}
				System.out.println("");
				data.add(columns);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			columns.clear();
			try {
				if (buff != null) {
					buff.close();
				}
				if (reader != null) {
					reader.close();
				}
				stream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * レコードの確定
	 * レコード確定では，入力テキストデータに対して，ダブルクォーテーション（二重引用符）のペアをヒントに各レコードの末尾を確定して，レコードの切り分けを行います。処理手順は以下のようになります。
	 * BufferedReaderのreadLineメソッドを使ってテキストを１行分（現在の位置から改行が現れるまで，またはファイルの終了まで）取り出して，行の先頭からダブルクォーテーションを探す。
	 * 見つからなければその１行を１レコードとして確定する。（readLineメソッドは「CR」「LF」「CRLF」を改行と認識するので，「CRLF以外の改行も考慮する」仕様の要求を満たしています。）
	 * ダブルクォーテーションが見つかった場合，ペアになる後ろのダブルクォーテーションを探す。後ろのダブルクォーテーションが見つかったらその位置から後続のダブルクォーテーションのペアを探す。
	 * この手順を行の終わりまで繰り返す。ダブルクォーテーションペアの外側で行が終了していれば，その行を１レコードとして確定する。
	 * ペアの後ろのダブルクォーテーションが見つからずにダブルクォーテーションペアの内側で改行に達したら，
	 * その改行を文字列フィールドに含まれる改行と見なしてBufferedReaderのreadLineメソッドより次の行を取り出して前の行と連結し，
	 * ペアの後ろのダブルクォーテーションを探すところから処理から継続する。これをダブルクォーテーションペアの外側で改行が見つかるまで繰り返す。
	 * 後ろのダブルクォーテーションが見つからずにファイルの末尾に達したときは，ファイルの末尾にダブルクォーテーションを付加して行の末尾とする。
	 * この連結した行を１レコードとして確定する。
	 */
	// ------------------------------------------------------------------
	/**
	 * BufferedReaderから1レコード分のテキストを取り出す。
	 * 
	 * @param reader 行データを取り出すBufferedReader。
	 * @return 1レコード分のテキスト。
	 * @throws IOException 入出力エラー
	 */
	public static String buildRecord(BufferedReader reader) throws IOException {
		String result = reader.readLine();
		int pos;
		if (result != null && 0 < result.length() && 0 <= (pos = result.indexOf("\""))) {
			boolean inString = true;
			String rawline = result;
			String newline = null;
			StringBuffer buff = new StringBuffer(1024);
			while (true) {
				while (0 <= (pos = rawline.indexOf("\"", ++pos))) {
					inString = !inString;
				}
				if (inString && (newline = reader.readLine()) != null) {
					buff.append(rawline);
					buff.append("\n");
					pos = -1;
					rawline = newline;
					continue;
				} else {
					if (inString || 0 < buff.length()) {
						buff.append(rawline);
						if (inString) {
							buff.append("\"");
						}
						result = buff.toString();
					}
					break;
				}
			}
		}
		return result;
	}

	/**
	 * レコードのフィールドへの分割
	 * フィールド分割では，レコードに切り分けたテキストに対して，最初にレコード全体をカンマで分割し，分割した個々の文字列にダブルクォーテーションをヒントに必要な連結やエスケープ処理を行って，
	 * 個々のフィールドを確定します。処理手順は以下のようになります。
	 * レコード全体をStringクラスのsplitメソッドを使ってカンマで分割し，分割した個々の文字列データを順に先頭からダブルクォーテーションを探す。見つからなければその文字列は１フィールドとして確定する。
	 * ダブルクォーテーションが見つかったら，次のダブルクォーテーションを探す。次のダブルクォーテーションの直後にダブルクォーテーションがあれば，エスケープされたダブルクォーテーションとして処理し，
	 * そうでなければフィールドの終わりと見なす。
	 * フィールドで後ろのダブルクォーテーションが見つからない場合，フィールドに含まれるカンマでsplitメソッドが分割したものと見なして，フィールドの後ろに（splitメソッドが削除した）カンマと次のフィールドを連結する。
	 * フィールドの開始と終了のダブルクォーテーションは削除する。
	 */
	/**
	 * 1レコード分のテキストを分割してフィールドの配列にする。
	 * 
	 * @param src  1レコード分のテキストデータ。
	 * @param dest フィールドの配列の出力先。
	 */
	public static LinkedList<String> splitRecord(String src) {
		LinkedList<String> dest = new LinkedList<>();
		String[] columns = src.split(",");
		int maxlen = columns.length;
		int startPos, endPos, columnlen;
		StringBuffer buff = new StringBuffer(1024);
		String column;
		boolean isInString, isEscaped;
		for (int index = 0; index < maxlen; index++) {
			column = columns[index];
			if ((endPos = column.indexOf("\"")) < 0) {
				dest.addLast(column);
			} else {
				isInString = (endPos == 0);
				isEscaped = false;
				columnlen = column.length();
				buff.setLength(0);
				startPos = (isInString) ? 1 : 0;
				while (startPos < columnlen) {
					if (0 <= (endPos = column.indexOf("\"", startPos))) {
						buff.append((startPos < endPos) ? column.substring(startPos, endPos) : isEscaped ? "\"" : "");
						isEscaped = !isEscaped;
						isInString = !isInString;
						startPos = ++endPos;
					} else {
						buff.append(column.substring(startPos));
						if (isInString && index < maxlen - 1) {
							column = columns[++index];
							columnlen = column.length();
							buff.append(",");
							startPos = 0;
						} else {
							break;
						}
					}
				}
				dest.addLast(buff.toString());
			}
		}
		return dest;
	}

}
