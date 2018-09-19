// Module Code: system.dreampackage.main
import CoreServices.CoreService_Start;
public class Main{
	public static void main(String[] args) {
		boolean chkapnonce = true;
		String customapnonce = null;
		if(args.length == 2){
			if(args[0].equals("setnonce=0")){
				chkapnonce = true;
				if(args[1].startsWith("apn=")) {
					String[] temp = args[1].split("=");
					customapnonce = temp[1];
					if(!customapnonce.startsWith("0xc9")) {
						customapnonce = null;
					}
				}else {
					customapnonce = null;
				}
			}
		}else {
			customapnonce = null;
		}
		CoreService_Start startup = new CoreService_Start();
		startup.init(chkapnonce, customapnonce);
	}
}