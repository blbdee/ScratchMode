package broadcast.voice;

import javax.sound.sampled.*;

import java.io.*;

import javax.sound.sampled.AudioFileFormat.Type;

import buttons.SelectWav;
import commands.send.CheckBoxGroup;
import config.FromConfig;

public class JRecorder extends Thread {

    private TargetDataLine m_line;
    private AudioFileFormat.Type m_targetType;
    private AudioInputStream m_audioInputStream;
    private File m_outputFile;

    public JRecorder(TargetDataLine m_line, Type m_targetType,  File m_outputFile) {
        this.m_line = m_line;
        this.m_targetType = m_targetType;
        this.m_audioInputStream = new AudioInputStream(m_line);
        this.m_outputFile = m_outputFile;
    }

    public void start() {    	
        m_line.start();
        super.start();
        
    }

    public void stopRecording() {
        m_line.stop();
        m_line.close();
    }

    public void run() {
    	try {
    		AudioSystem.write(m_audioInputStream, m_targetType, m_outputFile);
    		AudioInputStream sourceAudioInputStream;
    		sourceAudioInputStream = AudioSystem.getAudioInputStream(m_outputFile);
    		AudioFormat targetFormat = new AudioFormat(new AudioFormat.Encoding("ALAW"), 15000, 8, 1, 1, 15000, false);
    		AudioInputStream targetAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, sourceAudioInputStream);
    		File targetFile = new File(FromConfig.fileVoiceDecPath);
    		AudioSystem.write(targetAudioInputStream, AudioFileFormat.Type.WAVE, targetFile); 
    		
    		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		CheckBoxGroup.selectFiles.clear();
    		CheckBoxGroup.selectFiles.add(targetFile + "");                
            System.out.println("select= "+CheckBoxGroup.selectFiles);
  		}    	
    	catch (IOException e) {
    		e.printStackTrace();
        }
    	catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
    }    
    
}

