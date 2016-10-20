package buttons;

import gui.LogsBtnPane;
import tableObjects.TableModel;

import commands.send.ThreadCommandsWithAuth;

import config.FromConfig;

public class StopFromMemory {
		
	public static void butStopFromMemory() {
		
		for(int i=0; i<TableModel.ipList.size(); i++) {
			String ip = TableModel.ipList.get(i);
    		Integer port = TableModel.portList.get(i);
    		LogsBtnPane.name = TableModel.nameList.get(i);		
    		String strPort = port.toString(); 
    		LogsBtnPane.strSocket = ip + ":" + strPort;
    		System.out.println(LogsBtnPane.strSocket);
    		String command = "/stop.htm";
    		LogsBtnPane.event = "Отбой";
			System.out.println(command);
    		new ThreadCommandsWithAuth(LogsBtnPane.strSocket, command, LogsBtnPane.event, LogsBtnPane.name, FromConfig.authLog, FromConfig.authPass);	
    		LogsBtnPane.btnRefrash.doClick();
    	}
	}

}
