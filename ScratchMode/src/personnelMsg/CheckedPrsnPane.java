package personnelMsg;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import tableObjects.TableModel;
import tablePersonnel.TablePersonnel;

public class CheckedPrsnPane extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static final DefaultListModel<Object> dlm = new DefaultListModel<Object>();
	public JList<Object> list = new JList<Object>(dlm);

	public CheckedPrsnPane(TablePersonnel modelPersonnel) {
		
		super(new GridLayout());
		this.add(new JScrollPane(list));
		modelPersonnel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				dlm.removeAllElements();
				for (Object name : TablePersonnel.nameListPrsn) {						
					dlm.addElement(name);						
				}
			}
		});
	}

}
