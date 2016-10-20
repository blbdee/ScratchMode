package bsk;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;

import config.FromConfig;
import test.sound.JRecorder;

public class GetAudioStreamBsk extends Thread {
	
	public static JRecorder jRec;
	
	public void run() {
		
		File outputFile = new File(FromConfig.inputSoundBsk);
    	AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 15000, 16, 1, 2, 15000, false);
    	Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
    	Mixer mixer = AudioSystem.getMixer( mixerInfo[4] );
    	Info m = mixer.getMixerInfo();
    	TargetDataLine data = null;
		try {
			data = AudioSystem.getTargetDataLine(format, m);
			data.open();
			data.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
    	
    	AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;        
    	jRec = new JRecorder(data,targetType,outputFile); 
    	jRec.start();
        System.out.println("Recording..."); 
	}

}
