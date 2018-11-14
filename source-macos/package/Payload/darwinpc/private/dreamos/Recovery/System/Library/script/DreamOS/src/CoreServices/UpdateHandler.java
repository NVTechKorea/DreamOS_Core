package CoreServices;

import CoreModules.DeleteFile;

import java.util.Scanner;

import CoreModules.ReadFile;
import CoreModules.DownloadHelper;
import commands.Shutdown;

public class UpdateHandler {
	Scanner input = new Scanner(System.in);
	public boolean init() {
		return false;
	}
	public void update(boolean atLaunch) {
		String path = null;
			if(true) {
				print("Running update tool...");
				print("Getting information...");
				boolean downgrade = false;
				DownloadHelper dlhelper = new DownloadHelper();
				String sUrl = "https://raw.githubusercontent.com/NVTechKorea/MessageEncryptor/master/VerificationData/Latest.signdoc";
				path = infod.getCertainPath("var") + "Latest.signdoc";
				dlhelper.initiate(sUrl, path, "getUpdateInfo");
				ReadFile rf = new ReadFile();
				String LatestVer = rf.initiate(path);
				if (LatestVer != null) {
					if(Double.parseDouble(LatestVer)<infod.getVersionInDouble()) {
						downgrade = true;
						print("Current version has a problem and needed to be\ndowngraded to the latest signed version.");
					}else {
						downgrade = false;
					}
					if (!LatestVer.equals(infod.getVersionInDouble() + "")) {
						boolean yes = true;
						if(!downgrade) {
							String yn = input.nextLine();
							yn = yn.toLowerCase();
							if(yn.startsWith("y")) {
								yes = true;
							}else {
								yes = false;
							}
						}else {
							yes = true;
						}
						if(yes) {
							sUrl = "https://github.com/NVTechKorea/DreamOS_Core/raw/master/Test%20Beta/" + LatestVer + ".jar";
							path = infod.getUserDirectory() + infod.getDirectoryIdentifier() + "Downloads" + infod.getDirectoryIdentifier() + "DreamOS_" + LatestVer + "_Update.zip";
							dlhelper.initiate(sUrl, path, "updateServer");
							print("Update download finished.\nPlease launch the downloaded software.");
							Shutdown.init("downloadedUpdate");
						}
					} else {
						if (!atLaunch) {
							print("You are using the latest version.");
						}
						DeleteFile df = new DeleteFile();
						path = infod.getCertainPath("var") + "Latest.signdoc";
						df.initiate(path, true);
					}
				}
			}
	}
	public void print(String s) {
		System.out.println("UpdateHandler [INFO]: " + s);
	}

}
