package bsk;

import gui.BskPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import config.FromConfig;
import mainWindow.SenderCommands;

public class BskCommandKit extends Thread {	
	
	private static int num2;

	public BskCommandKit(int num) {
		num2 = num;
	}
	
	@Override
	public void run() {
		
		SenderCommands.f.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.red));
		
		if (num2 == 1) { 
			BskPanel.labelCom1.setBackground(new Color(240,128,128));
			BskPanel.labelCom1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
			}
		else if (num2 == 2) {
			BskPanel.labelCom2.setBackground(new Color(240,128,128));
			BskPanel.labelCom2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));  
			}
		else if (num2 == 3) { 
			BskPanel.labelCom3.setBackground(new Color(240,128,128));
			BskPanel.labelCom3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
			}
		else if (num2 == 4) { 
			BskPanel.labelCom4.setBackground(new Color(240,128,128));
			BskPanel.labelCom4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
		}
		else if (num2 == 5) { 			
			BskPanel.labelCom5.setBackground(new Color(240,128,128));
			BskPanel.labelCom5.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));			
		}
		StartThreadAlarmSound alarmSound = new StartThreadAlarmSound(FromConfig.alarmBsk);
		alarmSound.start();	
		
	}	
	
	
}



