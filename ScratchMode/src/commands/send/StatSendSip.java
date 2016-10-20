package commands.send;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class StatSendSip {
	
	public static String result;
	
	public static String StatSip(String result) {
		
		try {
			
			String webPage = "http://api.digital-direct.ru/status_dispatch?login=zakaz@idis.ru&pass=xo6n9kc3b&id="+result;
													
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
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
			System.out.println(result);
			System.out.println("*** END ***");
		} catch (MalformedURLException e1) {
			System.out.println("Œÿ»¡ ¿1"+e1);
			result=null;
		} catch (IOException e1) {
			System.out.println("Œÿ»¡ ¿2"+e1);
			result=null;
		}	
		return result;
	}
}
