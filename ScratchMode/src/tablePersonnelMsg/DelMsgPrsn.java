package tablePersonnelMsg;

import java.sql.SQLException;
import java.sql.Statement;

public class DelMsgPrsn {

	private static Statement stmt;

	public DelMsgPrsn(String number) {		
						
		String query = "DELETE FROM `personnelmsg` WHERE `personnelmsg`.`¹` ="+number;
		System.out.println(query);
		
	    try {		          	
	    	stmt = QueryTo_PersonnelMsg.con.createStatement();
	        stmt.executeUpdate(query);          
	        
	    } catch (SQLException sqlEx) {
	    	System.out.println(sqlEx);		        
	    }
	    finally {
	        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	    }
	}
	
}
