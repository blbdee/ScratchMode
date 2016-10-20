package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import bsk.BSK;
import mainWindow.SenderCommands;

public class MainMenu {

	public MainMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		SenderCommands.f.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Файл");
        JMenu helpMenu = new JMenu("Справка");
        JMenu exitMenu = new JMenu("Выход");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(exitMenu);

       
        JMenuItem bskAction = new JMenuItem("Запуск БСК");
        JMenuItem helpAction = new JMenuItem("Справка");
        JMenuItem exitAction = new JMenuItem("Выход");

        
        fileMenu.add(bskAction);
        helpMenu.add(helpAction);
        exitMenu.add(exitAction);
        
        bskAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BskPanel.startBsk = new BSK(SenderCommands.serialPort);
				BskPanel.startBsk.start();
				try {
					BSK.sleep(1000);
					if (BskPanel.startBsk.isAlive()) {
						BskPanel.btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.green));
						BskPanel.btnIncludeBsk.setText("Bsk ON");
					}
					else JOptionPane.showConfirmDialog(null, "БСК не запущен!");
				} 
				catch (InterruptedException e1) {
					e1.printStackTrace();
				}
								
			}
		});
        
        helpAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	
	            try {
		            Desktop d=Desktop.getDesktop();
		            d.browse(new URI("http://www.google.ru"));
	            } catch (IOException ioe) {
	            	ioe.printStackTrace();
	            } catch (URISyntaxException use) {
	            	use.printStackTrace();
	            }
            	
            }

        });
        
        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {		            	
            	System.exit(0);	
            }
        });
	}

}
