package tableObjects;

import gui.CheckedObjPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Имена колонок;
    public static Vector<String> columnNames;    
    //Данные;
    private Vector<Vector<Object>> tableData;    
    //Классы колонок;
    protected Vector<Object> vColClass;
    
    
    public static List<Boolean> rowList;
    public static int rows2;
    Set<Integer> checked = new TreeSet<Integer>();
    public static List<String> ipList = new ArrayList<String>();
    public static List<String> phoneList = new ArrayList<String>();
    public static List<String> phoneTfopList = new ArrayList<String>();
    public static List<Integer> portList = new ArrayList<Integer>();
    public static List<String> nameList = new ArrayList<String>();
    public static List<Integer> rowsList = new ArrayList<Integer>();
    public static List<String> typeList = new ArrayList<String>();
    
    public static JButton butCheckAll;
    public static JButton butUncheckAll;
    public static JButton btnCheckAllRsu;
    public static JButton btnCheckAllSrn;
    
    
    
    public TableModel()	{
    	super();
        vColClass = new Vector<Object>(); 
        columnNames = new Vector<String>();
        
        rows2 = 500;
        rowList = new ArrayList<Boolean>(rows2);
        for (int i = 0; i < rows2; i++) {
            rowList.add(Boolean.FALSE);
        }
        
        
        butCheckAll = new JButton("Циркулярно");
        butCheckAll.addActionListener(new ActionListener() {        	
    		@Override
    		public void actionPerformed(ActionEvent e) { 
    						
    			if (CheckedObjPane.dlm.size() != getRowCount()) {    				
    				for (int i=0; i<getRowCount(); i++) {   					
	   					String var = QueryToObjects.model.getValueAt(i, 0).toString();
						if (var == "false") {
							setValueAt(true, i, 0);     							
						}        				   			    				
	    			}         		
            	}    			
    		}        	
        });
        
        butUncheckAll = new JButton("Снять выбор");
        butUncheckAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (CheckedObjPane.dlm.size() > 0) {
					
					for (int i=0; i<getRowCount(); i++) {
						String var = QueryToObjects.model.getValueAt(i, 0).toString();
						if (var == "true") {
							setValueAt(false, i, 0);  							
						}	    				     			    				
					}	
					ipList.clear();
					phoneList.clear();
					portList.clear(); 
					nameList.clear();
					rowsList.clear();
					phoneTfopList.clear();
					CheckedObjPane.dlm.clear();
				}
				
			}            	            	            	
        });  
        
        btnCheckAllRsu = new JButton("Выбрать РСУ");
        btnCheckAllRsu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (CheckedObjPane.dlm.size() != getRowCount()) {    				
    				for (int i=0; i<getRowCount(); i++) { 
    					
	   					String var = QueryToObjects.model.getValueAt(i, 0).toString();
	   					int var2 = Integer.parseInt(QueryToObjects.model.getValueAt(i, 8).toString());
						if ((var == "false") & (var2==1)) {
							setValueAt(true, i, 0);     							
						}        				   			    				
	    			}         		
            	}
			}            	            	            	
        });
        
        btnCheckAllSrn = new JButton("Выбрать УЗСК");
        btnCheckAllSrn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (CheckedObjPane.dlm.size() != getRowCount()) {    				
    				for (int i=0; i<getRowCount(); i++) { 
    					
	   					String var = QueryToObjects.model.getValueAt(i, 0).toString();
	   					int var2 = Integer.parseInt(QueryToObjects.model.getValueAt(i, 8).toString());
						if ((var == "false") & (var2==0)) {
							setValueAt(true, i, 0);     							
						}        				   			    				
	    			}         		
            	}
								
			}            	            	            	
        });
        
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        boolean b = (Boolean) aValue;
        rowList.set(row, b);
        if (b) {
            checked.add(row);
            System.out.println("add "+checked);
            
            Object ip = QueryToObjects.model.getValueAt(row, 3);
            String ipStr = ip.toString();
            ipList.add(ipStr);
            
            Object phone = QueryToObjects.model.getValueAt(row, 5);
            String phoneStr = phone.toString();
            phoneList.add(phoneStr);
            
            Object phoneTfop = QueryToObjects.model.getValueAt(row, 9);
            String phoneTfopStr = phoneTfop.toString();
            phoneTfopList.add(phoneTfopStr);
            
            Object port = QueryToObjects.model.getValueAt(row, 4);
            String portStr = port.toString();
            Integer portInt = Integer.valueOf(portStr);
            portList.add(portInt);
                       
            Object name = QueryToObjects.model.getValueAt(row, 2);
            String nameStr = name.toString();
            nameList.add(nameStr);
            
            Object type = QueryToObjects.model.getValueAt(row, 8);
            String typeStr = type.toString();
            typeList.add(typeStr);
            
            rowsList.add(row);
            
        } else {
        	System.out.println("rem "+checked); 
            checked.remove(row);                       
            Object name = QueryToObjects.model.getValueAt(row, 2);
            String nameStr = name.toString();
            
            Object ip = QueryToObjects.model.getValueAt(row, 3);
            String ipStr = ip.toString();
            
            for (int i=0; i<=nameList.size(); i++) {
            	if (nameList.get(i)==nameStr ) {
            		nameList.remove(i);
            		break;
            	}
            }
            
            
            for (int i=0; i<=ipList.size(); i++) {
            	if (ipList.get(i)==ipStr ) {
            		ipList.remove(i);
            		portList.remove(i);
            		phoneList.remove(i);
            		phoneTfopList.remove(i);
            		typeList.remove(i);
            		break;
            	} 
            }
                        
        }
        //перерисовка работает
        fireTableRowsUpdated(row, row);
    }
    
  //количество колонок//
    @Override
    public int getColumnCount() {
    	//убрать последнюю колонку
    	return columnNames.size()-2;        
    }
    //количество строк?//
    @Override
    public int getRowCount() {
    	return getTableData().size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
        	return rowList.get(row);
        	
        } else {
        	return getTableData().get(row).get(col);
        }
    }
    
    //Показывать заголовки колонок;
    public String getColumnName(int column) {
    	return columnNames.get(column);
    }
    
    @Override
    public Class<?> getColumnClass(int column) {
    	return getValueAt(0, column).getClass();
    }
    //запрет редактирования колонок
    @Override
    public boolean isCellEditable(int row, int column) {
    	if(column != 0) {
    		return false;
        }
    	return true;
    }
    
    public void setTableData(Vector<Vector<Object>> tableData) {
    	this.tableData = tableData;
    }

    public Vector<Vector<Object>> getTableData() {
    	return tableData;
    }
    
    public String getNameGsm(String number) {
    	
    	String name = null;
    	for	(int i=0;i<getRowCount();i++) {
    		if(getValueAt(i,5).toString().equals(number)){
    			name = getValueAt(i,2).toString();
    			break;
    		}
    	}    	    	
    	return name;
    }
    
    public String getNameTfop(String number) {
    	
    	String name = null;
    	for	(int i=0;i<getRowCount();i++) {
    		if(getValueAt(i,9).toString().equals(number)){
    			name = getValueAt(i,2).toString();
    			break;
    		}
    	}    	    	
    	return name;
    }
    
}
