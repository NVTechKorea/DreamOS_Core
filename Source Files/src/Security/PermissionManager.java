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
}
