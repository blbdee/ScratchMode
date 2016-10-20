package test.SipLapsha;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Start {

	public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
		
		new Test("C:\\Users\\Admin\\Desktop\\numbers\\music.wav", "C:\\Users\\Admin\\Desktop\\numbers\\RES.mp3");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String wavFile1 = "C:\\Users\\Admin\\workspace\\ScratchMode\\audio_tfop\\dtmf_pass_long.mp3";
	    String wavFile2 = "C:\\Users\\Admin\\Desktop\\numbers\\res.wav";
	    String wavFile3 = "C:\\Users\\Admin\\workspace\\ScratchMode\\audio_tfop\\dtmf_cancel.mp3";
	    
		AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
	    AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));		    
	    AudioInputStream clip3 = AudioSystem.getAudioInputStream(new File(wavFile3));
	    
	    System.out.println(clip1.getFormat());
	    System.out.println(clip2.getFormat());
	    System.out.println(clip3.getFormat());
	    
	    //AudioInputStream appendedFiles = new AudioInputStream(new SequenceInputStream(clip1, clip2), clip2.getFormat(), clip1.getFrameLength() + clip2.getFrameLength());
	    //AudioInputStream appendedFiles2 = new AudioInputStream(new SequenceInputStream(appendedFiles, clip3), appendedFiles.getFormat(), appendedFiles.getFrameLength() + clip3.getFrameLength());
	    //AudioSystem.write(appendedFiles2, AudioFileFormat.Type.WAVE, new File("C:\\Users\\Admin\\Desktop\\numbers\\wavAppended000m2.wav"));
	}

}
