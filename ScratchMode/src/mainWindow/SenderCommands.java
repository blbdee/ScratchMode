package mainWindow;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import authentication.LoginWindow;
import commands.getMessages.GetMessage;
import commands.send.CheckBoxGroup;
import gui.AlertModePane;
import gui.BskPanel;
import gui.ChooseLocationPane;
import gui.ChooseObjectsBtn;
import gui.LogsBtnPane;
import gui.LogsPane;
import gui.MainMenu;
import gui.ObjectsPane;
import gui.ShowActiveModePane;
import gui.SirenPaneEth;
import gui.TimePane;
import jssc.SerialPort;
import tableEvents.QueryToEvent_log;
import tableMsgMemory.QueryToMsgMemory;
import tableObjects.QueryToObjects;
import tablePersonnel.QueryToPersonnel;
import tablePersonnelMsg.QueryToPersonnelMsg;
import tableSensors.QueryToSensor_log;
import timer.TimerGetSensors;
import config.FromConfig;

import java.io.IOException;

public class SenderCommands{
	
	public static SerialPort serialPort;
	public static JFrame f;	
	public static JScrollPane scrollPane;	
	
	public SenderCommands() {	
		new MainFormTime();				
		try {
			new FromConfig();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		String portStr = FromConfig.portBsk;
		serialPort = new SerialPort(portStr);		
		new TimerGetSensors(FromConfig.refrashSensors);	

		EventQueue.invokeLater(new Runnable() {			

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
				
				f = new JFrame("Система дистанционного управления");
				f.getRootPane().setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, UIManager.getColor("MenuItem.background")));
				f.getContentPane().setLayout(null);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(1920, 1024);
				f.setBackground(new Color(245, 245, 245));
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				
				FontUIResource fontP = new FontUIResource("Arial", Font.PLAIN, 14);
				FontUIResource fontB = new FontUIResource("Arial", Font.BOLD, 14);
				UIManager.put("Table.font", fontP);
				UIManager.put("CheckBox.font", fontP);
				UIManager.put("Button.font", fontB);
				UIManager.put("RadioButton.font", fontP);
				UIManager.put("Label.font", fontP);
				UIManager.put("List.font", fontP);
				UIManager.put("TitledBorder.font", fontB);
				UIManager.put("EditorPane.font", fontP);
				UIManager.put("TextArea.font", fontP);
				
				/*************/
				
				new MainMenu();
				
		        /*************/
				//нахуй это делать здесь?! Убери в главный поток.
				System.out.println(0);
				new QueryToObjects();
				System.out.println(1);				
				new QueryToSensor_log();
				System.out.println(2);	
				new QueryToEvent_log();	
				System.out.println(3);	
				new QueryToPersonnel();
				System.out.println(4);	
				new QueryToPersonnelMsg();
				System.out.println(5);
				new QueryToMsgMemory();
				System.out.println(6);
				/*******************************************************/
				
				new CheckBoxGroup();
				
				/*******************************************************/
				
				new TimePane();
				
				/*******************************************************/	
				
				new ChooseObjectsBtn();
				
				/*******************************************************/
				
				new ObjectsPane();
						
				/*******************************************************/
				
				new LogsPane();
				
				/*******************************************************/
				
				new ShowActiveModePane();
							
				/*******************************************************/
				
				new AlertModePane();
				new SirenPaneEth();	
								
				/*******************************************************/
				
				new BskPanel();				
								
				/***********************************************************/				
				
				new LogsBtnPane();
				
				/********************************************************/
				
				new ChooseLocationPane();
				
				/*******************************************************/
				
				if(LoginWindow.rdbtnSip.isSelected()) {
					GetMessage getSms = new GetMessage(FromConfig.portModem);
					getSms.start();
				}
				
				/*******************************************************/

			}
			
		});

	}



}
