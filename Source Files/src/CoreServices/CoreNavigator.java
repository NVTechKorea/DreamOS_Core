package CoreServices;
import InternalPackages.*;
import InternalPackages.Shutdown;
import Security.*;

import java.io.File;

import CoreFramework.GetInput;
import CoreFramework.InfoServer;
import CoreFramework.LoadPreferences;
import CoreFramework.UpdateHandler;
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
	String currentDir = null;
	
	int permission = 2;
	
	boolean checkUpdate = true;
	boolean debug = false;
	boolean lockSystemPart = true;
	boolean notifyInfoServerAccess = true;
	
	public void startupNavigator(boolean disableSecurity) {
		prints("Navigator Started.");
		prints("Querying installed packages...");
		queryPackages(disableSecurity);
		decryptToken = alshield.init();
		prints("Getting current permission...");
		PermissionManager.scan(permission);
		prints("Getting file system ready...");
		prints("Starting InfoServer...");
		InfoServer infod = new InfoServer("navigator");
		prints("Accessing request.server...");
		prints("Getting file system data...");
		currentDir = infod.getCertainPath("data");
		prints("Hello. Welcome to DreamOS.");
	}
	public void userInterface(boolean security) {
		String command = "shutdown";
		GetInput input = new GetInput();
		for(;;) {
			System.out.print(">");
			command = input.init();
			if(command.equals("shutdown")) {
				Shutdown.init("");
				break;
			}else if(command.startsWith("help")) {
				help.init(command.split(" "));
			}else if(command.startsWith("cd")) {
				String[] commandParse = command.split(" ");
				currentDir = currentDir + commandParse[1];
			}else if(command.startsWith("mkdir")) {
				mkdir.init(command.split(" "));
			}else if(command.startsWith("list")) {
				
			}else if(command.startsWith("write")) {
				String[] commandParse = command.split(" ");
				if(commandParse.length==3) {
					commandParse[1] = currentDir + commandParse[1];
					write.init(commandParse, decryptToken);
				}
			}else if(command.startsWith("su")) {
				if(permission==0) {
					
				}else {
					print("You are not user@lev0!");
				}
			}else if(command.startsWith("rdpref")) {
				rdpref.init(command.split(" "));
			}else if(command.startsWith("chpref")) {
				boolean temp = chpref.init(command.split(" "));
				if(temp) {
					reloadPreferences();
				}
			}else if(command.equals("reloadpref")) {
				
			}else if(command.startsWith("debugOption.restartNavigator@localOS")) {
				String[] parse = command.split(" ");
				if(parse.length == 2) {
					if(parse[1].equals("--ALSGuard-disabled")) {
						init(false);
					}else if(parse[1].equals("--ALSGuard-enabled")) {
						init(true);
					}else {
						print("Error: parsing");
					}
				}else {
					print("Error: parsing");
				}
			}
		}
	}
	public void init(boolean disableSecurity) {
		startupNavigator(disableSecurity);
		reloadPreferences();
		checkUpdate();
		userInterface(disableSecurity);
	}
	public void checkUpdate() {
		if(checkUpdate) {
			UpdateHandler uh = new UpdateHandler();
			boolean success = uh.init();
			if(success) {
				Shutdown.init("downloadedUpdate");
			}
		}else {
			
		}
	}
	public void reloadPreferences() {
		checkUpdate = LoadPreferences.loadUserConfigBool("checkForUpdate");
		notifyInfoServerAccess = LoadPreferences.loadUserConfigBool("notifyInfoServerAccess");
		lockSystemPart = LoadPreferences.loadSystemConfigBool("lockSystemPart");
		debug = LoadPreferences.loadSystemConfigBool("debug");
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
		InfoServer infod = new InfoServer("DreamOS CoreNavigator");
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