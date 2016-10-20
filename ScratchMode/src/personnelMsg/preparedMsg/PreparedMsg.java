package personnelMsg.preparedMsg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import personnelMsg.ChooseTypeSignalPrsn;
import personnelMsg.MakeMsgPrsn;
import tablePersonnelMsg.DelMsgPrsn;
import tablePersonnelMsg.QueryToPersonnelMsg;

public class PreparedMsg {
	
	private static String msg;

	public static void getMsg(int typeMsg) {
		
		MakeMsgPrsn.makeMsgPane.setVisible(false);
		ChooseTypeSignalPrsn.lblNewLabel.setText("Список заранее подготовленных сообщений");	
		
		JPanel preparedMsgPane = new JPanel();
		preparedMsgPane.setBounds(10, 93, 1000, 700);
		preparedMsgPane.setLayout(null);
		preparedMsgPane.setVisible(true);
		ChooseTypeSignalPrsn.mainFrame.getContentPane().add(preparedMsgPane);
		
		JScrollPane PrsnTable = new JScrollPane();
		PrsnTable.setBounds(10, 40, 800, 400);
		PrsnTable.setBorder(BorderFactory.createTitledBorder("Список сообщений"));
		PrsnTable.setViewportView(QueryToPersonnelMsg.tablePersonnelMsg);
		((TitledBorder) PrsnTable.getBorder()).
        setTitleFont(new Font("Arial", Font.PLAIN, 14));
		preparedMsgPane.add(PrsnTable);
		
		JButton btnAddMsg = new JButton("Добавить");
		btnAddMsg.setBounds(825, 180, 125, 60);
		btnAddMsg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPreparedMsg();	
			}			
		});
		
		JButton btnDelMsg = new JButton("Удалить");
		btnDelMsg.setBounds(825, 240, 125, 60);
		btnDelMsg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<QueryToPersonnelMsg.modelPersonnelMsg.getRowCount();i++) {
					Boolean s = Boolean.valueOf(QueryToPersonnelMsg.modelPersonnelMsg.getValueAt(i, 0).toString());
					if(s==true) {
						QueryToPersonnelMsg.modelPersonnelMsg.setValueAt(false, i, 0); 
						String num = QueryToPersonnelMsg.modelPersonnelMsg.getValueAt(i, 1).toString();
						new DelMsgPrsn(num);								
					}
					QueryToPersonnelMsg.modelPersonnelMsg.setTableData(QueryToPersonnelMsg.bazaPersonnelMsg.getNomen("SELECT * FROM personnelmsg"));
					QueryToPersonnelMsg.modelPersonnelMsg.getTableData();								    	
					QueryToPersonnelMsg.modelPersonnelMsg.fireTableDataChanged();	
				}
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
				
				preparedMsgPane.setVisible(false);
				if(typeMsg==0) {
					ChooseTypeSignalPrsn.lblNewLabel.setText("Формирование текстового сообщения");		
				}
				else if(typeMsg==1) {
					ChooseTypeSignalPrsn.lblNewLabel.setText("Формирование голосового сообщения");		
				}
				MakeMsgPrsn.makeMsgPane.setVisible(true);				
			}			
		});
		
		JButton btnSelect = new JButton("Выбрать");
		btnSelect.setBounds(600, 15, 200, 70);
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msg = "";
				for(int i=0;i<QueryToPersonnelMsg.tablePersonnelMsg.getRowCount();i++) {
					String var = QueryToPersonnelMsg.modelPersonnelMsg.getValueAt(i, 0).toString();
					if (var == "true") {
						msg = msg + QueryToPersonnelMsg.modelPersonnelMsg.getValueAt(i, 3).toString();     							
					}					
				}
				System.out.println(msg);
				MakeMsgPrsn.textPane.append(msg);
				btnBack.doClick();
			}			
		});
		
		movePane.add(btnSelect);
		movePane.add(btnBack);
		preparedMsgPane.add(movePane);
		preparedMsgPane.add(btnAddMsg);
		preparedMsgPane.add(btnDelMsg);
		
	}
}
