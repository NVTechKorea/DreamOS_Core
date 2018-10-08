package recoveryOS;

import java.util.Scanner;

public class CoreNavigator {
	Scanner recoveryReservedInputSystem = new Scanner(System.in);
	public CoreNavigator() {
		print("Welcome to recoveryOS.");
	}
	public void Core() {
		print("RecoveryOS will allow you to use command up to 50 times as default.");
		for(int limit = 0; limit<50; limit ++) {
			String usrinput = recoveryReservedInputSystem.nextLine();
			if(usrinput.equals("help")) {
				print("run-su setRecoveryLimitCounter [0-49]");
				print("run-su resetRecoveryLimitCounter");
				print("eraseUserData");
				print("runReInstallProcess");
				print("rollSystem");
				print("shutdown");
			}else if(usrinput.startsWith("run-su setRecoveryLimitCounter")) {
				String[] parse = usrinput.split(" ");
				if(parse.length >= 3) {
					if(parse.length==3) {
						int temp = limit;
						try {
							temp = Integer.parseInt(parse[2]);
						}catch(Exception e) {
							print("Please type in numbers only.");
							temp = limit;
						}
						if(temp<0) {
							print("Minimum number is 0.");
						}else if(temp > 49) {
							print("Maximum number is 49.");
						} else {
							limit = Integer.parseInt(parse[2]);
							print("Setting successful.");
						}
					}else {
						print("Too many parameter.");
					}
				}else {
					print("Missing parameter: int [0-49]");
				}
			}else if(usrinput.equals("shutdown")) {
				System.exit(0);
			}
		}
	}
	public void print(String s) {
		System.out.println("[Recovery]: " + s);
	}
}
