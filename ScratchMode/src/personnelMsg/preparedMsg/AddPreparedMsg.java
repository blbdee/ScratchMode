package personnelMsg.preparedMsg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import tablePersonnel.InsertIntoPersonnel;
import tablePersonnel.QueryToPersonnel;
import tablePersonnelMsg.InsertIntoPersonnelMsg;
import tablePersonnelMsg.QueryToPersonnelMsg;

public class AddPreparedMsg {
	
	public static JFrame newMsgPane;
	public static JEditorPane editNum;
	public static JEditorPane editMsg;
	public static JEditorPane editGroup;

	public AddPreparedMsg() {
		
		newMsgPane = new JFrame("Карточка добавления сообщения в базу данных");
		newMsgPane.setVisible(true);
		newMsgPane.setSize(600, 600);
		newMsgPane.setLocationRelativeTo(null);
		newMsgPane.getContentPane().setLayout(null);
		
		editNum = new JEditorPane();
		editNum.setBounds(87, 52, 400, 30);
		newMsgPane.getContentPane().add(editNum);
		
		editMsg = new JEditorPane();
		editMsg.setBounds(87, 216, 400, 280);
		newMsgPane.getContentPane().add(editMsg);
		
		editGroup = new JEditorPane();
		editGroup.setBounds(87, 134, 400, 30);
		newMsgPane.getContentPane().add(editGroup);
		
		JLabel lblFio = new JLabel("Введите порядковый номер сообщения");
		lblFio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFio.setBounds(87, 11, 400, 30);
		newMsgPane.getContentPane().add(lblFio);
		
		JLabel lblNum = new JLabel("Введите текст добавляемого сообщения");
		lblNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNum.setBounds(87, 175, 400, 30);
		newMsgPane.getContentPane().add(lblNum);
		
		JLabel lblPos = new JLabel("Введите название группы");
		lblPos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPos.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPos.setBounds(87, 93, 400, 30);
		newMsgPane.getContentPane().add(lblPos);
		
		JButton btnAddPrsn = new JButton("Добавить");
		btnAddPrsn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((editNum.getText().length()==0)|(editMsg.getText().length()==0)|(editGroup.getText().length()==0)) {
					int res = JOptionPane.showConfirmDialog(newMsgPane, "Для занесения абонента в базу необходимо заполнить все поля.\nПовторить попытку?", 
							"Ошибка заполнения карточки абонента", 0, 0);
					if (res == 0) {
						System.out.println(res);
						editNum.setText("");
						editMsg.setText("");
						editGroup.setText("");																																			
					}
					else if (res == 1) {	
						newMsgPane.setVisible(false);
					}
				}				
				else {
					new InsertIntoPersonnelMsg(editNum.getText(), editMsg.getText(), editGroup.getText());					
					QueryToPersonnelMsg.modelPersonnelMsg.setTableData(QueryToPersonnelMsg.bazaPersonnelMsg.getNomen("SELECT * FROM personnelmsg"));
					QueryToPersonnelMsg.modelPersonnelMsg.getTableData();								    	
					QueryToPersonnelMsg.modelPersonnelMsg.fireTableDataChanged();
				}
			}
		});
		btnAddPrsn.setFont(new Font("Arial", Font.PLAIN, 18));
		btnAddPrsn.setBounds(211, 517, 150, 30);
		newMsgPane.getContentPane().add(btnAddPrsn);
	}

}
