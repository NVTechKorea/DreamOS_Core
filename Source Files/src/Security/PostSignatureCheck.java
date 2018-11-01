package Security;

import CoreModules.ReadFile;
import CoreModules.DownloadHelper;
import java.io.File;

import CoreFramework.InfoServeDaemon;

public class PostSignatureCheck {
	private String signatureFileLoc = null;
	private String signatureFileLink = null;
	private String ver = null;
	InfoServeDaemon infod = new InfoServeDaemon("/system/Security/PostSignatureCheck");
	public void regvar() {
		ver = infod.getVersionInDouble() + "0";
		String path = infod.getCertainPath("var");
		signatureFileLoc = path + "SignatureFile.signdoc";
		signatureFileLink = "https://raw.githubusercontent.com/NVTechKorea/verificationServer/master/DreamOS/Sign/" + ver + ".vkey";
	}

	public void initiate() {
		System.out.println("PostSignatureCheck [INFO]: Checking signature...");
		File signatureFile = new File(signatureFileLoc);
		ReadFile rf = new ReadFile();
		DownloadHelper dl = new DownloadHelper();
		dl.initiate(signatureFileLink, signatureFileLoc, "verificationServer-post");
		if (signatureFile.exists()){
			String data = rf.initiate(signatureFileLoc);
			if (data.startsWith("VERSION=" + ver)) {
				String parsable[] = data.split(";");
				int parsableLength = parsable.length;
				if (parsableLength == 3) {
					if (parsable[1].equals("SIGNED=TRUE")) {
						if (parsable[2].equals("EXPIRED=FALSE")) {
							System.out.println("PostSignatureCheck [INFO]: Signature passed.");
						} else {
							showError("This version is permanently expired. You cannot use this OS any more.");
						}
					}else {
						showWarning("This version is no longer being signed.\nWe recommend you to update.");
					}
				}
			}
		}
	}

	public void showWarning(String warningMsg) {
		System.out.println("PostSignatureCheck [WARN]: " + warningMsg);
	}
	public void showError(String msg) {
		System.out.println("PostSignatureCheck [ERROR]: " + msg);
		System.exit(0);
	}
}
