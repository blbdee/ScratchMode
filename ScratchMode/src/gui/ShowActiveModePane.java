package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mainWindow.SenderCommands;

public class ShowActiveModePane {

	public static JLabel editorPane = new JLabel();
	
	public ShowActiveModePane() {

		JPanel totalObjectsChecked = new JPanel();
		totalObjectsChecked.setBounds(260, 290, 1350, 60);
		totalObjectsChecked.setBorder(BorderFactory.createTitledBorder("Режим оповещения"));
		SenderCommands.f.getContentPane().add(totalObjectsChecked);
		totalObjectsChecked.setLayout(null);
		
		editorPane.setBounds(480, 17, 510, 34);
		totalObjectsChecked.add(editorPane);
		editorPane.setForeground(Color.BLACK);
		editorPane.setBackground(new Color(240, 240, 240));
		editorPane.setFont(new Font("Arial", Font.BOLD, 22));
		editorPane.setText("Активный режим: Сирена.");
	}

}
