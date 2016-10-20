package timer;

import gui.LogsBtnPane;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import tableObjects.TableModel;

public class TimerGetSensors {
	
  Toolkit toolkit;
  Timer timer;

  public TimerGetSensors(int seconds) {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 60000, seconds * 60000);
  }

  class RemindTask extends TimerTask {
    public void run() {
    	TableModel.butCheckAll.doClick();
    	LogsBtnPane.btnGetSensors.doClick(500);    	
    	System.out.println("Time's up!");
    	toolkit.beep();      
    }
  }
}