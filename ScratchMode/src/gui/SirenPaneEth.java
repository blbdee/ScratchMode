package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import buttons.AlarmFromMemory;
import buttons.StopFromMemory;
import tableObjects.QueryToObjects;
import tableObjects.TableModel;
import authentication.LoginWindow;
import mainWindow.SenderCommands;
import commands.send.CheckBoxGroup;
import commands.send.ThreadCommands;
import commands.send.ThreadCommandsWithAuth;
import config.FromConfig;
import equipmentType.CheckRSU;

public class SirenPaneEth {
	
	public static JPanel panelCommands1;
	public static JPanel panelCommands2;
	public static JPanel panelCommands3;	
	public static JPanel panelCommands4;
	public static JPanel changingPaneSiren = new JPanel();
	public static JPanel typeSignalSiren;
	public static JPanel numSignal;
	private static JPanel CheckedObjectsPane;
	private static CheckedObjPane displayPane;
	private static JButton butAlarmFromMemory = new JButton("Запустить");
	private static JButton butStop = new JButton("Отбой");
	private static JButton butChooseCmd = new JButton("Выбрать сообщение");
	public static JTextArea labelNumArea;
	public static JTextArea labelTextCmdArea;
	public static JPanel PlayerPane;	
	public static JTextArea textArea;
	public static List<JLabel> labels;
	public static JCheckBox tfopChecked;
	

	public SirenPaneEth() {
		
		
		/***********Основная панель************/
		changingPaneSiren.setBounds(260, 360, 1350, 270);
		changingPaneSiren.setBorder(BorderFactory.createTitledBorder("Сирена"));			
		SenderCommands.f.getContentPane().add(changingPaneSiren);
		changingPaneSiren.setLayout(null);
		
		CheckedObjectsPane = new JPanel();
		CheckedObjectsPane.setBounds(10, 25, 368, 150);
		changingPaneSiren.add(CheckedObjectsPane);
		CheckedObjectsPane.setBorder(BorderFactory.createTitledBorder("Выбранные объекты"));			
		CheckedObjectsPane.setLayout(null);		
						
		displayPane = new CheckedObjPane(QueryToObjects.model);
		displayPane.list.setBackground(UIManager.getColor("MenuItem.background"));
		displayPane.setBounds(10, 20, 348, 120);
		CheckedObjectsPane.add(displayPane);
		/***********Основная панель************/		
		
								
		panelCommands1 = new JPanel();
		panelCommands1.setBounds(10, 180, 1370, 80);
		panelCommands1.setBorder(BorderFactory.createTitledBorder("Команды"));
		changingPaneSiren.add(panelCommands1);
		panelCommands1.add(CheckBoxGroup.butAlarm);
		panelCommands1.add(CheckBoxGroup.butAuth);
		panelCommands1.setLayout(null);	
		CheckBoxGroup.butAlarm.setBounds(303, 20, 220, 50);
		CheckBoxGroup.butAuth.setBounds(826, 20, 220, 50);
		panelCommands1.setVisible(true);
		
						
		panelCommands2 = new JPanel();
		panelCommands2.setBounds(10, 180, 1370, 80);
		changingPaneSiren.add(panelCommands2);
		panelCommands2.setBorder(BorderFactory.createTitledBorder("Команды"));
		panelCommands2.setLayout(null);
		panelCommands2.add(CheckBoxGroup.butStartRecord);
		CheckBoxGroup.butStartRecord.setBounds(222, 20, 220, 50);
		panelCommands2.setVisible(false);
		
			
		panelCommands3 = new JPanel();
		panelCommands3.setBounds(10, 180, 1370, 80);
		changingPaneSiren.add(panelCommands3);
		panelCommands3.setBorder(BorderFactory.createTitledBorder("Команды"));
		panelCommands3.setLayout(null);					
		panelCommands3.add(CheckBoxGroup.butSelectWav);
		CheckBoxGroup.butSelectWav.setBounds(222, 20, 220, 50);
		CheckBoxGroup.butBroadcast.setBounds(564, 20, 220, 50);	
		CheckBoxGroup.butStopBroadcast.setBounds(906, 20, 220, 50);
		panelCommands3.setVisible(false);
		
		
		panelCommands4 = new JPanel();
		panelCommands4.setBounds(10, 180, 1370, 80);
		changingPaneSiren.add(panelCommands4);
		panelCommands4.setBorder(BorderFactory.createTitledBorder("Команды"));
		panelCommands4.setLayout(null);		
		butAlarmFromMemory.setBounds(222, 20, 220, 50);
		panelCommands4.add(butAlarmFromMemory);
		butStop.setBounds(564, 20, 220, 50);
		panelCommands4.add(butStop);
		butChooseCmd.setBounds(906, 20, 220, 50);
		panelCommands4.add(butChooseCmd);
		panelCommands4.setVisible(false);
		
		/***********Сирена*****************/
		typeSignalSiren = new JPanel();
		typeSignalSiren.setBounds(388, 25, 992, 150);
		changingPaneSiren.add(typeSignalSiren);
		typeSignalSiren.setLayout(new GridLayout(4, 1, 0, 0));
		typeSignalSiren.setBorder(BorderFactory.createTitledBorder("Тип сигнала"));
		typeSignalSiren.add(CheckBoxGroup.rButVnimanie);
		typeSignalSiren.add(CheckBoxGroup.rButVozduh);
		typeSignalSiren.add(CheckBoxGroup.rButTest);
		typeSignalSiren.add(CheckBoxGroup.rButStop);
		/***********Сирена*****************/
		
		
		/********проигрыватель************/
		PlayerPane = new JPanel();
		PlayerPane.setBounds(388, 25, 992, 150);
		changingPaneSiren.add(PlayerPane);
		PlayerPane.setBorder(BorderFactory.createTitledBorder("Проигрыватель"));			
		PlayerPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 24, 673, 46);
		tfopChecked = new JCheckBox("По ТФОП");
		tfopChecked.setBounds(700, 70, 100, 20);
		if(LoginWindow.rdbtnSip.isSelected()) {
			PlayerPane.add(tfopChecked );				
		}
		PlayerPane.add(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setEditable(false);				
		/********проигрыватель************/
		
		/**********Готовые ролики********/
		numSignal = new JPanel();
		numSignal.setBounds(388, 25, 992, 150);
		changingPaneSiren.add(numSignal);
		numSignal.setBorder(BorderFactory.createTitledBorder("Готовые ролики в памяти РСУ."));
		numSignal.setLayout(null);
		
		JLabel labelNum = new JLabel("Вы выбрали команду:");
		labelNum.setBounds(20, 35, 150, 23);
		numSignal.add(labelNum);
		
		labelNumArea = new JTextArea();
		labelNumArea.setBounds(180, 35, 250, 23);
		numSignal.add(labelNumArea);
		
		labelTextCmdArea = new JTextArea();
		labelTextCmdArea.setBounds(20, 70, 800, 65);
		labelTextCmdArea.setLineWrap(true);
		numSignal.add(labelTextCmdArea);
									
		butAlarmFromMemory.addActionListener(new ActionListener() {   
			@Override
	        public void actionPerformed(ActionEvent e) {   
				
				AlarmFromMemory.butAlarmFromMemory(ChooseCmdFromMemory.cmdFromMemory);				
	        }
		});			
						
		butStop.addActionListener(new ActionListener() {
			@Override					
	        public void actionPerformed(ActionEvent e) {
				
	        	StopFromMemory.butStopFromMemory();
	        }
		});
		
		butChooseCmd.addActionListener(new ActionListener() {
			@Override					
	        public void actionPerformed(ActionEvent e) {
				
	        	ChooseCmdFromMemory.chooseCmdFromMemory();
	        }
		});
		
		//command = alarmFromMemoryPane.getText().toString();		
		/**********Готовые ролики********/
		
					
		PlayerPane.setVisible(false);
		
		if(LoginWindow.rdbtnEthernet.isSelected() == true) {
			int x = 0;
			labels = new ArrayList<JLabel>();
			for (int i = 0; i<QueryToObjects.model.getRowCount(); i++) {
				
				int type = Integer.parseInt(QueryToObjects.model.getValueAt(i, 8).toString());
				if (type == 1) {
					
			        JLabel label = new JLabel();
					label.setOpaque(true);
					label.setBackground(Color.RED);
					label.setBounds(300+x, 75, 14, 14);
					labels.add(label);						
					PlayerPane.add(label);						
					x = x +30;						
				}									
			}						
		}		
		
		AlertModePane.btnSirenPane.doClick();			
	}

}
