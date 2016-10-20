package buttons;

import tableObjects.TableModel;
import authentication.LoginWindow;
import commands.send.CheckBoxGroup;
import commands.send.ThreadCommandsWithAuth;
import config.FromConfig;
import equipmentType.CheckRSU;
import gui.ChooseCmdFromMemory;
import gui.LogsBtnPane;

public class AlarmFromMemory {
		
	public static void butAlarmFromMemory(String command) {
		
		new CheckRSU();		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(LoginWindow.rdbtnEthernet.isSelected()==true) {
        	for(int i=0; i<TableModel.ipList.size(); i++) {
				String ip = TableModel.ipList.get(i);
        		Integer port = TableModel.portList.get(i);
        		LogsBtnPane.name = TableModel.nameList.get(i);		
        		String strPort = port.toString(); 
        		LogsBtnPane.strSocket = ip + ":" + strPort;        		
        		LogsBtnPane.event = "Команда № "+command.substring(Math.max(command.length() - 2, 0));
        		if(LogsBtnPane.event.equals("Команда № 95")) {
        			new ThreadCommandsWithAuth(LogsBtnPane.strSocket, command, "Тестовый сигнал", LogsBtnPane.name, FromConfig.authLog, FromConfig.authPass);
        		}
        		else {
        			new ThreadCommandsWithAuth(LogsBtnPane.strSocket, command, LogsBtnPane.event, LogsBtnPane.name, FromConfig.authLog, FromConfig.authPass);	        			
        		}
        		
        		LogsBtnPane.btnRefrash.doClick();
        	}
		}
		else if (LoginWindow.rdbtnSip.isSelected()==true) {
			System.out.println(command);
			String event = command.substring(11,13);
			System.out.println(event);
			if(event.equals("95")) {
				CheckBoxGroup.sendCommandsSip(command, "Тестовый сигнал");
			}
			else {
				CheckBoxGroup.sendCommandsSip(command, "Команда № "+event);
			}			
		}	
	}

	
}
