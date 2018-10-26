package CoreServices;
import CoreFramework.CoreFilesUtility;
import CoreFramework.InfoServer;
import CoreModules.*;
import Security.TCEngine;
public class Installer {
	DeleteFile df = new DeleteFile();
	DeleteFolder rm = new DeleteFolder();
	DownloadHelper dh = new DownloadHelper();
	MakeDir md = new MakeDir();
	ReadFile rf = new ReadFile();
	WriteFile wf = new WriteFile();
	InfoServer infod = new InfoServer("Installer");
	String signCode = "serial:CBED2FD52CED6D52973A92C7AC393C83B9272A8BCAB0F4D0492DC33216A16CC1";
	static String staticSignCode = "serial:CBED2FD52CED6D52973A92C7AC393C83B9272A8BCAB0F4D0492DC33216A16CC1";
	public void init() {
		print("Entered installer.");
		print("Setting up storage...");
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
		wf.initiate(infod.getCertainFile("userpref_notifyInfoServerAccess"), "true");
		wf.initiate(infod.getCertainFile("syspref_lockSystemPart"), "true");
		CoreFilesUtility cfu = new CoreFilesUtility("InitialInstaller", true);
		String[] infoFiles = cfu.getFile("$allinfopath").split("<SPLIT>");
		wf.initiate(infoFiles[0], infod.getVersion());
		wf.initiate(infoFiles[2], infod.getSystemBuild());
		wf.initiate(infoFiles[3], infod.getVersionInDouble() + "");
		wf.initiate(infoFiles[1], infod.getSystemName());
		writeLockTicket();
	}
	public void writeLockTicket() {
		CoreFilesUtility cfu = new CoreFilesUtility("InitialInstaller", true);
		TCEngine tce = new TCEngine();
		tce.silence(true);
		wf.initiate(cfu.getFile("lockTicket.lock"), tce.encrypt(signCode, "digital"));
	}
	public static void onlyWriteLockTicket() {
		CoreFilesUtility cfu = new CoreFilesUtility("InitialInstaller", true);
		TCEngine tce = new TCEngine();
		tce.silence(true);
		WriteFile wwf = new WriteFile();
		wwf.initiate(cfu.getFile("lockTicket.lock"), tce.encrypt(staticSignCode, "digital"));
	}
	public static void print(String s) {
		System.out.println("Installer [INFO]: " + s);
	}
}
