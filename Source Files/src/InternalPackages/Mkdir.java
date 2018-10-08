package InternalPackages;
import CoreModules.MakeDir;
public class Mkdir {
	public void init(String[] arg) {
		MakeDir md = new MakeDir();
		md.initiate(arg[1]);
	}
	public void print(String s) {
		System.out.println(s);
	}
}
