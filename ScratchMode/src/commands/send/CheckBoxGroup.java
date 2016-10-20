package commands.send;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import personnelMsg.SendMsgPrsn;
import tableEvents.InsertIntoEventLogDes;
import tableObjects.TableModel;
import tablePersonnel.QueryToPersonnel;
import authentication.LoginWindow;
import broadcast.files.CloseSockets;
import buttons.Alarm;
import buttons.Authentification;
import buttons.Broadcast;
import buttons.SelectWav;
import buttons.StartRecord;
import gui.SirenPaneEth;

public class CheckBoxGroup {	
			
	
	public static String strSocketStop;	
	public static String command;
	public static String command2;	
	public static String event;
	
	public static JButton butAuth;	
	public static JButton butAlarm;
	public static JButton butStopBroadcast;
	public static JButton butBroadcast;
	public static JButton butBroadcastOnline;
	public static JButton butSelectWav;
	public static JButton butStartRecord;
	public static JButton butPlayRec;	
	public static JButton butStopRec;
	public static ArrayList<String> selectFiles = new ArrayList<String>();
	public static String uploadFileId;
    public static JRadioButton rb;
    public static JRadioButton rButVnimanie;
    public static JRadioButton rButVozduh;
    public static JRadioButton rButTest;
    public static JRadioButton rButStop;   

    public CheckBoxGroup() {
    	        
        ItemListener ItemListener2 = new ItemListener() {
            @Override
			public void itemStateChanged(ItemEvent e) {
            	rb = (JRadioButton) e.getItem();
            	if(rb.isSelected()) {
            		
            		System.out.println("ChB"+LoginWindow.rdbtnEthernet.isSelected());
            		
            		if(LoginWindow.rdbtnEthernet.isSelected()==true) {
            			if (rb.getText() == "Внимание всем") { command = "/vnimvsem.htm"; }
            			if (rb.getText() == "Воздушная тревога") { command = "/vozduh.htm"; } 
            			if (rb.getText() == "Тестовый сигнал") { command = "/testsound.htm"; }  
            			if (rb.getText() == "Отбой") { command = "/stop.htm"; } 
            		}            		
            		else if(LoginWindow.rdbtnSip.isSelected()==true) {
            			if (rb.getText() == "Внимание всем") { command = "w*p1234%3Bq211%3B"; command2 = "w*p1234%3Bs1n11%3B"; }
            			if (rb.getText() == "Воздушная тревога") { command = "w*p1234%3Bq311%3B"; command2 = "w*p1234%3Bs2n11%3B"; }
            			if (rb.getText() == "Тестовый сигнал") { command = "w*p1234%3Bm9501%3B"; command2 = "w*p1234%3Bs3n11%3B"; }
            			if (rb.getText() == "Отбой") { command = "w*p1234%3Bq600%3B"; command2 = "w*p1234%3Bs4n11%3B"; }
            		} 
            		else if(LoginWindow.rdbtnTfop.isSelected()==true) {
            			if (rb.getText() == "Внимание всем") { command = "614"; }
            			if (rb.getText() == "Воздушная тревога") { command = "615"; }
            			if (rb.getText() == "Тестовый сигнал") { command = "616"; }
            			if (rb.getText() == "Отбой") { command = "w*p1234%3Bq600%3B"; }            			
            		}
            		else {
            			System.out.println("ChB"+"ethernet or sip?!@#@@#@#@");
            		}
            		
            		event=null;
					try {
						event = new String( rb.getText().getBytes(), "cp1251" );
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					
					System.out.println("ChB"+event);
            	}
			}
        };
        
        butAlarm = new JButton("Запуск");
        butAlarm.addActionListener(new ActionListener() {
        	
			@Override
            public void actionPerformed(ActionEvent e) {	
				Alarm alarmThread = new Alarm();
				alarmThread.start();			
				System.out.println(alarmThread.getName());
        	}
         });
        
        butAuth = new JButton("Аутентификация");
        butAuth.setVisible(false);
        butAuth.addActionListener(new ActionListener() {  
			@Override
            public void actionPerformed(ActionEvent e) {   
				Authentification.butAuthentification();      		
        	}
         });   
        
        rButVnimanie = new JRadioButton("Внимание всем");
        rButVozduh = new JRadioButton("Воздушная тревога");
        rButTest = new JRadioButton("Тестовый сигнал");
        rButStop = new JRadioButton("Отбой");
        ButtonGroup bG = new ButtonGroup();
        bG.add(rButVnimanie);
        bG.add(rButVozduh);
        bG.add(rButTest);
        bG.add(rButStop);
        rButVnimanie.addItemListener(ItemListener2);
        rButVozduh.addItemListener(ItemListener2); 
        rButTest.addItemListener(ItemListener2);
        rButStop.addItemListener(ItemListener2);        
        
        /*butBroadcastOnline = new JButton("Online");
        butBroadcastOnline.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent arg0) {            	
            		VoiceOnline2.StartVoiceOnline2();
            }
        });*/        
        
        butBroadcast = new JButton("Трансляция");
        butBroadcast.addActionListener(new ActionListener() {        	
            @Override
            public void actionPerformed(ActionEvent arg0) {  
            	
            	Broadcast.butBroadcast();            	
            }
            	
        });        
        
        butStopBroadcast = new JButton("Остановить трансляцию");
        butStopBroadcast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CloseSockets.CloseSock();
				for(int i=0; i<SirenPaneEth.labels.size();i++) {
					SirenPaneEth.labels.get(i).setBackground(Color.red);
				}
			}        	
        });        
        
        butSelectWav = new JButton("Выбрать файл");
        butSelectWav.addActionListener(new ActionListener() { 
			@Override
            public void actionPerformed(ActionEvent e) {
				
				SelectWav.butSelectWav();
            }
        });        
        
        butStartRecord = new JButton("Начать запись");        
        butStartRecord.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) { 
        		
        		StartRecord.butStartRecord();        		
           	}
         });   
        
    }
    
    public static void sendCommandsSip(String command, String event) {
    	    	
    	String phoneNumbersRsu ="";
    	String phoneNumbersSrn ="";
		for(int j=0;j<TableModel.phoneList.size();j++) {
			
			if(Integer.parseInt(TableModel.typeList.get(j))==1) {
				System.out.println("RSU");
				if(phoneNumbersRsu!="") {
					phoneNumbersRsu=phoneNumbersRsu+","+TableModel.phoneList.get(j);							
				}
				else {
					phoneNumbersRsu=phoneNumbersRsu+TableModel.phoneList.get(j);		
				}				
			}			
		}
		
		for(int i=0;i<TableModel.phoneList.size();i++) {
			
			if(Integer.parseInt(TableModel.typeList.get(i))==0) {
				System.out.println("SIRENA");
				if(phoneNumbersSrn!="") {
					phoneNumbersSrn=phoneNumbersSrn+","+TableModel.phoneList.get(i);							
				}
				else {
					phoneNumbersSrn=phoneNumbersSrn+TableModel.phoneList.get(i);		
				}				
			}
		}
		
		
		System.out.println(phoneNumbersRsu);
		System.out.println(phoneNumbersSrn);
				
		if(phoneNumbersRsu!="") {
			SendCommandSip.SendSip(phoneNumbersRsu, command, event);
		}
		
		if(phoneNumbersSrn!="") {
			SendCommandSip.SendSip(phoneNumbersSrn, command2, event);
		}
		
		for(int i=0;i<TableModel.nameList.size();i++) {
			new InsertIntoEventLogDes(TableModel.nameList.get(i), event, "В процессе", LoginWindow.UserName);
		}
			
    	
    }
}
