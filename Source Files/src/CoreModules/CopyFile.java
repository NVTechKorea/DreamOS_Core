package CoreModules;

// Declaration:
// Source: https://www.journaldev.com/861/java-copy-file
import java.io.*;
import CoreServices.ErrorAnalyzer;
public class CopyFile{
    public CopyFile(){}
    public void initiate(String src, String destination){
        File source = new File(src);
        File dest = new File(destination);
        InputStream is = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        }catch(IOException e){
            ErrorAnalyzer ea = new ErrorAnalyzer();
            ea.initiate(e, "Copy file", false);
        }
    }
}