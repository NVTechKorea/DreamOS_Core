package CoreFramework;
public class CoreFilesUtility {
	public CoreFilesUtility(String process, boolean silence) {
		if(!silence){
			System.out.println("CFU [NOTIFY]: Process " + process + " accessed to core files.");
		}
	}
	public String getFile(String fileName) {
		InfoServer infod = new InfoServer("CoreFilesUtility");
		if(fileName.equals("updateReady.flag")) {
			return infod.getCertainPath("var") + "updateReady.flag";
		}else if(fileName.equals("lockTicket.lock")) {
			return infod.getCertainPath("system") + "lockTicket.lock";
		}else if(fileName.equals("version.info")) {
			return infod.getCertainPath("system") + "version.info";
		}else if(fileName.equals("build.info")) {
			return infod.getCertainPath("system") + "build.info";
		}else if(fileName.equals("versionNum.info")) {
			return infod.getCertainPath("system") + "versionNum.info";
		}else if(fileName.equals("sysname.info")) {
			return infod.getCertainPath("system") + "sysname.info";
		}else if(fileName.equals("$allinfopath")) {
			String dat = infod.getCertainPath("system") + "version.info" + "|||" + infod.getCertainPath("system") + "build.info" + "|||" + infod.getCertainPath("system") + "versionNum.info" + "|||" + infod.getCertainPath("system") + "sysname.info";
			return dat;
		}else {
			return "FileNotFoundException";
		}
	}
}
