package CoreServices;
import InternalPackages.*;
import InternalPackages.Shutdown;
import Security.*;

import java.io.File;

import CoreFramework.GetInput;
import CoreFramework.InfoServer;
import CoreFramework.PanicHandler;
import CoreFramework.LoadPreferences;
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
	List list = null;
	PanicHandler ea = new PanicHandler();
	
	String decryptToken = null;
	String currentDir = null;
	
	int permission = 2;
	
	boolean checkUpdate = true;
	boolean debug = false;
	boolean lockSystemPart = true;
	boolean notifyInfoServerAccess = true;
	
	boolean superuser = false;
	
	public void startupNavigator(boolean disableSecurity) {
		try {
			prints("Navigator Started.");
			prints("Querying installed packages...");
			queryPackages(disableSecurity);
			decryptToken = alshield.init();
			prints("Getting file system ready...");
			prints("Starting InfoServer...");
			InfoServer infod = new InfoServer("navigator");
			prints("Accessing request.server...");
			prints("Getting current permission...");
			PermissionManager.scan(permission);
			readLockToken(infod);
			prints("Getting file system data...");
			currentDir = infod.getCertainPath("data");
			prints("Hello. Welcome to DreamOS.");
		}catch(Exception e) {
			ea.initiate(e, "StartupNavigator", false);
		}
	}
	public void readLockToken(InfoServer infod) {
		String presetString = "read lockTicket.lock";
		String[] presetArray = presetString.split(" ");
		String parsable = read.init(presetArray, "digital", infod.getCertainPath("system"));
		if(parsable.equals(PermissionManager.getSerial())) {
			superuser = false;
		}else {
			if(parsable.equals("serial:3C6753C48DAA370844B13B685856581F37A027D1843036C1FA472CC88392148D")) {
				superuser = true;
				prints("Firmware unlocked!! (3C6753C48DAA370844B13B685856581F37A027D1843036C1FA472CC88392148D)");
			}else {
				superuser = false;
			}
		}
	}
	public void userInterface(boolean security) {
		try {
			String command = "shutdown";
			GetInput input = new GetInput();
			for(;;) {
				System.out.print(">");
				command = input.init();
				if(command.equals("shutdown")) {
					Shutdown.init("");
					break;
				}else if(command.startsWith("help")) {
					help.init();
				}else if(command.startsWith("cd")) {
					currentDir = cd.init(command.split(" "), currentDir);
				}else if(command.startsWith("mkdir")) {
					mkdir.init(command.split(" "));
				}else if(command.equals("list")) {
					list.init(currentDir);
				}else if(command.startsWith("read")) {
					print(read.init(command.split(" "), decryptToken, currentDir));
				}else if(command.startsWith("write")) {
					String[] commandParse = command.split(" ");
					if(commandParse.length==3) {
						commandParse[1] = currentDir + commandParse[1];
						write.init(commandParse, decryptToken);
					}else {
						print("Syntax error.");
					}
				}else if(command.startsWith("su")) {
					if(permission==0) {
						
					}
				}else if(command.startsWith("delete")) {
					delete.init(command.split(" "), currentDir);
				}else if(command.startsWith("rdpref")) {
					rdpref.init(command.split(" "));
				}else if(command.startsWith("chpref")) {
					boolean temp = chpref.init(command.split(" "));
					if(temp) {
						reloadPreferences();
					}
				}else if(command.equals("reloadpref")) {
					reloadPreferences();
				}else if(command.equals("load_kernelinfo")) {
					print(SystemQuery.getDeepSystemInfo("kernel"));
				}else if(command.startsWith("exec")) {
					Exec.init(command.split(" "), currentDir);
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
		}catch(Exception e) {
			ea.initiate(e, "CoreUserInterface", false);
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
		list = new List();
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