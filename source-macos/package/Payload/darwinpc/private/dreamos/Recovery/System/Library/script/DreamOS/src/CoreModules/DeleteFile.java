package CoreModules;

// Module Code: system.dreampackage.deletefile
import java.io.File;
import java.util.Scanner;
public class DeleteFile{
	public DeleteFile(){}
	public void initiate(String path, boolean silent){
		File file = new File(path);
		if(file.delete()){
			if(!silent){
				System.out.println("Successfully deleted: " + path);
			}
		}else{
			System.out.println("Deleting failure: " + path);
		}
	}
	 public boolean checkConnection(String process) {
	    	System.out.println("DeleteFile [NOTIFY]: Process " + process + " is trying to access Writing permission. Type yes to authorize, type n to reject.");
	    	Scanner input = new Scanner(System.in);
	    	String i = input.nextLine();
	    	input.close();
	    	if(i.equals("yes")) {
	    		return true;
	    	}else {
	    		return false;
	    	}
	    	
	    }
}