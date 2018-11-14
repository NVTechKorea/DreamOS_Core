package commands;
import Security.TCEngine;
import CoreModules.WriteFile;
import CoreServices.LangManager;
public class Write {
	public void init(String[] arg, String decryptToken) {
		if(arg.length==3) {
			TCEngine tce = new TCEngine();
			WriteFile wf = new WriteFile();
			tce.silence(true);
			wf.initiate(arg[1], tce.encrypt(arg[2], decryptToken));
		}else {
			print(LangManager.init("UNIVERSAL_SYNTAX_ERROR"));
		}
	}
	public void print(String s) {
		System.out.println(s);
	}
}
