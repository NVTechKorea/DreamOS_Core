package InternalPackages;
import CoreModules.DeleteFile;
import CoreModules.DeleteFolder;
public class Delete {
	public void init(String[] arg, String currentDir) {
		if(arg.length==2) {
			DeleteFile df = new DeleteFile();
			df.initiate(currentDir + arg[1], false);
		}else if(arg.length==3) {
			if(arg[1].equals("-d")) {
				DeleteFolder ddir = new DeleteFolder();
				ddir.initiate(currentDir + arg[1]);
			}else {
				print("No such arguments.");
			}
		}else {
			print("Parsing error.");
		}
	}
	public void print(String s) {
		System.out.println(s);
	}
}
