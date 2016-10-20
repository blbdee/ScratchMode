package bsk;

import gui.BskPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

import tableObjects.TableModel;
import mainWindow.SenderCommands;
import commands.send.CheckBoxGroup;

public class BskKitStopCom extends Thread {

	public BskKitStopCom() {
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(500);
			TableModel.butCheckAll.doClick();  
			CheckBoxGroup.rButStop.doClick();
	    	CheckBoxGroup.butAlarm.doClick();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
    	
    	SenderCommands.f.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, UIManager.getColor("MenuItem.background"))); 
    	
    	BskPanel.labelCom1.setBackground(UIManager.getColor("MenuItem.background"));
    	BskPanel.labelCom1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
    	BskPanel.labelCom1.setBorder(BskPanel.borderBsk);
    	
    	BskPanel.labelCom2.setBackground(UIManager.getColor("MenuItem.background"));
    	BskPanel.labelCom2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
    	BskPanel.labelCom2.setBorder(BskPanel.borderBsk);
    	
    	BskPanel.labelCom3.setBackground(UIManager.getColor("MenuItem.background"));
    	BskPanel.labelCom3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
    	BskPanel.labelCom3.setBorder(BskPanel.borderBsk);
    	
    	BskPanel.labelCom4.setBackground(UIManager.getColor("MenuItem.background"));
    	BskPanel.labelCom4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
    	BskPanel.labelCom4.setBorder(BskPanel.borderBsk);
    	
    	BskPanel.labelCom5.setBackground(UIManager.getColor("MenuItem.background"));
    	BskPanel.labelCom5.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white)); 
    	BskPanel.labelCom5.setBorder(BskPanel.borderBsk);
    	
    	BskPanel.labelCom6.setBackground(new Color(0,255,127));
    	
	}

}
