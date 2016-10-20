package tablePersonnelMsg;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import tableObjects.DesignOfTable;
import tablePersonnelMsg.NotesTableCellRenderer;

public class QueryToPersonnelMsg {
	
	public static JTable tablePersonnelMsg;
	public static TablePersonnelMsg modelPersonnelMsg;
	public static QueryTo_PersonnelMsg bazaPersonnelMsg;	

	public QueryToPersonnelMsg() {

/*ru*/	bazaPersonnelMsg = new QueryTo_PersonnelMsg("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usr2_db", "Ivan", "12345678");
		modelPersonnelMsg = new TablePersonnelMsg();	
		modelPersonnelMsg.setTableData(bazaPersonnelMsg.getNomen("SELECT * FROM personnelmsg"));
/**/	tablePersonnelMsg = new JTable(modelPersonnelMsg);
		tablePersonnelMsg.setSize(1039, 160);	

		JTableHeader headerTable = tablePersonnelMsg.getTableHeader();
		headerTable.setDefaultRenderer(new DesignOfTable(headerTable.getDefaultRenderer()));
		
		
		tablePersonnelMsg.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablePersonnelMsg.getColumnModel().getColumn(0).setPreferredWidth(70);
		tablePersonnelMsg.getColumnModel().getColumn(1).setPreferredWidth(40);
		tablePersonnelMsg.getColumnModel().getColumn(2).setPreferredWidth(98);
		tablePersonnelMsg.getColumnModel().getColumn(3).setPreferredWidth(580);
		
		tablePersonnelMsg.setDefaultRenderer(String.class, new NotesTableCellRenderer());
		tablePersonnelMsg.setDefaultRenderer(Integer.class, new NotesTableCellRenderer());
		
	}	

}



    
