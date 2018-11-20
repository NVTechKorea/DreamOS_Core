package CoreServices;
import commands.Shutdown;
// Module Code: system.dreampackage.erroranalyzer
public class PanicHandler{
	public PanicHandler(){}
	public void initiate(Exception e, String process, boolean debug){
		String er = e.toString();
		uiprint("Your system ran into unhandled exceptions and has to be stopped.");
		uiprint("Panic Records================");
		uiprint("Process: " + process);
		if(er.contains("NullPointerException")){
			uiprint("NullPointerException found.");
			uiprint("Please check all the variables are not null, or contact to software manufacturer.");
		}else if (er.contains("ArrayIndexOutOfBoundsException")) {
			uiprint("ArrayIndexOutOfBoundsException found.");
			uiprint("Please check whether you entered too many items.");
		}else if (er.contains("NoClassDefFoundError")) {
			uiprint("NoClassDefFoundError found.");
		}else if (er.contains("FileNotFoundException")) {
			uiprint("FileNotFoundException found.");
			uiprint("Please check all resource files are located correctly.");
		}else if (er.contains("ClassNotFoundException")) {
			uiprint("ClassNotFoundException found.");
			uiprint("Please check wheter you've renamed system file, or script is compiled.");
		}else{
			uiprint("Unknown error. Running printStackTrace only.");
			e.printStackTrace();
		}
		uiprint("=============================");
		if(true){
			uiprint("/////////////PANIC/////////////");
			e.printStackTrace();
			uiprint("///////////////////////////////");	
			uiprint("Please report this error to the developer. Thanks.");
		}
		Shutdown.init("");
	}
	public static void uiprint(String s){
		System.out.println("PanicHandler [INFO]: " + s);
	}
}