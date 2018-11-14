package commands;
import CoreModules.ReadFile;
import CoreServices.LangManager;
import Security.TCEngine;

public class Read {
	ReadFile rf = null;
	public String init(String[] arg, String password, String currentDir) {
		rf = new ReadFile();
		if(arg.length == 3) {
			if(arg[1].equals("-raw")) {
				return readRaw(currentDir + arg[2]);
			}else {
				return "Argument error.";
			}
		}else if(arg.length == 2) {
			return read(currentDir + arg[1], password);
		}else {
			print(LangManager.init("UNIVERSAL_SYNTAX_ERROR"));
			return "";
		}
	}
	public String read(String path, String pass) {
		String temp = rf.initiate(path);
		TCEngine tce = new TCEngine();
		tce.silence(true);
		if(tce.decrypt(temp, pass).equals("")) {
			return "[SYSTEM]: Decrypt failure";
		}else{
			return tce.decrypt(temp, pass);
		}
	}
	public String readRaw(String path) {
		return rf.initiate(path);
	}
	public void print(String s) {
		System.out.println(s);
	}
}
