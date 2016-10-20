package test.SipLapsha;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Decoding {
	
	public Decoding() throws UnsupportedAudioFileException, IOException {
		
		String wavFile1 = "C:\\Users\\Admin\\Desktop\\numbers\\12345ster.wav";
	    String wavFile2 = "C:\\Users\\Admin\\Desktop\\numbers\\My.mp3";
	    //String wavFile3 = "C:\\Users\\Admin\\Desktop\\numbers\\000.wav";
	    
	    AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
	    AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));		    

	    System.out.println(clip1.getFormat());
	    //System.out.println(clip2.getFormat());
	    //AudioFormat targetFormat = new AudioFormat(new AudioFormat.Encoding("PCM_SIGNED"), 44100, 16, 2, 4, 44100, false);
        //AudioInputStream in = AudioSystem.getAudioInputStream(targetFormat, clip2);
        
        //AudioInputStream targetAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, clip2);
        //File targetFile = new File("C:\\Users\\Admin\\Desktop\\numbers\\res.wav");
		//AudioSystem.write(targetAudioInputStream, AudioFileFormat.Type.WAVE, targetFile); 
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		try {
			new Decoding();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
