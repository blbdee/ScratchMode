package broadcast.sip;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import tableEvents.InsertIntoEventLogDes;
import tableObjects.QueryToObjects;
import authentication.LoginWindow;
import equipmentType.CheckRSU;
import gui.CheckedObjPane;

public class StatSipBroadcast extends Thread {
	
	private static String boradcastSipResult;
	private static int attemptNum;
	private static String fileName;
	
	public static void statSipBroadcast(String boradcastSipResult, int attemptNum, String fileName) {
		
		StatSipBroadcast.boradcastSipResult = boradcastSipResult;
		StatSipBroadcast.attemptNum = attemptNum;
		StatSipBroadcast.fileName = fileName;
		
		StatSipBroadcast getStat = new StatSipBroadcast();
		getStat.start();		
	}
		
		
	public void run() {
		
		String webPage = "https://api.digital-direct.ru/stats_acdispatch?login=zakaz@idis.ru&pass=xo6n9kc3b&id="+boradcastSipResult;
		try {
			URL url = new URL(webPage);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			
			int numCharsRead;
			char[] array = new char[1024];
			StringBuffer sb = new StringBuffer();
			
			while((numCharsRead=isr.read(array)) > 0) {
				sb.append(array, 0, numCharsRead);				
			}			
			String str = new String(sb).replace(";;", ";");
			String str2 = str.replace("\"", "");

			System.out.println(str2);
			String s = str2.replaceAll("\\p{Cntrl}", "");
						
			String aStr[] = s.split(";");
						
			for(int i=0;i<aStr.length;i++) {
				System.out.println(i+"="+aStr[i]);
			}
			
			System.out.println("");
			
			
			int q=0;
			int w=2;
			String num = null;
			for(int i=0;i<aStr.length;i++) {
				if (w<aStr.length) {
					if(aStr[q].length()==12) {
						num = removeCharAt(aStr[q], 0);							
					}
					else if(aStr[q].length()==11) {
						num = aStr[q];						
					}			
					System.out.println(aStr[w]);
					System.out.println(num);
					String name = QueryToObjects.model.getNameTfop(num);
					System.out.println("INSEEERT!");
					new InsertIntoEventLogDes(name, "BroadcastSip", aStr[w], LoginWindow.UserName);
					q+=3;
					w+=3;					
				}				
			}	
						
						
		} catch (IOException e) {
			System.out.println(attemptNum+" - Try to get result of broadcast. Id is "+boradcastSipResult+"...");
			//сделать таймер
			if(attemptNum<250) {
				attemptNum+=1;			
				try {
					Thread.sleep(3000);
					statSipBroadcast(boradcastSipResult, attemptNum, fileName);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				System.out.println(attemptNum+"DOOOONE%%%%%%%%%%%%");
			}
		}						
	}
	
	public static String removeCharAt(String s, int pos) {
	   return s.substring(0,pos)+s.substring(pos+1);
	}

}
