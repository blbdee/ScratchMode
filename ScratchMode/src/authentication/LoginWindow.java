package authentication;

import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import config.FromConfig;
import mainWindow.SenderCommands;

import javax.swing.JRadioButton;

import tableObjects.DatabaseFirstConnect;

public class LoginWindow extends JFrame {	
	
	private static final long serialVersionUID = -1759850100583056385L;

	public static void main(String args[]){
		try {
			new DatabaseFirstConnect();
			new FromConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new LoginWindow();
		new LoginFormTime();
	}

	private static JTextField loginField;
	private static JPasswordField passwordField;	
	private static Integer counter = 0;
	public static List<String> logPass = new ArrayList<String>();
	private static Boolean verif = false;
	private static ImagePanel pp;
	public static JLabel lblLogWinTime;
	public static JLabel lblLogWinDate;
	public static String UserName;
	public static JRadioButton rdbtnSip;
	public static JRadioButton rdbtnEthernet;
	public static JRadioButton rdbtnTfop;
	
 
	public LoginWindow() {
		
		super("Вход в систему");
		
		new CheckMacAddr();
		
		String query = "Select * from users";	        
        try {
        	Statement st = DatabaseFirstConnect.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs. next()) {         	             	
            	logPass.add((rs.getString(2)+rs.getString(3)));
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            System.err.println("QueryToEventLog.There are problems with the query " + query);
            e.printStackTrace();
        }    	
      
        		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.gray, Color.gray);		
		pp = new ImagePanel();
		pp.setBorder(border);
        
        try {
            pp.setImage(ImageIO.read(new File(FromConfig.backgrdPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
				
        pp.setPreferredSize(new Dimension(1400, 815));
        getContentPane().add(new JScrollPane(pp));
        
        JLabel lblNewLabel1 = new JLabel("Система дистанционного управления и");
        lblNewLabel1.setBounds(595, 85, 700, 52);
        JLabel lblNewLabel2 = new JLabel("контроля терминалами оповещения");
        lblNewLabel2.setBounds(595, 148, 700, 52);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setForeground(new Color(255, 255, 255));
        lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel2.setForeground(new Color(255, 255, 255));
        lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 35));
        pp.setLayout(null);
        pp.add(lblNewLabel1);
        pp.add(lblNewLabel2);
        
        JPanel panelAuth = new JPanel();
        panelAuth.setBounds(595, 248, 700, 430);
        panelAuth.setBackground(new Color(255, 250, 240));
        pp.add(panelAuth);
        panelAuth.setLayout(null);        
        panelAuth.setBorder(border);
        
        JLabel loginLabel = new JLabel("Имя оператора:");
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginLabel.setBounds(134, 196, 147, 31);
        panelAuth.add(loginLabel);
              
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordLabel.setBounds(134, 260, 147, 31);
        panelAuth.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBorder(border);
        passwordField.setBounds(333, 265, 201, 31);
        panelAuth.add(passwordField);
        
        loginField = new JTextField(15);
        loginField.setBorder(border);
        loginField.setBounds(333, 200, 201, 31);
        panelAuth.add(loginField);
        loginField.setColumns(10);
        
        JButton btnLogin = new JButton("  Вход");
        btnLogin.setBorder(border);
        btnLogin.addActionListener(new ActionListener() {    	
        	
        	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
        		
        		String loginAuth = loginField.getText();
        		String passAuth = passwordField.getText();
        		String mixLogPass = loginAuth+passAuth;        		
        		        		
        		for (int i = 0; i<logPass.size(); i++) {
                	if (logPass.get(i).equals(mixLogPass)) {                		
                		verif = true;  
                		getVerif(verif);
                		setVisible(false); 
                		UserName = loginAuth;
                		break;                		
                	}  
                	else {
                		verif = false; 
                		if (logPass.size()-1 == i) {
                			getVerif(verif);                			
                		}                		
                	}
                }
        	}
        });
        
        btnLogin.setIcon(new ImageIcon(LoginWindow.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogin.setBounds(440, 334, 147, 48);
        panelAuth.add(btnLogin);
        
        JButton btnCancel = new JButton(" Отмена");
        btnCancel.setBorder(border);
        btnCancel.setIcon(new ImageIcon(LoginWindow.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCancel.setBounds(89, 334, 147, 48);
        panelAuth.add(btnCancel);
        
        JLabel lblLogo = new JLabel();
        ImageIcon ii = new ImageIcon(FromConfig.logoPath);
        ii.getImage();
        lblLogo.setIcon(new ImageIcon(ii.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
        lblLogo.setBounds(273, 28, 154, 143);
        panelAuth.add(lblLogo);
        
        JPanel panelDown = new JPanel();
        panelDown.setBorder(border);
        panelDown.setBackground(new Color(255, 250, 240));
        panelDown.setBounds(10, 924, 1890, 60);
        
        pp.add(panelDown);
        panelDown.setLayout(null);
        
        
        lblLogWinTime = new JLabel("");
        lblLogWinTime.setBounds(1680, 10, 200, 40);
        panelDown.add(lblLogWinTime);
        lblLogWinTime.setOpaque(true);
        lblLogWinTime.setBackground(new Color(0, 0, 51));		
        lblLogWinTime.setBorder(border);
        lblLogWinTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogWinTime.setForeground(new Color(102, 204, 255));				
        lblLogWinTime.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblLogWinDate = new JLabel("");
        lblLogWinDate.setOpaque(true);
        lblLogWinDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogWinDate.setForeground(new Color(102, 204, 255));
        lblLogWinDate.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblLogWinDate.setBackground(new Color(0, 0, 51));
        lblLogWinDate.setBounds(10, 10, 200, 40);
        panelDown.add(lblLogWinDate);
        
        JLabel labelIdis = new JLabel("ЗАО АК «Дизайн-центр ИДИС» 1992-2016 версия 1.3_ES"); 
        labelIdis.setOpaque(true);
        labelIdis.setHorizontalAlignment(SwingConstants.CENTER);
        labelIdis.setForeground(new Color(102, 204, 255));
        labelIdis.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelIdis.setBackground(new Color(0, 0, 51));
        labelIdis.setBounds(595, 10, 700, 40);
        panelDown.add(labelIdis);
        
        ButtonGroup group = new ButtonGroup();
        rdbtnEthernet = new JRadioButton("Ethernet", true);
        rdbtnEthernet.setEnabled(true);
        rdbtnEthernet.setBounds(887, 692, 110, 23);
        pp.add(rdbtnEthernet);
        
        rdbtnSip = new JRadioButton("Sip", false);
        rdbtnSip.setEnabled(true);
        rdbtnSip.setBounds(887, 718, 110, 23);
        pp.add(rdbtnSip);
        
        rdbtnTfop = new JRadioButton("ТФОП", false);
        rdbtnTfop.setEnabled(true);
        rdbtnTfop.setBounds(887, 744, 110, 23);
        pp.add(rdbtnTfop);
        
        group.add(rdbtnEthernet);
        group.add(rdbtnSip);
        group.add(rdbtnTfop);
        
        
        
		setSize(1920, 1024);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		//btnLogin.doClick();
		
	}
	
	public static void getVerif(Boolean verif) {
		
			
		if (verif == true) {
			new SenderCommands();			
		}
		else {
			if (counter>=3) { 
    			JOptionPane.showMessageDialog(pp, "Количество попыток исчерпано.");  
    			System.exit(0);
    			
			}
			else {
				JOptionPane.showMessageDialog(pp, "Неверное имя пользователя или пароль. Повторите попытку"); 
				passwordField.setText("");
				loginField.setText("");
				counter++;				
			}
		}
		
	}

}