package CoreServices;
import java.io.File;
public class InitialRun {
	public void init() {
		print("Runmanager entered.");
		InfoServer infod = new InfoServer();
		String path = infod.getCertainPath("var");
		path = path + "setupDone.flag";
		File variable = new File(path);
		if(!variable.exists()) {
			Installer installer = new Installer();
			installer.init();
		}
	}
	public static void print(String s) {
		System.out.println("Runmanager [INFO]: " + s);
	}
}
