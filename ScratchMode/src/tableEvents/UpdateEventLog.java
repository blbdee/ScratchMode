package tableEvents;

import java.sql.SQLException;
import java.sql.Statement;

import tableObjects.QueryToObjects;

public class UpdateEventLog {
	
	private static Statement stmt;
	
	public UpdateEventLog(String num, String name, String event, String result, String activeUser) {
		
		String name2 = QueryToObjects.model.getNameGsm(name);
		//String query = "UPDATE `event_log` SET `№`='"+val1+"',`Объект`='33333333',`Событие`='Отбой',`Результат`='Успешно',`Дата`='2016-09-01',`Время`='16:07:29',`Оператор`='Сизый' WHERE `№`='2439'";
		String query ="UPDATE `event_log` SET `№`='"+num+"',`Объект`='"+name2+"',`Событие`='"+event+"',`Результат`='"+result+"',`Дата`=CURRENT_DATE,"
				+ "`Время`=CURRENT_TIME,`Оператор`='"+activeUser+"' WHERE `№`='"+num+"' AND `Объект`='"+name2+"' AND `Событие`='"+event+"'";
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
