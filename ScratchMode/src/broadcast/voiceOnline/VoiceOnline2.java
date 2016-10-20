/*package broadcast.voiceOnline;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JLabel;

import mainWindow.SenderCommands;
import broadcast.files.Main;
import database.TableModel;
import equipmentType.CheckRSU;
import gui.CheckedObjPane;

public class VoiceOnline2 {
	
	public static Socket sock;	
	public static Integer port;
	public static Integer numRow;
	static ArrayList<DataOutputStream> outs = new ArrayList<DataOutputStream>();
	private static TargetDataLine microphone;
	public static int CHUNK_SIZE = 256;
	public static byte[] data;
	public static AudioInputStream in;
	static byte[] buf;

	public static DataOutputStream out;
	public static DataOutputStream out2;
	public static void StartVoiceOnline2() {
		
		/*if (DisplayPanel.dlm.size() > 0) {
			
			new CheckRSU();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i=0; i<TableModel.ipList.size(); i++) {			
						
			String addres = TableModel.ipList.get(i);
			port = TableModel.portList.get(i);
			numRow = TableModel.rowsList.get(i);
	        InetAddress ipAddress = null;
	       	        
	        
	        try {
	        	//извлечение ip из строки элемента массива
	            ipAddress = InetAddress.getByName(addres);
	            sock = new Socket(ipAddress, port);	            
	            //sockets.add(sock);
	            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
	            out.writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.150:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
	            System.out.println(sock);
	            outs.add(out);
	        } 
	        catch (UnknownHostException e) {
	            e.printStackTrace();
	            System.out.println("STH1= "+e);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("STH2= "+e);
	        }
		}
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 15000, 16, 1, 2, 15000, false);		
	    
	    SourceDataLine speakers;
	    try {
			microphone = AudioSystem.getTargetDataLine(format);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	        microphone = (TargetDataLine) AudioSystem.getLine(info);
	        microphone.open(format);
	       
	        data = new byte[microphone.getBufferSize() / 5];
	        System.out.println(data.length);
	        microphone.start();
	        
	        System.out.println(microphone.getBufferSize());
	        
	        
	    	AudioFormat targetFormat = new AudioFormat(new AudioFormat.Encoding("ALAW"), 15000, 8, 1, 1, 15000, false);
	        in = AudioSystem.getAudioInputStream(targetFormat, new AudioInputStream(microphone));
	        
	        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
	        speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	        speakers.open(format);
	        speakers.start();
		
	        ThreadSend ts = new ThreadSend();
	        ts.start();
					
	
	    } catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		if (CheckedObjPane.dlm.size() > 0) {
			
			new CheckRSU();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i=0; i<TableModel.ipList.size(); i++) {			
			
			String addres = TableModel.ipList.get(i);
			port = TableModel.portList.get(i);
			numRow = TableModel.rowsList.get(i);
	        InetAddress ipAddress = null;
	       	        
	        
	        try {
	        	//извлечение ip из строки элемента массива
	            ipAddress = InetAddress.getByName(addres);
	            sock = new Socket(ipAddress, port);	            
	            //sockets.add(sock);
	            DataOutputStream out2 = new DataOutputStream(sock.getOutputStream());
	            System.out.println(sock);
	            outs.add(out2);
	        } 
	        catch (UnknownHostException e) {
	            e.printStackTrace();
	            System.out.println("STH1= "+e);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("STH2= "+e);
	        }
		}
		
		
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 15000, 16, 1, 2, 15000, false);		
	    TargetDataLine microphone;
	    SourceDataLine speakers;
	    try {
	        microphone = AudioSystem.getTargetDataLine(format);
	
	        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	        microphone = (TargetDataLine) AudioSystem.getLine(info);
	        microphone.open(format);
	
	       
	        
	        
			//sock = new Socket("192.168.0.194",54321);
			//out = new DataOutputStream(sock.getOutputStream());
			
	        int numBytesRead;
	        int CHUNK_SIZE = 512;
	        byte[] data = new byte[microphone.getBufferSize() / 5];
	        microphone.start();
	        AudioFormat targetFormat = new AudioFormat(new AudioFormat.Encoding("ALAW"), 15000, 8, 1, 1, 15000, false);
	        AudioInputStream input  = new AudioInputStream(microphone);
	        AudioInputStream in = AudioSystem.getAudioInputStream(targetFormat, input);  
	
	        int bytesRead = 0;
	        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
	        speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	        speakers.open(format);
	        speakers.start();
	        /*for (int i=0;i<outs.size();i++) {
	        	numBytesRead = in.read(data, 0, CHUNK_SIZE);
	        	bytesRead += numBytesRead;
		        outs.get(i).writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
		        outs.get(i).write(data, 0, numBytesRead);
		        System.out.println(bytesRead);
	        }
	        
	        outs.get(0).writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
	        outs.get(1).writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
	        outs.get(2).writeBytes("POST /bc_srazu HTTP/1.1\r\nHost: 192.168.0.194:54321\r\nUser-Agent:\r\nContent-Type: multipart/form-data; boundary=---------------------------13193046731000\r\nContent-Length: 3941121\r\n\r\n-----------------------------13193046731000\r\nContent-Disposition: form-data; name=\"br_file\"; filename=\"destination.wav\"\r\nContent-Type: audio/wav\r\n\r\nRIFF?­)\0WAVEfmt \0\0\0\0\6\0\1\0?:\0\0?:\0\0\1\0\b\0\0\0fact\4\0\0\0­d)\0LIST\26\0\0\0INFOISFT....Lavf56.40.100.datad.).");
	        int i=0;
	        while (bytesRead < 1000000000) {
	            numBytesRead = in.read(data, 0, CHUNK_SIZE);
	            bytesRead += numBytesRead;
	            
	            // write the mic data to a stream for use later
	            if (i<=2) {
	            	outs.get(i).write(data, 0, numBytesRead);
	            }
	            else {
	            	i=0;
	            }
	            System.out.println(bytesRead);
	            // write mic data to stream for immediate playback
	            //speakers.write(data, 0, numBytesRead);
	            i++;
	        }
	        speakers.drain();
	        speakers.close();
	        microphone.close();
	        
	    } catch (LineUnavailableException e) {
	        e.printStackTrace();
	    } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}*/
