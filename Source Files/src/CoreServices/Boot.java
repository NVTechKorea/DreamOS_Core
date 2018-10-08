package CoreServices;

import CoreFramework.PanicHandler;

import java.io.File;

import CoreFramework.CoreFilesUtility;
import CoreFramework.InfoServer;
import InternalPackages.Shutdown;
public class Boot {
	public void init(boolean chkticket, String apticket, boolean override) {
		try {
			print("Launching InfoServer...");
			InfoServer infod = new InfoServer("bootloader");
			print("Bootloader info:");
			print("Name: Dynamic Neo Bootloader XII");
			print("Version: 12.6");
			print("OS to boot: " + infod.getSystemName() + " " + infod.getVersion());
			try {
				RunManager ir = new RunManager();
				boolean disableSecurity = ir.init(chkticket, apticket);
				CoreServices.CoreNavigator cn = new CoreServices.CoreNavigator();
				CoreFilesUtility cfu = new CoreFilesUtility("bootloader", true);
				File updateDownloaded = new File(cfu.getFile("updateReady.flag"));
				if(updateDownloaded.exists()&&!override) {
					print("OS is ready to update. If you don't want to update, add override=1 at the end of the running option.");
					print("Please run update helper!");
					Shutdown.init("");
				}
				try {
					cn.init(disableSecurity);
				}catch(Exception e) {
					PanicHandler ea = new PanicHandler();
					ea.initiate(e, "Main", false);
				}
			}catch(Exception e) {
				print("Bootloader detected critical error and it caused system to panic.");
				print("Restarting machine to recoveryOS.");
				recoveryOS.CoreNavigator core = new recoveryOS.CoreNavigator ();
				core.Core();
			}
		}catch(Exception ee) {
			print("Bootloader detected critical error and it caused system to panic.");
			print("Restarting machine to recoveryOS.");
			recoveryOS.CoreNavigator core = new recoveryOS.CoreNavigator ();
			core.Core();
		}
	}
	public static void print(String s) {
		System.out.println("Boot [INFO]: " + s);
	}
}
