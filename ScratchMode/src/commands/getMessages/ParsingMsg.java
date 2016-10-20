package commands.getMessages;

import authentication.LoginWindow;
import tableEvents.InsertIntoEventLogDes;
import tableEvents.UpdateEventLog;

public class ParsingMsg {

	public ParsingMsg(String msg) {
		
		System.out.println("Getting sms with answer");
		
		String phone = getPhone(msg);
		System.out.println("Phone= "+phone);
		
		String cutMsg = cutMsg(msg.replace("\n", ""));
		
		String id = getId(cutMsg);
		System.out.println("Id= "+id);	
		
		String event = getEvent(cutMsg);
		System.out.println("Command= "+event);
		
		if((event.equals("2:"))|(event.equals("s1"))) {
			event="Внимание всем";
		} else if((event.equals("3:"))|(event.equals("s2"))) {
			event="Воздушная тревога";
		} else if((event.equals("6:"))|(event.equals("s4"))) {
			event="Отбой";
		} else if(event.equals("95")|(event.equals("s3"))) {
			event="Тестовый сигнал";
		} else {
			event="Команда №"+event;
		}
						
		for(int i=0;i<InsertIntoEventLogDes.numLastRow.size();i++) {
			new UpdateEventLog(InsertIntoEventLogDes.numLastRow.get(i), phone, event, "Успешно", LoginWindow.UserName);	
		}	
		
	}
	
	private String cutMsg(String s) {	
		
		int pos = s.indexOf("ID");
		String msg = s.substring(pos+3,s.length());
				
		return msg;		
	}
	
	private String getId(String s) {	
		
		int pos = s.indexOf(":");
		String id = s.substring(0, pos);
		
		return id;		
	}
	
	private String getEvent(String s) {	
		
		String event = null;
		if((s.length()==20)|(s.length()>30)) {
			int pos = s.indexOf("#")+1;
			event = s.substring(pos, pos+2);
		}
		else {
			int pos = s.indexOf("Cmd")+4;
			event = s.substring(pos, pos+2);
		}
		
		return event;		
	}
	
	private String getPhone(String s) {	
		
		int pos = s.indexOf("\"+");
		String phone = s.substring(pos+2, pos+13);
		
		return phone;		
	}

}
