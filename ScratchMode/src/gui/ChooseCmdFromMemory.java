package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tableMsgMemory.QueryToMsgMemory;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ChooseCmdFromMemory {
	
	public static String cmdFromMemory; 
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static String chooseCmdFromMemory() {
		
		//new QueryToMsgMemory();
		
		JFrame cmdFromMemoryPane = new JFrame();
		cmdFromMemoryPane.setResizable(false);
		cmdFromMemoryPane.getContentPane().setLayout(null);
		cmdFromMemoryPane.setSize(1440,900);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1414, 742);
		cmdFromMemoryPane.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(QueryToMsgMemory.msgMemoryTable);		
		
		JPanel mp3Pane = new JPanel();
		mp3Pane.setBounds(10, 764, 1414, 97);
		cmdFromMemoryPane.getContentPane().add(mp3Pane);
		mp3Pane.setLayout(null);
		
		JButton butGetStatus = new JButton("Подтвердить выбор");
		butGetStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				SirenPaneEth.labelNumArea.setText("");
				SirenPaneEth.labelTextCmdArea.setText("");
				cmdFromMemory = QueryToMsgMemory.msgMemoryModel.getCommand();
				SirenPaneEth.labelNumArea.append(QueryToMsgMemory.msgMemoryModel.getName());
				SirenPaneEth.labelTextCmdArea.append(QueryToMsgMemory.msgMemoryModel.getCmdText());
				cmdFromMemoryPane.dispose();
			}
		});
		butGetStatus.setBounds(269, 26, 196, 46);
		mp3Pane.add(butGetStatus);
		
		JButton button = new JButton("Отменить выбор");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(900, 26, 196, 46);
		mp3Pane.add(button);
		cmdFromMemoryPane.setVisible(true);
		
		return cmdFromMemory;			
	}
}
