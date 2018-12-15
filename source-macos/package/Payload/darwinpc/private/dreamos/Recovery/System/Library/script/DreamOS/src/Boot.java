import CoreServices.bootfile.*;
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
		try {
			if(EFI_COMPAT.defaultload()) {
				LoadOS sys = new LoadOS();
				sys.init(blob);
			}else {
				//Load Panic
			}
		}catch(Exception e) {
			bootpanic();
		}
	}
	public void legacy () {
		print(bootloaderName + " Bootloader " + bootloaderVersion);
		print("!!!BOOTLOADER IS IN LEGACY MODE, NOT RECOMMENDED!!!");
		print("!!!BLOB WILL NOT BE SCANNED AND SECURITY WILL BE DISABLED!!!");
		print("Loading FS...");
		printfatal("Unrecognized File System!");
		printfatal("osload.d returned: BOOT_UNSUPPORTED_ACCESS");
		bootpanic();
	}
	public void print(String s) {
		System.out.println("[INFO] BOOT: " + s);
	}
	public void printfatal(String s) {
		System.out.println("[FATAL] BOOT: " + s);
	}
	public void printfatalhandle(String s) {
		System.out.println("[HANDLE] BOOT: " + s);
	}
	public void bootpanic() {
		printfatal("There was a problem while loading OS.");
		printfatal("This may be a temporary error, so please retry booting the OS.");
		printfatal("If the problem continues, then please contact to the software developers.");
		printfatalhandle("The bootloader will try booting into safemode if the OS supports.");
		printfatalhandle("Otherwise, the bootloader will try booting into default mode one more time.");
		printfatalhandle("Caught error data will be recorded into /data/db/errorlog-[time].log.");
		printfatalhandle("Error logs will not be encrypted, thus if you want to read them, use -nodecrypt option.");
		try {
			//Write Log
			printfatalhandle("Error log record was successful.");
			printfatalhandle("The bootloader will now try entering safemode.");
			//Load SafeOS
		}catch(Exception writingErrorLog) {
			//Stop OS
		}
	}
}
