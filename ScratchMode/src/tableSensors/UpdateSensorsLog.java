package tableSensors;

import java.sql.SQLException;
import java.sql.Statement;

import tableObjects.QueryToObjects;

public class UpdateSensorsLog {
	
	private static Statement stmt;
	
	public UpdateSensorsLog(String name, String power, String temp, String uak1, String uak2, String ant, String firmwate, String door) {
		
		String name2 = QueryToObjects.model.getNameGsm(name);		
		
		//String query = "UPDATE `event_log` SET `�`='"+val1+"',`������`='33333333',`�������`='�����',`���������`='�������',`����`='2016-09-01',`�����`='16:07:29',`��������`='�����' WHERE `�`='2439'";
		String query ="UPDATE `sensor_log` SET `���`='"+name2+"',`�������`='"+power+"',`�����������`='"+temp+"',`�����������1`='"+uak1+"',"
				+ "`�����������2`='"+uak2+"',`GSM_������`='"+ant+"',`����`=CURRENT_DATE,`�����`=CURRENT_TIME,`��������`='"+firmwate+"',`�����`='"+door+"' "
				+ "WHERE `���`='"+name2+"'";
		System.out.println(query);
		try {          	
	    	stmt = QueryToSensorsLog.con.createStatement();
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
