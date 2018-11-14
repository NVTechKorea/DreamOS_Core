package Launch;

import java.io.File;

import CoreModules.ReadFile;

// Module Code: system.dreampackage.infoserver
public class BaseInfoStorageWriter{
	public static String isdver = "1.0b2";
	public static String isdsig = "proper";
	public static String getVersion(){
		done();
		return "Beta10_CoreOS";
	}
	public static String getOS(){
		done();
		return System.getProperty("os.name");
	}
	public static String getSystemName(){
		done();
		return "DreamOS";
	}
	public static String getSystemBuild(){
		done();
		return "18A1009";
	}
	public static double getVersionInDouble(){
		done();
		return 0.10;
	}
	public static String getPath(){
		ReadFile rf = new ReadFile();
		String path = getDefaultPath();
		File pathloc = new File(getPathFileLocation());
		if(!pathloc.exists()) {
			path = rf.initiate(path + "system" + getDirectoryIdentifier() + "rootfs");
		}
		return path;
	}
	public static String getCertainFile(String s) {
		String path = null;
		done();
		if(s.equals("uuid")) {
			path = getCertainPath("system") + "machine_uuid.aptic";
		}else if(s.equals("apticket")) {
			path = getCertainPath("system") + "authorizedBootTicket.aptic";
		}else if(s.equals("firstRunFlag")) {
			path = getCertainPath("var") + "setupDone.flag";
		}else if(s.equals("logintoken")) {
			path = getCertainPath("als") + "login_token.als";
		}else if(s.equals("loginrandomtoken")) {
			path = getCertainPath("als") + "random.als";
		}else if(s.equals("encryptortoken")) {
			path = getCertainPath("als") + "encryptFiles.als";
		}else if(s.equals("wide_permission")) {
			path = getCertainPath("var") + "permission.pms";
		}
		//Preference files
		else if(s.equals("syspref_debug")) {
			path = getCertainPath("var") + "debug.pref";
		}else if(s.equals("userpref_checkForUpdate")) {
			path = getCertainPath("var") + "checkForUpdate.pref";
		}else if(s.equals("userpref_notifyInfoServerAccess")) {
			path = getCertainPath("var") + "notifyInfoServerAccess.pref";
		}else if(s.equals("syspref_lockSystemPart")) {
			path = getCertainPath("var") + "lockSystemPart.pref";
		}
		return path;
	}
	public static String getCertainPath(String s) {
		String path = getPath();
		done();
		if(s.equals("system")) {
			path = path + "system" + getDirectoryIdentifier();
		}else if(s.equals("data")) {
			path = path + "data" + getDirectoryIdentifier();
		}else if(s.equals("var")) {
			path = path + "var" + getDirectoryIdentifier();
		}else if(s.equals("als")) {
			path = getCertainPath("system") + "absolutelevelshield" + getDirectoryIdentifier();
		}
		return path;
	}
	public static String getManufacturer(){
		done();
		return "DreamProjectGroup";
	}
	public static String getDirectoryIdentifier(){
		done();
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			return "//";
		}else{
			return "/";
		}
	}
	public static String getUserDirectory(){
		done();
		String s = System.getProperty("user.home").toString() + getDirectoryIdentifier().toString();
		return s;
	}
	public static void done() {}
	public static String getDefaultPath() {
		String path = "";
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			path = "";
		}else{
			path = "/private/dreamos/";
		}
		return path;
	}
	public static String getPathFileLocation() {
		return getDefaultPath() + "System" + getDirectoryIdentifier() + "inf" + getDirectoryIdentifier() + "rootfs";
	}
}