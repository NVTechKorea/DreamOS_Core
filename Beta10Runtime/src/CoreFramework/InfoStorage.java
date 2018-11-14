package CoreFramework;

public class InfoStorage{
	public static final String isdver = "1.0b2";
	public static final String isdsig = "proper";
	public static String version = "";
	public static String dir = "";
	public static String rootfs = "";
	public static String build = "";
	public static String manufacture = "";
	public static String partaddr_system = "";
	public static String partaddr_user = "";
	public void writeOnRandomAccessStorage() {
		version = InfoStorageWriter.getVersion();
		build = InfoStorageWriter.getSystemBuild();
		dir = InfoStorageWriter.getDirectoryIdentifier();
		rootfs = InfoStorageWriter.getPath();
		manufacture = InfoStorageWriter.getManufacturer();
		partaddr_system = InfoStorageWriter.getCertainPath(s);
	}
}