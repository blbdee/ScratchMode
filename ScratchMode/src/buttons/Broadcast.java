package buttons;

import commands.send.CheckBoxGroup;
import gui.LogsBtnPane;
import gui.SirenPaneEth;
import tableObjects.TableModel;
import authentication.LoginWindow;
import broadcast.files.StartThreads;
import broadcast.sip.BroadcastSip;
import broadcast.sip.StatSipBroadcast;

public class Broadcast {
	
	private static String boradcastSipResult;
	private static String phoneNumbers = "";

	public static void butBroadcast() {
		
		if(LoginWindow.rdbtnEthernet.isSelected() == true) {
    		StartThreads.thr();   
    		CheckBoxGroup.butStopBroadcast.setEnabled(true);
    	}
		
    	else if(LoginWindow.rdbtnSip.isSelected() == true) { 
    		
    		System.out.println(CheckBoxGroup.uploadFileId);
			System.out.println(boradcastSipResult);  			
			phoneNumbers = "";
			
			for (int i=0; i<TableModel.phoneList.size(); i++) {
    			String phone = TableModel.phoneList.get(i);			
    			if(i!=0) {
    				phoneNumbers = phoneNumbers + "," + phone ;
    			}
    			else {
    				phoneNumbers = phoneNumbers + phone ;
    			}			
    		}					
			
			startSipBroadcast(CheckBoxGroup.uploadFileId, phoneNumbers);
    	}
		
    	else if(LoginWindow.rdbtnTfop.isSelected()==true) {
    		
    		System.out.println("ChB"+CheckBoxGroup.uploadFileId);
			System.out.println("ChB"+boradcastSipResult);  			
			phoneNumbers = "";			
			

			for(int i=0;i<TableModel.phoneTfopList.size();i++) { 
				String phone = TableModel.phoneTfopList.get(i);			
    			if(i!=0) {
    				phoneNumbers = phoneNumbers + "," + phone ;
    			}
    			else {
    				phoneNumbers = phoneNumbers + phone ;
    			}        					
			}	
			       				
    		
    		startSipBroadcast(CheckBoxGroup.uploadFileId, phoneNumbers);
		}	
    	
    	boradcastSipResult="1";
    }
	
	public static void startSipBroadcast(String uploadFileId, String phoneNumbers) {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(phoneNumbers);
    	boradcastSipResult = BroadcastSip.broadcastSip(uploadFileId, phoneNumbers);            		
    	System.out.println("ChB"+boradcastSipResult); 
		System.out.println("waiting...");    			
		
		if(LoginWindow.rdbtnSip.isSelected() == true) { 
			StatSipBroadcast.statSipBroadcast(boradcastSipResult, 0, SelectWav.fileName);
		}
		else if(LoginWindow.rdbtnTfop.isSelected()==true) {
			StatSipBroadcast.statSipBroadcast(boradcastSipResult, 0, CheckBoxGroup.event);
		}	
		
		LogsBtnPane.btnRefrash.doClick();		
	}
}
