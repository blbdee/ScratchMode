package tablePersonnelMsg;

import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class NotesTableCellRenderer extends JTextArea implements TableCellRenderer {
	
	
	public NotesTableCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setFont(new Font("Arial", Font.PLAIN, 14));
        
    }
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	
    	System.out.println(1234);
        setText((value == null) ? "" : value.toString());        
        setSize(table.getColumnModel().getColumn(column).getWidth(),getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height) {  
            table.setRowHeight(row, getPreferredSize().height); 
        } 
               
                
        //DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        //centerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        //centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        //table.getColumnModel().getColumn(1).setCellRenderer(setVerticalAlignment(SwingConstants.CENTER););
        //table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        return this;
    }
    
    
}