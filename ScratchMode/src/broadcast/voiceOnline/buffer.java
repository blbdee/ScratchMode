/*package broadcast.voiceOnline;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class buffer extends Thread {
	
	private static byte[] buf;
	private static DataOutputStream out;
	private static DataOutputStream out2;
	private static DataOutputStream out3;
	private static DataOutputStream out4;
	private static DataOutputStream out5;
	private static DataOutputStream out6;
	private static DataOutputStream out7;
	
	public static int CHUNK_SIZE = 512;
	public void run() {
		try {
			buf = new byte[16*1024];
			
			Socket sock = new Socket("192.168.0.150",54321);
			out = new DataOutputStream(sock.getOutputStream());
			out.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.150:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			Socket sock2 = new Socket("192.168.0.194",54321);
			out2 = new DataOutputStream(sock2.getOutputStream());
			out2.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			Socket sock3 = new Socket("192.168.0.153",54321);
			out3 = new DataOutputStream(sock3.getOutputStream());
			out3.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			Socket sock4 = new Socket("192.168.0.157",54321);
			out4 = new DataOutputStream(sock4.getOutputStream());
			out4.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			Socket sock5 = new Socket("192.168.0.100",54321);
			out5 = new DataOutputStream(sock5.getOutputStream());
			out5.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			Socket sock6 = new Socket("192.168.0.146",54321);
			out6 = new DataOutputStream(sock6.getOutputStream());
			out6.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			Socket sock7 = new Socket("192.168.0.141",54321);
			out7 = new DataOutputStream(sock7.getOutputStream());
			out7.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			

			
			int bytesRead = 0;
	        while (bytesRead < 1000000000) {
	        	VoiceOnline.in.read(buf, 0, CHUNK_SIZE);
	        	int numBytesRead = 512;
	            bytesRead += numBytesRead;

	            out.write(buf, 0, numBytesRead); 
	            out2.write(buf, 0, numBytesRead);
	            out3.write(buf, 0, numBytesRead);
	            out4.write(buf, 0, numBytesRead);
	            out5.write(buf, 0, numBytesRead);
	            out6.write(buf, 0, numBytesRead);
	            out7.write(buf, 0, numBytesRead);
	            System.out.println(bytesRead);
	            
	            
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


/*Socket sock2 = new Socket("192.168.0.194",54321);
			out2 = new DataOutputStream(sock2.getOutputStream());
			out2.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			
			Socket sock3 = new Socket("192.168.0.153",54321);
			out3 = new DataOutputStream(sock3.getOutputStream());
			//out3.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			
			Socket sock4 = new Socket("192.168.0.157",54321);
			out4 = new DataOutputStream(sock4.getOutputStream());
			//out4.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
			*/
