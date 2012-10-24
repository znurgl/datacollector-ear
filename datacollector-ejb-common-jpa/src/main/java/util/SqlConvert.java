package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SqlConvert {
	public static void main(String[] args) {
		try {
			convert("create-ddl-jdbc.sql");
			convert("drop-ddl-jdbc.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void convert(String fn) throws IOException {
		File f = new File("sql\\" + fn);
		File w = new File("sql\\" + "runnable-" + fn);
		BufferedReader r = new BufferedReader(new FileReader(f));
		PrintWriter pr = new PrintWriter(w);
		String line = r.readLine();
		while (line != null) {
			pr.println(line + ";");
			pr.flush();
			line = r.readLine();
		}
		r.close();
		pr.close();
	}

}
