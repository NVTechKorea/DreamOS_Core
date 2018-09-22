package Security;

import CoreModules.DeleteFile;
import CoreModules.WriteFile;
import CoreServices.InfoServer;

public class PermissionManager {
	public static void scan(int permission) {
		WriteFile wf = new WriteFile();
		InfoServer infod = new InfoServer();
		if(permission == 0) {
			wf.initiate(infod.getCertainFile("wide_permission"), "root");
		}else {
			wf.initiate(infod.getCertainFile("wide_permission"), "default");
		}
	}
	public static void reset() {
		DeleteFile df = new DeleteFile();
		InfoServer infod = new InfoServer();
		df.initiate(infod.getCertainFile("wide_permission"), true);
	}
}
