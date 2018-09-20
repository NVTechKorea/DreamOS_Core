package Security;
import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;
import CoreModules.*;
import CoreServices.OSReader;
import CoreServices.ErrorAnalyzer;
public class Shield{
	ReadFile rf = null;
	WriteFile wf = null;
	OSReader os = null;
	ErrorAnalyzer ea = null;
	String process = null;
	String path = null;
	String splitter = null;
	String userdata = null;
	String library = null;
	boolean pass = false;
	boolean secure = true;
	private String salt = "ALPINE_MLDY";
	public static String version = "security.dreamOS.fs4-forked.edition4";
	public Shield(String highpath, boolean secureOption){
		path = highpath;
		secure = secureOption;
		rf = new ReadFile();
		wf = new WriteFile();
		os = new OSReader();
		ea = new ErrorAnalyzer();
		String temp[] = os.initiate();
		splitter = temp[0];
		library = path + "storage" + splitter + "protection";
		userdata = library + splitter + "comparable.mldy";
	}
	public void initiate(){
		String process = "Started Fusion Shield";
		try{
			initiate2();
		}catch(Exception e){
			error("Master try");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}
	}
	public static void print(String s){
		System.out.println("SHIELD [INFO]: " + s);
	}
	public static void error(String s){
		System.out.println("Shield [ERROR]: " + s);
	}
	public void initiate2(){
		try{
			process = "Initiated FS";
			library = path + "storage" + splitter + "protection";
			userdata = library + splitter + "comparable.mldy";
			File shieldlib = new File(library);
			File locked = new File(library + splitter + "lock.mldy");
			if(!shieldlib.exists()){
				shieldlib.mkdir();
			}
			if(locked.exists()){
				String data = rf.initiate(library + splitter + "lock.mldy");
				if(data.contains("bfaDetection")){
					error("Brute Force Attack detected... You are no longer able to login.");
					endOS();
				}
			}
			File userData = new File(userdata);
			if(userData.isDirectory()){
				error("Encrypted data is a directory.");
				warn("Cleaning directory!");
				File[] deleteTarget = userData.listFiles();
				for(int counter = 0; counter < deleteTarget.length; counter++){
					if(deleteTarget[counter].delete()){
						print("Successfully deleted inner contents.");
					}else{
						error("Failed to erase inner contents.");
					}
				}
				if(userData.delete()){
					print("Successfully deleted broken encrypted file.");
					makeNewUser(userData);
				}else{
					error("Failed to remove broken encrypted data.");
				}
			}else if(userData.exists()){
				loginToUser(userData);
			}else{
				makeNewUser(userData);
			}
		}catch(Exception e){
			ea.initiate(e, process, false);
		}
	}
	public void makeNewUser(File data){
		Scanner input = new Scanner(System.in);
		print("No user data found.");
		boolean doneMaking = false;
		while(!doneMaking){
			System.out.print("New Username: ");
			String username = input.nextLine();
			System.out.print("New Password: ");
			String password = input.nextLine();
			System.out.print("Confirm Password: ");
			String confirm_pass = input.nextLine();
			if(password.equals(confirm_pass)){
				String singleLine = username + salt + password;
				String encryptResult = fusionEncryptor(singleLine);
				wf.initiate(userdata, encryptResult);
				doneMaking = true;
				loginToUser(data);
			}else{
				error("Password does not match!");
			}
		}
		input.close();
	}
	public void loginToUser(File data){
		if(!secure){
			boolean pass = false;
			String comparable = rf.initiate(userdata);
			String encryptResult = null;
			String singleLine = null;
			Scanner input = new Scanner(System.in);
			String af_usr = null;
			String af_pas = null;
			File auto1 = new File(library + splitter + "autofill_usr.mldy");
			File auto2 = new File(library + splitter + "autofill_pas.mldy");
			if(auto1.exists()||auto2.exists()){
				af_usr = rf.initiate(library + splitter + "autofill_usr.mldy");
				af_pas = rf.initiate(library + splitter + "autofill_pas.mldy");
				singleLine = af_usr + salt + af_pas;
				encryptResult = fusionEncryptor(singleLine);
				if(comparable.equals(encryptResult)){
					print("Login successful!");
					pass = true;
				}else{
					error("Autofiller's username or password is wrong.");
					for(int c = 0; c < 5; c++){
						System.out.print("Username: ");
						String username = input.nextLine();
						if(username.equals(";shutdown")){
							endOS();
						}
						System.out.print("Password: ");
						String password = input.nextLine();
						singleLine = username + salt + password;
						encryptResult = fusionEncryptor(singleLine);
						comparable = rf.initiate(userdata);
						if(comparable.equals(encryptResult)){
							print("Login successful!");
							pass = true;
							break;
						}else{
							error("Username or password is wrong.");
							pass = false;
						}
					}
					if(!pass){
						destroyUser();
					}
				}
			}else{
				for(int c = 0; c < 5; c++){
					System.out.print("Username: ");
					String username = input.nextLine();
					if(username.equals(";shutdown")){
						endOS();
					}
					System.out.print("Password: ");
					String password = input.nextLine();
					singleLine = username + salt + password;
					encryptResult = fusionEncryptor(singleLine);
					comparable = rf.initiate(userdata);
					if(comparable.equals(encryptResult)){
						print("Login successful!");
						pass = true;
						break;
					}else{
						error("Username or password is wrong.");
						pass = false;
					}
				}
				if(!pass){
					destroyUser();
				}
			}
			input.close();
		}else{
			Scanner input = new Scanner(System.in);
			boolean pass = false;
			for(int c = 0; c < 6; c++){
				System.out.print("Username: ");
				String username = input.nextLine();
				if(username.equals(";shutdown")){
					endOS();
				}
				System.out.print("Password: ");
				String password = input.nextLine();
				String singleLine = username + salt + password;
				String encryptResult = fusionEncryptor(singleLine);
				String comparable = rf.initiate(userdata);
				if(comparable.equals(encryptResult)){
					print("Login successful!");
					pass = true;
					break;
				}else{
					error("Username or password is wrong.");
					pass = false;
				}
				if(c==4){
					if(!pass){
						destroyUser();
					}
				}
			}
			input.close();
		}
	}
	//ENGINES
	public void destroyUser(){
		String removed = "shield.locked.bfaDetection";
		File deleteTarget = new File(userdata);
		if(deleteTarget.delete()){}else{
			wf.initiate(userdata, removed);
		}
		wf.initiate(library + splitter + "lock.mldy", removed);
		error("Brute Force Attack detected... You are no longer able to login.");
		endOS();
	}
	public String fusionEncryptor(String usrinput) {
		String res = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(usrinput.getBytes("UTF-8"));
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
	public static void warn(String s){
		System.out.println("Shield [WARN]: " + s);
	}
	public void chpw(){
		Scanner input = new Scanner(System.in);
		boolean pass = false;
			for(int c = 0; c < 5; c++){
				System.out.print("Username: ");
				String username = input.nextLine();
				System.out.print("Password: ");
				String password = input.nextLine();
				String singleLine = username + salt + password;
				String encryptResult = fusionEncryptor(singleLine);
				String comparable = rf.initiate(userdata);
				if(comparable.equals(encryptResult)){
					print("Login successful!");
					pass = true;
					break;
				}else{
					error("Username or password is wrong.");
					pass = false;
				}
			}
		if(pass){
			DeleteFile df = new DeleteFile();
			df.initiate(userdata, true);
			File userData = new File(userdata);
			makeNewUser(userData);
		}
		input.close();
	}
	public void delUser(){
		Scanner input = new Scanner(System.in);
		boolean pass = false;
			for(int c = 0; c < 5; c++){
				System.out.print("Username: ");
				String username = input.nextLine();
				System.out.print("Password: ");
				String password = input.nextLine();
				String singleLine = username + salt + password;
				String encryptResult = fusionEncryptor(singleLine);
				String comparable = rf.initiate(userdata);
				if(comparable.equals(encryptResult)){
					print("Login successful!");
					pass = true;
					break;
				}else{
					error("Username or password is wrong.");
					pass = false;
				}
			}
		if(pass){
			DeleteFile df = new DeleteFile();
			df.initiate(userdata, true);
		}
		input.close();
	}
	public void endOS(){
		print("Shutting down OS!");
		try{
			File cache = new File(path + "resources" + splitter + "boot" + splitter + "systemCache.sc");
			if(cache.delete()){}else{System.out.println("WARNING [DreamOS_Core]: Cache delete was unsuccessful.");}
			System.exit(0);
		}catch(Exception e){
			System.exit(0);
		}
	}
}
