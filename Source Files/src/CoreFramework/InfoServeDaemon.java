package CoreFramework;

import java.io.File;
import java.util.Scanner;

import CoreModules.ReadFile;

// Module Code: system.dreampackage.infoserver
public class InfoServeDaemon{
	public static String isdver = "1.0b1";
	public static String isdsig = "proper";
	Scanner input = new Scanner(System.in);
	public InfoServeDaemon(String process){
		if(!process.equals("bootloader")) {
			if(readPref()) {
				if(!process.equals("PreferenceLoader")) {
					System.out.println("InfoServeDaemon [NOTIFY]: Process " + process + " accessed to InfoServeDaemon Data.");
				}
			}
		}
	}
	public boolean readPref() {
		return LoadPreferences.loadUserConfigBool("notify");
	}
	public String getVersion(){
		done();
		return "Beta10_CoreOS";
	}
	public String getOS(){
		done();
		return System.getProperty("os.name");
	}
	public String getSystemName(){
		done();
		return "DreamOS";
	}
	public String getSystemBuild(){
		done();
		return "18A1009";
	}
	public double getVersionInDouble(){
		done();
		return 0.10;
	}
	public String getPath(){
		ReadFile rf = new ReadFile();
		String path = getDefaultPath();
		File pathloc = new File(getPathFileLocation());
		if(pathloc.exists()) {
			path = rf.initiate(path + "system" + getDirectoryIdentifier() + "path.scv");
		}
		return path;
	}
	public String getCertainFile(String s) {
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
	public String getCertainPath(String s) {
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
	public String getManufacturer(){
		done();
		return "DreamProjectGroup";
	}
	public String getDirectoryIdentifier(){
		done();
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			return "//";
		}else{
			return "/";
		}
	}
	public String getUserDirectory(){
		done();
		String s = System.getProperty("user.home").toString() + getDirectoryIdentifier().toString();
		return s;
	}
	public void done() {}
	public boolean checkConnection(String process) {
		System.out.println("InfoServeDaemon [NOTIFY]: Process " + process + " is requesting system information. Type yes to authorize, type n to reject.");
	    String i = input.nextLine();
	    if(i.equals("yes")) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	public String getDefaultPath() {
		String path = System.getProperty("user.home") + getDirectoryIdentifier() + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
		return path;
	}
	public String getPathFileLocation() {
		return getDefaultPath() + "system" + getDirectoryIdentifier() + "path.scv";
	}
}