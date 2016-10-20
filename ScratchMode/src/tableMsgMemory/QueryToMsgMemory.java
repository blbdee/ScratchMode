package tableMsgMemory;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import tableObjects.DesignOfTable;
import tablePersonnelMsg.NotesTableCellRenderer;

public class QueryToMsgMemory {
	
	public static JTable msgMemoryTable;
	public static MsgMemoryTableModel msgMemoryModel;
	public static ConnectToMsgMemory msgMemoryBaza;	
	//по умолчанию radioButStatus = 0, даже если радиокнопка не выбрана
	private static int radioButStatus = 10000;
	
	public QueryToMsgMemory() {
	
		msgMemoryBaza = new ConnectToMsgMemory("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/usr2_db", "Ivan", "12345678");
		msgMemoryModel = new MsgMemoryTableModel();
		//model.setTableData(baza.getNomen("SELECT Выбрать, Уникальный_номер, Имя, Сетевой_адрес, Сетевой_порт, Сотовый_номер, Физический_адрес FROM objects where " + s));
		msgMemoryModel.setTableData(msgMemoryBaza.getNomen("SELECT * FROM msg_memory"));	
		msgMemoryTable = new JTable(msgMemoryModel);
		msgMemoryTable.getColumnModel().getColumn(0).setCellRenderer(new RadioButtonCellEditorRenderer());
		msgMemoryTable.getColumnModel().getColumn(0).setCellEditor(new RadioButtonCellEditorRenderer());
		msgMemoryTable.getColumnModel().getColumn(1).setCellRenderer(new NotesTableCellRenderer());
		msgMemoryTable.getColumnModel().getColumn(2).setCellRenderer(new NotesTableCellRenderer());
				
		msgMemoryTable.setRowHeight(19);
		msgMemoryTable.setSize(1039, 160);	
		
		//JTableHeader headerTable = msgMemoryTable.getTableHeader();
		//headerTable.setDefaultRenderer(new DesignOfTable(headerTable.getDefaultRenderer()));		
				
		/*DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment( JLabel.LEFT );				
		for (int i=1; i<msgMemoryTable.getColumnCount(); i++) {
			msgMemoryTable.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
		}*/
		
		msgMemoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		msgMemoryTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		msgMemoryTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		msgMemoryTable.getColumnModel().getColumn(2).setPreferredWidth(1120);
		
		
	}
	
	/** Renderer class for JRadioButton **/
    public class RadioButtonCellEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ItemListener {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private JRadioButton radioButton;

        public RadioButtonCellEditorRenderer() {
            this.radioButton = new JRadioButton();
            radioButton.addItemListener(this);
            radioButton.setOpaque(false);
            radioButton.setHorizontalAlignment(JRadioButton.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            radioButton.setSelected(Boolean.TRUE.equals(value));
            return radioButton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            radioButton.setSelected(Boolean.TRUE.equals(value));
            return radioButton;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
        	
        	if (e.getStateChange() == ItemEvent.SELECTED) {
        		radioButStatus = msgMemoryTable.getSelectedRow();
            }
        	else {
        		radioButStatus = 10000;
        	}
        }     

        @Override
        public Object getCellEditorValue() {
            return radioButton.isSelected();
        }

    }
    
    public static int getRadioButStatus() {
		return radioButStatus;
	}

}
