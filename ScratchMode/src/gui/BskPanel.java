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
		panelBsk.setBorder(BorderFactory.createTitledBorder("������� ���"));
		SenderCommands.f.getContentPane().add(panelBsk);
		panelBsk.setLayout(null);
		borderBsk = BorderFactory.createLoweredBevelBorder();
		
		labelCom1.setOpaque(true);
		labelCom1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom1.setText("������� 1.");
		labelCom1.setBounds(10, 28, 220, 28);
		labelCom1.setBorder(borderBsk);
		panelBsk.add(labelCom1);
		
		labelCom2.setOpaque(true);
		labelCom2.setText("������� 2. �������� ����.");
		labelCom2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom2.setBounds(10, 67, 220, 28);
		labelCom2.setBorder(borderBsk);
		panelBsk.add(labelCom2);
		
		labelCom3.setOpaque(true);
		labelCom3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom3.setText("������� 3. ��������� �������.");
		labelCom3.setBounds(10, 106, 220, 28);
		labelCom3.setBorder(borderBsk);
		panelBsk.add(labelCom3);
		
		labelCom4.setOpaque(true);
		labelCom4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom4.setText("������� 4.");
		labelCom4.setBounds(10, 145, 220, 28);
		labelCom4.setBorder(borderBsk);
		panelBsk.add(labelCom4);
		
		labelCom5.setOpaque(true);
		labelCom5.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom5.setText("������� 5. ����������.");
		labelCom5.setBounds(10, 184, 220, 28);
		labelCom5.setBorder(borderBsk);
		panelBsk.add(labelCom5);
		
		labelCom6.setOpaque(true);
		labelCom6.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		labelCom6.setText("������� 6. ����.");
		labelCom6.setBounds(10, 223, 220, 28);
		labelCom6.setBorder(borderBsk);
		panelBsk.add(labelCom6);
		
		
		btnIncludeBsk = new JButton("�������� ���");
		btnIncludeBsk.setBounds(42, 262, 154, 30);
		panelBsk.add(btnIncludeBsk);
		btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3,3, 3, Color.red));
		btnIncludeBsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnIncludeBsk.getText().equals("�������� ���")) {
					startBsk = new BSK(SenderCommands.serialPort);
					startBsk.start();
					try {
						BSK.sleep(1000);
						if (startBsk.isAlive()) {
							btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.green));
							btnIncludeBsk.setText("��������� ���");
						}
						else {
							JOptionPane.showMessageDialog(SenderCommands.f, "��������, ��� �� ��� �������.\n��������� ����������� ���������� ���.", "��������� �� ���", 0);			
						}
					} 
					catch (InterruptedException e1) {
						e1.printStackTrace();
					}													
				}
				else {
					
					int reply = JOptionPane.showConfirmDialog(SenderCommands.f, "��������, ���������� ��� ������� � ������ ���������� � \n����������� "
															  + "������� ������� ����������", "��������� �� ���", 0, 2);
					if (reply == JOptionPane.YES_OPTION) {
						btnIncludeBsk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red));
			        	btnIncludeBsk.setText("�������� ���");				        	
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
