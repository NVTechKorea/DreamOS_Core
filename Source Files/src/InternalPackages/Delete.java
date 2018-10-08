package InternalPackages;
import CoreModules.DeleteFile;
import CoreModules.DeleteFolder;
public class Delete {
	public void init(String[] arg) {
		if(arg.length==2) {
			DeleteFile df = new DeleteFile();
			df.initiate(arg[1], false);
		}else if(arg.length==3) {
			if(arg[1].equals("-d")) {
				DeleteFolder ddir = new DeleteFolder();
				ddir.initiate(arg[1]);
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
