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
		}else {
			return "FileNotFoundException";
		}
	}
}
