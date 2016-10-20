/*package broadcast.voiceOnline;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;


public class VoiceOnline extends Thread {
	
	public static DataOutputStream out;
	public static DataOutputStream out2;
	public static Socket sock;
	public static Socket sock2;
	public static Socket sock4;
	
	private static TargetDataLine microphone;
	
	//public static int numBytesRead;
	public static int CHUNK_SIZE = 256;
	public static byte[] data;
	public static AudioInputStream in;
		
	
	public static void StartVoiceOnline() {
		
		
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
	        
	        Thread.sleep(3000);
	        buffer b = new buffer();
	        b.start();	        	
	        					
	        
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
*/
