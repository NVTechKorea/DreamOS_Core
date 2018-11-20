package Launch;
import CoreModules.ReadFile;
import CoreServices.InfoStorage;
public class InfoLoader {
	public static String version = "1.00";
	String rootfs = "";
	public void run() {
		ReadFile r = new ReadFile();
		readRootfs(r);
		readVersion(r);
		readBuild(r);
		readManu(r);
		readrw(r);
		readname(r);
		readDI(r);
	}
	public void readVersion(ReadFile r) {
		
	}
	public void readDI(ReadFile r) {
		
	}
	public void readBuild(ReadFile r) {
		
	}
	public void readManu(ReadFile r) {
		
	}
	public void readRootfs(ReadFile r) {
		rootfs = r.initiate(getDefaultPath() + "System" + getDirectoryIdentifier() + "inf" + getDirectoryIdentifier() + "rootfs");
		InfoStorage.rootfs = rootfs;
	}
	public void readrw(ReadFile r) {
		
	}
	public void readname(ReadFile r) {
		
	}
	public static String getDirectoryIdentifier(){
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			return "//";
		}else{
			return "/";
		}
	}
	public static String getDefaultPath() {
		String path = "";
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			System.out.println("[ERROR]: FATAL: UNSUPPORTED PARENT OS. ROOTFS IS NOT DEFINED.");
			System.exit(0);
		}else{
			path = "/private/dreamos/";
		}
		return path;
	}
}
