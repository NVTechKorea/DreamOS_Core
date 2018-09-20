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
		String path = System.getProperty("user.home") + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
		done();
		return path;
	}
	public String getCertainPath(String s) {
		String path = System.getProperty("user.home") + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
		done();
		if(s.equals("system")) {
			path = path + "system" + getDirectoryIdentifier();
		}else if(s.equals("data")) {
			path = path + "data" + getDirectoryIdentifier();
		}else if(s.equals("var")) {
			path = path + "var" + getDirectoryIdentifier();
		}else if(s.equals("installer")) {
			path = path + "system" + getDirectoryIdentifier() + "dneo.installer" + getDirectoryIdentifier();
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
		return System.getProperty("user.home").toString();
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