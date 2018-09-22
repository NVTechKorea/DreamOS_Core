package CoreModules;
// Declaration:
// MakeDir version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import java.io.*;
import java.util.Scanner;

import CoreServices.ErrorAnalyzer;
public class MakeDir{
	public MakeDir(){}
	public void initiate(String path){
		String process = "Making directory: " + path;
		File file = null;
		try{
			file = new File(path);
			file.mkdir();
			if(!file.isDirectory()) {
				System.out.println("MAKEDIR [ERROR]: There was a problem while making the directory. Path: " + path);
			}
		}catch(Exception e){
			System.out.println("MAKEDIR [ERROR]: " + e.toString());
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}
	}
	 public boolean checkConnection(String process) {
	    	System.out.println("MAKEDIR [NOTIFY]: Process " + process + " is trying to access Writing permission. Type yes to authorize, type n to reject.");
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