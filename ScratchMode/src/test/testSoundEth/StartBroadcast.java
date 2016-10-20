package test.testSoundEth;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class StartBroadcast extends Thread {
	
	private static Socket sock;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		int port = 54321;
		InetAddress ipAddress;
		ArrayList<String> list = new ArrayList<String>();
		list.add("192.168.0.140");
		list.add("192.168.0.141");
		list.add("192.168.0.150");
		list.add("192.168.0.194");
		list.add("192.168.0.100");
		list.add("192.168.0.153");
		
		for(int i = 0; i<list.size(); i++) {
			
			ipAddress = InetAddress.getByName(list.get(i));
			sock = new Socket(ipAddress, port);	
			new StartBroadcast().start();
			Thread.sleep(500);
		}
		
	}
	
	public void run() {
		try {
			new Broadcast(sock);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
	}

}
