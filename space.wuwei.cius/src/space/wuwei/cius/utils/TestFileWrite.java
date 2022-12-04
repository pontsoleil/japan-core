package space.wuwei.cius.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestFileWrite {
	public static void main(String[] args) {
		try {
//			Charset cs = Charset.forName("MS932");
			Charset cs = Charset.forName("SJIS");
			FileOutputStream fo = new FileOutputStream("output.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fo,cs);// StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(osw);
			String tmp = "あいうえお\r\nかきくけこ";
			bw.write(tmp);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
