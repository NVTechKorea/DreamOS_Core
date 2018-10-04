package Security;

import java.util.Random;
import java.io.File;
import java.util.UUID;

import CoreFramework.InfoServer;
import Security.TCEngine;
import CoreModules.ReadFile;
import CoreModules.WriteFile;

public class CheckTicket {
	public String init(boolean chkticket, String apticket) {
		InfoServer infod = new InfoServer();
		String path_bootTicket = infod.getCertainPath("system") + "authorizedBootTicket.aptic";
		String path_uuid = infod.getCertainPath("system") + "machine_uuid.aptic";
		File location = new File(path_uuid);
		if(location.exists()) {
			if(chkticket) {
				ReadFile rf = new ReadFile();
				String ticket = rf.initiate(path_bootTicket);
				String uuid = rf.initiate(path_uuid);
				TCEngine tce = new TCEngine();
				tce.silence(true);
				ticket = tce.decrypt(ticket, uuid);
				if(apticket!=null) {
					if(apticket.equals(ticket)) {
						print("Ticket is valid.");
						return "1";
					}else {
						print("This ticket isn't valid.");
						return "2";
					}
				}else {
					printw("Something went wrong while checking your ticket.");
					printw("Please report this error to the developer.");
					print("Ticket is considered as not valid.");
					return "2";
				}
			}else {
				return "0";
			}
		}else {
			Random random = new Random();
			int rand = random.nextInt(99999)+10000;		
			String generatedAPticket = "0xc9" + rand;
			WriteFile wf = new WriteFile();
			TCEngine tce = new TCEngine();
			String uuid = UUID.randomUUID().toString();
			String ticket = tce.encrypt(generatedAPticket, uuid);
			wf.initiate(path_uuid, uuid);
			wf.initiate(path_bootTicket, ticket);
			return "0";
		}
	}
	public static void print(String s) {
		System.out.println("TicketGuard [INFO]: " + s);
	}
	public static void printw(String s) {
		System.out.println("TicketGuard [WARN]: " + s);
	}
}
