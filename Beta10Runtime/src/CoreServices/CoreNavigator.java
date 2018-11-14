package CoreServices;
import InternalPackages.*;
import InternalPackages.Shutdown;
import Security.*;

import CoreFramework.GetInput;
import CoreFramework.InfoServeDaemon;
import CoreFramework.PanicHandler;
import CoreFramework.LoadPreferences;
import CoreModules.ReadFile;
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
	
	boolean runFromApp = false;
	boolean runReset = true;
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
			InfoServeDaemon infod = new InfoServeDaemon("navigator");
			prints("Accessing request.server...");
			prints("Getting current permission...");
			PermissionManager.scan(permission);
			readLockToken(infod);
			prints("Getting file system data...");
			currentDir = infod.getCertainPath("data");
			prints("Hello. Welcome to " + infod.getSystemName() + " " + infod.getVersion() + ".");
		}catch(Exception e) {
			ea.initiate(e, "StartupNavigator", false);
		}
	}
	public void readLockToken(InfoServeDaemon infod) {
		String presetString = "read lockTicket.lock";
		String[] presetArray = presetString.split(" ");
		String parsable = read.init(presetArray, "digital", infod.getCertainPath("system"));
		if(parsable.equals(PermissionManager.getSerial())) {
			superuser = false;
		}else {
			if(parsable.equals("serial:3C6753C48DAA370844B13B685856581F37A027D1843036C1FA472CC88392148D")) {
				superuser = true;
				prints("Firmware unlocked. (3C6753C48DAA370844B13B685856581F37A027D1843036C1FA472CC88392148D)");
			}else {
				superuser = false;
			}
		}
	}
	public void navigate(String command, boolean security) {
		if(command.equals("shutdown")) {
			Shutdown.init("");
		}else if(command.startsWith("help")) {
			help.init();
		}else if(command.startsWith("cd")) {
			currentDir = cd.init(command.split(" "), currentDir, security);
		}else if(command.startsWith("mkdir")) {
			mkdir.init(command.split(" "), currentDir);
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
			boolean saveData = false;
			if(command.contains("-save")) {
				saveData = true;
			}
			if(saveData) {
				String fd = Exec.init(command.split(" "), currentDir);
				if(fd.contains("runcmd:")) {
					String[] fdd = fd.split("runcmd:");
					command = fdd[1];
					runFromApp = true;
				}
			}else {
				Exec.init(command.split(" "), currentDir);
			}
		}else if(command.startsWith("debugOption.allowIncomingSignals@localOS")) {
			acceptIncomingSignals(security);
		}else if(command.startsWith("debugOption.changeGlobalVariables@localOS")) {
//			if(security) {
//				String[] parse = command.split(" ");
//				if(parse.length==3) {
//					if(parse[1].equals("runFromApp")) {
//						boolean mod = false;
//						if(parse[2].equals("true")) {
//							mod = true;
//						}else if(parse[2].equals("false")){
//							mod = false;
//						}else {
//							
//						}
//						
//					}else if(parse[1].equals("runReset")) {
//						
//					}else if(parse[1].equals("checkUpdate")) {
//						
//					}else if(parse[1].equals("debug")) {
//						
//					}else if(parse[1].equals("lockSystemPart")) {
//						
//					}else if(parse[1].equals("notifyInfoServerAccess")) {
//						
//					}else if(parse[1].equals("superuser")) {
//						
//					}else {
//						
//					}
//				}
//			}
		}else if(command.startsWith("debugOption.restartNavigator@localOS")) {
			if(runFromApp) {
				String[] parse = command.split(" ");
				if(parse.length == 2) {
					if(parse[1].equals("--ALSGuard-disabled")) {
						startupNavigator(false);
					}else if(parse[1].equals("--ALSGuard-enabled")) {
						startupNavigator(true);
					}else {
						print("Error: parsing");
					}
				}else {
					print("Error: parsing");
				}
			}else {
				print("Unknown Command.");
			}
		}else {
			print("Unknown Command.");
		}
	}
	
	public void userInterface(boolean security) {
		try {
			String command = "shutdown";
			GetInput input = new GetInput();
			for(;;) {
				if(!runFromApp) {
					System.out.print(">");
					command = input.init();
				}else {
					if(runReset) {
						runFromApp = false;
					}
				}
				navigate(command, security);
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
	
	public void acceptIncomingSignals(boolean security) {
		prints("DreamOS will now accept all the incoming data.");
		prints("Generating container.");
		String[] ar= {"commandSpace","packets"};
		InfoServeDaemon infod = new InfoServeDaemon("communicator.reciever");
		mkdir.init(ar, infod.getCertainPath("data"));
		prints("Ready to accept.");
		ReadFile rf = new ReadFile();
		for(;;) {
			String dat = rf.initiate(infod.getCertainPath("data") + "packets");
			print("DATA: " + dat);
			if(dat.contains("runcmd:")) {
				String[] send = dat.split(":");
				if(send[1].equals("debugOption.allowIncomingSignals@localOS")) {
					prints("Already in communicator.reciever!");
				}else {
					navigate(send[1], security);
				}
			}
		}
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