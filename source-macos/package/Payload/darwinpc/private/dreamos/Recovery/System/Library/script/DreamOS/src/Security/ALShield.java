package Security;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Random;

import Boot;
import CoreFramework.GetInput;
import CoreFramework.InfoServeDaemon;
import CoreModules.WriteFile;
import CoreServices.PanicHandler;
import CoreModules.ReadFile;
import CoreModules.DeleteFile;
import CoreModules.MakeDir;

import commands.Shutdown;
public class ALShield {
	String version = "AbsoluteLevelShield-1.16";
	PanicHandler ea = new PanicHandler();
	WriteFile wf = new WriteFile();
	ReadFile rf = new ReadFile();
	MakeDir md = new MakeDir();
	TCEngine tce = new TCEngine();
	GetInput iosys = new GetInput();
	String library = null;
	String loginToken = null;
	String machine_uuid = null;
	String randomToken = null;
	String encryptorToken = null;
	String decryptToken = null;
	public String init() {
		library = infod.getCertainPath("als");
		print("ALS initiated.");
		print(version);
		print("Getting machine UUID...");
		machine_uuid = infod.getCertainFile("uuid");
		machine_uuid = rf.initiate(machine_uuid);
		print("Getting login token...");
		loginToken = infod.getCertainFile("logintoken");
		encryptorToken = infod.getCertainFile("encryptortoken");
		print("Getting random token...");
		randomToken = infod.getCertainFile("loginrandomtoken");
		File login_token = new File(loginToken);
		tce.silence(true);
		if(!login_token.exists()) {
			print("Login token does not exist.");
			makeLoginToken();
			print("Applying changes.");
			Boot boot = new Boot();
			boot.init(false, null, true);
		}else {
			print("Logging in...");
			decryptToken = userLogin();
		}
		return decryptToken;
	}
	public String userLogin() {
		getRandomToken();
		loginToken = rf.initiate(loginToken);
		String decrypted = tce.decrypt(loginToken, randomToken);
		boolean pass = false;
		for(int i = 0; i<5; i++) {
			String[] data = getUserInfo();
			String decrypted2 = tce.encrypt(data[0], data[1]);
			if(decrypted.equals(decrypted2)) {
				print("Login successful.");
				pass = true;
				break;
			}else {
				printe("Login failure.");
				i++;
			}
		}
		if(!pass) {
			printe("You've tried too many times.");
			print("Your random token is deleted.");
			print("All the data are not readable now.");
			DeleteFile df = new DeleteFile();
			df.initiate(infod.getCertainFile("loginrandomtoken"), true);
			Shutdown.init("");
		}
		getEncryptorToken();
		String loadEncryptToken = tce.decrypt(rf.initiate(infod.getCertainFile("encryptortoken")), machine_uuid);
		if(loadEncryptToken.equals("SYSTEM_return:wrongPasswordData")) {
			printe("Files cannot be decrypted.");
			printe("2843: UUID does not match.");
			Shutdown.init("");
		}
		loadEncryptToken = tce.decrypt(loadEncryptToken, loginToken);
		if(encryptorToken.equals("SYSTEM_return:wrongPasswordData")) {
			printe("Files cannot be decrypted.");
			printe("2853: Login token does not match.");
			Shutdown.init("");
		}
		if(!loadEncryptToken.equals(encryptorToken)) {
			printe("Files cannot be decrypted.");
			printe("2863: Decrypt password does not match.");
			Shutdown.init("");
		}
		return loadEncryptToken;
	}
	public void makeLoginToken() {
		getRandomToken();
		print("Please make your account.");
		String[] info = getUserInfo();
		String enc = tce.encrypt(info[0], info[1]);
		print("Encrypting...");
		enc = tce.encrypt(enc, randomToken);
		wf.initiate(infod.getCertainFile("logintoken"), enc);
		getEncryptorToken();
		enc = tce.encrypt(encryptorToken, enc);
		decryptToken = tce.encrypt(enc, machine_uuid);
		wf.initiate(infod.getCertainFile("encryptortoken"), decryptToken);
	}
	public String[] getUserInfo() {
		print("-----Input Manager-----");
		for(;;) {
			System.out.print("Username: ");
			String usrname = iosys.init();
			System.out.print("Password: ");
			String password = iosys.init();
			String[] pack = {usrname, password};
			if(usrname==null || password==null) {
				System.out.println("E: Some of the fields are empty.");
			}else {
				return pack;
			}
		}
	}
	public void getEncryptorToken() {
		print("Type your password for file encryption.");
		String i = null;
		for(;;) {
			System.out.print("Key: ");
			i = iosys.init();
			if(i == null) {
				printw("Encryption password is empty.");
			}else {
				break;
			}
		}
		encryptorToken = i;
	}
	public void getRandomToken() {
		File token = new File(randomToken);
		if(!token.exists()) {
			byte[] array = new byte[32];
		    new Random().nextBytes(array);
		    String rand = new String(array, Charset.forName("UTF-8"));
		    wf.initiate(randomToken, rand);
		    randomToken = rand;
		}else {
			randomToken = rf.initiate(randomToken);
		}
	}
	public void print(String s) {
		System.out.println("ALS [INFO]: " + s);
	}
	public void printw(String s) {
		System.out.println("ALS [WARN]: " + s);
	}
	public void printe(String s) {
		System.out.println("ALS [ERROR]: " + s);
	}
}
