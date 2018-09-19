package CoreServices;
public class CoreService_Start{
	public CoreService_Start(){}
	public void init(boolean checkticket, String apticket){
		print("init Started.");
		print("Entering to bootloader...");
		Boot bootloader = new Boot();
		bootloader.init(checkticket, apticket);
	}
	public static void print(String s){
		System.out.println("init [INFO]: " + s);
	}
}