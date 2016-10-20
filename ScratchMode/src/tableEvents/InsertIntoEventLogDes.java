package tableEvents;

import gui.LogsBtnPane;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import authentication.LoginWindow;

public class InsertIntoEventLogDes {
	
	private static Statement stmt;
	private static String val1;
	private static String val2;
	private static String val3=null;
	private static String val4;
	public static ArrayList<String> numLastRow = new ArrayList<String>();

	public InsertIntoEventLogDes(String name, String event, String result, String activeUser) {
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		if(LoginWindow.rdbtnEthernet.isSelected()==true) {			
			if (result !=null) {
				val1="Успешно";		
			}
			else { 
				val1="Неудачно";
			}			
		}
		
		else if((LoginWindow.rdbtnSip.isSelected()==true)|(LoginWindow.rdbtnTfop.isSelected()==true)) {			
			val1 = result;			
		}
		
		val2=event;
		val3=name;	
		val4=activeUser;
				
			
		
		System.out.println("EVENTINTOLOG= "+val2);
		if ((val1==null) | (val2==null) | (val3==null) | (val4==null)) {
			System.out.println("АЛГОРИТМ НЕВЕРЕН!!!");
			System.out.println("val1 "+val1);
			System.out.println("val2 "+val2);
			System.out.println("val3 "+val3);
			System.out.println("val4 "+val4);
		}
		else {						
			String query = "INSERT INTO event_log (Результат, Событие, Объект, Дата, Время, Оператор) \n" +
			" VALUES ("+"'"+val1+"'"+", "+"'"+val2+"'"+", "+"'"+val3+"'"+", CURRENT_DATE, CURRENT_TIME, "+"'"+val4+"'"+");";
		
		    try {
		          	
		    	stmt = QueryToEventLog.con.createStatement();
		        stmt.executeUpdate(query); 
		        
		        if(LoginWindow.rdbtnSip.isSelected()) {		        	
			        numLastRow.add(QueryToEvent_log.modelLog.getLastRowNumber().toString());
		        }
		        
		        
		    } catch (SQLException sqlEx) {
		        sqlEx.printStackTrace();
		        System.out.println(sqlEx);
		    }
		    
		    finally {
		        //try { QueryToEventLog.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { 
		        	stmt.close(); 
		        } catch(SQLException se) { 
		        	se.printStackTrace();
		        }
		    }
		}
		
		
	}

}
