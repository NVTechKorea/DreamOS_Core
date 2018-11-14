package CoreServices;

import CoreFramework.CoreFilesUtility;
import CoreModules.ReadFile;
import langpack.EN_us;
import langpack.KR_ko;

public class LangManager {
	public static String init(String message) {
		CoreFilesUtility cfu = new CoreFilesUtility("LanguageManager", true);
		ReadFile rf = new ReadFile();
		if(rf.initiate(cfu.getFile("lang")).equals("os.mainlang=en_us")) {
			EN_us lang = new EN_us();
			return lang.init(message);
		}else if(rf.initiate(cfu.getFile("lang")).equals("os.mainlang=ko_kr")) {
			KR_ko lang = new KR_ko();
			return lang.init(message);
		}else {
			EN_us lang = new EN_us();
			return lang.init(message);
		}
	}
}
