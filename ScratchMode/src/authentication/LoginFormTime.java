package authentication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
	 
	public class LoginFormTime extends JFrame {
	 
	    /**
		 * 
		 */
		private static final long serialVersionUID = -4219262528141534912L;

		private Timer ClockTimer = new Timer(500, new Clock());
	    
	    public JFrame f = new JFrame();
    	public JLabel l = new JLabel();
    	public static String reportDate;
	 
	    public LoginFormTime() {	    	
	        ClockTimer.start();	        
	    }
	 
	    class Clock implements ActionListener {
	 
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            // *** For DATE : 2013.12.12
	            Date d = new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.dd.MM");
	            LoginWindow.lblLogWinDate.setText(sdf.format(d));
	 
	            // *** For TIME : 22:33:22
	            DateFormat df = new SimpleDateFormat("HH:mm:ss");
	            Date time = Calendar.getInstance().getTime();
	            reportDate = df.format(time);
	            LoginWindow.lblLogWinTime.setText(reportDate);
	        }
	    }    
	    
    
    
}