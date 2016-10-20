package broadcast.files;

import gui.SirenPaneEth;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;

import timer.TimerStartRepeater;

public class BW {
	
	public static int countFiles;
	public static ArrayList<String> var;
	public static ArrayList<Socket> socketList;
	
	public BW(ArrayList<String> list, Socket sock, JLabel labelNum, int numRow) {

		countFiles = list.size();
	    var = list;	        
	    System.out.println("BW is ok");  	    
			
			DataOutputStream outD; 
	        try{
	            outD = new DataOutputStream(sock.getOutputStream());
	            System.out.println("send"+sock);
	            	            
	            outD.writeInt(BW.countFiles);//записываем файл в байтах
	                        
	            for(int i = 0; i<BW.countFiles; i++) {
	                
	            	File f = new File(BW.var.get(i));
	            	System.out.println(f);
	            	
	                outD.writeUTF("POST /bc_srazu HTTP/1.1\r\nConnection: keep-alive\r\n\r\nContent-Type: audio/wav\r\n\r\n");//отсылаем заголовок пакета
	                System.out.println("TITLEEE");
	                FileInputStream in = new FileInputStream(f);
	                byte [] buffer = new byte[64*1024];
	                int count;
	                while((count = in.read(buffer)) != -1){
	                    outD.write(buffer, 0, count);
	                    System.out.println(sock.getInetAddress()+ "= true");
	                    labelNum.setBackground(Color.GREEN);
	                    SirenPaneEth.changingPaneSiren.revalidate();
	                }
	                outD.flush();	
	                outD.close();
	                in.close();
	            }           
	        }
	        catch(IOException e){
	            e.printStackTrace();
	            System.out.println("BW= "+e);
	            if (e.toString().equals("java.net.SocketException: Socket closed")) {
	            	System.out.println("Broadcasting is over.");		                        	
	            }   
	            else {
	            	System.out.println(sock.getInetAddress()+ "= FALSE");
		            labelNum.setBackground(Color.RED);
		            SirenPaneEth.changingPaneSiren.revalidate();	           
		            new TimerStartRepeater(20, labelNum, sock.getInetAddress());
	            }
	        }
	        
	        labelNum.setBackground(Color.RED);
	        SirenPaneEth.changingPaneSiren.revalidate();
		}
	
	
	    
	            
}

	
	


