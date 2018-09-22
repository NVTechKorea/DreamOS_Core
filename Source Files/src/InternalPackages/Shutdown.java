package InternalPackages;

import Security.PermissionManager;

public class Shutdown {
	public static void init() {
		PermissionManager.reset();
		System.exit(0);
	}
	public void print(String s) {
		System.out.println(s);
	}
}
