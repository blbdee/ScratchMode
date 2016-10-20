package broadcast.sip;

import gui.LogsBtnPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import buttons.SelectWav;
import tableEvents.InsertIntoEventLogDes;
import authentication.LoginWindow;

public class UploadSipFile {
		
	private static HttpURLConnection httpConn;
    private static OutputStream outputStream;
    private static PrintWriter writer;
    private static String charset = "UTF-8";
    private static String uploadFileId;
    
	public static String uploadSipFile(File file) {
		
		List<String> response = new ArrayList<String>();
		String result = null;
					        
	    try {           
	    	URL url = new URL("https://api.digital-direct.ru/upload_ac_file?");
		    httpConn = (HttpURLConnection) url.openConnection();
		    httpConn.setDoOutput(true);
		    httpConn.setRequestProperty("Host", "api.digital-direct.ru");
		    httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary1BEF0A57BE110FD467A");
		    httpConn.setRequestProperty("Cache-Control", "max-age=0");
		    httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		    httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");		        
		        
		    outputStream = httpConn.getOutputStream();
		    writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
	        writer.append("------WebKitFormBoundary1BEF0A57BE110FD467A\r\nContent-Disposition: form-data; name=\"login\"\r\n\r\nzakaz@idis.ru\r\n");
		    writer.append("------WebKitFormBoundary1BEF0A57BE110FD467A\r\nContent-Disposition: form-data; name=\"pass\"\r\n\r\nxo6n9kc3b\r\n");
		    writer.append("------WebKitFormBoundary1BEF0A57BE110FD467A\r\nContent-Disposition: form-data; name=\"filename\"\r\n\r\ntemp\r\n");
		    writer.append("------WebKitFormBoundary1BEF0A57BE110FD467A\r\nContent-Disposition: form-data; name=\"file\"; filename=\""+file.getName()+"\"\r\n");		        
		    writer.append("Content-Type: audio/mp3\r\n\r\n");
		    writer.flush();
		        
		    FileInputStream inputStream = new FileInputStream(file);
		    byte[] buffer = new byte[4096];
		    int bytesRead = -1;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outputStream.write(buffer, 0, bytesRead);
		    }
	        outputStream.flush();
	        inputStream.close();	         
	        writer.append("\r\n------WebKitFormBoundary1BEF0A57BE110FD467A--\r\n");
	        writer.flush();
	            
	        int status = httpConn.getResponseCode();
	        System.out.println(httpConn.getResponseMessage());
	        
	        System.out.println("STATUS= "+status);
	        if (status == HttpURLConnection.HTTP_OK) {
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
	                
	            while ((uploadFileId = reader.readLine()) != null) {
	            	System.out.println(uploadFileId);
	                response.add(uploadFileId);
	            }	               
	            reader.close();
	            httpConn.disconnect();
	            result="Успешно";	        
	        } else {
	         	result="Неудачно";
	            throw new IOException("Server returned non-OK status: " + status);
	        }
		        	            
	    } catch (UnknownHostException e) {
	            e.printStackTrace();
	    } catch (IOException e) {
	            e.printStackTrace();
	    }
	        
	    new InsertIntoEventLogDes("file.mp3", "Загрузка файла", result, LoginWindow.UserName);
	    LogsBtnPane.btnRefrash.doClick();
	    
	   
	    return response.get(0);	    
	}
}
