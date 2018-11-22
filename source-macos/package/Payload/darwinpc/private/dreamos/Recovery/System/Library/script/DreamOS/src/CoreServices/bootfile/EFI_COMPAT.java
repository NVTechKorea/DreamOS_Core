package CoreServices.bootfile;

public class EFI_COMPAT {
	static boolean pass = false;
	public static String load = "";
	public static boolean defaultload() {
		pass = true;
		load = "DEFAULT";
		return pass;
	}
	public static boolean safemode() {
		pass = false;
		load = "SAFE";
		return pass;
	}
	public static void reset() {
		pass = false;
		load = "";
	}
}
