package commands.getMessages;

import java.util.ArrayList;

import authentication.LoginWindow;
import tableEvents.InsertIntoEventLogDes;
import tableEvents.UpdateEventLog;
import tableSensors.InsertIntoSensorLog;
import tableSensors.UpdateSensorsLog;

public class ParsingMsgSensors {

	public ParsingMsgSensors(String msg) {
		
		System.out.println("Getting sms with sensors");
		
		String phone = getPhone(msg);
		System.out.println(phone);
		
		String id = getId(msg);
		System.out.println(id);
		
		String cutMsg = cutMsg(msg.replace("\n", ""));
		//System.out.println(cutMsg);
		
		String temp = getTemp(cutMsg);
		System.out.println(temp);
		
		String power = getPower(cutMsg);
		System.out.println(power);
		
		String uak1 = getUak1(cutMsg);
		System.out.println(uak1);
		
		String uak2 = getUak2(cutMsg);
		System.out.println(uak2);
		
		String ant = getAnt(cutMsg);
		System.out.println(ant);
								
		
		ArrayList<String> sensorList = new ArrayList<String>();
		sensorList.add("unknown");
		sensorList.add(power);
		sensorList.add(temp);		
		sensorList.add(uak1);
		sensorList.add(uak2);		
		sensorList.add("door");
		sensorList.add(ant);
		
		new UpdateSensorsLog(phone, power, temp, uak1, uak2, ant, "firmware", "door");
		//System.out.println("SIZE= "+sensorList.size());
		//new InsertIntoSensorLog(sensorList, phone, 1);
		//new UpdateEventLog(InsertIntoEventLogDes.numLastRow.get(i), phone, "датчики", "Успешно", LoginWindow.UserName);
		/*for(int i=0;i<InsertIntoEventLogDes.numLastRow.size();i++) {
			System.out.println("IIIII"+i);
			System.out.println("NUUUM"+InsertIntoEventLogDes.numLastRow.get(i));
			System.out.println("SIIZE"+InsertIntoEventLogDes.numLastRow.size());
			System.out.println();
			new UpdateSensorsLog(InsertIntoEventLogDes.numLastRow.get(i), phone, event, "Успешно", LoginWindow.UserName);	
		}*/
				
		
	}
	
	private String cutMsg(String s) {	
		
		int pos = s.indexOf("ID");
		String msg = s.substring(pos+3,s.length());
				
		return msg;		
	}
	
	private String getId(String s) {	
		
		int pos = s.indexOf(">");
		String id = s.substring(0, pos);
		
		return id;		
	}
	
	private String getTemp(String s) {	
		
		int pos = s.indexOf("T=")+2;
		String temp = s.substring(pos, pos+4);
		
		return temp;		
	}
	
	private String getPower(String s) {	
		
		int pos = s.indexOf("220")+6;
		String temp = s.substring(pos, pos+3);
		
		return temp;		
	}
	
	private String getUak1(String s) {	
		
		int pos = s.indexOf("Uak1")+5;
		String uak1 = s.substring(pos, pos+6);
		
		return uak1;		
	}
	
	private String getUak2(String s) {	
		
		int pos = s.indexOf("Uak2")+5;
		String uak2 = s.substring(pos, pos+6);
		
		return uak2;		
	}
	
	private String getAnt(String s) {	
		
		int pos = s.indexOf("Ant")+4;
		String ant = s.substring(pos, pos+3);
		
		return ant;		
	}
	
	private String getPhone(String s) {	
		
		int pos = s.indexOf("\"+");
		String phone = s.substring(pos+2, pos+13);
		
		return phone;		
	}	

}
