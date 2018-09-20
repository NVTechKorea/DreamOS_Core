package CoreServices;
import CoreModules.*;
import java.io.File;
public class Installer {
	DeleteFile df = new DeleteFile();
	DeleteFolder rm = new DeleteFolder();
	DownloadHelper dh = new DownloadHelper();
	MakeDir md = new MakeDir();
	ReadFile rf = new ReadFile();
	WriteFile wf = new WriteFile();
	InfoServer infod = new InfoServer();
	public void init() {
		print("Running installer!");
		checkDirectories();
	}
	public void checkDirectories() {
		String path = infod.getPath();
		File masterDir = new File(path);
		if(!masterDir.exists()) {
			masterDir.mkdirs();
		}
		String Datapath = infod.getCertainPath("data");
		File dataPart = new File(Datapath);
		if(!dataPart.exists()) {
			dataPart.mkdirs();
		}
		String system = infod.getCertainPath("system");
		File systemPart = new File(system);
		if(!systemPart.exists()) {
			systemPart.mkdirs();
		}
		String var = infod.getCertainPath("var");
		File varPart = new File(var);
		if(!varPart.exists()) {
			varPart.mkdirs();
		}
		
	}
	public static void print(String s) {
		System.out.println("Install [INFO]: " + s);
	}
}
