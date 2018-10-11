package InternalPackages;

import java.io.File;

public class List {
	public void init(String currentPath) {
		File target = new File(currentPath);
		File[] list = target.listFiles();
		String[] fileList = new String[list.length];
		for(int a=0; a<list.length; a++){
			if(list[a].isDirectory()){
				fileList[a] = "[DIR] " + list[a].getName();
			}else{
				fileList[a] = "[FILE] " + list[a].getName();
			}
			System.out.println(fileList[a]);
		}
	}
}
