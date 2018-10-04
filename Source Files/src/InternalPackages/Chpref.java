package InternalPackages;

public class Chpref {
	public void init(String[] arg) {
		if(!in.contains(" ")){
			print("Wrong format!");
			print("Usage: chpref [option name / all] [option]");
		}else{
			String inParse[] = in.split(" ");
			int leng = inParse.length; 
			if(leng==3){
				chpref(inParse[1], inParse[2]);
			}else{
				print("Wrong format!");
				print("Usage: chpref [option name / all] [option]");
			}
		}
	}
	public void print(String s) {
		System.out.println(s);
	}
}
