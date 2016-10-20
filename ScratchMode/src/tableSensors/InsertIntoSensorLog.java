package tableSensors;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tableEvents.QueryToEventLog;

public class InsertIntoSensorLog {
	
	private static Statement stmt;
	private static Statement stmtDel;
	private static String val1=null;
	private static String val2=null;
	private static String val3=null;
	private static String val4=null;
	private static String val5=null;
	private static String val6=null;
	private static String val7=null;
	private static String val0=null;
	
	public InsertIntoSensorLog(ArrayList<String> sensorList, String name, Integer counter) {
				
		val0 = name;
		val1 = sensorList.get(0);
		val2 = sensorList.get(1);
		val3 = sensorList.get(2);
		val4 = sensorList.get(3);
		val5 = sensorList.get(4);
		val6 = sensorList.get(5);
		val7 = sensorList.get(6);
				
		System.out.println("val "+val1);
		System.out.println("val "+val2);
		System.out.println("val "+val3);
		System.out.println("val "+val4);
		System.out.println("val "+val5);
		System.out.println("val "+val6);
		System.out.println("val "+val7);
				
				
		if ((val1==null) | (val2==null) | (val3==null) | (val4==null) | (val5==null) | (val6==null) | (val7==null)) {
			System.out.println("АЛГОРИТМ НЕВЕРЕН!!!");
		}
		else {
			System.out.println("CCCCCCCCCCCCCCUUUUUUUUUUUUUUUUUUUUU"+counter);
			
			if (counter==0) {				
				String queryDel = "Delete from sensor_log";	
				try {
					stmtDel = QueryToEventLog.con.createStatement();
					stmtDel.executeUpdate(queryDel);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
									
			String query = "INSERT INTO sensor_log (Имя, Прошивка, Питание, Температура, Аккумулятор1, Аккумулятор2, Дверь, GSM_сигнал, Дата, Время) \n" +
			" VALUES ("+"'"+val0+"'"+", "+"'"+val1+"'"+", "+"'"+val2+"'"+", "+"'"+val3+"'"+", "+"'"+val4+"'"+", "+"'"+val5+"'"+", "+"'"+val6+"'"+", "+"'"+val7+"'"+", CURRENT_DATE, CURRENT_TIME);";			
			System.out.println(query);
			
		    try {
		          	
		    	stmt = QueryToEventLog.con.createStatement();
		    	stmt.executeUpdate(query);
		        
		    } catch (SQLException sqlEx) {
		        sqlEx.printStackTrace();
		    }
		    finally {
		        
		        try { stmt.close(); } catch(SQLException se) { }
		    }
		}
	}

}
