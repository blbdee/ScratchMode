package buttons;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import commands.send.CheckBoxGroup;
import authentication.LoginWindow;
import broadcast.sip.UploadSipFile;
import broadcast.tfop.JoinFiles;
import config.FromConfig;
import ffmpeg.Coder;
import ffmpeg.CoderTfop;
import ffmpeg.CoderTfopMp3;
import gui.AlertModePane;
import gui.SirenPaneEth;

public class SelectWav {
	
	public static String path;				
	public static String fileName;
	private static File fileForPlayer; 
	
	public static void butSelectWav() {
		
		JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	SirenPaneEth.textArea.setText("");
        	SirenPaneEth.textArea.append("Выбран файл для трансляции: " );                    
            File[] file = chooser.getSelectedFiles();
            for (int i=0; i<file.length;i++) {
            	path = file[i].toString();                    	
            }
            path = path.replace('\\', '/');
            fileName = file[0].getName();
            SirenPaneEth.textArea.append(fileName+"\n");
            
            if(LoginWindow.rdbtnEthernet.isSelected()== true) {                    	
            	
            	try {
            		
					Coder.startCoder();				                                    
					fileForPlayer = new File(Coder.second);
	                CheckBoxGroup.selectFiles.clear();
	                CheckBoxGroup.selectFiles.add(fileForPlayer+"");
	                System.out.println("SelectWav "+"select= "+CheckBoxGroup.selectFiles);
            	} catch (Exception e1) {
					e1.printStackTrace();
				}                    	
            }
            else if(LoginWindow.rdbtnSip.isSelected()==true) {
            	
            	try {
            		Coder.startCoder();
					Thread.sleep(500);					
					new CoderTfopMp3(Coder.second, FromConfig.outputFileMp3);				
    				Thread.sleep(500);
    			} catch (InterruptedException e1) {
    				e1.printStackTrace();
    			} catch (IOException e) {
					e.printStackTrace();
				}
            	fileForPlayer = new File(FromConfig.outputFileWav);
            	CheckBoxGroup.uploadFileId = UploadSipFile.uploadSipFile(new File(FromConfig.outputFileMp3));
            }
            else if(LoginWindow.rdbtnTfop.isSelected()==true) {            	
            	
        		try {
        			new CoderTfop(path, FromConfig.coderPath);
					Thread.sleep(1000);
					new JoinFiles(FromConfig.coderPath, FromConfig.outputFileWav);      
					Thread.sleep(500);
	        		new CoderTfopMp3(FromConfig.outputFileWav, FromConfig.outputFileMp3);
	        		Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
        		fileForPlayer = new File(FromConfig.outputFileWav);
        		CheckBoxGroup.uploadFileId = UploadSipFile.uploadSipFile(new File(FromConfig.outputFileMp3));        		            	
            }
            /*else if((LoginWindow.rdbtnSip.isSelected()==true)|(LoginWindow.rdbtnTfop.isSelected()==true)) {
            	
            	if(LoginWindow.rdbtnTfop.isSelected()) {
            		System.out.println("decoding");
            		new CoderTfop(path, FromConfig.coderPath);
            		try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		new JoinFiles(FromConfig.coderPath, FromConfig.outputFileWav);            		
            		new CoderTfopMp3(FromConfig.outputFileWav, FromConfig.outputFileMp3);                    		                   		
        		}   
            	
            	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	CheckBoxGroup.uploadFileId = UploadSipFile.uploadSipFile(new File(FromConfig.outputFileMp3));     
                System.out.println("upload");
            }*/
            
            else {
    			System.out.println("ChB"+"ethernet or sip?!@#@@#@#@");
    		}
            try {
            	Thread.sleep(500);
				AlertModePane.load(fileForPlayer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            CheckBoxGroup.butBroadcast.setEnabled(true);
        }
		
	}
		
}
