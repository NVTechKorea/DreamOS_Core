package CoreModules;

import java.io.File;

public class CheckFileExist {
	public boolean init(String path, boolean file) {
		File checkTarget = new File(path);
		if(file) {
			if(checkTarget.exists()&&checkTarget.isFile()) {
				return true;
			}else {
				return false;
			}
		}else {
			if(checkTarget.exists()&&checkTarget.isDirectory()) {
				return true;
			}else {
				return false;
			}
		}
		
	}
}
