package CoreServices;

public class PathProvider {
	//Libraries
	static String rootfs = "";
	static String dirid = "";
	static String infdir = "";
	
	//ALS
	static String alslogintoken = "";
	static String alsfilecryptoken = "";
	
	//Files - Flag
	static String flagSetupDone = "";
	static String flagOverLimit = "";
	
	//Files - Info
	static String verinf = "";
	static String buldinf = "";
	static String manuinf = "";
	static String nameinf = "";

	public static void register() {
		rootfs = InfoStorage.rootfs;
		dirid = InfoStorage.dirid;
		infdir = rootfs + "System" + dirid + "inf";
	}
	public static void write() {
		
	}
}
