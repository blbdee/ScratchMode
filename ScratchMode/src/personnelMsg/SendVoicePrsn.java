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

import tableEvents.InsertIntoEventLogDes;
import authentication.LoginWindow;

public class SendVoicePrsn {

	public SendVoicePrsn(String msgText, String phoneNumbers) {
				
		String result;
		
		Date curdate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm dd:MM:yyyy");
        String strDate = format1.format(curdate);
		System.out.println(strDate);
		
				
		try {
			
			String webPageWin = null;
			
			String webPage = "https://api.digital-direct.ru/submit_acdispatch?login=zakaz@idis.ru&pass=xo6n9kc3b&typelist=2&msisdn="
					+phoneNumbers+"&number=9057180110&startdate="+strDate+"&tts_text="+msgText+"&autocall_voice=1&autocall_successfull_call_length=3"
					+ "&autocall_max_attempts=2&autocall_attempts_interval=60&send_cg_27=1&send_cg_28=0&send_cg_29=0&send_cg_30=0&send_cg_31=0"
					+ "&byabonentdate=1&shour01=1&shour02=1&shour03=1&shour04=1&shour05=1&shour06=1&shour07=1&shour08=1&shour09=1&shour10=1&shour11=1"
					+ "&shour12=1&shour13=1&shour14=1&shour15=1&shour16=1&shour17=1&shour18=1&shour19=1&shour20=1&shour21=1&shour22=1&shour23=1&shour24=1";
			
			try {
				webPageWin = new String(webPage.getBytes("UTF-8"), "Cp1251");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
												
			System.out.println(webPageWin);
			URL url = new URL(webPageWin);
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
			System.out.println("ОШИБКА1"+e1);
			result=null;
		} catch (IOException e1) {
			System.out.println("ОШИБКА2"+e1);
			result=null;
		}
		
		if(result!=null) {			
			new InsertIntoEventLogDes("Голосовая рассылка", "Сообщение персоналу", "Успешно", LoginWindow.UserName);			
		}
		else {
			new InsertIntoEventLogDes("Голосовая рассылка", "Сообщение персоналу", "Неудачно", LoginWindow.UserName);
		}
		LogsBtnPane.btnRefrash.doClick();
	}

}
