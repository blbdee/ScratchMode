package test.SipLapsha;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendFiles {
	
	private static Socket sock;
	private static FileInputStream in;
	
	public SendFiles(String file) {
		
		try {
			sock = new Socket("192.168.0.111",54321);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			DataOutputStream outD; 
	        try{
	            outD = new DataOutputStream(SendFiles.sock.getOutputStream());
	            System.out.println("send"+SendFiles.sock);
	            
	            	File f = new File(file);
	            	System.out.println(f);
	            	
	                outD.writeUTF("POST /bc_srazu HTTP/1.1..Content-Type: audio/wav....");//отсылаем заголовок пакета
	            
	                in = new FileInputStream(f);
	                byte [] buffer = new byte[64*1024];
	                int count;
	                
	                while((count = in.read(buffer)) != -1){
	                    outD.write(buffer, 0, count);
	                    System.out.println(SendFiles.sock.getInetAddress()+ "= true");
	                }
	                outD.flush();
	                //in.close(); стабильнее
	                
	                
	                      
	        }
	        catch(IOException e){
	            e.printStackTrace();
	            System.out.println("SF= "+e);
	            System.out.println(SendFiles.sock.getInetAddress()+ "= FALSE");
	        }
		}		
	
	public static void main(String[] args) {
		new SendFiles("C:\\Users\\Admin\\Desktop\\x64\\95_8.wav");
	}
	
}
	  
	
		

