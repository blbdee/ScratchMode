package commands.getMessages;

import gui.LogsBtnPane;

import java.util.ArrayList;

import jssc.SerialPort;
import jssc.SerialPortException;

public class CopyOfGetMessage extends Thread {
	
	private static String modemPort;
	private ArrayList<String> msgList = new ArrayList<String>();
	private static SerialPort serialPort;	
	
	public CopyOfGetMessage(String modemPort) {
		CopyOfGetMessage.modemPort = modemPort; 
	}
	
	public void run() {			
		
		try {
			
			serialPort = new SerialPort(modemPort);			
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			while (serialPort.isOpened()) {
				serialPort.writeString("AT+CMGF=1"+"\r");
				sleep(500);
				
				int j=0;
				String s;
				while(j<10) {
					j++;
					serialPort.writeString("AT+CMGR="+j+"\r");
					Thread.sleep(500);
					s = serialPort.readString();
					//System.out.println("SSSSSSSSSSS= "+s);
					if(s.length()>40) {
						System.out.println(s);
						msgList.add(s);
					}
					
				}
				
				for(int i=0;i<msgList.size();i++) {
					if(msgList.get(i).contains("ID#")) {	
						
						if(msgList.get(i).length()<170) {
							new ParsingMsg(msgList.get(i));
						}
						else {
							new ParsingMsgSensors(msgList.get(i));
						}						
					}				
				}	
				
				if(msgList.size()>0) {
					clearModemMemory();		
					LogsBtnPane.btnRefrash.doClick();
				}				
				msgList.clear();
			}					
				
		} catch (SerialPortException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	public void clearModemMemory() throws SerialPortException {
		serialPort.writeString("AT+CMGD=1,2"+"\r");
	}

}
