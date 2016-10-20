package tablePersonnel;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import personnelMsg.AddPrsnCard;

public class InsertIntoPersonnel {
	
	private static Statement stmt;
	private static int val1;
	private static String val2=null;
	private static String val3=null;
	private static String val4=null;
	private static String val5=null;

	public InsertIntoPersonnel(String name, String number, String position, String group) {
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		val1=0;
		val2=name;
		val3=number;	
		val4=position;
		val5=group;
		
		System.out.println("InsertIntoPersonnel= "+val2);
		if ((val2==null) | (val3==null) | (val4==null)) {
			System.out.println("АЛГОРИТМ НЕВЕРЕН!!!");
			System.out.println("val1 "+val1);
			System.out.println("val2 "+val2);
			System.out.println("val3 "+val3);
			System.out.println("val4 "+val4);
			System.out.println("val5 "+val5);
		}
		else {						
			String query = "INSERT INTO personnel(Выбрать, Имя, Сотовый_номер, Должность, Группа) \n" +
			" VALUES ("+"'"+val1+"'"+", "+"'"+val2+"'"+", "+"'"+val3+"'"+", "+"'"+val4+"'"+", "+"'"+val5+"'"+");";
			System.out.println(query);
		    try {		          	
		    	stmt = QueryTo_Personnel.con.createStatement();
		        stmt.executeUpdate(query);          
		        
		    } catch (SQLException sqlEx) {
		    	System.out.println(sqlEx);
		        int result = JOptionPane.showConfirmDialog(null, "Абонент с таким номером телефона уже существует.\n Повторить ввод?", 
		        		"Ошибка заполнения карточки абонента", 0, 0);
		        if (result == 0) {
		        	AddPrsnCard.editFio.setText("");
		        	AddPrsnCard.editNum.setText("");
		        	AddPrsnCard.editPos.setText("");
		        	AddPrsnCard.editGroup.setText("");
		        }
		    }
		    finally {
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		    }
		}
		
		
	}

}
