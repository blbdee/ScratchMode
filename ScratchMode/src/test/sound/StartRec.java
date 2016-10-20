package test.sound;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartRec {
	
	public static JRecorder j;
	public static String audioRec;
	
	public static void main(String args[]) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(null);
		f.setVisible(true);
		f.setSize(500,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Запись");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				voiceRecord();
			}
		});
		
		btnNewButton.setBounds(59, 138, 157, 103);
		f.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Стоп");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StartRec.j.stopRecording();
			}
		});
		
		button.setBounds(313, 138, 157, 103);
		f.getContentPane().add(button);
	}
	
	public static void voiceRecord() {
    	
		audioRec = "C:\\Users\\Admin\\workspace\\ScratchMode\\testSound\\test.wav";
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
}
