package personnelMsg;

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

public class AddPrsnCard {
	
	public static JEditorPane editFio;
	public static JEditorPane editNum;
	public static JEditorPane editPos;
	public static JEditorPane editGroup;

	public AddPrsnCard() {			
		
		JFrame newObjPane = new JFrame("Карточка абонента");
		newObjPane.setVisible(true);
		newObjPane.setSize(600, 420);
		newObjPane.setLocationRelativeTo(null);
		newObjPane.getContentPane().setLayout(null);
		
		editFio = new JEditorPane();
		editFio.setBounds(87, 52, 400, 30);
		newObjPane.getContentPane().add(editFio);
		
		editNum = new JEditorPane();
		editNum.setBounds(87, 134, 400, 30);
		newObjPane.getContentPane().add(editNum);
		
		editPos = new JEditorPane();
		editPos.setBounds(87, 216, 400, 30);
		newObjPane.getContentPane().add(editPos);
		
		editGroup = new JEditorPane();
		editGroup.setBounds(87, 298, 400, 30);
		newObjPane.getContentPane().add(editGroup);
		
		JLabel lblFio = new JLabel("Введите ФИО абонента");
		lblFio.setHorizontalAlignment(SwingConstants.CENTER);
		lblFio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFio.setBounds(87, 11, 400, 30);
		newObjPane.getContentPane().add(lblFio);
		
		JLabel lblNum = new JLabel("Введите номер в формате \"79176543210\"");
		lblNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNum.setBounds(87, 93, 400, 30);
		newObjPane.getContentPane().add(lblNum);
		
		JLabel lblPos = new JLabel("Введите должность абонента");
		lblPos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPos.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPos.setBounds(87, 175, 400, 30);
		newObjPane.getContentPane().add(lblPos);
		
		JLabel lblGroup = new JLabel("Введите группу");
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setFont(new Font("Arial", Font.PLAIN, 20));
		lblGroup.setBounds(87, 257, 400, 30);
		newObjPane.getContentPane().add(lblGroup);
		
		JButton btnAddPrsn = new JButton("Добавить");
		btnAddPrsn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((editFio.getText().length()==0)|(editNum.getText().length()==0)|(editPos.getText().length()==0)|(editGroup.getText().length()==0)) {
					int res = JOptionPane.showConfirmDialog(newObjPane, "Для занесения абонента в базу необходимо заполнить все поля.\nПовторить попытку?", 
							"Ошибка заполнения карточки абонента", 0, 0);
					if (res == 0) {
						System.out.println(res);
						editFio.setText("");
						editNum.setText("");
						editPos.setText("");
						editGroup.setText("");
					}
					else if (res == 1) {	
						newObjPane.setVisible(false);
					}					
					
				}
				else {	
					String num = editNum.getText();
					int num2 = Character.getNumericValue(num.charAt(0));
					
					if((num2==7)&(num.length()==11)) {
						new InsertIntoPersonnel(editFio.getText(), num, editPos.getText(), editGroup.getText());						
						QueryToPersonnel.modelPersonnel.setTableData(QueryToPersonnel.bazaPersonnel.getNomen("SELECT * FROM personnel"));
						QueryToPersonnel.modelPersonnel.getTableData();								    	
						QueryToPersonnel.modelPersonnel.fireTableDataChanged();							
					}
					else {
						int res = JOptionPane.showConfirmDialog(newObjPane, "Ошибка заполнения карточки абонента.\nНеверный формат номера. \nПовторить ввод данных?", 
								"Ошибка заполнения карточки абонента", 0, 0);
						if (res == 0) {
							editFio.setText("");
							editNum.setText("");
							editPos.setText("");	
							editGroup.setText("");
						}
						else if (res == 1) {	
							newObjPane.setVisible(false);
						}
					}
				}
														
			}
		});
		btnAddPrsn.setFont(new Font("Arial", Font.PLAIN, 18));
		btnAddPrsn.setBounds(211, 340, 150, 30);
		newObjPane.getContentPane().add(btnAddPrsn);
		
	}

}
