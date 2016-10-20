package infoMessage;

import javax.swing.JOptionPane;
import mainWindow.SenderCommands;

public class infoMessage {
	
	public static String message;
	public static int typeMessage;
	public static String titleMessage;
	
    public infoMessage (String message, String titleMessage, int typeMessage) {
    	
    	
    	infoMessage.message = message;
    	infoMessage.typeMessage = typeMessage;
    	infoMessage.titleMessage = titleMessage;
    	
    	infoMessageRunnable r = new infoMessageRunnable();
        Thread t = new Thread(r);
        t.start();
    }
}
 
class infoMessageRunnable implements Runnable {
	
	public void run() {
        System.out.println(infoMessage.message);
        JOptionPane.showMessageDialog(SenderCommands.f, infoMessage.message, infoMessage.titleMessage, infoMessage.typeMessage);
        
    }
}
