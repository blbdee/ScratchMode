package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import commands.send.SendCommandSip;
import commands.send.StatSendSip;
import authentication.LoginWindow;
import mainWindow.SenderCommands;
import reports.CreateReportFile;
import tableEvents.InsertIntoEventLogDes;
import tableEvents.QueryToEvent_log;
import tableObjects.TableModel;
import tableSensors.GetStateSensor;
import tableSensors.InsertIntoSensorLog;
import tableSensors.QueryToSensor_log;
import config.FromConfig;

public class LogsBtnPane {
	

	public static JButton btnRefrash;
	public static JButton btnGetSensors;	
	public static String name;
	public static String event;
	public static String strSocket;
	
	public LogsBtnPane() {

		JPanel panelLogBtn = new JPanel();
		panelLogBtn.setBorder(new TitledBorder(null, "���������� ���������", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLogBtn.setBounds(1623, 635, 250, 300);
		SenderCommands.f.getContentPane().add(panelLogBtn);
		panelLogBtn.setLayout(new GridLayout(0, 1, 0, 0));
						
		btnRefrash = new JButton("��������");
		panelLogBtn.add(btnRefrash);
		btnRefrash.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
					    	
		    	QueryToEvent_log.modelLog.setTableData(QueryToEvent_log.bazaLog.getNomen("SELECT * FROM event_log where ���� = CURRENT_DATE"));
		    	QueryToEvent_log.modelLog.getTableData();								    	
		    	QueryToEvent_log.modelLog.fireTableDataChanged();
						    									    	
				QueryToSensor_log.modelSensor.setTableData(QueryToSensor_log.bazaSensor.getNomen("SELECT * FROM sensor_log"));
				QueryToSensor_log.modelSensor.getTableData();								    	
				QueryToSensor_log.modelSensor.fireTableDataChanged();
			}
		});
							
		btnGetSensors = new JButton("�������");
		btnGetSensors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(LoginWindow.rdbtnEthernet.isSelected()==true) {				
		     		for(int i=0; i<TableModel.ipList.size(); i++) {
			        			
		      			String ip = TableModel.ipList.get(i);
		       			Integer port = TableModel.portList.get(i);
		       			name = TableModel.nameList.get(i);		
		       			String strPort = port.toString(); 
		       			strSocket = ip + ":" + strPort;							        								        			   			
		       			GetStateSensor.GetSensor(strSocket, FromConfig.authLog, FromConfig.authPass, name, i);
				        									        			        			        			
		       		}  
				}
				
				else if (LoginWindow.rdbtnSip.isSelected()==true) {
	        		
        			String phoneNumbers ="";
        			for(int i=0;i<TableModel.phoneList.size();i++) {
						if(i!=0) {
							phoneNumbers=phoneNumbers+","+TableModel.phoneList.get(i);							
						}
						else {
							phoneNumbers=phoneNumbers+TableModel.phoneList.get(i);		
						}												
					}
        			
        			String result = SendCommandSip.SendSip(phoneNumbers, "w%26p1234%3Bb%3B", event);
        			String res = null;
        			int count = 0;
        			
        			while((res==null)&(count!=10)) {
        				res = StatSendSip.StatSip(result);
        				try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				count+=1;
        			}
        			
        			if(count<10) {
        				int resInt = Integer.parseInt(res);
            			if((resInt>0)&(resInt!=200)&(resInt!=300)&(resInt!=1000)) {
            				//new InsertIntoEventLogDes("��������", event, "�������", LoginWindow.UserName);
            				System.out.println("��������� ��������: �������.");
            				for(int i=0;i<TableModel.phoneList.size();i++) {
            					//new InsertIntoEventLogDes(TableModel.phoneList.get(i), "�������", "� ��������", LoginWindow.UserName);
            					ArrayList<String> sensorList = new ArrayList<String>();            					
            					sensorList.add("� ��������");
            					sensorList.add("� ��������");
            					sensorList.add("� ��������");
            					sensorList.add("� ��������");
            					sensorList.add("� ��������");
            					sensorList.add("� ��������");
            					sensorList.add("� ��������");
            					new InsertIntoSensorLog(sensorList, TableModel.nameList.get(i), i);
            					//new InsertIntoSensorLog(null, name, i);
            				}
            			}        
            			else {
            				System.out.println("��������� ��������: ��������.");
            				//new InsertIntoEventLogDes("��������", event, "��������", LoginWindow.UserName);
            				JOptionPane.showMessageDialog(SenderCommands.f, "�������� �� �������, ���������� � �������������� �������.", "������", 2);
            			}
        			}
        			else {
        				System.out.println("��������� ��������: ��������.");
        				//new InsertIntoEventLogDes("��������", event, "��������", LoginWindow.UserName);
        			} 
        		}
				        		
		       	btnRefrash.doClick();	
		       	System.out.println("Detection IS OOOOOOOOOKKKKKK");											
			}
		});
		
		JButton btnReport = new JButton("�����");
		panelLogBtn.add(btnReport);							
		
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SenderCommands.f,"��� ������������ ������ ����������\n������ ��������� � �������� ����", "������������ ������", 1);						
				String reportDateFirstDay = JOptionPane.showInputDialog(SenderCommands.f, "������� ��������� ���� � �������: '2016.01.26'","������������ ������", 1);
				String reportDateLastDay = JOptionPane.showInputDialog(SenderCommands.f, "������� �������� ���� � �������: '2016.01.26'", "������������ ������", 1);
				new CreateReportFile(reportDateFirstDay, reportDateLastDay);
			}
		});
						
		panelLogBtn.add(btnGetSensors);
	}

}
