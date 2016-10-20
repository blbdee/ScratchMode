package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.media.ControllerAdapter;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import authentication.LoginWindow;
import commands.send.CheckBoxGroup;
import config.FromConfig;
import mainWindow.SenderCommands;

public class AlertModePane {	
	
	public static JButton btnSirenPane;

	public AlertModePane() {
		
		JPanel modeAlert = new JPanel();
		modeAlert.setBounds(1620, 290, 250, 340);
		modeAlert.setBorder(BorderFactory.createTitledBorder("Режим оповещения"));
		SenderCommands.f.getContentPane().add(modeAlert);
		modeAlert.setLayout(new GridLayout(4, 1, 0, 0));

		btnSirenPane = new JButton("Сирена");
		JButton btnMp3Pane = new JButton("Проигрыватель");
		JButton btnMicPane = new JButton("Микрофон");				
		JButton btnMemoryPane = new JButton("Готовые ролики");
		
		
		btnSirenPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SirenPaneEth.typeSignalSiren.setVisible(true);
				SirenPaneEth.numSignal.setVisible(false);						
				SirenPaneEth.panelCommands1.setVisible(true);						
				SirenPaneEth.panelCommands2.setVisible(false);
				SirenPaneEth.panelCommands3.setVisible(false);
				SirenPaneEth.panelCommands4.setVisible(false);
				SirenPaneEth.PlayerPane.setVisible(false);	
				
				btnSirenPane.setBackground(Color.green);
				btnMicPane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMp3Pane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMemoryPane.setBackground(UIManager.getColor("MenuItem.background"));
				
				if(LoginWindow.rdbtnSip.isSelected() == true) {
					CheckBoxGroup.butAuth.setEnabled(false);
				}
				
				ShowActiveModePane.editorPane.setText("Активный режим: Сирена.");
			}
		});

		
		btnMicPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SirenPaneEth.typeSignalSiren.setVisible(false);
				SirenPaneEth.numSignal.setVisible(false);						
				SirenPaneEth.panelCommands1.setVisible(false);
				SirenPaneEth.panelCommands2.setVisible(true);						
				SirenPaneEth.panelCommands3.setVisible(false);
				SirenPaneEth.panelCommands4.setVisible(false);
				SirenPaneEth.PlayerPane.setVisible(false);	
				
				btnSirenPane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMicPane.setBackground(Color.green);
				btnMp3Pane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMemoryPane.setBackground(UIManager.getColor("MenuItem.background"));
				
				SirenPaneEth.panelCommands3.remove(CheckBoxGroup.butBroadcast);
				SirenPaneEth.panelCommands2.add(CheckBoxGroup.butBroadcast);
				
				SirenPaneEth.panelCommands3.remove(CheckBoxGroup.butStopBroadcast);
				SirenPaneEth.panelCommands2.add(CheckBoxGroup.butStopBroadcast);
				
				CheckBoxGroup.butBroadcast.setEnabled(false);
				CheckBoxGroup.butStopBroadcast.setEnabled(false);
				ShowActiveModePane.editorPane.setText("Активный режим: Микрофон.");
				ShowActiveModePane.editorPane.setForeground(Color.BLACK);
				
				
				
				//Кнопка онлайн трансляции выключена. Доработать.
				//SirenPaneEth.panelCommands2.add(CheckBoxGroup.butBroadcastOnline);
				//CheckBoxGroup.butBroadcastOnline.setBounds(840, 11, 90, 48);				
				/*if(LoginWindow.rdbtnSip.isSelected() == true) {
					CheckBoxGroup.butBroadcastOnline.setEnabled(false);
				}*/				
			}
		});
		
		btnMp3Pane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SirenPaneEth.typeSignalSiren.setVisible(false);
				SirenPaneEth.numSignal.setVisible(false);						
				SirenPaneEth.panelCommands1.setVisible(false);
				SirenPaneEth.panelCommands2.setVisible(false);						
				SirenPaneEth.panelCommands3.setVisible(true);	
				SirenPaneEth.panelCommands4.setVisible(false);
				
				btnSirenPane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMicPane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMp3Pane.setBackground(Color.green);
				btnMemoryPane.setBackground(UIManager.getColor("MenuItem.background"));
				
				SirenPaneEth.panelCommands2.remove(CheckBoxGroup.butBroadcast);						
								
				SirenPaneEth.panelCommands3.add(CheckBoxGroup.butBroadcast);	
				
				SirenPaneEth.panelCommands2.remove(CheckBoxGroup.butStopBroadcast);
				SirenPaneEth.panelCommands3.add(CheckBoxGroup.butStopBroadcast);
				
				SirenPaneEth.PlayerPane.setVisible(true);
				CheckBoxGroup.butBroadcast.setEnabled(false);
				CheckBoxGroup.butStopBroadcast.setEnabled(false);
				ShowActiveModePane.editorPane.setText("Активный режим: Проигрыватель.");						
				try {
					load(new File(FromConfig.coderPath));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});

		
		btnMemoryPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SirenPaneEth.typeSignalSiren.setVisible(false);
				SirenPaneEth.numSignal.setVisible(true);						
				SirenPaneEth.panelCommands1.setVisible(false);
				SirenPaneEth.panelCommands2.setVisible(false);
				SirenPaneEth.panelCommands3.setVisible(false);
				SirenPaneEth.panelCommands4.setVisible(true);
				SirenPaneEth.PlayerPane.setVisible(false);		
				
				btnSirenPane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMicPane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMp3Pane.setBackground(UIManager.getColor("MenuItem.background"));
				btnMemoryPane.setBackground(Color.green);
				
				ShowActiveModePane.editorPane.setText("Активный режим: Готовые ролики.");
			}
		});

		modeAlert.add(btnSirenPane);
		modeAlert.add(btnMicPane);
		modeAlert.add(btnMp3Pane);
		modeAlert.add(btnMemoryPane);				
		
	}
	
	
	static Player player;
    static Component center;
    static Component south;
    static Component cpc;
    

	
	public static void load(File file) throws Exception {		
		
    	@SuppressWarnings("deprecation")
		URL url = file.toURL();
    	System.out.println("Path to mp3-player= "+url);
    	      
    	if (player != null) {
    		player.stop();
    		
    	}
      
    	player = Manager.createPlayer(url);
    	ControllerListener listener = new ControllerAdapter() {
    	  
    		public void realizeComplete(RealizeCompleteEvent event) { 
    			
    			if(cpc != null) {
    				SirenPaneEth.PlayerPane.remove(cpc);     				
    				System.out.println("cpc!");    			   				
    			}
    			cpc = player.getControlPanelComponent(); 
	    		cpc.setBounds(10, 93, 673, 25);
	    		SirenPaneEth.PlayerPane.add(cpc);
    			
    		}
    	};
      
    	player.addControllerListener(listener);    	
    	player.prefetch();    	
    }

}
