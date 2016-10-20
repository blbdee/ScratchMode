package commands.send;

import java.net.*;
import java.io.*;

import org.apache.commons.codec.binary.Base64;

import buttons.Alarm;

public class MakeLinkWithSound {
	
	public static String result;
	
    public static void Sound() throws Exception {
        
		try {
			String ip = Alarm.strSocket;
			String command = CheckBoxGroup.command;
			String webPage = "http://"+ip+command;
			
			
			String name = "123";
			String password = "4567";

			String authString = name + ":" + password;
			System.out.println("auth string: " + authString);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			System.out.println("Base64 encoded auth string: " + authStringEnc);

			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			
			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();

			System.out.println("*** BEGIN ***");
			//System.out.println(result); вывод результата запроса
			System.out.println("*** END ***");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("ОШИБКА1");
			result=null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ОШИБКА2");
			result=null;
		}
    }
}

