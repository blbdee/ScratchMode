package personnelMsg;

import infoMessage.infoMessage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import personnelMsg.preparedMsg.PreparedMsg;
import tableObjects.QueryToObjects;
import tableObjects.TableModel;
import tablePersonnel.QueryToPersonnel;
import tablePersonnel.TablePersonnel;

public class MakeMsgPrsn {
	
	public static JPanel makeMsgPane;
	public static JTextArea textPane;

	public MakeMsgPrsn(int typeMsg) {	
		
		ChoosePrsnTable.choosePrsnPane.setVisible(false);
		if(typeMsg==0) {
			ChooseTypeSignalPrsn.lblNewLabel.setText("Формирование текстового сообщения");		
		}
		else if(typeMsg==1) {
			ChooseTypeSignalPrsn.lblNewLabel.setText("Формирование голосового сообщения");		
		}
		
		makeMsgPane = new JPanel();
		makeMsgPane.setBounds(10, 93, 1000, 700);
		makeMsgPane.setLayout(null);
		ChooseTypeSignalPrsn.mainFrame.getContentPane().add(makeMsgPane);
			
		JPanel textMsgPane = new JPanel();
		textMsgPane.setBounds(310, 10, 650, 440);
		textMsgPane.setLayout(null);
		textMsgPane.setVisible(true);
		textMsgPane.setBorder(BorderFactory.createTitledBorder("Текст сообщения"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 20, 630, 320);		
		
		textPane = new JTextArea();
		textPane.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane.setLineWrap(true);
		scrollPane.setViewportView(textPane);
					
		JButton btnPreparedMsg= new JButton("Готовые сообщения");
		btnPreparedMsg.setBounds(430, 390, 210, 40);
		btnPreparedMsg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PreparedMsg.getMsg(typeMsg);				
			}
		});
		
		JButton btnClearTextPane= new JButton("Очистить");
		btnClearTextPane.setBounds(10, 390, 210, 40);
		btnClearTextPane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				textPane.setText("");
			}
		});
		
		JPanel movePane = new JPanel();
		movePane.setBounds(10, 455, 950, 95);		
		movePane.setLayout(null);
		movePane.setVisible(true);
		movePane.setBorder(BorderFactory.createTitledBorder("Панель перехода"));
	
		JButton btnBack = new JButton("Назад");
		btnBack.setBounds(200, 15, 200, 70);
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				makeMsgPane.setVisible(false);
				ChooseTypeSignalPrsn.lblNewLabel.setText("Формирование списка абонентов оповещения");								
				ChoosePrsnTable.choosePrsnPane.setVisible(true);
			}			
		});
		
		JButton btnNext = new JButton("Далее");
		btnNext.setBounds(600, 15, 200, 70);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msgText = textPane.getText();
				System.out.println(msgText);
				int res = JOptionPane.showConfirmDialog(null, "Количество абонентов для оповещения "+TablePersonnel.phoneListPrsn.size()+"\nВыполнить рассылку?", "Сообщение персоналу", 2, 3);
				if (res==0) {
					String phoneNumbers ="";
					for(int i=0;i<TablePersonnel.phoneListPrsn.size();i++) {
						if(i!=0) {
							phoneNumbers=phoneNumbers+","+TablePersonnel.phoneListPrsn.get(i);							
						}
						else {
							phoneNumbers=phoneNumbers+TablePersonnel.phoneListPrsn.get(i);		
						}
												
					}
					if(typeMsg==0) {						
						System.out.println(phoneNumbers);
						new SendMsgPrsn(msgText, phoneNumbers);												
					}
					else if(typeMsg==1) {
						System.out.println(phoneNumbers);
						new SendVoicePrsn(msgText, phoneNumbers);						
					}									
				}
				ChooseTypeSignalPrsn.mainFrame.setVisible(false);
			}			
		});
		
		movePane.add(btnNext);
		movePane.add(btnBack);
		makeMsgPane.add(ChoosePrsnTable.prsnListPane);
		makeMsgPane.add(textMsgPane);
		makeMsgPane.add(movePane);
		textMsgPane.add(scrollPane);	
		textMsgPane.add(btnPreparedMsg);
		textMsgPane.add(btnClearTextPane);
		
	}

}
