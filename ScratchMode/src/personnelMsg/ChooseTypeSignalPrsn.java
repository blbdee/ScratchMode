package personnelMsg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ChooseTypeSignalPrsn {
	
	public static JFrame mainFrame;
	public static JPanel titlePanel;
	public static JPanel chooseSingnalPanel;
	public static JLabel lblNewLabel;

	public ChooseTypeSignalPrsn() {
		
		mainFrame = new JFrame("Сообщение персоналу");	
		mainFrame.setSize(1000, 700);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().setLayout(null);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(10, 27, 964, 55);
		mainFrame.getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		lblNewLabel = new JLabel("Выберите тип сообщения");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		lblNewLabel.setBounds(156, 11, 621, 33);
		titlePanel.add(lblNewLabel);
		
		chooseSingnalPanel = new JPanel();
		chooseSingnalPanel.setBounds(10, 93, 1000, 550);
		mainFrame.getContentPane().add(chooseSingnalPanel);
		chooseSingnalPanel.setLayout(null);
		
		JButton btnTextMsg = new JButton("Текстовое");
		btnTextMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				new ChoosePrsnTable(0);						
			}
		});
		
		btnTextMsg.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTextMsg.setBounds(110, 215, 260, 88);
		chooseSingnalPanel.add(btnTextMsg);
		
		JButton btnVoiceMsg = new JButton("Голосовое");
		btnVoiceMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChoosePrsnTable(1);
			}
		});
		btnVoiceMsg.setFont(new Font("Arial", Font.PLAIN, 20));
		btnVoiceMsg.setBounds(575, 215, 260, 88);
		chooseSingnalPanel.add(btnVoiceMsg);
		
			
		
	}

}
