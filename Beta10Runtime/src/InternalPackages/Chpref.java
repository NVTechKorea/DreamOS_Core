package InternalPackages;

import CoreFramework.InfoServeDaemon;
import CoreModules.WriteFile;
import CoreServices.LangManager;

public class Chpref {
	public boolean init(String[] arg) {
		int leng = arg.length;
		boolean changed = false;
		if (leng == 3) {
			changed = chpref(arg[1], arg[2]);
		} else {
			print(LangManager.init("UNIVERSAL_SYNTAX_ERROR"));
			print("Usage: chpref [option name / all] [option]");
		}
		return changed;
	}

	public boolean chpref(String targetOption, String value) {
		boolean changed = false;
		InfoServeDaemon infod = new InfoServeDaemon("/system/InternalPackages/Chpref");
		WriteFile wf = new WriteFile();
		if (targetOption.equals("all")) {
			print("This option is only applied to boolean options.");
			if (value.equals("true")) {
				wf.initiate(infod.getCertainFile("userpref_checkForUpdate"), "true");
				changed = true;
			} else if (value.equals("false")) {
				wf.initiate(infod.getCertainFile("userpref_checkForUpdate"), "false");
				changed = true;
			} else {
				print("Boolean option cannot apply the following option: " + value);
			}
		} else if (targetOption.equals("checkForUpdate")) {
			if (value.equals("true")) {
				wf.initiate(infod.getCertainFile("userpref_checkForUpdate"), "true");
				changed = true;
			} else if (value.equals("false")) {
				wf.initiate(infod.getCertainFile("userpref_checkForUpdate"), "false");
				changed = true;
			} else {
				print("Boolean option cannot apply the following option: " + value);
			}
		} else {
			print("No such preference");
			print("Available preferences: ");
			print("(boolean) checkForUpdate");
		}
		return changed;
	}

	public void print(String s) {
		System.out.println(s);
	}
}
