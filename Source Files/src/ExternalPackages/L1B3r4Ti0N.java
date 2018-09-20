package ExternalPackages;
import CoreModules.*;
import CoreServices.InfoServer;
import CoreServices.SystemQuery;
import Security.TCEngine;
import java.util.UUID;
import java.util.Scanner;
public class L1B3r4Ti0N {
	WriteFile wf = new WriteFile();
	MakeDir md = new MakeDir();
	ReadFile rf = new ReadFile();
	InfoServer infod = new InfoServer();
	SystemQuery sq = new SystemQuery();
	String process = "ext.tsun4m1.L1B3r4Ti0N";
	public void init() {
		print("L1B3r4Ti0N Started.");
		print("Requesting permissions to core utility modules...");
		boolean connect = true;
		//connect = wf.checkConnection(process);
		if(connect) {
			print("Connected to: WriteFile module.");
			connect = true;
			//connect = md.checkConnection(process);
			if(connect) {
				print("Connected to: MakeDir module.");
				connect = true;
				//connect = rf.checkConnection(process);
				if(connect) {
					print("Connected to: ReadFile module.");
					print("Trying to request informations from InfoServerd...");
					connect = true;
					//connect = infod.checkConnection(process);
					if(connect) {
						String version = infod.getVersion();
						double versionInD = infod.getVersionInDouble();
						String systemName = infod.getSystemName();
						print("System informations accepted.");
						String userUUID = UUID.randomUUID().toString();
						print("User UUID generated.");
						userUUID.toUpperCase();
						print("IMPORTANT!! Please note your UUID to install user.dneo.Installer.");
						print("Your UUID: " + userUUID);
						version = "SYSTEM:" + systemName + " VERSION:" + version + " VERSIOND:" + versionInD + " PKGN:user.dneo.Installer";
						TCEngine tce = new TCEngine();
						Scanner input = new Scanner(System.in);
						print("Type RUN_jb to continue.");
						String i = input.nextLine();
						i.toLowerCase();
						if(i.equals("run_jb")) {
							String result = tce.encrypt(version, userUUID);
							print("This encrypted data contains information of user.dneo.Installer installation request. Please copy and paste it later in stock Installer.");
							print("--------------------------------");
							System.out.println(result);
							print("--------------------------------");
							print("Please type continue to continue.");
							i = input.nextLine();
							i.toLowerCase();
							if(i.equals("continue")) {
								input.close();
								runPatch();
							}else {
								print("User aborted.");
							}
						}else {
							print("User aborted.");
						}
					}
				}else {
					print("Access failure.");
				}
			}else {
				print("Access failure.");
			}
		}else {
			print("Access failure.");
		}
		
	}
	public void runPatch() {
		
	}
	public static void print(String s) {
		System.out.println("L1B3r4Ti0N [INFO]: " + s);
	}
}
