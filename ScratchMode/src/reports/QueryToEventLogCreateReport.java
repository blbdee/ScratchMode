package reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

import tableEvents.LogTableModel;

public class QueryToEventLogCreateReport {
	
    private Connection con = null;
    private static int countNewRow = 0; // счетчик новых строк

    public  QueryToEventLogCreateReport(String driver, String url, String login, String pass) {
        
    	try {
    		Class.forName(driver);
    		Properties properties=new Properties();
    		properties.setProperty("user","Ivan");
    		properties.setProperty("password","12345678");
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
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();           
            for (int i = 1; i <= cols; i++) {
            	LogTableModel.columnNames.addElement( rsmd.getColumnName(i) );
            	System.out.println(LogTableModel.columnNames);
            }
            while (rs.next()) {
            	Vector<Object> newRow = new Vector<Object>(cols);
                 	for (int i = 1; i <= cols; i++) {
                       	newRow.addElement(rs.getObject(i).toString());
                    }                	
                 	   
                 	System.out.println(newRow);
                 	
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

	public static int getCountNewRow() {
		return countNewRow;
	}

	public static void setCountNewRow(int countNewRow) {
		QueryToEventLogCreateReport.countNewRow = countNewRow;
	}
    
    

}
