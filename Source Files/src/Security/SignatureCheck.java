package Security;

import CoreModules.ReadFile;
import CoreModules.DeleteFile;
import CoreModules.DownloadHelper;
import java.io.File;

import CoreFramework.InfoServer;

public class SignatureCheck {
	private String signatureFileLoc = null;
	private String signatureFileLink = null;
	private String ver = null;
	private String var = null;
	InfoServer infod = new InfoServer("/system/Security/SignatureCheck");
	public SignatureCheck() {
		double version = infod.getVersionInDouble();
		ver = version + "0";
	}
	public void regvar() {
		String path = infod.getCertainPath("var");
		signatureFileLoc = path + "SignatureFile.signdoc";
		var = path + infod.getDirectoryIdentifier() + "setupDone.flag";
		signatureFileLink = "https://raw.githubusercontent.com/NVTechKorea/verificationServer/master/DreamOS/Sign/" + ver + ".vkey";
	}

	public void initiate() {
		System.out.println("SignatureCheck [INFO]: Checking signature...");
		boolean pass = false;
		pass = check();
		if (!pass) {
			showWarning("Unable to execute program.\nFailed to check integrity.");
		} else {
			System.out.println("SignatureCheck [INFO]: Integrity check was successful.");
		}
	}

	public boolean check() {
		File signatureFile = new File(signatureFileLoc);
		boolean pass = false;
		ReadFile rf = new ReadFile();
		for (;;) {
			if (signatureFile.exists()) {
				String data = rf.initiate(signatureFileLoc);
				if (data.startsWith("VERSION=" + ver)) {
					String parsable[] = data.split(";");
					int parsableLength = parsable.length;
					if (parsableLength == 3) {
						if (parsable[1].equals("SIGNED=TRUE")) {
							if (parsable[2].equals("EXPIRED=FALSE")) {
								pass = true;
								break;
							} else {
								showWarning("This version is permanently expired.");
							}
						} else {
							showWarning("This version is no longer being signed.");
						}
					} else {
						showWarning("Failed parsing signature file.");
					}
				} else {
					showWarning("Broken signature file.");
				}
			} else {
				DownloadHelper dl = new DownloadHelper();
				dl.regvar(var);
				dl.initiate(signatureFileLink, signatureFileLoc, "verificationServer");
				pass = false;
			}
		}
		return pass;
	}

	public void showWarning(String warningMsg) {
		System.out.println("SignatureCheck [ERROR]: " + warningMsg);
		DeleteFile df = new DeleteFile();
		df.initiate(signatureFileLoc, true);
		df.initiate(var, true);
		System.exit(0);
	}
}
