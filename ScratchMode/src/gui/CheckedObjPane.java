package gui;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import tableObjects.TableModel;

public class CheckedObjPane extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static final DefaultListModel<Object> dlm = new DefaultListModel<Object>();
	public JList<Object> list = new JList<Object>(dlm);

	public CheckedObjPane(TableModel model) {
		
		super(new GridLayout());
		this.add(new JScrollPane(list));

		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				dlm.removeAllElements();
				
				for (Object name : TableModel.nameList) {						
					dlm.addElement(name);						
				}
				
				
			}
		});
	}

}
