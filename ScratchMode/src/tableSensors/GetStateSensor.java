package tableSensors;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;
import authentication.AuthenticationPage;

public class GetStateSensor {
	
	public static String login;
	public static String pass;
	public static String strSocket;
	public static String name;
	public static String link = "/broadcast.htm";
	
	public static void GetSensor(String strSocket, String login, String pass, String name, Integer counter) {
		
		
		AuthenticationPage.strSocket = strSocket;
		AuthenticationPage.login = login;
		AuthenticationPage.pass = pass;
		AuthenticationPage.name = name;	
		
       
		try {			
			
			URL oracle = new URL("http://"+strSocket);
			String authString = login + ":" + pass;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());			
			String authStringEnc = new String(authEncBytes);
	        URLConnection yc = oracle.openConnection();
	        
	        yc.setConnectTimeout(5000);
	        yc.setRequestProperty("Authorization", "Basic " + authStringEnc);
	        InputStream is = yc.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);	
/*L*/       //InputStreamReader isr = new InputStreamReader(yc.getInputStream(), "windows-1251");	
	        int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);				
			}
			
					
				
				GetSingleSensor.sensorList.clear();
		        
		        int sensor6 = sb.lastIndexOf("Версия");
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor6+18, 11, sb);
		        
		        int sensor4 = sb.lastIndexOf("Питание");	        
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor4+21, 4, sb);
		        
		        int sensor3 = sb.lastIndexOf("Температура");	   
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor3+15, 4, sb);
		        	        
		        int sensor1 = sb.lastIndexOf("аккумулятора 1");	     
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor1+23, 6, sb);
		        
		        int sensor2 = sb.lastIndexOf("аккумулятора 2");	 
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor2+23, 6, sb);
		        
		        int sensor5 = sb.lastIndexOf("Корпус");	    
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor5+8, 6, sb);
		        
		        int sensor7 = sb.lastIndexOf("сигнала");	    
		        GetSingleSensor.sensorStr = "";
		        new GetSingleSensor(sensor7+10, 3, sb);
		        
		        new InsertIntoSensorLog(GetSingleSensor.sensorList, name, counter);
	        
		} catch (MalformedURLException e) {
			System.out.println(e);		
			for (int i=0; i<7;i++) {
				GetSingleSensor.sensorList.add("0");					
			}
			new InsertIntoSensorLog(GetSingleSensor.sensorList, name, counter);			
		} catch (IOException e) {			
			System.out.println(e);
			GetSingleSensor.sensorList.clear();
			for (int i=0; i<7;i++) {
				GetSingleSensor.sensorList.add("0");					
			}
			new InsertIntoSensorLog(GetSingleSensor.sensorList, name, counter);
		}
		
	}
	
	
	


}
