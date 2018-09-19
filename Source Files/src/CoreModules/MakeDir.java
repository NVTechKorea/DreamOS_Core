package CoreModules;
// Declaration:
// MakeDir version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import java.io.*;
import CoreServices.ErrorAnalyzer;
public class MakeDir{
	public MakeDir(){}
	public void initiate(String path){
		String process = "Making directory: " + path;
		File file = null;
		try{
			file = new File(path);
			file.mkdir();
		}catch(Exception e){
			System.out.println("ERROR [MAKEDIR]");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}
	}
}