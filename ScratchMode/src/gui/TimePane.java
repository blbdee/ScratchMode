package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import authentication.LoginWindow;
import mainWindow.SenderCommands;

public class TimePane {

	public static JLabel lblTime = new JLabel();
	public static JLabel lblDate = new JLabel();

	public TimePane() {
		
		JPanel panelTime = new JPanel();
		panelTime.setBounds(10, 10, 240, 270);
		SenderCommands.f.getContentPane().add(panelTime);
		panelTime.setBorder(BorderFactory.createTitledBorder("Системное время"));
		panelTime.setLayout(null);
						
		lblTime.setOpaque(true);
		lblTime.setBackground(new Color(0, 0, 51));
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray);
		lblTime.setBorder(border);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new Color(102, 204, 255));				
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTime.setBounds(10, 25, 220, 50);
		panelTime.add(lblTime);
		
		JLabel lblUserName = new JLabel("Имя оператора:");
		lblUserName.setForeground(SystemColor.windowBorder);
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserName.setBounds(10, 90, 220, 45);
		panelTime.add(lblUserName);			
		
		
		JLabel lblUser = new JLabel(LoginWindow.UserName);
		lblUser.setOpaque(true);
		lblUser.setBackground(new Color(0, 0, 51));
		lblUser.setBorder(border);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(new Color(102, 204, 255));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUser.setBounds(10, 140, 220, 45);
		panelTime.add(lblUser);
		
		lblDate.setOpaque(true);
		lblDate.setBorder(border);
		lblDate.setBackground(new Color(0, 0, 51));
		lblDate.setForeground(new Color(102, 204, 255));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 25));			
		lblDate.setBounds(10, 210, 220, 45);
		panelTime.add(lblDate);
		
		
	}

}
