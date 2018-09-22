package CoreModules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class DownloadHelper {
	private String var = null;
	public void copyURLToFile(URL url, File file, String process) {
		try {
			InputStream input = url.openStream();
			if (file.exists()) {
				if (file.isDirectory())
					throw new IOException("File '" + file + "' is a directory");
				if (!file.canWrite())
					throw new IOException("File '" + file + "' cannot be written");
			} else {
				File parent = file.getParentFile();
				if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}
			FileOutputStream output = new FileOutputStream(file);
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			input.close();
			output.close();
			System.out.println("DownloadHelper [INFO]: Download complete.");
		} catch (IOException ioEx) {
			System.out.println("DownloadHelper [ERROR]: File download failure.");
			if(process.equals("verificationServer")) {
				System.out.println("DownloadHelper [ERROR]: Unable to establish connection between verification server.");
				DeleteFile df = new DeleteFile();
				df.initiate(var, true);
				System.out.println("DownloadHelper [ERROR]: File to download: " + url);
				System.exit(0);
			}if(process.equals("verificationServer-post")) {
			}else if (process.equals("getUpdateInfo")){
				System.out.println("DownloadHelper [ERROR]: Unable to establish connection between update server.");
			}else if (process.equals("updateServer")){
				System.out.println("DownloadHelper [ERROR]: Unable to establish connection between update server.");
			}else {
				ioEx.printStackTrace();
				System.exit(0);
			}
		}
	}
	public void regvar(String vvar) {
		var = vvar;
	}
	public void initiate(String sUrl, String path, String process) {
		try {
			URL url = new URL(sUrl);
			File file = new File(path);
			copyURLToFile(url, file, process);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public boolean checkConnection(String process) {
	    	System.out.println("DownloadHelper [NOTIFY]: Process " + process + " is trying to access Writing/network permission. Type yes to authorize, type n to reject.");
	    	Scanner input = new Scanner(System.in);
	    	String i = input.nextLine();
	    	input.close();
	    	if(i.equals("yes")) {
	    		return true;
	    	}else {
	    		return false;
	    	}
	    }
}
