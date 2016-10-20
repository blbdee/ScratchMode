package commands.send;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import tableEvents.InsertIntoEventLogDes;
import authentication.LoginWindow;

public class ThreadCommands {
	
	public static String command;
	public static String result;

	
	public ThreadCommands(String strSocket, String command, String event, String name) {
		
		ThreadCommands.command = strSocket +command;	
					
		
		//System.out.println(this.command);
		System.out.println("EVENT= "+event);
		//System.out.println(this.name);
		try {
			
			
			//вот тут многопоточность с новым циклом получением нужных данных из массивов
			String webPage = "http://" + ThreadCommands.command;
			System.out.println(webPage);			
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			new InputStreamReader(is);						
			int numCharsRead = 0;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();			
			//while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			//}
			result = sb.toString();		
	    }
		catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("ОШИБКА"+e);
			result=null;						
		} 
		
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("ОШИБКА"+e);
			result=null;	
		}
		System.out.println(LoginWindow.UserName);
		new InsertIntoEventLogDes(name, event, result, LoginWindow.UserName);

    }



}
