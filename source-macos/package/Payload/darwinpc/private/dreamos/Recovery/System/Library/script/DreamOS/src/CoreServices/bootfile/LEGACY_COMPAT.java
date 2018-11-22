package CoreServices.bootfile;

public class LEGACY_COMPAT {
	static boolean pass = false;
	public static String load = "";
	public static boolean defaultload(String blob) {
		pass = true;
		load = "DEFAULT";
		return pass;
	}
	public static void reset() {
		pass = false;
		load = "";
	}
}
