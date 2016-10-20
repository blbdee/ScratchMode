package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import personnelMsg.ChooseTypeSignalPrsn;
import tableObjects.TableModel;
import mainWindow.SenderCommands;

public class ChooseObjectsBtn {	

	public ChooseObjectsBtn() {
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(1620, 10, 250, 270);
		panelButtons.setBorder(BorderFactory.createTitledBorder("Выбор объектов"));
		panelButtons.setLayout(new GridLayout(5, 0, 0, 0));
		
		SenderCommands.f.getContentPane().add(panelButtons);
		TableModel.butCheckAll.setBounds(10, 20, 225, 45);
		TableModel.btnCheckAllRsu.setBounds(10, 79, 225, 45);
		TableModel.btnCheckAllSrn.setBounds(10, 138, 225, 45);
		TableModel.butUncheckAll.setBounds(10, 197, 225, 45);

		JButton btnPrsnMsg = new JButton("Сообщение персоналу");	
		btnPrsnMsg.setEnabled(true);
		btnPrsnMsg.setBounds(10, 256, 225, 45);
		btnPrsnMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ChooseTypeSignalPrsn();
			}			
		});
		
		panelButtons.add(TableModel.butCheckAll);
		panelButtons.add(TableModel.butUncheckAll);
		panelButtons.add(TableModel.btnCheckAllRsu);
		panelButtons.add(TableModel.btnCheckAllSrn);
		panelButtons.add(btnPrsnMsg);

	}

}
