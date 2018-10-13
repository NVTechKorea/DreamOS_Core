package CoreServices;

public class Exec {
	public static void init(String[] path, String currentDir) {
		try {
			Process ps = Runtime.getRuntime().exec(new String[]{"java","-jar", currentDir + path[1] + ".jar"});
	        ps.waitFor();
	        java.io.InputStream is=ps.getInputStream();
	        byte b[]=new byte[is.available()];
	        is.read(b,0,b.length);
	        System.out.println(new String(b));
		}catch(Exception e) {
			
		}
	}
}
