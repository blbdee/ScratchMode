package broadcast.files;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import tableObjects.TableModel;
import equipmentType.CheckRSU;
import gui.CheckedObjPane;
import gui.SirenPaneEth;

public class StartThreads {
		
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();
	public static Socket sock;	
	public static Integer port;
	public static Integer numRow;
	
	public static void thr() {
		
		
		if (CheckedObjPane.dlm.size() > 0) {
			
			new CheckRSU();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i=0; i<TableModel.ipList.size(); i++) {			
						
			String addres = TableModel.ipList.get(i);
			port = TableModel.portList.get(i);
			numRow = TableModel.rowsList.get(i);
	        InetAddress ipAddress = null;	       	        
	        
	        try {
	            ipAddress = InetAddress.getByName(addres);
	            sock = new Socket(ipAddress, port);	            
	            sockets.add(sock);	            
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	            System.out.println("STH1= "+e);
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("STH2= "+e);
	        }
		
			Thread t1 = new Main(sock, SirenPaneEth.labels.get(i), numRow);
			t1.start();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("STH3= "+e);
			}
		}
	}

}
