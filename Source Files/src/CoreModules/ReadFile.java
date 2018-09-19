package CoreModules;
// Declaration:
// ReadFile version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import CoreServices.ErrorAnalyzer;
import java.io.*;
public class ReadFile{
	public ReadFile(){}
	@SuppressWarnings("resource")
	public String initiate(String path){
		String data = null;
		String process = "Reading file: " + path;
		try{
			File dir = new File(path);
			if(dir.isDirectory()){
				System.out.println("ERROR [READER]: Requested argument is not a file; is a directory.");
				data = " ";
			}else if(!dir.exists()){
				System.out.println("ERROR [READER]: Requested argument does not exist.");
				data = " ";
			}else{
				BufferedReader breader = null;
				breader = new BufferedReader(new FileReader(path));
				data = breader.readLine();
			}
		}catch(Exception e){
			System.out.println("ERROR [READER]");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}
		return data;
	}
}