package InternalPackages;

import CoreModules.CheckFileExist;

public class Cd {
	public String init(String[] arg, String currentDir) {
		if(arg.length!=2) {
			print("Syntax error.");
			return currentDir;
		}else {
			CheckFileExist cfe = new CheckFileExist();
			if(cfe.init(currentDir + arg[1], false)) {
				return currentDir + arg[1];
			}else {
				print("No such directory.");
				return currentDir;
			}
		}
		
	}
	public void print(String s) {
		System.out.println(s);
	}
}
