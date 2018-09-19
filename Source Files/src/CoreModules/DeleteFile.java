package CoreModules;

// Module Code: system.dreampackage.deletefile
import java.io.File;
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
}