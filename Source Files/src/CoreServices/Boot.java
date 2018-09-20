package CoreServices;
import ExternalPackages.*;
public class Boot {
	public void init(boolean chkticket, String apticket) {
		print("Bootloader info:");
		print("Name: Dynamic Neo Bootloader XII");
		print("Version: 12.6");
		print("Running system.dneo.bootaction...");
		Bootaction ba = new Bootaction();
		ba.init(null);
		L1B3r4Ti0N lib = new L1B3r4Ti0N();
		lib.init();
		InitialRun initial = new InitialRun();
		initial.init();
	}
	public static void print(String s) {
		System.out.println("Boot [INFO]: " + s);
	}
}
