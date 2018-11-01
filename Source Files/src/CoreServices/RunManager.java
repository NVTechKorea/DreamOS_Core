package CoreServices;
import java.io.File;

import CoreFramework.InfoServeDaemon;
import CoreModules.WriteFile;
import Security.CheckTicket;
import Security.PostSignatureCheck;
import Security.SignatureCheck;
public class RunManager {
	public boolean init(boolean chkticket, String apticket) {
		print("RunManager entered.");
		InfoServeDaemon infod = new InfoServeDaemon("RunManager");
		print("Checking initial run history...");
		File file = new File(infod.getCertainFile("firstRunFlag"));
		File pathloc = new File(infod.getPathFileLocation());
		if(!file.exists()||!pathloc.exists()) {
			print("Running installer...");
			Installer installer = new Installer();
			installer.setupStorage(infod);
			installer.setupFiles(infod);
			setupFlag(infod);
			SignatureCheck sigchk = new SignatureCheck();
			sigchk.regvar();
			sigchk.initiate();
		}else {
			print("Running directly to Core...");
			PostSignatureCheck psc = new PostSignatureCheck();
			psc.regvar();
			psc.initiate();
		}
		CheckTicket ct = new CheckTicket();
		String returned = ct.init(chkticket, apticket);
		boolean disableSecurity = false;
		if(returned.equals("1")) {
			disableSecurity = true;
		}else {
			disableSecurity = false;
		}
		return disableSecurity;
	}
	public void setupFlag(InfoServeDaemon infod) {
		String path = infod.getCertainFile("firstRunFlag");
		WriteFile wf = new WriteFile();
		wf.initiate(path, "");
	}
	
	public static void print(String s) {
		System.out.println("InitialRunManager [INFO]: " + s);
	}
}
