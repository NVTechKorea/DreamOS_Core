package CoreServices;
public class Boot {
	public void init(boolean chkticket, String apticket) {
		InfoServer infod = new InfoServer();
		print("Bootloader info:");
		print("Name: Dynamic Neo Bootloader XII");
		print("Version: 12.6");
		print("OS to boot: " + infod.getSystemName() + " " + infod.getVersion());
		InitialRun ir = new InitialRun();
		boolean disableSecurity = ir.init(chkticket, apticket);
		CoreNavigator cn = new CoreNavigator();
		try {
			cn.init(disableSecurity);
		}catch(Exception e) {
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, "Main", false);
		}
	}
	public static void print(String s) {
		System.out.println("Boot [INFO]: " + s);
	}
}
