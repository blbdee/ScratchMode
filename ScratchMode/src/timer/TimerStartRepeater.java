package timer;

import java.awt.Color;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import tableObjects.QueryToObjects;
import tableObjects.TableModel;
import commands.send.CheckBoxGroup;

public class TimerStartRepeater {
	
	Toolkit toolkit;
	private Timer timer;
	private String ip;
	private JLabel labelNum;

	public TimerStartRepeater(int seconds, JLabel labelNum, InetAddress ip) {
		toolkit = Toolkit.getDefaultToolkit();
	    timer = new Timer();
	    timer.schedule(new RemindTask(), seconds * 1000);
	    this.ip = ip.toString();
	    this.labelNum = labelNum;
	}

	class RemindTask extends TimerTask {
		public void run() {

	    	TableModel.butUncheckAll.doClick(); 
    	
	    	for (int i=0; i<QueryToObjects.model.getRowCount();i++) {
	    		String s = "/"+QueryToObjects.model.getValueAt(i, 3);
	    		if (s.equals(ip)) {
	    			System.out.println("WORK");
	    			System.out.println(s);
	    			System.out.println(ip);
	    			QueryToObjects.model.setValueAt(true, i, 0);
	    		}
	    		else {
	    			System.out.println("NOWORK");
	    		}
	    	}
	    	System.out.println("NUMROOW= "+ip);
	    	CheckBoxGroup.butBroadcast.doClick(); 
	    	labelNum.setBackground(Color.green);
	    	System.out.println("OKKK");
		}
	}
}