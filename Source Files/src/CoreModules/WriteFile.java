package CoreModules;
// Declaration:
// WriteFile version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import java.io.*;
import CoreServices.ErrorAnalyzer;
public class WriteFile{
	public WriteFile(){}
	public void initiate(String path, String contents){
		String process = "Writing file: " + path;
		Writer writer = null;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			writer.write(contents);
		}catch(Exception e){
			System.out.println("ERROR [WRITER]");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}finally {
			try {
				writer.close();
			} catch (Exception ex) {
				System.out.println("ERROR");
			}
		}
	}
}