package tableEvents;

import java.sql.SQLException;
import java.sql.Statement;

import tableObjects.QueryToObjects;

public class UpdateEventLog {
	
	private static Statement stmt;
	
	public UpdateEventLog(String num, String name, String event, String result, String activeUser) {
		
		String name2 = QueryToObjects.model.getNameGsm(name);
		//String query = "UPDATE `event_log` SET `�`='"+val1+"',`������`='33333333',`�������`='�����',`���������`='�������',`����`='2016-09-01',`�����`='16:07:29',`��������`='�����' WHERE `�`='2439'";
		String query ="UPDATE `event_log` SET `�`='"+num+"',`������`='"+name2+"',`�������`='"+event+"',`���������`='"+result+"',`����`=CURRENT_DATE,"
				+ "`�����`=CURRENT_TIME,`��������`='"+activeUser+"' WHERE `�`='"+num+"' AND `������`='"+name2+"' AND `�������`='"+event+"'";
		System.out.println(query);
		try {          	
	    	stmt = QueryToEventLog.con.createStatement();
	        stmt.executeUpdate(query);          
	        stmt.close();
	    } catch (SQLException sqlEx) {
	        sqlEx.printStackTrace();
	    }
	    finally {
	        //try { QueryToEventLog.con.close(); } catch(SQLException se) {  }
	        try { stmt.close(); } catch(SQLException se) {  }
	    }
	}

}
