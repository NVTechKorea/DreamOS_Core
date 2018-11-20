package CoreServices;

public class Boot {
	final String bootloaderVersion = "42.193";
	final String bootloaderName = "Pine";
	boolean secureboot = true;
	public void efi(String blob) {
		print(bootloaderName + " Bootloader " + bootloaderVersion);
		print("Scanning blob.");
		if(blob.equals("")) {
			
		}
		print("Loading FS...");
		print("Scanning corefiles...");
	}
	public void print(String s) {
		System.out.println("[INFO] BOOT: " + s);
	}
}
