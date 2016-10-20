/*package broadcast.voiceOnline;

import java.io.IOException;

public class ThreadSend extends Thread {

	public ThreadSend() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
		try {
			VoiceOnline2.outs.get(1).write(VoiceOnline2.buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("I'm start");
		int bytesRead = 0;
		int numBytesRead = 512;
		for (int j=0;j<VoiceOnline2.outs.size();j++) {			
			try {
				VoiceOnline2.in.read(VoiceOnline2.buf, 0, VoiceOnline2.CHUNK_SIZE);
				bytesRead += numBytesRead;
				VoiceOnline2.outs.get(j).write(VoiceOnline2.buf, 0, numBytesRead);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 						
		}
	}
}*/
