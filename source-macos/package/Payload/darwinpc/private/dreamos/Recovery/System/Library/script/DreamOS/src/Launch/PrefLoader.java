package Launch;
import CoreModules.ReadFile;
import CoreServices.InfoStorage;
public class PrefLoader {
	public static String version = "1.00";
	String rootfs = "";
	String infodir = "";
	String directoryidentifier = "";
	public void run() {
		ReadFile r = new ReadFile();
		readRootfs(r);
		readDI(r);
		infodir = rootfs + "System" + directoryidentifier + "inf" + directoryidentifier;
		readVersion(r);
		readBuild(r);
		readManu(r);
		readrw(r);
		readname(r);
	}
	public void readVersion(ReadFile r) {
		InfoStorage.version = r.initiate(infodir + "version");
	}
	public void readDI(ReadFile r) {
		if(System.getProperty("os.name").toLowerCase().equals("windows")){
			InfoStorage.dirid = "//";
			directoryidentifier = "//";
		}else{
			InfoStorage.dirid = "/";
			directoryidentifier = "/";
		}
	}
	public void readBuild(ReadFile r) {
		InfoStorage.build = r.initiate(infodir + "build");
	}
	public void readManu(ReadFile r) {
		InfoStorage.manufacture = r.initiate(infodir + "manu");
	}
	public void readRootfs(ReadFile r) {
		rootfs = r.initiate(getDefaultPath() + "System" + getDirectoryIdentifierInternal() + "inf" + getDirectoryIdentifierInternal() + "rootfs");
		InfoStorage.rootfs = rootfs;
	}
	public void readrw(ReadFile r) {
		InfoStorage.rwpermission = r.initiate(infodir + "rw");
	}
	public void readname(ReadFile r) {
		InfoStorage.name = r.initiate(infodir + "name");
	}
	static String getDirectoryIdentifierInternal(){
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
