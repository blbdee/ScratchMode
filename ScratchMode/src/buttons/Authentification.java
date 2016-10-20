package buttons;

import gui.LogsBtnPane;
import infoMessage.infoMessage;
import tableObjects.TableModel;
import authentication.AuthenticationPage;
import config.FromConfig;

public class Authentification {
	
	public static void butAuthentification() {
		
		//new infoMessage("Идёт процесс авторизации...", "Авторизация", 1);								
		for(int i=0; i<TableModel.ipList.size(); i++) {        			
			String ip = TableModel.ipList.get(i);
			Integer port = TableModel.portList.get(i);
			Alarm.name = TableModel.nameList.get(i);		
			String strPort = port.toString(); 
			Alarm.strSocket = ip + ":" + strPort;         			
			AuthenticationPage.AuthentPage(Alarm.strSocket, FromConfig.authLog, FromConfig.authPass, Alarm.name);        			        			        			
		}    		        		
		System.out.println("ChB"+"AUTHHHH IS OOOOOOOOOKKKKKK");	
		LogsBtnPane.btnRefrash.doClick();
		new infoMessage("Авторизация окончена.", "Авторизация", 1);  
		
	}

}
