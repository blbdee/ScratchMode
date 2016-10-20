package test;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class JRadioAsRendererEditor extends JPanel {

    private static final String[] COLUMN_NAMES = { "List ID", "Expiration Date", "Status", "Date Created"};
    private MyTableModel tableModel;
    private JTable table;
    private JFrame frame = new JFrame("TestRadioButtonRenderer");

    public JRadioAsRendererEditor() {
        super(new BorderLayout(0, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tableModel = new MyTableModel();
        table = new JTable(tableModel);
        
        table.setDefaultEditor(Status.class, new StatusEditor());
        table.setDefaultRenderer(Status.class, new StatusRenderer());
        tableModel.add(new TableEntry());
        table.setRowHeight(70);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.pack();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JRadioAsRendererEditor();
            }
        });
    }

    private enum Status {

        Single, Married, Divorced;
    }

    private class StatusPanel extends JPanel {

        private JRadioButton theSingleOption;
        private JRadioButton theMarriedOption;
        private JRadioButton theDivorcedOption;
        private ButtonGroup buttonGroup = new ButtonGroup();

        StatusPanel() {
            super(new GridLayout(0, 1));
            setOpaque(true);
            theSingleOption = createRadio(Status.Single);
            theMarriedOption = createRadio(Status.Married);
            theDivorcedOption = createRadio(Status.Divorced);
        }

        private JRadioButton createRadio(Status status) {
            JRadioButton jrb = new JRadioButton(status.toString());
            jrb.setOpaque(false);
            add(jrb);
            buttonGroup.add(jrb);
            return jrb;
        }

        public Status getStatus() {
            if (theMarriedOption.isSelected()) {
                return Status.Married;
            } else if (theDivorcedOption.isSelected()) {
                return Status.Divorced;
            } else {
                return Status.Single;
            }
        }

        public void setStatus(Status status) {
            if (status == Status.Married) {
                theMarriedOption.setSelected(true);
            } else if (status == Status.Divorced) {
                theDivorcedOption.setSelected(true);
            } else {
                theSingleOption.setSelected(true);
            }
        }
    }

    private class TableEntry {

        private int instanceNumber;
        private Long theId;
        private Date theExpirationDate;
        private Status theStatus;
        private Date theCreationDate;

        TableEntry() {
            instanceNumber++;
            theId = new Long(instanceNumber);
            theExpirationDate = new Date();
            theStatus = Status.Single;
            theCreationDate = new Date();
        }

        TableEntry(Long anId, Date anExpirationDate, Status aStatus, Date aCreationDate) {
            theId = anId;
            theExpirationDate = anExpirationDate;
            theStatus = aStatus;
            theCreationDate = aCreationDate;
        }

        public Long getId() {
            return theId;
        }

        public Date getExpirationDate() {
            return theExpirationDate;
        }

        public Status getStatus() {
            return theStatus;
        }

        public Date getCreationDate() {
            return theCreationDate;
        }

        public void setId(Long anId) {
            theId = anId;
        }

        public void setExpirationDate(Date anExpirationDate) {
            theExpirationDate = anExpirationDate;
        }

        public void setStatus(Status aStatus) {
            theStatus = aStatus;
        }

        public void setCreationDate(Date aCreationDate) {
            theCreationDate = aCreationDate;
        }
    }

    private class MyTableModel extends AbstractTableModel {

        private Vector<Object> theEntries;

        MyTableModel() {
            theEntries = new Vector<Object>();
        }

        @SuppressWarnings("unchecked")
        public void add(TableEntry anEntry) {
            int index = theEntries.size();
            theEntries.add(anEntry);
            fireTableRowsInserted(index, index);
        }

        

        public int getRowCount() {
            return theEntries.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Long.class;
              
                case 2:
                    return Status.class;
                
            }
            return Object.class;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            TableEntry entry = (TableEntry) theEntries.elementAt(rowIndex);
            switch (columnIndex) {
                case 0:
                    try {
                        entry.setId(new Long(Long.parseLong(aValue.toString())));
                    } catch (NumberFormatException nfe) {
                        return;
                    }
                    break;
                case 1:
                    entry.setExpirationDate((Date) aValue);
                    break;
                case 2:
                    entry.setStatus((Status) aValue);
                    break;
                case 3:
                    entry.setCreationDate((Date) aValue);
                    break;
                default:
                    return;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TableEntry entry = (TableEntry) theEntries.elementAt(rowIndex);
            switch (columnIndex) {
                case 0:
                    return entry.getId();
                case 1:
                    return entry.getExpirationDate();
                case 2:
                    return entry.getStatus();
                case 3:
                    return entry.getCreationDate();
            }
            return null;
        }
    }

       

    private class StatusEditor extends AbstractCellEditor implements TableCellEditor {

        private StatusPanel theStatusPanel;

        StatusEditor() {
            theStatusPanel = new StatusPanel();
        }

        @Override
        public Object getCellEditorValue() {
            return theStatusPanel.getStatus();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            theStatusPanel.setStatus((Status) value);
            if (isSelected) {
                theStatusPanel.setBackground(table.getSelectionBackground());
            } else {
                theStatusPanel.setBackground(table.getBackground());
            }
            return theStatusPanel;
        }
    }

    private class StatusRenderer extends StatusPanel implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            setStatus((Status) value);
            
            return this;
        }
    }
}