package tableMsgMemory;

import gui.CheckedObjPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import authentication.LoginWindow;

public class MsgMemoryTableModel extends AbstractTableModel {
	
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
		
	public MsgMemoryTableModel() {
		
	    super();
	    vColClass = new Vector<Object>(); 
	    columnNames = new Vector<String>();
	        
	    rows2 = 500;
	    rowList = new ArrayList<Boolean>(rows2);
	    for (int i = 0; i < rows2; i++) {
	    	rowList.add(Boolean.FALSE);
	    }
	        
	}
	    
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		boolean b = (Boolean) aValue;
		//rowList.set(row, b);	  
	    
        //перерисовка работает
        fireTableRowsUpdated(row, row);
	}
	
	//количество колонок//
    @Override
    public int getColumnCount() {
    	//убрать последнюю колонку
    	return columnNames.size()-2;        
    }
    
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
    
    public String getCommand() {
    	String msg = "";
    	if(QueryToMsgMemory.getRadioButStatus()!=10000) {
    		if(LoginWindow.rdbtnEthernet.isSelected()) {
    			msg = getValueAt(QueryToMsgMemory.getRadioButStatus(), 3).toString(); 
    		}
    		else {
    			msg = getValueAt(QueryToMsgMemory.getRadioButStatus(), 4).toString(); 
    		}	
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Необходимо выбрать сообщение", "Ошибка", 1);
    	}
    	return msg;
    }
    
    public String getName() {
    	String msg = "";
    	msg = getValueAt(QueryToMsgMemory.getRadioButStatus(), 1).toString();     	
    	return msg;
    }
    public String getCmdText (){
    	String msg = "";
    	msg = getValueAt(QueryToMsgMemory.getRadioButStatus(), 2).toString();     	
    	return msg;    	
    }
	
}
