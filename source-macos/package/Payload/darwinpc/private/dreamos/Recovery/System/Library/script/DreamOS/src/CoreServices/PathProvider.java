package CoreServices;

public class PathProvider {
	static String rootfs = "";
	static String dirid = "";
	static String infdir = "";
	static String verinf = "";
	static String buldinf = "";
	static String manuinf = "";
	static String nameinf = "";
	static String alslogintoken = "";
	static String alsfilecryptoken = "";
	static String flagSetupDone = "";
	static String flagOverLimit = "";
	public static void register() {
		rootfs = InfoStorage.rootfs;
		dirid = InfoStorage.dirid;
		infdir = rootfs + "System" + dirid + "inf";
	}
}
