package tableEvents;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class LogTableModel extends AbstractTableModel {	
	
  
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
    
    public static ArrayList<Integer> rowList;
    public static int rows2;
    
    public LogTableModel()	{
    	super();
        vColClass = new Vector<Object>(); 
        columnNames = new Vector<String>();
        
        rows2 = 1000000; //���������� ����� � �������
        rowList = new ArrayList<Integer>(rows2);
        for (int i = 0; i < rows2; i++) {
            rowList.add(i);            
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
    	//������� ��������� �����
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

    
    public void setValueAt(Vector<Object> strings) {
    	
    	tableData.add(strings);
       
    }
    
    @Override
    public Class<?> getColumnClass(int column) {
    	return getValueAt(0, column).getClass();
    }
    //������ �������������� �������
    @Override
    public boolean isCellEditable(int row, int column) {    	
    	return false;        
    }
    
    public void setTableData(Vector<Vector<Object>> tableData) {
    	this.tableData = tableData;
    }

    public Vector<Vector<Object>> getTableData() {
    	return tableData;
    }
    
    public Object getLastRowNumber() {    	
        return getTableData().get(getRowCount()-1).get(0);        
    }
}
