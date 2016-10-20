package personnelMsg;

import gui.ChooseLocationPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import tablePersonnel.QueryToPersonnel;

public class ChooseGroupPrsn {
	
	private JCheckBox checkbox;

	public ChooseGroupPrsn() {
		
		ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
		List<String> typeList = new ArrayList<String>();
		
		QueryToPersonnel.modelPersonnel.setTableData(QueryToPersonnel.bazaPersonnel.getNomen("SELECT * FROM personnel"));	
		QueryToPersonnel.modelPersonnel.getTableData();
				
		JFrame chooseGrpPane = new JFrame("Выбор групп оповещения");
		chooseGrpPane.setVisible(true);
		chooseGrpPane.setSize(350, 400);
		chooseGrpPane.setLocationRelativeTo(null);
		chooseGrpPane.getContentPane().setLayout(null);
		
		JLabel lblPos = new JLabel("Выбор групп абонентов");
		lblPos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPos.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPos.setBounds(25, 10, 300, 30);
		chooseGrpPane.getContentPane().add(lblPos);		
		
		JPanel grpPane = new JPanel();
		grpPane.setBounds(25, 50, 280, 250);
		grpPane.setLayout(null);
		chooseGrpPane.add(grpPane);
		grpPane.setBorder(BorderFactory.createTitledBorder("Выберите группы"));
		((TitledBorder) grpPane.getBorder()).
        setTitleFont(new Font("Arial", Font.BOLD, 14));
		
		int j =0;
		for(int i=0;i<QueryToPersonnel.modelPersonnel.getRowCount();i++) {		
			checkbox = new JCheckBox(QueryToPersonnel.tablePersonnel.getValueAt(i, 4).toString());					
			checkbox.setBounds(10, 25+j, 180, 30);
			checkbox.setSelected(true);	
			checkBoxes.add(checkbox);
			grpPane.add(checkbox);
			j = j + 25;
		}
				
		JButton btnSelect = new JButton("Выбрать группу");
		btnSelect.setBounds(100, 320, 150, 30);
		chooseGrpPane.add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeList.clear();
				for(int i=0;i<checkBoxes.size();i++) {
					if(checkBoxes.get(i).isSelected()) {
						if(typeList.isEmpty()) {  
							typeList.add("Группа = "+"'"+checkBoxes.get(i).getText()+"'");			            				
						}
						else {
							typeList.add(" or Группа = "+"'"+checkBoxes.get(i).getText()+"'");
						}						
					}
				}
				
				String query = "";
				for(int j = 0; j<typeList.size(); j++) {
					query = query + typeList.get(j);							
				}
				
				System.out.println(typeList.isEmpty());
				if (typeList.isEmpty()==false) {
					QueryToPersonnel.modelPersonnel.setTableData(QueryToPersonnel.bazaPersonnel.getNomen("SELECT * FROM personnel where " + query));	
					QueryToPersonnel.modelPersonnel.getTableData();
					QueryToPersonnel.modelPersonnel.fireTableDataChanged();
				}				
				else {
					JOptionPane.showMessageDialog(chooseGrpPane, "Необходимо выбрать хотя бы одну группу", "Ошибка", 2);
				}
				chooseGrpPane.setVisible(false);				
			}						
		});
	}

}
