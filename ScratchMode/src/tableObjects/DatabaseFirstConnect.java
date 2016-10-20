package tableObjects;

import infoMessage.infoMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseFirstConnect {
	
	public static Connection con = null;
	
	public DatabaseFirstConnect() {
		
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		Properties properties = new Properties();
    		properties.setProperty("user", "Ivan");
    		properties.setProperty("password", "12345678");
    		properties.setProperty("useUnicode","true");
    		properties.setProperty("characterEncoding","CP1251");
            con = DriverManager.getConnection("jdbc:mysql://localhost/usr2_db", properties);
            
        } 		
    	catch (ClassNotFoundException ex) {
            System.err.println("KFDB.Cannot find this db driver classes.");
            ex.printStackTrace();
        } 
    	catch (SQLException e) {
            System.err.println("KFDB.Cannot connect to this db.");
            e.printStackTrace();
            new infoMessage("Программа не запущена.\nПроверьте работоспособность базы данных", "Ошибка базы данных", 0);
        }
		
	}

}
