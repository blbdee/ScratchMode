package tablePersonnel;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DelPrsnCard {

	private static Statement stmt;

	public DelPrsnCard(String number) {
		
						
			String query = "DELETE FROM `personnel` WHERE `personnel`.`Сотовый_номер` = "+number;
		
		    try {
		          	
		    	stmt = QueryTo_Personnel.con.createStatement();
		        stmt.executeUpdate(query);          
		        
		    } catch (SQLException sqlEx) {
		    	System.out.println(sqlEx);
		        
		    }
		    finally {
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		    }
		}
		
		
	

}
