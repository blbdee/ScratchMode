package tableObjects;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class QueryToObjects {
	
	public static JTable table;
	public static TableModel model;
	public static ConnectToObject baza;	
	

	public QueryToObjects() {

/*ru*/	baza = new ConnectToObject("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usr2_db", "Ivan", "12345678");
		model = new TableModel();
		//model.setTableData(baza.getNomen("SELECT Выбрать, Уникальный_номер, Имя, Сетевой_адрес, Сетевой_порт, Сотовый_номер, Физический_адрес FROM objects where " + s));
		model.setTableData(baza.getNomen("SELECT * FROM objects"));		
/**/	table = new JTable(model);
		table.setRowHeight(19);
		table.setSize(1039, 160);	
		
		JTableHeader headerTable = table.getTableHeader();
		headerTable.setDefaultRenderer(new DesignOfTable(headerTable.getDefaultRenderer()));		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );				
		for (int i=1; i<table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		table.getColumnModel().getColumn(6).setPreferredWidth(330);
		table.getColumnModel().getColumn(7).setPreferredWidth(220);
		
	}

}
