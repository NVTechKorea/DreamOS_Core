package CoreModules;
// Declaration:
// DeleteFile version: Test Beta 9
// Package Build: 18B080552UD-TB8
// Copyright (C) Dream Project Group
import java.io.File;
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
}