package personnelMsg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tablePersonnel.DelPrsnCard;
import tablePersonnel.QueryToPersonnel;
import tablePersonnel.TablePersonnel;

public class ChoosePrsnTable {
	
	public static JPanel choosePrsnPane;
	public static JPanel prsnListPane;

	public ChoosePrsnTable(int typeMsg) {

		ChooseTypeSignalPrsn.lblNewLabel.setText("Формирование списка абонентов оповещения");
		ChooseTypeSignalPrsn.chooseSingnalPanel.setVisible(false);
		
		choosePrsnPane = new JPanel();
		choosePrsnPane.setBounds(10, 93, 1000, 550);
		choosePrsnPane.setLayout(null);
		ChooseTypeSignalPrsn.mainFrame.getContentPane().add(choosePrsnPane);		
		
		JScrollPane PrsnTable = new JScrollPane();
		PrsnTable.setBounds(10, 40, 800, 400);
		PrsnTable.setBorder(BorderFactory.createTitledBorder("Выбраные объекты"));
		PrsnTable.setViewportView(QueryToPersonnel.tablePersonnel);
		((TitledBorder) PrsnTable.getBorder()).
        setTitleFont(new Font("Arial", Font.BOLD, 14));
		choosePrsnPane.add(PrsnTable);
		
		JButton btnChkAll = new JButton("Циркулярно");
		btnChkAll.setBounds(825, 90, 125, 60);
		btnChkAll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<QueryToPersonnel.modelPersonnel.getRowCount(); i++) {									
					String var = QueryToPersonnel.modelPersonnel.getValueAt(i, 0).toString();
					if (var == "false") {
						QueryToPersonnel.modelPersonnel.setValueAt(true, i, 0);     							
					} 
				}								
			}
			
		});
		
		JButton btnUncheckAll = new JButton("Снять выбор");
		btnUncheckAll.setBounds(825, 150, 125, 60);
		btnUncheckAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<QueryToPersonnel.modelPersonnel.getRowCount(); i++) {									
					String var = QueryToPersonnel.modelPersonnel.getValueAt(i, 0).toString();
					if (var == "true") {
						QueryToPersonnel.modelPersonnel.setValueAt(false, i, 0);     							
					} 
				}
				TablePersonnel.phoneListPrsn.clear();
			}
			
		});
		
		JButton btnAddPrsn = new JButton("Добавить");
		btnAddPrsn.setBounds(825, 210, 125, 60);
		btnAddPrsn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPrsnCard();			
			}
			
		});
		
		JButton btnDelPrsn = new JButton("Удалить");
		btnDelPrsn.setBounds(825, 270, 125, 60);
		btnDelPrsn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<QueryToPersonnel.modelPersonnel.getRowCount();i++) {
					Boolean s = Boolean.valueOf(QueryToPersonnel.modelPersonnel.getValueAt(i, 0).toString());
					if(s==true) {
						QueryToPersonnel.modelPersonnel.setValueAt(false, i, 0); 
						String num = QueryToPersonnel.modelPersonnel.getValueAt(i, 2).toString();						
						new DelPrsnCard(num);									
					}																		
				}	
				QueryToPersonnel.modelPersonnel.setTableData(QueryToPersonnel.bazaPersonnel.getNomen("SELECT * FROM personnel"));
				QueryToPersonnel.modelPersonnel.getTableData();								    	
				QueryToPersonnel.modelPersonnel.fireTableDataChanged();	
			}			
		});
		
		JButton btnChooseGrp = new JButton("Группы");
		btnChooseGrp.setBounds(825, 330, 125, 60);
		btnChooseGrp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChooseGroupPrsn();				
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
				
				choosePrsnPane.setVisible(false);
				ChooseTypeSignalPrsn.lblNewLabel.setText("Выберите тип сообщения");								
				ChooseTypeSignalPrsn.chooseSingnalPanel.setVisible(true);
				
			}			
		});
		
		JButton btnNext = new JButton("Далее");
		btnNext.setBounds(600, 15, 200, 70);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0;i<QueryToPersonnel.modelPersonnel.getRowCount();i++) {				
					if(Boolean.valueOf(QueryToPersonnel.modelPersonnel.getValueAt(i, 0).toString())==true) {
						new MakeMsgPrsn(typeMsg);
						break;
					}					
					else if(i==QueryToPersonnel.modelPersonnel.getRowCount()-1) {
						JOptionPane.showMessageDialog(choosePrsnPane, "Необходимо выбрать хотя бы одного абонента.", 
								"Ошибка формирования списка абонентов", 2);
					}
				}
			}			
		});
		
		movePane.add(btnNext);
		movePane.add(btnBack);
		
		
		prsnListPane = new JPanel();
		prsnListPane.setBounds(10, 10, 290, 440);
		prsnListPane.setLayout(null);
		prsnListPane.setVisible(true);
		prsnListPane.setBorder(BorderFactory.createTitledBorder("Выбранные объекты"));
		CheckedPrsnPane displayPane = new CheckedPrsnPane(QueryToPersonnel.modelPersonnel);
		displayPane.list.setBackground(UIManager.getColor("MenuItem.background"));
		displayPane.setBounds(10, 20, 270, 410);
		prsnListPane.add(displayPane);
		
		choosePrsnPane.add(btnChkAll);
		choosePrsnPane.add(btnUncheckAll);
		choosePrsnPane.add(btnAddPrsn);
		choosePrsnPane.add(btnDelPrsn);
		choosePrsnPane.add(btnChooseGrp);
		choosePrsnPane.add(movePane);
		
	}	

}
