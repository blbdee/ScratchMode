package broadcast.files;

import java.io.IOException;
import java.net.Socket;

public class CloseSockets {
	
	public static void CloseSock() {
		for (Socket sock : StartThreads.sockets) {
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
	}

}
