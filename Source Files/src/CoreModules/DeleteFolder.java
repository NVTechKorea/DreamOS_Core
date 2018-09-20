package CoreModules;
// Declaration:
// DeleteFile version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import java.io.File;
import java.util.Scanner;
public class DeleteFolder{
	public DeleteFolder(){}
	public void initiate(String path){
		File file = new File(path);
		File[] list = file.listFiles();
		for(int a=0; a<list.length; a++){
			if(list[a].isDirectory()){
				System.out.println("There is a directory inside this directory.");
			}else{
				if(list[a].delete()){
				}else{
					System.out.println("Deleting failure: " + path);
				}
			}
		}
		if(file.delete()){}else{System.out.println("Deleting failure: " + path);}
	}
	 public boolean checkConnection(String process) {
	    	System.out.println("DeleteFolder [NOTIFY]: Process " + process + " is trying to access Writing permission. Type yes to authorize, type n to reject.");
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