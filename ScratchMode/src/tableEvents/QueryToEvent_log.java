package tableEvents;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import tableObjects.DesignOfTable;

public class QueryToEvent_log {
	
	public static JTable tableLog;
	public static LogTableModel modelLog;
	public static QueryToEventLog bazaLog;	

	public QueryToEvent_log() {

		
/*ru*/	bazaLog = new QueryToEventLog("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usr2_db", "Ivan", "12345678");				
		modelLog = new LogTableModel();
		modelLog.setTableData(bazaLog.getNomen("SELECT * FROM event_log where Дата = CURRENT_DATE"));	
/**/	tableLog = new JTable(modelLog) {
		
			
		private static final long serialVersionUID = -2370751564394010264L;

			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				
		        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);			        			        
			    String value = getValueAt(rowIndex, 3).toString();
				if (tableLog.isRowSelected(rowIndex)==true) {
					c.setBackground(Color.blue);
				}
				else {
					if ((value.equals("Успешно"))|(value.equals("Успешный звонок"))) {
					  	c.setBackground(new Color(0,255,127));
					}
					else if ((value.equals("Неудачно"))|(value.equals("Недозвон"))|(value.equals("Не дослушали"))) {
					  	c.setBackground(Color.RED);
					} 
					else if (value.equals("В процессе")) {
					  	c.setBackground(Color.ORANGE);					  	
					}
				}													
			    return c;					   
		    }
	    };
	    
	    tableLog.setRowHeight(19);
	    
	    
	    JTableHeader headerTableLog = tableLog.getTableHeader();
	    headerTableLog.setDefaultRenderer(new DesignOfTable(headerTableLog.getDefaultRenderer()));
		
		DefaultTableCellRenderer centerTableLog = new DefaultTableCellRenderer();
		centerTableLog.setHorizontalAlignment( JLabel.CENTER );				
		for (int i=0; i<tableLog.getColumnCount(); i++) {
			tableLog.getColumnModel().getColumn(i).setCellRenderer(centerTableLog);
		}
		
		tableLog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableLog.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableLog.getColumnModel().getColumn(1).setPreferredWidth(230);
		tableLog.getColumnModel().getColumn(2).setPreferredWidth(230);
		tableLog.getColumnModel().getColumn(3).setPreferredWidth(180);
		tableLog.getColumnModel().getColumn(4).setPreferredWidth(190);
		tableLog.getColumnModel().getColumn(5).setPreferredWidth(180);
		tableLog.getColumnModel().getColumn(6).setPreferredWidth(243);
	    
	}

}
