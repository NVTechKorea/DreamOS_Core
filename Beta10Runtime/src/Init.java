// Module Code: system.dreampackage.main
import CoreServices.Boot;
public class Init{
	public static void main(String[] args) {
		boolean chkapnonce = true;
		String customapnonce = null;
		boolean override = false;
		if(args.length == 3){
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
			if(args[2].equals("override=1")) {
				override = true;
			}
		}else {
			customapnonce = null;
		}
		if(args.length==1) {
			if(args[0].equals("override=1")) {
				override = true;
			}
		}
		Boot bootloader = new Boot();
		bootloader.init(chkapnonce, customapnonce, override);
	}
}