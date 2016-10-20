package test;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ModemGetSms extends Thread {
		
	private static String serialPortModem;

	public ModemGetSms(String serialPort) {
		ModemGetSms.serialPortModem = serialPort;    	    	
    }

	public void run() {	
		
		SerialPort serialPort = new SerialPort(ModemGetSms.serialPortModem);	
		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			serialPort.writeString("AT+CMGF=1"+"\r");
			sleep(500);
			
			System.out.println(serialPort.readString());
			sleep(500);
			
			serialPort.writeString("AT+CMGL=\"ALL\""+"\r");
			sleep(1000);
			
			
			System.out.println(serialPort.readString());
							
			//serialPort.writeString("AT+CMGL=\"REC READ\""+"\r");
			//serialPort.writeString("AT+CMGR=1"+"\r");
			//sleep(500);
			
			String cutMsg = cutMsg((serialPort.readString()).replace("\n", ""));	
			
			String id = getId(cutMsg);
			System.out.println(id);	
			
			String event = getEvent(cutMsg);
			System.out.println(event);
			
			String result = getRes(cutMsg);
			System.out.println(result);
						
						
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		int pos = s.indexOf("#")+1;
		String event = s.substring(pos, pos+2);
		return event;		
	}
	
	private String getRes(String s) {	
		
		int pos = s.indexOf("OK");
		String res = s.substring(pos, pos+2);
		return res;		
	}	

	public static void main(String[] args) {
		ModemGetSms getSms = new ModemGetSms("COM14");
		getSms.start();
	}
	
}
