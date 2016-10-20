package commands.send;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

import tableEvents.InsertIntoEventLogDes;
import authentication.LoginWindow;

public class ThreadCommandsWithAuth {
	
	public static String command;
	public static String result;

	
	public ThreadCommandsWithAuth(String strSocket, String command, String event, String name, String login, String pass) {
		
		ThreadCommandsWithAuth.command = strSocket +command;	
		
					
		
		System.out.println("EVENT= "+event);
		
		try {
						
			URL oracle = new URL("http://" + ThreadCommandsWithAuth.command);
			System.out.println(oracle);
			String authString = login + ":" + pass;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());			
			String authStringEnc = new String(authEncBytes);
	        
			URLConnection yc = oracle.openConnection();
			
			yc.setConnectTimeout(5000);
	        yc.setRequestProperty("Host", strSocket);	
	        yc.setRequestProperty("Cache-Control", "max-age=0");
	        yc.setRequestProperty("Content-Type", "text/html;"+"charset:utf-8");
	        yc.setRequestProperty("Accept-Encoding", "gzip,deflate");
	        yc.setRequestProperty("Authorization", "Basic " + authStringEnc);
	        yc.setRequestProperty("Upgrade-Insecure-Requests", "1");
	        
	        InputStream is = yc.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);	       
	        int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			is.close();
			isr.close();
			
			result = sb.toString();		
			
			System.out.println("################### BEGIN #################");
			System.out.println(result);
			System.out.println("################### END #################");
			
	        System.out.println("#################auth is ok#######################");
	    }
		catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Œÿ»¡ ¿"+e);
			result=null;						
		} 
		
		catch (IOException e) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ START IOEXCEPTION @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			e.printStackTrace();				
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ END IOEXCEPTION @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			result=null;
		}
		finally {
			System.out.println(LoginWindow.UserName);
			new InsertIntoEventLogDes(name, event, result, LoginWindow.UserName);
		}
		

    }



}
