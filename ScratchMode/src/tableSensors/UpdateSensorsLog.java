package tableSensors;

import java.sql.SQLException;
import java.sql.Statement;

import tableObjects.QueryToObjects;

public class UpdateSensorsLog {
	
	private static Statement stmt;
	
	public UpdateSensorsLog(String name, String power, String temp, String uak1, String uak2, String ant, String firmwate, String door) {
		
		String name2 = QueryToObjects.model.getNameGsm(name);		
		
		//String query = "UPDATE `event_log` SET `№`='"+val1+"',`Объект`='33333333',`Событие`='Отбой',`Результат`='Успешно',`Дата`='2016-09-01',`Время`='16:07:29',`Оператор`='Сизый' WHERE `№`='2439'";
		String query ="UPDATE `sensor_log` SET `Имя`='"+name2+"',`Питание`='"+power+"',`Температура`='"+temp+"',`Аккумулятор1`='"+uak1+"',"
				+ "`Аккумулятор2`='"+uak2+"',`GSM_сигнал`='"+ant+"',`Дата`=CURRENT_DATE,`Время`=CURRENT_TIME,`Прошивка`='"+firmwate+"',`Дверь`='"+door+"' "
				+ "WHERE `Имя`='"+name2+"'";
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
