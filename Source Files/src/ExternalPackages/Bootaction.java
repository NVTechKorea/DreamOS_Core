package ExternalPackages;
// Module Code: system.dneo.bootaction
// Copyright (C) Dynamic Neo Engines 2014 - 2018
public class Bootaction{
	public void init(String[] injectedActions) {
		print("Querying injected actions...");
		if(injectedActions != null) {
			if(injectedActions.length != 0) {
				runInjectedActions(injectedActions);
			}else {
				print("No injected action found.");
			}
		}
	}
	public void runInjectedActions(String[] injectedActions) {
		
	}
	public static void print(String s) {
		System.out.println("Bootaction [INFO]: " + s);
	}
}