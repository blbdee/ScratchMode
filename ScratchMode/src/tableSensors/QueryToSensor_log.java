package tableSensors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import tableEvents.QueryToEventLog;
import tableObjects.DesignOfTable;

public class QueryToSensor_log {	
	
	public static JTable tableSensor;
	public static SensorsTableModel modelSensor;
	public static QueryToSensorsLog bazaSensor;

	public QueryToSensor_log() {
		
		bazaSensor = new QueryToSensorsLog("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usr2_db", "Ivan", "12345678");				
		modelSensor = new SensorsTableModel();
		modelSensor.setTableData(bazaSensor.getNomen("SELECT * FROM sensor_log"));			
/**/	tableSensor = new JTable(modelSensor){	
			
		private static final long serialVersionUID = -8907484347873321029L;

			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
				
		        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		        String value = getValueAt(rowIndex, 3).toString();		
					
			    if (tableSensor.isRowSelected(rowIndex)==true) {
					c.setBackground(Color.blue);
				}
				else {
					if (value.equals("0")) {
					  	c.setBackground(Color.RED);
					}
					else if(value.equals("нет")) {
						c.setBackground(new Color(255,255,153));							
					}		
					else {
					  	c.setBackground(new Color(153,255,153));
					} 		
				}													
			    return c;
		    }
		};
		
		tableSensor.setRowHeight(19);
		
		JTableHeader headerTableSensor = tableSensor.getTableHeader();
		headerTableSensor.setDefaultRenderer(new DesignOfTable(headerTableSensor.getDefaultRenderer()));
		
		DefaultTableCellRenderer centerRendererSensorTable = new DefaultTableCellRenderer();
		centerRendererSensorTable.setHorizontalAlignment( JLabel.CENTER );				
		for (int i=0; i<tableSensor.getColumnCount(); i++) {
			tableSensor.getColumnModel().getColumn(i).setCellRenderer(centerRendererSensorTable);
		}
		
		tableSensor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableSensor.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableSensor.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableSensor.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableSensor.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableSensor.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableSensor.getColumnModel().getColumn(5).setPreferredWidth(120);
		tableSensor.getColumnModel().getColumn(6).setPreferredWidth(120);
		tableSensor.getColumnModel().getColumn(7).setPreferredWidth(130);
		tableSensor.getColumnModel().getColumn(8).setPreferredWidth(180);
		tableSensor.getColumnModel().getColumn(9).setPreferredWidth(160);
		
	}

}
