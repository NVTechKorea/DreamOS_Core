package Security;

import CoreFramework.InfoServer;
import CoreModules.DeleteFile;
import CoreModules.WriteFile;

public class PermissionManager {
	static InfoServer infod = new InfoServer("/system/Security/PermissionManager");
	public static void scan(int permission) {
		WriteFile wf = new WriteFile();
		if(permission == 0) {
			wf.initiate(infod.getCertainFile("wide_permission"), "root");
		}else {
			wf.initiate(infod.getCertainFile("wide_permission"), "default");
		}
	}
	public static void reset() {
		DeleteFile df = new DeleteFile();
		df.initiate(infod.getCertainFile("wide_permission"), true);
	}
	public static String getSerial() {
		return "serial:CBED2FD52CED6D52973A92C7AC393C83B9272A8BCAB0F4D0492DC33216A16CC1";
	}
}
