package commands.getMessages;


public class CheckMsgList extends Thread {
	
	private static String s2;
	
	public static void checkMsgList(String s) {
		s2 = s;
	}
			
	public void run() {
		
		System.out.println(s2);
		if(s2.contains("ID#")) {	
			//if(s2.length()<170) {
			if(s2.contains("Uak")) {
				new ParsingMsgSensors(s2);				
			}
			else {
				new ParsingMsg(s2);				
			}						
		}	
				
	}

}
