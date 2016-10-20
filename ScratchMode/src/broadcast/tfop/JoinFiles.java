package broadcast.tfop;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import config.FromConfig;

public class JoinFiles {
	
	private String dtmfPass = FromConfig.dtmfPass;
	private String dtmfCancel = FromConfig.dtmfCancel;

	public JoinFiles(String inputFile, String outputFile) {
		
		AudioInputStream clip1;
		try {
			clip1 = AudioSystem.getAudioInputStream(new File(dtmfPass));
			AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(inputFile));				
		    AudioInputStream clip3 = AudioSystem.getAudioInputStream(new File(dtmfCancel));
		    
		    System.out.println(clip1.getFormat());
		    System.out.println(clip2.getFormat());
		    System.out.println(clip3.getFormat());
		    
		    AudioInputStream appendedFiles = new AudioInputStream(new SequenceInputStream(clip1, clip2), clip2.getFormat(), clip1.getFrameLength() + clip2.getFrameLength());
		    AudioInputStream appendedFiles2 = new AudioInputStream(new SequenceInputStream(appendedFiles, clip3), appendedFiles.getFormat(), appendedFiles.getFrameLength() + clip3.getFrameLength());
		    AudioSystem.write(appendedFiles2, AudioFileFormat.Type.WAVE, new File(outputFile));
		    
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
