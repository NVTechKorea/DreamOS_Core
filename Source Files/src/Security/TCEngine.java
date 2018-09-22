package Security;
import CoreServices.ErrorAnalyzer;
public class TCEngine {
	boolean silence = false;
	public void silence(boolean gotSilence) {
		silence = gotSilence;
	}
	public String encrypt (String content, String pass) {
		print("Encryption started.");
		String finalResult = null;
		try {
			String result = null;
			result = EncryptTool.aesEncrypt(content, pass);
			String integer = pass.length() + "";
			integer = EncryptTool.aesEncrypt(integer, "alpine");
			result = EncryptTool.aesEncrypt(result, integer);
			finalResult = "<key>" + integer + "<key>" + result + "<key> <data>";
		}catch(Exception e) {
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, "TCE Encryption", true);
		}
		print("Encryption process finished.");
		print("Returned value:" + finalResult);
		return finalResult;
	}
	public String decrypt (String content, String pass) {
		String result = null;
		String integer = pass.length() + "";
		integer = EncryptTool.aesEncrypt(integer, "alpine");
		try {
			if(!content.startsWith("<key>" + integer)) {
				result = "SYSTEM_return:noHeaderData";
			}else {
				String decrypt[] = content.split("<key>");
				if(decrypt.length!=4) {
					result = "SYSTEM_return:brokenData";
				}else {
					String head = EncryptTool.aesDecrypt(decrypt[1], "alpine");
					if(head.equals(pass.length() + "")) {
						result = EncryptTool.aesDecrypt(decrypt[2], integer);
						result = EncryptTool.aesDecrypt(result, pass);
					}else {
						result = "SYSTEM_return:wrongPasswordData";
					}
				}
			}
		}catch(Exception e) {
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, "TCE Decryption", true);
		}
		print("Decryption process finished.");
		print("Returned value:" + result);
		return result;
	}
	public void print(String s) {
		if(!silence) {
			System.out.println("TCE [INFO]: " + s);
		}
	}
}
