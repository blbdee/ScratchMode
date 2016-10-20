package tablePersonnelMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class TablePersonnelMsg extends AbstractTableModel {   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����� �������;
    public static Vector<String> columnNames;    
    //������;
    private Vector<Vector<Object>> tableData;    
    //������ �������;
    protected Vector<Object> vColClass;
    
    
    public static List<Boolean> rowList;
    public static int rows2;
    Set<Integer> checked = new TreeSet<Integer>();
    public static List<String> phoneListPrsn = new ArrayList<String>();
    public static List<String> nameListPrsn = new ArrayList<String>();
    
    public TablePersonnelMsg()	{
    	super();
        vColClass = new Vector<Object>(); 
        columnNames = new Vector<String>();        
        rows2 = 500;
        rowList = new ArrayList<Boolean>(rows2);
        for (int i = 0; i < rows2; i++) {
            rowList.add(Boolean.FALSE);
        }
    }
    
    //���������� �������//
    @Override
    public int getColumnCount() {
    	return columnNames.size();        
    }
    //���������� �����?//
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
    
    //���������� ��������� �������;
    public String getColumnName(int column) {
    	return columnNames.get(column);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
    	
        boolean b = (Boolean) aValue;
        rowList.set(row, b);
        String phone = (QueryToPersonnelMsg.modelPersonnelMsg.getValueAt(row, 2)).toString(); 
    	String name = (QueryToPersonnelMsg.modelPersonnelMsg.getValueAt(row, 1)).toString();
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
        //����������� ��������
        fireTableRowsUpdated(row, row);
    }
    
    @Override
    public Class<?> getColumnClass(int column) {
    	return getValueAt(0, column).getClass();
    }
    //������ �������������� �������
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
    
}
