package authentication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

import tableEvents.InsertIntoEventLogDes;

public class AuthenticationPage {
	
	public static String login;
	public static String pass;
	public static String strSocket;
	public static String name;
	public static String link = "/broadcast.htm";
	private static String result;
	private static String event = "Аутентификация";
	
	public static void AuthentPage(String strSocket, String login, String pass, String name) {
		
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
	        
	        yc.setRequestProperty("Authorization", "Basic " + authStringEnc);
	        InputStream is = yc.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);	       
	        int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
						
			result = sb.toString();
			
			System.out.println("################### BEGIN #################");
			System.out.println(result);
			System.out.println("################### END #################");
			
	        System.out.println("#################auth is ok#######################");
		} catch (MalformedURLException e) {
			System.out.println(e);
			result=null;
			//e.printStackTrace();
		} catch (IOException e) {
			result=null;
			System.out.println(e);
			//e.printStackTrace();
		}
		new InsertIntoEventLogDes(name, event, result, LoginWindow.UserName);
		
	}

	
	
	

}
