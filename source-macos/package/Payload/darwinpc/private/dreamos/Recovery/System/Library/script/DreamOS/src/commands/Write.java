package commands;
import CoreModules.WriteFile;
public class Write {
	public void init(String[] arg, String decryptToken) {
		if(arg.length==3) {
			WriteFile wf = new WriteFile();
			wf.initiate(arg[1], arg[2], decryptToken);
		}else {
			print("Wrong syntax.");
		}
	}
	public void print(String s) {
		System.out.println(s);
	}
}
