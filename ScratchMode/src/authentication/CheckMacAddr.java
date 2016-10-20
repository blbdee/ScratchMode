package authentication;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tableObjects.DatabaseFirstConnect;

public class CheckMacAddr {

	public CheckMacAddr() {
		
		List<String> macStrList = new ArrayList<String>();		
		String queryGetMac = "Select * from mcaddr";
        try {
        	Statement st = DatabaseFirstConnect.con.createStatement();
            ResultSet rs = st.executeQuery(queryGetMac);
            while(rs.next()) {         	             	
            	macStrList.add(rs.getString(2));
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            System.err.println("QueryToEventLog.There are problems with the query " + queryGetMac);
            e.printStackTrace();
        }
        
        
        String macStr = "";
        try {        	
            InetAddress address = InetAddress.getLocalHost();            
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                	String tempStr = "";
                    for (int i = 0; i < mac.length; i++) {
                    	tempStr = tempStr + mac[i];                    	
                    }
                    macStr = tempStr.replace("-", "");
                    
                } else {
                    System.out.println("Address doesn't exist or is not accessible.");
                }
            } else {
                System.out.println("Network Interface for the specified address is not found.");
            }
        	} catch (UnknownHostException e) {
        		e.printStackTrace();
        	} catch (SocketException e) {
        		e.printStackTrace();
        	}
		
        
        if(macStrList.size()!=0) {  
        	String s = "";
        	for(int i=0;i<macStrList.size();i++) {
        		s = s+macStrList.get(i);
        	}
        	        	
        	if((macStr.equals(s))!=true){
        		JOptionPane.showMessageDialog(null, "Ошибка безопасности!", "Security error", 0);
        		System.exit(0);        		
        	}
        }
        else {        
        	String queryInsertMac = "INSERT INTO mcaddr (mcaddr) VALUES("+"'"+macStr+"'"+")";
            try {
            	Statement st = DatabaseFirstConnect.con.createStatement();
                st.executeUpdate(queryInsertMac);                 
                st.close();
            }
            catch (SQLException e) {
                System.err.println("QueryToEventLog.There are problems with the query " + queryInsertMac);
                e.printStackTrace();
            }
    		
    	}
	}

}
