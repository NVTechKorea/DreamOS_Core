package CoreFramework;
import java.util.Scanner;
public class GetInput {
	Scanner input = new Scanner(System.in);
	public String init() {
		String s = null;
		try {
			s = input.nextLine();
		}catch(Exception e) {
			PanicHandler ea = new PanicHandler();
			ea.initiate(e, "Input Manager", false);
		}
		return s;
	}
}
