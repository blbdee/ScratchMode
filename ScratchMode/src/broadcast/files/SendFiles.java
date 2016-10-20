package broadcast.files;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class SendFiles {
	
	private static Socket sock;
	private static FileInputStream in;
	
	public static void Send1(Socket sock) {
		
		SendFiles.sock = sock;		
		
		
		if (SendFiles.sock.isConnected() == true) {
		
			DataOutputStream outD; 
	        try{
	            outD = new DataOutputStream(SendFiles.sock.getOutputStream());
	            System.out.println("send"+SendFiles.sock);
	            	            
	            outD.writeInt(BW.countFiles);//���������� ���� � ������
	                        
	            for(int i = 0; i<BW.countFiles; i++) {
	                
	            	File f = new File(BW.var.get(i));
	            	System.out.println(f);
	            	
	                outD.writeUTF("POST /bc_srazu HTTP/1.1..Content-Type: audio/wav....");//�������� ��������� ������
	            
	                in = new FileInputStream(f);
	                byte [] buffer = new byte[64*1024];
	                int count;
	                
	                while((count = in.read(buffer)) != -1){
	                    outD.write(buffer, 0, count);
	                    System.out.println(SendFiles.sock.getInetAddress()+ "= true");
	                }
	                outD.flush();
	                //in.close(); ����������
	                
	                
	            }           
	        }
	        catch(IOException e){
	            e.printStackTrace();
	            System.out.println("SF= "+e);
	            System.out.println(SendFiles.sock.getInetAddress()+ "= FALSE");
	        }
		}
		
		else {
			System.out.println("Socket"+StartThreads.sock+"is closed");
		}
					
	}
}
	  
	
		

