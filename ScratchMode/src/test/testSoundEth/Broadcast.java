package test.testSoundEth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Broadcast {
		
	
	public Broadcast(Socket sock) throws InterruptedException {
				
		File f = new File("C:\\Users\\Admin\\workspace\\ScratchMode\\audioDecWav.wav");
       	System.out.println(f);
        	
       	try {
			DataOutputStream outD = new DataOutputStream(sock.getOutputStream());
			
            outD.writeUTF("POST /bc_srazu HTTP/1.1\r\nConnection: keep-alive\r\n\r\nContent-Type: audio/wav\r\n\r\n");
            Thread.sleep(200);
            FileInputStream in = new FileInputStream(f);
            byte [] buffer = new byte[320*1024];
            int count;
            InputStream inD = sock.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inD));
            
            while((count = in.read(buffer)) != -1){
                outD.write(buffer, 0, count);
                System.out.println(sock.getInetAddress()+ "= true"+br.readLine());
            }
            outD.flush();
            outD.close();
            inD.close();
            in.close();      
		} catch (IOException e) {
			e.printStackTrace();
		}
        	
       
	}		
	
	
	    
	            
}