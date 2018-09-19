package Security;
import CoreModules.*;
import CoreServices.OSReader;
import java.io.*;
import java.security.MessageDigest;
public class SecurelyBootHelper{
	String bootedHash = null;
	String readSign = null;
	String writtensign = null;
	String splitter = null;
	boolean secureOption = true;
	String path = null;
	ReadFile rf = null;
	OSReader os = null;
	Shield shield = null;
	String hardUnlockSign = "7BC490A7E38F746C04EC8E12D1165F29B352305CD6FFD88F817E30918126AB32";
	public SecurelyBootHelper(){
		shield = new Shield(null, true);
	}
	public void registerType1(String boothash, String signature, String highpath, String read, boolean secopt){
		bootedHash = boothash;
		writtensign = signature;
		path = highpath;
		readSign = read;
		rf = new ReadFile();
		os = new OSReader();
		secureOption = secopt;
	}
	public void registerType2(String boothash, String highpath, boolean secopt){
		secureOption = secopt;
		path = highpath;
		rf = new ReadFile();
		bootedHash = boothash;
		os = new OSReader();
	}
	public boolean noSecureOption(){
		String[] getSplitter = os.initiate();
		splitter = getSplitter[0];
		String comparable = rf.initiate(path + splitter + "storage" + splitter + "system" + splitter + "firmwareSignature.mldy");
		// String tempGottenHash = encryptionEngine(comparable);
		String gottenHash = encryptionEngine(comparable);
		gottenHash = gottenHash.toLowerCase();
		bootedHash = bootedHash.toLowerCase();
		hardUnlockSign = hardUnlockSign.toLowerCase();
		boolean pass = false;
		brint("Custom boot hash is enabled.");
		brint("Requested boot hash: " + gottenHash);
		if(bootedHash.equals(gottenHash)){
			if(gottenHash.equals(hardUnlockSign)){
				brint("Boot successful with: " + bootedHash);
				pass=true;
			}else{
				berror("Generated hash does not match: " + bootedHash);
				endOS();
			}
		}else{
			berror("Generated hash does not match: " + bootedHash);
			endOS();
		}
		return pass;
	}
	public String encryptionEngine(String key) {
		String res = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(key.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			res = (hexString.toString());

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return res;
	}
	public static void print(String s){
		System.out.println("INFO [SBH]: " + s);
	}
	public static void brint(String s){
		System.out.println("INFO [BOOT]: " + s);
	}
	public static void berror(String s){
		System.out.println("ERROR [BOOT]: " + s);
	}
	public void endOS(){
		print("Shutting down OS!");
		try{
			File cache = new File(path + "resources" + splitter + "boot" + splitter + "systemCache.sc");
			if(cache.delete()){}else{print("Cache delete was unsuccessful.");}
			System.exit(0);
		}catch(Exception e){
			System.exit(0);
		}
	}
}