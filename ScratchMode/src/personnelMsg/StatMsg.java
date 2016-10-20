package personnelMsg;

import gui.LogsBtnPane;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import authentication.LoginWindow;
import tableEvents.InsertIntoEventLogDes;

public class StatMsg {
		
	public static void GetStatMsg(String res) {
		
		String result;
		
		try {			
			
			String webPage = "https://api.digital-direct.ru/status_dispatch?login=zakaz@idis.ru&pass=xo6n9kc3b&id="+res;																			
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
			//e1.printStackTrace();
			System.out.println("ОШИБКА1"+e1);
			result=null;
		} catch (IOException e1) {
			//e1.printStackTrace();
			System.out.println("ОШИБКА2"+e1);
			result=null;
		}
		if(result!=null) {			
			new InsertIntoEventLogDes("Смс рассылка", "Сообщение персоналу", "Успешно", LoginWindow.UserName);			
		}
		else {
			new InsertIntoEventLogDes("Смс рассылка", "Сообщение персоналу", "Неудачно", LoginWindow.UserName);
		}
		LogsBtnPane.btnRefrash.doClick();
	}

}
