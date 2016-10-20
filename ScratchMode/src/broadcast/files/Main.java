package broadcast.files;

import java.net.Socket;

import javax.swing.JLabel;

import buttons.SelectWav;
import commands.send.CheckBoxGroup;

public class Main extends Thread {
	
	public static Socket socket;
	public static Socket sock;
	public static JLabel labelNum;
	public static Integer numRow;
			
	public Main(Socket sock, JLabel labelNum, int numRow) {
		
		Main.sock = sock;	
		Main.labelNum = labelNum;
		Main.numRow = numRow;
	}   
	
	@Override
    public void run() {		
      	System.out.println("Running SOCK: "+sock+"LabelNum=" +labelNum);
      	new BW(CheckBoxGroup.selectFiles, sock, labelNum, numRow);	
    }
}

