package CoreServices;
import java.util.Scanner;
public class GetInput {
	Scanner input = new Scanner(System.in);
	public String init() {
		String s = input.nextLine();
		return s;
	}
}
