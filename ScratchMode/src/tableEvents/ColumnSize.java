package tableEvents;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ColumnSize {
	
	public final static void setColumnsWidth(JTable tableLog, int size) {
		tableLog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    JTableHeader th = tableLog.getTableHeader();
	    for (int i = 0; i < tableLog.getColumnCount(); i++) {
	        TableColumn column = tableLog.getColumnModel().getColumn(i);
	        int prefWidth = Math.round((float) th.getFontMetrics(th.getFont()).getStringBounds(th.getTable().getColumnName(i),th.getGraphics()).getWidth());
	        column.setPreferredWidth(prefWidth + size);
	    }
	}
}

