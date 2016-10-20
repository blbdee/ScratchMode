package tableObjects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DesignOfTable implements TableCellRenderer {
	
	private TableCellRenderer defaultRenderer;
	
	public DesignOfTable(TableCellRenderer defaultRenderer) {
		this.defaultRenderer = defaultRenderer;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component comp = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (comp instanceof JLabel) {
			JLabel label = (JLabel) comp;
			label.setFont(new Font("Consolas", Font.BOLD, 14));
			label.setForeground(Color.BLACK);
			label.setBorder(BorderFactory.createEtchedBorder());
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
		}
		
		return comp;
	}

}