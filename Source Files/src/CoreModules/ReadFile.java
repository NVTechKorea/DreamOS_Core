package CoreModules;
// Declaration:
// ReadFile version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import CoreServices.ErrorAnalyzer;
import java.io.*;
import java.util.Scanner;
public class ReadFile{
	public ReadFile(){}
	@SuppressWarnings("resource")
	public String initiate(String path){
		String data = null;
		String process = "Reading file: " + path;
		try{
			File dir = new File(path);
			if(dir.isDirectory()){
				System.out.println("READER [ERROR]: Requested argument is not a file; is a directory.");
				data = " ";
			}else if(!dir.exists()){
				System.out.println("READER [ERROR]: Requested argument does not exist.");
				data = " ";
			}else{
				BufferedReader breader = null;
				breader = new BufferedReader(new FileReader(path));
				data = breader.readLine();
			}
		}catch(Exception e){
			System.out.println("READER [ERROR]");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}
		return data;
	}
	 public boolean checkConnection(String process) {
	    	System.out.println("READER [NOTIFY]: Process " + process + " is trying to access Read permission. Type yes to authorize, type n to reject.");
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