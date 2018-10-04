package CoreServices;
import InternalPackages.*;
import InternalPackages.Shutdown;
import Security.*;

import java.io.File;

import CoreFramework.GetInput;
import CoreFramework.InfoServer;
import CoreModules.ReadFile;
import CoreModules.WriteFile;
public class CoreNavigator{
	Cd cd = null;
	Chpref chpref = null;
	Delete delete = null;
	Help help = null;
	Mkdir mkdir = null;
	Rdpref rdpref = null;
	Read read = null;
	Shutdown shutdown = null;
	Write write = null;
	TCEngine tce = null;
	ALShield alshield = new ALShield();
	
	String decryptToken = null;
	
	int permission = 2;
	
	public void init(boolean disableSecurity) {
		prints("Querying installed packages...");
		queryPackages(disableSecurity);
		decryptToken = alshield.init();
		print("Getting current permission...");
		PermissionManager.scan(permission);
		String command = "shutdown";
		prints(decryptToken);
		GetInput input = new GetInput();
		prints("Hello. Welcome to DreamOS.");
		for(;;) {
			System.out.print(">");
			command = input.init();
			if(command.equals("shutdown")) {
				Shutdown.init();
				break;
			}else if(command.startsWith("help")) {
				help.init(command.split(" "));
			}else if(command.startsWith("cd")) {
				cd.init(command.split(" "));
			}else if(command.startsWith("mkdir")) {
				mkdir.init(command.split(" "));
			}else if(command.startsWith("list")) {
				
			}else if(command.startsWith("write")) {
				write.init(command.split(" "));
			}else if(command.startsWith("su")) {
				if(permission==0) {
					
				}else {
					print("You are not user@lev0!");
				}
			}else if(command.startsWith("rdpref")) {
				rdpref.init(command.split(" "));
			}else if(command.startsWith("chpref")) {
				chpref.init(command.split(" "));
			}
		}
	}
	public void queryPackages(boolean b) {
		prints("Querying internal commands...");
		cd = new Cd();
		chpref = new Chpref();
		delete = new Delete();
		help = new Help();
		mkdir = new Mkdir();
		rdpref = new Rdpref();
		read = new Read();
		shutdown = new Shutdown();
		write = new Write();
		tce = new TCEngine();
		prints("Querying external commands...");
		if(b) {
			printw("System partition modification is enabled.");
		}
		prints("Querying complete.");
	}
	public String getExclude() {
		String excludeData = null;
		InfoServer infod = new InfoServer();
		String path = infod.getCertainPath("system") + "excluded.list";
		File excludeDataFile = new File(path);
		if(excludeDataFile.exists()) {
			ReadFile rf = new ReadFile();
			excludeData = rf.initiate(path);
		}else {
			WriteFile wf = new WriteFile();
			excludeData = "LiberateReality;R3Ality;L1B3r4Ti0N";
			wf.initiate(path, excludeData);
		}
		return excludeData;
	}
	public void print(String s) {
		System.out.println(s);
	}
	public void prints(String s) {
		System.out.println("Core [INFO]: " + s);
	}
	public void printw(String s) {
		System.out.println("Core [WARN]: " + s);
	}
}