package test;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class WavAppender {
    public static void main(String[] args) {
	    String wavFile1 = "C:\\Users\\Admin\\Desktop\\numbers\\12345ster.wav";
	    String wavFile2 = "C:\\Users\\Admin\\Desktop\\numbers\\95.wav";
	    String wavFile3 = "C:\\Users\\Admin\\Desktop\\numbers\\000.wav";

	    try {
		    AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
		    AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));		    
		    AudioInputStream clip3 = AudioSystem.getAudioInputStream(new File(wavFile3));
		    System.out.println(clip1.getFormat());
		    System.out.println(clip2.getFormat());
		    System.out.println(clip3.getFormat());
		    AudioInputStream appendedFiles = new AudioInputStream(new SequenceInputStream(clip1, clip2), clip2.getFormat(), clip1.getFrameLength() + clip2.getFrameLength());
		    AudioInputStream appendedFiles2 = new AudioInputStream(new SequenceInputStream(appendedFiles, clip3), appendedFiles.getFormat(), appendedFiles.getFrameLength() + clip3.getFrameLength());
		    AudioSystem.write(appendedFiles2, AudioFileFormat.Type.WAVE, new File("C:\\Users\\Admin\\Desktop\\numbers\\wavAppended000.wav"));
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
    }
}