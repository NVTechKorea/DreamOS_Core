package ExternalPackages;

public class Exec {
	public void init(String s) {
		print("DYNAMIC APP ADAPTER 1.0");
		if(s.contains("liberation")) {
			
		}else if(s.contains("reality")) {
			
		}else if(s.contains("liberatereality")) {
			
		}
	}
	public static void print(String s){
		System.out.println("INFO [DynamicAppAdapter]: " + s);
	}
}
