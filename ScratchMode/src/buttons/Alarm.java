package buttons;

import broadcast.sip.BroadcastSip;
import gui.LogsBtnPane;
import tableObjects.TableModel;
import authentication.LoginWindow;
import commands.send.CheckBoxGroup;
import commands.send.ThreadCommandsWithAuth;
import config.FromConfig;

public class Alarm extends Thread{
	
	public static String name;
	public static String strSocket;
	private static String staticEvent;
	private static String staticCommand;
	
	public static void butAlarm() {
		
		if(LoginWindow.rdbtnEthernet.isSelected()==true) {
			
			staticEvent = CheckBoxGroup.event;
			staticCommand = CheckBoxGroup.command;
			
        	for(int i=0; i<TableModel.ipList.size(); i++) {
        			
        		String ip = TableModel.ipList.get(i);		        		
        		Integer port = TableModel.portList.get(i);		        			
        		name = TableModel.nameList.get(i);
        		String strPort = port.toString(); 
        		strSocket = ip + ":" + strPort;
        		
        		new ThreadCommandsWithAuth(strSocket, staticCommand, staticEvent, name, FromConfig.authLog, FromConfig.authPass);
        		LogsBtnPane.btnRefrash.doClick(); 
        	} 
		}	        	
    	else if (LoginWindow.rdbtnSip.isSelected()==true) {	        		
    		
    		CheckBoxGroup.sendCommandsSip(CheckBoxGroup.command, CheckBoxGroup.event);     		    		
		}
		
    	else if (LoginWindow.rdbtnTfop.isSelected()==true) {	
    		
    		String phoneNumbers = "";
    		for(int i=0;i<TableModel.phoneTfopList.size();i++) { 
				String phone = TableModel.phoneTfopList.get(i);			
				if(i!=0) {
					phoneNumbers = phoneNumbers + "," + phone ;
				}
				else {
					phoneNumbers = phoneNumbers + phone ;
				}        					
			}    		
    		Broadcast.startSipBroadcast(CheckBoxGroup.command, phoneNumbers);	    		
		}
    	
    	LogsBtnPane.btnRefrash.doClick();        		     		
    	System.out.println("ChB"+"OOOOOOOOOKKKKKK");
		
	}
	
	public void run() {
		butAlarm();		
	}

}
