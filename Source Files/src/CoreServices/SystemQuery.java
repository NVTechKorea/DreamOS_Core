package CoreServices;
public class SystemQuery {
	public String getDeepSystemInfo(String s) {
		String data = "dream.errors.NoQueryDataFound";
		if(s.equals("rom")) {
			data = "ROM: DreamOS-BaseKernel";
		}else if(s.equals("kernel")) {
			data = "KERNEL: Alps10x-71.042";
		}
		return data;
	}
}
