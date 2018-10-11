package InternalPackages;
import Security.TCEngine;
import CoreModules.WriteFile;
public class Write {
	public void init(String[] arg, String decryptToken) {
		if(arg.length==3) {
			TCEngine tce = new TCEngine();
			WriteFile wf = new WriteFile();
			tce.silence(true);
			wf.initiate(arg[1], tce.encrypt(arg[2], decryptToken));
		}else {
			print("Parsing error: ARRAY_LENGTH_NOT_3");
		}
	}
	public void print(String s) {
		System.out.println(s);
	}
}
