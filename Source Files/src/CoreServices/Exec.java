package CoreServices;

public class Exec {
	public static String init(String[] path, String currentDir) {
		String data = null;
		try {
			Process ps = Runtime.getRuntime().exec(new String[]{"java","-jar", currentDir + path[1] + ".jar"});
	        ps.waitFor();
	        java.io.InputStream is=ps.getInputStream();
	        byte b[]=new byte[is.available()];
	        is.read(b,0,b.length);
	        data = new String(b);
	        System.out.println(data);
		}catch(Exception e) {
			
		}
		return data;
	}
}
