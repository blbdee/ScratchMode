package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tableObjects.AddObjectsToObjectsLog;
import tableObjects.QueryToObjects;
import mainWindow.SenderCommands;

public class ChooseLocationPane {
	
	public static ArrayList<String> result;
	private List<String> locationList;
	public static List<JCheckBox> checkBoxes;

	public ChooseLocationPane() {


		checkBoxes = new ArrayList<JCheckBox>();
		locationList = new ArrayList<String>();
									
		for (int i = 0; i<QueryToObjects.model.getRowCount();i++) {
			System.out.println(QueryToObjects.model.getValueAt(i, 7));
			locationList.add(QueryToObjects.model.getValueAt(i, 7).toString());			
		}
		
		result = new ArrayList<String>(new HashSet<String>(locationList));
        Collections.sort(result);
						
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Выбор района", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 290, 240, 340);
		SenderCommands.f.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		int j = 0;
		for (int i = 0; i<result.size();i++) {
			
			
			JCheckBox checkbox = new JCheckBox(result.get(i));					
			checkbox.setBounds(25, 45+j, 109, 23);
			checkbox.setSelected(true);
			checkBoxes.add(checkbox);		
			
			checkbox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	AddObjectsToObjectsLog.AddObjects();	            	
	            }			            
			});					
			
			JCheckBox checkAllCheckBoxes = new JCheckBox("Все районы");
			checkAllCheckBoxes.setSelected(true);
			checkAllCheckBoxes.setBounds(10, 20, 250, 23);
			panel_1.add(checkAllCheckBoxes);
			checkAllCheckBoxes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (checkAllCheckBoxes.isSelected()) {							
						for (int i = 0; i<checkBoxes.size(); i++) {
							checkBoxes.get(i).setSelected(true);
							AddObjectsToObjectsLog.AddObjects();
						}
					}
					else {
						for (int i = 0; i<checkBoxes.size(); i++) {
							checkBoxes.get(i).setSelected(false);
							AddObjectsToObjectsLog.AddObjects();									
						}
						
					}							
				}					
			});
			
			panel_1.add(checkbox);					
			panel_1.revalidate();			           
			j = j + 23;
			
		}	
	}

}
