package CoreModules;
// Declaration:
// WriteFile version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import java.io.*;
import java.util.Scanner;

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
			System.out.println("Writer [ERROR]");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}finally {
			try {
				writer.close();
			} catch (Exception ex) {
				//System.out.println("ERROR");
			}
		}
	}
	 public boolean checkConnection(String process) {
	    	System.out.println("Writer [NOTIFY]: Process " + process + " is trying to access Writing permission. Type yes to authorize, type n to reject.");
	    	Scanner input = new Scanner(System.in);
	    	String i = input.nextLine();
	    	input.close();
	    	if(i.equals("yes")) {
	    		return true;
	    	}else {
	    		return false;
	    	}
	    }
}