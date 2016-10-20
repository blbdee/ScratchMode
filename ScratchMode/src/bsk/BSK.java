package bsk;

import gui.BskPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import buttons.Broadcast;
import buttons.SelectWav;
import tableObjects.TableModel;
import mainWindow.SenderCommands;
import commands.send.CheckBoxGroup;
import config.FromConfig;
import jssc.SerialPort;
import jssc.SerialPortException;
  
public class BSK extends Thread {
  
	public static String command;
	public static SerialPort serialPort;
	private static String cmdStr;	 
	public static int num;
	private static int fifthCommand = 0;
	
	
    public BSK(SerialPort serialPort) {
    	BSK.serialPort = serialPort;    	    	
    }
    
    @Override    
    public void run() {
        try {            
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                      
     label: while (serialPort.isOpened()) {
    	    serialPort.writeString("d");
                        
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("I'm ERROR! "+e);
			}
                        
            String buffer = serialPort.readHexString();            
             	
        	if (buffer.length()>30) {
        		            	           	
	            //преборазовываем hex в int
        		String cmd = buffer.substring(21, 23);
	            Integer cmdInt = Integer.parseInt(cmd);
	            	
	            switch (cmdInt) {
	            		            	
	            	case 31: cmdStr = "command 1";
		            	/*CheckBoxGroup.rButTest.doClick();	
		            	BskCommandKit showCommand1 = new BskCommandKit(1);
		            	showCommand1.start();
		            	showAlarm();*/
	            		System.out.println("Поступила команда 1");
	            		serialPort.writeString("k=0"+"\n");
	            	break; 
	            	
	            	case 32: cmdStr = "Внимание всем";
		            	CheckBoxGroup.rButVnimanie.doClick();
		            	BskCommandKit showCommand2 = new BskCommandKit(2);
		            	showCommand2.start();
		            	showAlarm();
	            	break; 
	            	
	            	case 33: cmdStr = "Воздушная тревога"; 
		            	CheckBoxGroup.rButVozduh.doClick();
		            	BskCommandKit showCommand3 = new BskCommandKit(3);
		            	showCommand3.start();
		            	showAlarm();
	            	break; 
	            	
	            	case 34: cmdStr = "command 4";
	            		System.out.println("Поступила команда 4");
	            		serialPort.writeString("k=0"+"\n");
	            	break;
	            	
	            	case 35: cmdStr = "Трансляция";		            	
	            		System.out.println("Поступила команда 5");
	            		// Если fifthCommand=1, завершить прослушку линейного входа и начать трансляцию.
	            		fifthCommand = 1;
	            		new GetAudioStreamBsk().start();
		            	BskCommandKit showCommand5 = new BskCommandKit(5);
		            	showCommand5.start();		            	
	            		serialPort.writeString("k=0"+"\n");
	            	break;
	            	
	            	case 36: cmdStr = "Отбой";	    
	            		System.out.println("fifthCommand = "+fifthCommand);
	            		if(fifthCommand==0) {
	            			new BskKitStopCom().start();	
	            		}
	            		else if(fifthCommand==1) {
	            			GetAudioStreamBsk.jRec.stopRecording();
	            			fifthCommand=0;	 
	            			SenderCommands.f.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, UIManager.getColor("MenuItem.background"))); 
	            			BskPanel.labelCom5.setBackground(UIManager.getColor("MenuItem.background"));
	            	    	BskPanel.labelCom5.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
	            	    	BskPanel.labelCom5.setBorder(BskPanel.borderBsk);	            	    	
	            	    	BskPanel.labelCom6.setBackground(new Color(0,255,127));
	            	    	
	            	    	try {
	            				Thread.sleep(10000);
	            			} catch (InterruptedException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			}
	            			sendAudioStreamBsk();
	            		}
		            	            		
	            	break;
	            	
	            	} 
	            
	            	System.out.println("Получена команда: "+ cmdStr);
	            	continue label;
	            }
        	
	        	else {
	        		System.out.println("BSK is ready...");
	        		continue label;
	        	}
        	       
        	}            
            serialPort.closePort();                     
            
        }
        catch (SerialPortException ex) {
            System.out.println("Ошибка запуска БСК!");
            JOptionPane.showMessageDialog(SenderCommands.f, "Внимание, БСК был отключён.", "Сообщение от БСК", 2);
            BskPanel.btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red));
            BskPanel.btnIncludeBsk.setText("Включить БСК");
        } 
        catch (NullPointerException e) {
        	BskPanel.btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red));
        	BskPanel.btnIncludeBsk.setText("Включить БСК");  
        	StartThreadAlarmSound alarmSound = new StartThreadAlarmSound(FromConfig.alarmBsk);
    		alarmSound.start();
        	JOptionPane.showMessageDialog(SenderCommands.f, "Внимание, БСК был отключён.\nПерезапустите АРМ.", "Сообщение от БСК", 0);
        	try {
				serialPort.closePort();
			} catch (SerialPortException e1) {				
				e1.printStackTrace();
			}        	      	
        }
    }
        
    private static void showAlarm() {    	
    	
    	try {    		
			serialPort.writeString("k=0"+"\n");
			Thread.sleep(1000);
			TableModel.butCheckAll.doClick();
	    	CheckBoxGroup.butAlarm.doClick();
			
		} catch (SerialPortException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }   
    
    private static void sendAudioStreamBsk() {
    	TableModel.butCheckAll.doClick();
    	CheckBoxGroup.selectFiles.add(FromConfig.inputSoundBsk);
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	Broadcast.butBroadcast();
    }
}
