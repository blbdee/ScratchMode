package tablePersonnelMsg;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import personnelMsg.AddPrsnCard;
import personnelMsg.preparedMsg.AddPreparedMsg;

public class InsertIntoPersonnelMsg {
	
	private static Statement stmt;
	private static int val1;
	private static String val2=null;
	private static String val3=null;
	private static String val4=null;

	public InsertIntoPersonnelMsg(String number, String message, String group) {
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		val1=0;
		val2=number;
		val3=group;
		val4=message;	
		
		if ((val2==null) | (val3==null) | (val4==null)) {
			System.out.println("�������� �������!!!");
		}
		else {			
			
			String query = "INSERT INTO `personnelmsg` (`�������`, `�`, `������`, `���������`) VALUES ('0', "+"'"+val2+"'"+", "+"'"+val3+"'"+", "+"'"+val4+"'"+");";			
		    try {		          	
		    	stmt = QueryTo_PersonnelMsg.con.createStatement();
		        stmt.executeUpdate(query);          
		        AddPreparedMsg.newMsgPane.setVisible(false);
		    } catch (SQLException sqlEx) {
		    	int result = JOptionPane.showConfirmDialog(null, "��������� � ����� ������� �������� ��� ����������.\n ��������� ����?", 
		        		"������ ���������� �������� ���������� ���������", 0, 0);
		        if (result == 0) {
		        	AddPreparedMsg.editGroup.setText("");
		        	AddPreparedMsg.editMsg.setText("");
		        	AddPreparedMsg.editNum.setText("");
		        }
		    }
		    finally {
		        try { stmt.close(); } catch(SQLException se) { System.out.println(123); }
		    }
		}
		
		
	}

}
