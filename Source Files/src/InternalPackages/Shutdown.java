package InternalPackages;

import Security.PermissionManager;

public class Shutdown {
	public static void init(String arg) {
		if(arg.equals("downloadedUpdate")) {
			print("Preparing for update before shutdown.");
			readyForUpdate();
		}
		PermissionManager.reset();
		print("System terminated.");
		System.exit(0);
	}
	public static void readyForUpdate() {
		
	}
	public static void print(String s) {
		System.out.println(s);
	}
}
