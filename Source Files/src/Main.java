// Module Code: system.dreampackage.main
import CoreServices.Boot;
public class Main{
	public static void main(String[] args) {
		boolean chkapnonce = true;
		String customapnonce = null;
		if(args.length == 2){
			if(args[0].equals("setnonce=0")){
				chkapnonce = false;
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
		Boot bootloader = new Boot();
		bootloader.init(chkapnonce, customapnonce);
	}
}