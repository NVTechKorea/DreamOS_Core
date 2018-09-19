package CoreServices;
// Module Code: system.dreampackage.infoserver
public class InfoServer{
	public InfoServer(){}
	public String getVersion(){
		return "Prototype-10";
	}
	public String getSystemName(){
		return "DreamOS";
	}
	public double getVersionInDouble(){
		return 0.10;
	}
	public String getPath(){
		String path = System.getProperty("user.home") + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
		return path;
	}
	public String getCertainPath(String s) {
		String path = System.getProperty("user.home") + getManufacturer() + getDirectoryIdentifier() + getSystemName() + getDirectoryIdentifier() + getVersion() + getDirectoryIdentifier();
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
		return "DreamProjectGroup";
	}
	public String getDirectoryIdentifier(){
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			return "//";
		}else{
			return "/";
		}
	}
	public String getUserDirectory(){
		return System.getProperty("user.home").toString();
	}
}