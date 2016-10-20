package commands.send;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SendCommandSip {
	
	public static String result;
	
	public static String SendSip(String phoneNumbers, String command, String event) {
		
		Date curdate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm dd:MM:yyyy");
        String strDate = format1.format(curdate);
		System.out.println(strDate);

		try {
			
			String webPage = "https://api.digital-direct.ru/submit_dispatch?login=zakaz@idis.ru&pass=xo6n9kc3b&typelist=2&msisdn="+phoneNumbers+""
					+ "&mess="+command+"&isurl=0&startdate="+strDate+"&shour00=1&shour01=1&shour02=1&shour03=1&shour04=1&shour05=1&shour06=1"
					+ "&shour07=1&shour08=1&shour09=1&shour10=1&shour11=1&shour12=1&shour13=1&shour14=1&shour15=1&shour16=1&shour17=1&shour18=1"
					+ "&shour19=1&shour20=1&shour21=1&shour22=1&shour23=1&shour24=1";
							
			//System.out.println(webPage);
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
			System.out.println("Œÿ»¡ ¿1"+e1);
			result=null;
		} catch (IOException e1) {
			//e1.printStackTrace();
			System.out.println("Œÿ»¡ ¿2"+e1);
			result=null;
		}		
		return result;
	}
}
