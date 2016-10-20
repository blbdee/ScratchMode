package tablePersonnel;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tableObjects.DesignOfTable;
import tableObjects.TableModel;

public class QueryToPersonnel {
	
	public static JTable tablePersonnel;
	public static TablePersonnel modelPersonnel;
	public static QueryTo_Personnel bazaPersonnel;	
	

	public QueryToPersonnel() {

/*ru*/	bazaPersonnel = new QueryTo_Personnel("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usr2_db", "Ivan", "12345678");
		modelPersonnel = new TablePersonnel();	
		System.out.println(123);
		modelPersonnel.setTableData(bazaPersonnel.getNomen("SELECT * FROM personnel"));
/**/	tablePersonnel = new JTable(modelPersonnel);
		tablePersonnel.setSize(1039, 160);	

		JTableHeader headerTable = tablePersonnel.getTableHeader();
		headerTable.setDefaultRenderer(new DesignOfTable(headerTable.getDefaultRenderer()));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.LEFT);				
		for (int i=1; i<tablePersonnel.getColumnCount(); i++) {
			tablePersonnel.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);			
		}
		
		tablePersonnel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablePersonnel.getColumnModel().getColumn(0).setPreferredWidth(70);
		tablePersonnel.getColumnModel().getColumn(1).setPreferredWidth(250);
		tablePersonnel.getColumnModel().getColumn(2).setPreferredWidth(130);
		tablePersonnel.getColumnModel().getColumn(3).setPreferredWidth(220);
		tablePersonnel.getColumnModel().getColumn(4).setPreferredWidth(117);
	}

}
