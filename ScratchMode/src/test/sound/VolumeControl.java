package test.sound;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.TargetDataLine;

public class VolumeControl {

    private Port lineOut;
    private FloatControl volControl;
    private static Mixer mixer; 
    
    private static Port lineOut2;
    private FloatControl volControl2;
    private static Mixer mixer2; 
    
    public static void main(String[] args) throws LineUnavailableException, IOException {
    	
    	File outputFile = new File("C:\\Users\\Admin\\workspace\\ScratchMode\\testSound\\test.wav");
    	AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 15000, 16, 1, 2, 15000, false);
    	Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
    	mixer = AudioSystem.getMixer( mixerInfo[4] );
    	Info m = mixer.getMixerInfo();
    	TargetDataLine data = AudioSystem.getTargetDataLine(format, m);
    	data.open();
    	data.start();
    	
    	AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;        
        JRecorder j = new JRecorder(data,targetType,outputFile); 
        j.start();
        System.out.println("Recording...");  
    	
    	
    	/*
    	//String audioRec = "C:\\Users\\Admin\\workspace\\ScratchMode\\testSound\\test.wav";
    	//File outputFile = new File(audioRec);
    	AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 15000, 16, 1, 2, 15000, false);
    	
    	Line.Info info = Port.Info.LINE_IN;
    	
    	//DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
    	
    	TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
    	
    	line.open(format);
        line.start();
        
        AudioInputStream ais = new AudioInputStream(line);

        File file = new File("C:\\Users\\Admin\\workspace\\ScratchMode\\testSound\\test.wav");
        file.createNewFile();

        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);
    	*/
    }

    public VolumeControl() {
        lineOut = null;
        volControl = null;

        //It gets everyone of the System's Mixers
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        try{
            //it Looks for those Mixers that suport the OutPut SPEAKER
            for (int i =0; i < mixerInfo.length; i++){
                mixer = AudioSystem.getMixer( mixerInfo[i] );

                //If the SPEAKER is supported, then it gets a line
                if ( mixer.isLineSupported( Port.Info.SPEAKER ) ){
                    lineOut = (Port) mixer.getLine( Port.Info.SPEAKER );

                    lineOut.open();

                    //Once we have the line, we request the Volumen control as a FloatControl
                    volControl = (FloatControl) lineOut.getControl(FloatControl.Type.VOLUME);
                    //Everything is done
                }
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
    }

    public float getValue() {
        return volControl.getValue();
    }

    public void setValue(float value) {
        volControl.setValue( value );
    }

    public boolean isControlValid() {
        return (volControl == null) ? false : true;
    }

    public void close() {
        lineOut.close();
    }
} 