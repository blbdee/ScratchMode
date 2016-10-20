package tableSensors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

public class QueryToSensorsLog {
	
    public static Connection con = null;
    public static Statement stmt;
    public static ResultSet rs;
    

    public  QueryToSensorsLog(String driver, String url, String login, String pass) {
        
    	try {
    		Class.forName(driver);
    		Properties properties=new Properties();
    		properties.setProperty("user", login);
    		properties.setProperty("password", pass);
    		properties.setProperty("useUnicode","true");
    		properties.setProperty("characterEncoding","CP1251");
            con = DriverManager.getConnection(url, properties);
        } 
    	catch (ClassNotFoundException ex) {
            System.err.println("KFDB.Cannot find this db driver classes.");
            ex.printStackTrace();
        } 
    	catch (SQLException e) {
            System.err.println("KFDB.Cannot connect to this db.");
            e.printStackTrace();
        }         
    }

    public Vector<Vector<Object>> getNomen(String query) {
    	
        Vector<Vector<Object>> retVector = new Vector<Vector<Object>>();
        try {
        	Statement st = con.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();           
            for (int i = 1; i <= cols; i++) {
            	SensorsTableModel.columnNames.addElement( rsmd.getColumnName(i) );
            }
            while (rs.next()) {
            	Vector<Object> newRow = new Vector<Object>(cols);
                 	for (int i = 1; i <= cols; i++) {
                       	newRow.addElement(rs.getObject(i).toString());
                    }                 	
                 	retVector.add(newRow);     
                 	
            }
            rs.close();            
            st.close();
        }
        catch (SQLException e) {
            System.err.println("QueryToEventLog.There are problems with the query " + query);
            e.printStackTrace();
        }    	
        return retVector;        
    }
}
