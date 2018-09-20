package CoreServices;
public class Boot {
	public void init(boolean chkticket, String apticket) {
		InitialRun initial = new InitialRun();
		initial.init();
	}
	public static void print(String s) {
		System.out.println("Boot [INFO]: " + s);
	}
}
