package CoreServices;
import CoreFramework.InfoServer;
import CoreModules.*;
public class Installer {
	DeleteFile df = new DeleteFile();
	DeleteFolder rm = new DeleteFolder();
	DownloadHelper dh = new DownloadHelper();
	MakeDir md = new MakeDir();
	ReadFile rf = new ReadFile();
	WriteFile wf = new WriteFile();
	InfoServer infod = new InfoServer();
	public void init() {
		print("Entered installer.");
		print("Setting up storage...");
		InfoServer infod = new InfoServer();
		setupStorage(infod);
		setupFiles(infod);
		print("Setup complete.");
	}
	public void setupStorage(InfoServer infod) {
		MakeDir makeDir = new MakeDir();
		String path = infod.getUserDirectory() + infod.getDirectoryIdentifier() + infod.getManufacturer();
		makeDir.initiate(path);
		path = path + infod.getDirectoryIdentifier() + infod.getSystemName();
		makeDir.initiate(path);
		path = path + infod.getDirectoryIdentifier() + infod.getVersion();
		makeDir.initiate(path);
		path = infod.getCertainPath("var");
		makeDir.initiate(path);
		path = infod.getCertainPath("system");
		makeDir.initiate(path);
		path = infod.getCertainPath("data");
		makeDir.initiate(path);
		path = infod.getCertainPath("installer");
		makeDir.initiate(path);
		path = infod.getCertainPath("als");
		makeDir.initiate(path);
	}
	public void setupFiles(InfoServer infod) {
		wf.initiate(infod.getPathFileLocation(), infod.getDefaultPath());
		wf.initiate(infod.getCertainFile("syspref_debug"), "false");
		wf.initiate(infod.getCertainFile("userpref_checkForUpdate"), "true");
		wf.initiate(infod.getCertainFile("syspref_lockSystemPart"), "true");
	}
	public static void print(String s) {
		System.out.println("Installer [INFO]: " + s);
	}
}
