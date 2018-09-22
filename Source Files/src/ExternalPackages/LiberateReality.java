package ExternalPackages;
import CoreModules.ReadFile;
import CoreModules.WriteFile;
import CoreModules.MakeDir;
import CoreModules.DeleteFolder;
import CoreModules.DeleteFile;
import CoreServices.InfoServer;
// Module Code: user.dneo.Installer
public class LiberateReality{
	int permission = 501;
	String task = "SYSTEM_COMMAND:HOT_REBOOT";
	String path = null;
	String split = null;
	DeleteFile df = null;
	DeleteFolder dd = null;
	ReadFile rf = null;
	MakeDir md = null;
	WriteFile wf = null;
	InfoServer infoserver = null;
	public LiberateReality(){
		df = new DeleteFile();
		rf = new ReadFile();
		dd = new DeleteFolder();
		md = new MakeDir();
		wf = new WriteFile();
		infoserver = new InfoServer();
		path = infoserver.getCertainPath("installer");
	}
	public void init_var(int reg_permission){
		permission = reg_permission;
	}
	public String init(){
		print("Installer started.");
		print("Getting root status.");
		if(permission==755){
			task = run();
		}else{
			print("Stopping...");
			task = "APP_COMMAND:QUIT_user.dneo.Installer";
		}
		return task;
	}
	public String run() {
		String task = "SYSTEM_COMMAND:HOT_REBOOT";
		print("Reading installed tool information...");
		
		return task;
	}
	public String[] loadInstallerConfig() {
		String[] configArray;
		String temppath = path + "reg.mldy";
		String raw = rf.initiate(temppath);
		configArray = raw.split(";");
		return configArray;
	}
	public void removeInstaller() {
		print("Removing action started.");
		dd.initiate(path);
		md.initiate(path);
		String contents = "ERASER=0;INSTALLER=0;DPKG=0;LIBER_BOOT=0";
		wf.initiate(path + "reg.mldy", contents);
		print("Reloading...");
	}
	public static void print(String s){
		System.out.println("Installer [INFO]: " + s);
	}
}