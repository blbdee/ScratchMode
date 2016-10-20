package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import jssc.SerialPortException;
import mainWindow.SenderCommands;
import bsk.BSK;

public class BskPanel {
	
	public static JPanel panelBsk = new JPanel();
	public static JLabel labelCom1 = new JLabel();
	public static JLabel labelCom2 = new JLabel();
	public static JLabel labelCom3 = new JLabel();
	public static JLabel labelCom4 = new JLabel();
	public static JLabel labelCom5 = new JLabel();
	public static JLabel labelCom6 = new JLabel();	
	public static Border borderBsk;
	public static BSK startBsk;
	public static JButton btnIncludeBsk;

	public BskPanel() {

		panelBsk.setBounds(10, 635, 240, 300);
		panelBsk.setBorder(BorderFactory.createTitledBorder("Команды ЦСО"));
		SenderCommands.f.getContentPane().add(panelBsk);
		panelBsk.setLayout(null);
		borderBsk = BorderFactory.createLoweredBevelBorder();
		
		labelCom1.setOpaque(true);
		labelCom1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom1.setText("Команда 1.");
		labelCom1.setBounds(10, 28, 220, 28);
		labelCom1.setBorder(borderBsk);
		panelBsk.add(labelCom1);
		
		labelCom2.setOpaque(true);
		labelCom2.setText("Команда 2. Внимание всем.");
		labelCom2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom2.setBounds(10, 67, 220, 28);
		labelCom2.setBorder(borderBsk);
		panelBsk.add(labelCom2);
		
		labelCom3.setOpaque(true);
		labelCom3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom3.setText("Команда 3. Воздушная тревога.");
		labelCom3.setBounds(10, 106, 220, 28);
		labelCom3.setBorder(borderBsk);
		panelBsk.add(labelCom3);
		
		labelCom4.setOpaque(true);
		labelCom4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom4.setText("Команда 4.");
		labelCom4.setBounds(10, 145, 220, 28);
		labelCom4.setBorder(borderBsk);
		panelBsk.add(labelCom4);
		
		labelCom5.setOpaque(true);
		labelCom5.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom5.setText("Команда 5. Трансляция.");
		labelCom5.setBounds(10, 184, 220, 28);
		labelCom5.setBorder(borderBsk);
		panelBsk.add(labelCom5);
		
		labelCom6.setOpaque(true);
		labelCom6.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom6.setText("Команда 6. Стоп.");
		labelCom6.setBounds(10, 223, 220, 28);
		labelCom6.setBorder(borderBsk);
		panelBsk.add(labelCom6);
		
		
		btnIncludeBsk = new JButton("Включить БСК");
		btnIncludeBsk.setBounds(42, 262, 154, 30);
		panelBsk.add(btnIncludeBsk);
		btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3,3, 3, Color.red));
		btnIncludeBsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnIncludeBsk.getText().equals("Включить БСК")) {
					startBsk = new BSK(SenderCommands.serialPort);
					startBsk.start();
					try {
						BSK.sleep(1000);
						if (startBsk.isAlive()) {
							btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.green));
							btnIncludeBsk.setText("Выключить БСК");
						}
						else {
							JOptionPane.showMessageDialog(SenderCommands.f, "Внимание, БСК не был запущен.\nПроверьте подключение устройства БСК.", "Сообщение от БСК", 0);			
						}
					} 
					catch (InterruptedException e1) {
						e1.printStackTrace();
					}													
				}
				else {
					
					int reply = JOptionPane.showConfirmDialog(SenderCommands.f, "Внимание, отключение БСК приведёт к потере соединения с \nвышестоящим "
															  + "уровнем системы оповещения", "Сообщение от БСК", 0, 2);
					if (reply == JOptionPane.YES_OPTION) {
						btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red));
			        	btnIncludeBsk.setText("Включить БСК");				        	
			        	try {
							BSK.serialPort.closePort();
						} catch (SerialPortException e1) {								
							e1.printStackTrace();
							System.out.println(e1);
						}								
					}
					else {
						System.out.println("Nothing do");
					}
					
				}
			}
		});
	}

}
