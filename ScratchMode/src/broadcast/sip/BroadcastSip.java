package broadcast.sip;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import tableObjects.TableModel;
import equipmentType.CheckRSU;
import gui.CheckedObjPane;

public class BroadcastSip {
	
	private static String result="";
		
	public static String broadcastSip(String uploadFileId, String phoneNumbers) {
		
		if (CheckedObjPane.dlm.size() > 0) {			
			new CheckRSU();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		
		Date curdate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm dd:MM:yyyy");
        String strDate = format1.format(curdate);
		System.out.println(strDate);
		
	
		try {
			
			String webPage = "https://api.digital-direct.ru/submit_acdispatch?login=zakaz@idis.ru&pass=xo6n9kc3b&typelist=2&msisdn="
					+phoneNumbers+"&number=9057180110&file_id="+uploadFileId+"&autocall_successfull_call_length=25&autocall_max_attempts=3&autocall_attempts_interval=60"
					+ "&send_cg_27=1&send_cg_28=1&send_cg_29=1&send_cg_30=1&send_cg_31=1&startdate="+strDate+"&byabonentdate=1&shour01=0&shour01=1&shour02=1"
					+ "&shour03=1&shour04=1&shour05=1&shour06=1&shour07=1&shour08=1&shour09=1&shour10=1&shour11=1&shour12=1&shour13=1&shour14=1"
					+ "&shour15=1&shour16=1&shour17=1&shour18=1&shour19=1&shour20=1&shour21=1&shour22=1&shour23=1&shour24=1";
		
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
			
		} catch (MalformedURLException e1) {
			System.out.println("Œÿ»¡ ¿1"+e1);
			if (result.length()<1) {
				result="";
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				broadcastSip(uploadFileId, phoneNumbers);
			}			
				
		} catch (IOException e1) {
			System.out.println("Œÿ»¡ ¿2"+e1);
			if (result==null) {
				result="";
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				broadcastSip(uploadFileId, phoneNumbers);
			}		
		}
		
		System.out.println("result"+result);
		return result;
		
	}

}
