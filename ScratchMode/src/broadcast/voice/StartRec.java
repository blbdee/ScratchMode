package broadcast.voice;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import config.FromConfig;

public class StartRec {
	
	public static JRecorder j;
	public static String audioRec;
	
	public static void voiceRecord() {
    	
		audioRec = FromConfig.audioRecord;
    	File outputFile = new File(audioRec);
    	AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 15000, 16, 1, 2, 15000, false);    	   	
   	    
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine targetDataLine = null;
        try {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
        }
        catch (LineUnavailableException e) {
            System.out.println("unable to get a recording line");
            e.printStackTrace();
            System.exit(1);
        }

        AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;        
        j = new JRecorder(targetDataLine,targetType,outputFile);
        /*System.out.println("Press ENTER to start the recording.");
        try {
            System.in.read();            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        /* Here, the recording is actually started.
         */
        //начало записи
       
        j.start();
        System.out.println("Recording...");  
        System.out.println("Press ENTER to stop the recording.");
        /*
        try {
            System.in.read();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
        //конец записи
        //j.stopRecording();
        System.out.println("Recording stopped.");
    }
	
	/*public static void decoding() throws UnsupportedAudioFileException, IOException {
		
		File sourceFile = new File("C:\\Users\\Admin\\workspace\\ScratchMode\\audioRecord.wav");

		File targetFile = new File("C:\\Users\\Admin\\workspace\\ScratchMode\\Calling2.wav");

		AudioInputStream sourceAudioInputStream = AudioSystem.getAudioInputStream(sourceFile);

		AudioFormat targetFormat = new AudioFormat(new AudioFormat.Encoding("ALAW"), 15000, 8, 1, 1, 15000, false);

		AudioInputStream targetAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, sourceAudioInputStream);

		AudioSystem.write(targetAudioInputStream, AudioFileFormat.Type.WAVE, targetFile);
		
	}*/


}
