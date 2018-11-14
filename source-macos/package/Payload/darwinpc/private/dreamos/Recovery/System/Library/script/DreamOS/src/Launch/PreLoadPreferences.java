package Launch;

import CoreModules.ReadFile;

public class PreLoadPreferences {
	static ReadFile rf = new ReadFile();
	public static String loadSystemConfig(String option) {
		String config = "NoSuchOption";
		if(option.equals("")) {
			
		}else if(option.equals("")) {
			
		}
		return config;
	}
	public static boolean loadSystemConfigBool(String option) {
		boolean config = true;
		if(option.equals("debug")) {
			if(rf.initiate(infod.getCertainFile("syspref_debug")).equals("true")){
				config = true;
			}else {
				config = false;
			}
		}else if(option.equals("lockSystemPart")) {
			if(rf.initiate(infod.getCertainFile("syspref_lockSystemPart")).equals("true")){
				config = true;
			}else {
				config = false;
			}
		}
		return config;
	}
	public static String loadUserConfig(String option) {
		String config = "NoSuchOption";
		if(option.equals("")) {

		}else if(option.equals("")) {
			
		}
		return config;
	}
	public static boolean loadUserConfigBool(String option) {
		boolean config = true;
		if(option.equals("checkForUpdate")) {
			if(rf.initiate(infod.getCertainFile("userpref_checkForUpdate")).equals("true")){
				config = true;
			}else {
				config = false;
			}
		}else if(option.equals("notifyInfoServerAccess")) {
			if(rf.initiate(infod.getCertainFile("userpref_notifyInfoServerAccess")).equals("true")){
				config = true;
			}else {
				config = false;
			}
		}
		return config;
	}

}
