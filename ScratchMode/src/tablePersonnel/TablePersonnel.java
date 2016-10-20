package tablePersonnel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TablePersonnel extends AbstractTableModel {   
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
    public static List<String> phoneListPrsn = new ArrayList<String>();
    public static List<String> nameListPrsn = new ArrayList<String>();
    
    public TablePersonnel()	{
    	super();
        vColClass = new Vector<Object>(); 
        columnNames = new Vector<String>();        
        rows2 = 500;
        rowList = new ArrayList<Boolean>(rows2);
        for (int i = 0; i < rows2; i++) {
            rowList.add(Boolean.FALSE);
        }
    }
    
    //количество колонок//
    @Override
    public int getColumnCount() {
    	return columnNames.size();        
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
    public void setValueAt(Object aValue, int row, int column) {
    	
        boolean b = (Boolean) aValue;
        rowList.set(row, b);
        String phone = (QueryToPersonnel.modelPersonnel.getValueAt(row, 2)).toString(); 
    	String name = (QueryToPersonnel.modelPersonnel.getValueAt(row, 1)).toString();
        if (b==true) {        	
            checked.add(row);
            System.out.println("add "+checked);             
            phoneListPrsn.add(phone); 
            nameListPrsn.add(name);
        } 
        else if(b==false) {        	
        	checked.remove(row);
        	System.out.println("rem "+checked);
        	for (int i=0; i<=phoneListPrsn.size(); i++) {
            	if (phoneListPrsn.get(i).equals(phone)) {
            		phoneListPrsn.remove(i);
            		nameListPrsn.remove(i);
            		break;
            	}
            } 
        	
        }
        //перерисовка работает
        fireTableRowsUpdated(row, row);
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
    
    public String getPhoneNumbers() {
    	
    	String phoneNum = "";    	
    	for	(int i=0;i<getRowCount();i++) {
    		if(getValueAt(i,4).toString().equals("Ответственные")) {    			
    			if(i!=0) {
    				phoneNum=phoneNum+","+getValueAt(i,2).toString();							
				}
				else {
					phoneNum=phoneNum+getValueAt(i,2).toString();		
				}
    			break;
    		}
    	}
    	
    	return phoneNum;    	
    }
    
}
