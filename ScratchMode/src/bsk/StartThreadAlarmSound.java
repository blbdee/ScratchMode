package bsk;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StartThreadAlarmSound extends Thread {
	
		public static Clip clip;
		private String path;
		
		public StartThreadAlarmSound(String path) {
			this.path = path; 
		}
		
		public void run() {
			
			try {
				File soundFile = new File(path); //Звуковой файл				
				AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);			
				clip = AudioSystem.getClip();			
				clip.open(ais);			
				clip.setFramePosition(0); 
				clip.start();	
				Thread.sleep(clip.getMicrosecondLength()/1000);
				clip.stop(); 
				clip.close(); 
			} 
			catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
				exc.printStackTrace();
			} 
			catch (InterruptedException exc) {
				System.out.println(exc);
			}		
		}
		
	}