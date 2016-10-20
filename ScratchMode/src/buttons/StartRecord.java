package buttons;

import java.io.File;

import authentication.LoginWindow;
import broadcast.sip.UploadSipFile;
import broadcast.tfop.JoinFiles;
import broadcast.voice.StartRec;
import commands.send.CheckBoxGroup;
import config.FromConfig;
import ffmpeg.CoderTfop;
import ffmpeg.CoderTfopMp3;

public class StartRecord {
	
	private static String fileVoice;
	
	public static void butStartRecord() {
		
		if (CheckBoxGroup.butStartRecord.getText().equals("Остановить запись")) {
			
			if(LoginWindow.rdbtnEthernet.isSelected()) {
				StartRec.j.stopRecording();				
				fileVoice = FromConfig.audioRecord;
				File file3 = new File(fileVoice);  
	            CheckBoxGroup.selectFiles.clear();
	            CheckBoxGroup.selectFiles.add(file3 + "");
	            	            
			}
			
			else if(LoginWindow.rdbtnSip.isSelected()==true) {
				
				StartRec.j.stopRecording();				
				fileVoice = FromConfig.audioRecord;
				
        		try {
					Thread.sleep(1000);					
					new CoderTfopMp3(FromConfig.fileVoiceDecPath, FromConfig.outputFileMp3);				
    				Thread.sleep(1000);
    			} catch (InterruptedException e1) {
    				e1.printStackTrace();
    			}
            	CheckBoxGroup.uploadFileId = UploadSipFile.uploadSipFile(new File(FromConfig.outputFileMp3));  
			}
			
			else if(LoginWindow.rdbtnTfop.isSelected()==true) {
				
				StartRec.j.stopRecording();				
				fileVoice = FromConfig.audioRecord;	
				     
				try {  
					Thread.sleep(1000);	
					new CoderTfop(fileVoice, FromConfig.fileVoiceDecPath);
					Thread.sleep(1000);		
					new JoinFiles(FromConfig.fileVoiceDecPath, FromConfig.outputFileWav);									
					Thread.sleep(1000);
					new CoderTfopMp3(FromConfig.outputFileWav, FromConfig.outputFileMp3);
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				CheckBoxGroup.uploadFileId = UploadSipFile.uploadSipFile(new File(FromConfig.outputFileMp3));         		
			}
			
			else {
    			System.out.println("ChB"+"ethernet or sip?!@#@@#@#@");
    		}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
            
            CheckBoxGroup.butBroadcast.setEnabled(true);
            CheckBoxGroup.butStartRecord.setText("Начать запись");
			
			
		}
		else {
			CheckBoxGroup.butBroadcast.setEnabled(false);
			CheckBoxGroup.butStopBroadcast.setEnabled(false);
			StartRec.voiceRecord(); 
			CheckBoxGroup.butStartRecord.setText("Остановить запись");            		
		}
	}

}
