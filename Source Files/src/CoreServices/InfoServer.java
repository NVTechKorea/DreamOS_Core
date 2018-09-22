package CoreServices;

import java.util.Scanner;

// Module Code: system.dreampackage.infoserver
public class InfoServer{
	public InfoServer(){}
	public String getVersion(){
		done();
		return "Prototype-10";
	}
	public String getSystemName(){
		done();
		return "DreamOS";
	}
	public double getVersionInDouble(){
		done();
		return 0.10;
	}
	public String getPath(){
		String path = System.getProperty("user.home") + getDirectoryIdentifier() + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
		done();
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
		return path;
	}
	public String getCertainPath(String s) {
		String path = System.getProperty("user.home") + getDirectoryIdentifier() + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
		done();
		if(s.equals("system")) {
			path = path + "system" + getDirectoryIdentifier();
		}else if(s.equals("data")) {
			path = path + "data" + getDirectoryIdentifier();
		}else if(s.equals("var")) {
			path = path + "var" + getDirectoryIdentifier();
		}else if(s.equals("installer")) {
			path = getCertainPath("system" )+ "dneo.installer" + getDirectoryIdentifier();
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
	public void done() {
		//System.out.println("InfoServerd [INFO]: Information is delivered.");
	}
	 public boolean checkConnection(String process) {
	    	System.out.println("InfoServerd [NOTIFY]: Process " + process + " is requesting system information. Type yes to authorize, type n to reject.");
	    	Scanner input = new Scanner(System.in);
	    	String i = input.nextLine();
	    	input.close();
	    	if(i.equals("yes")) {
	    		return true;
	    	}else {
	    		return false;
	    	}
	 }
}