package gui;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import tableObjects.QueryToObjects;
import mainWindow.SenderCommands;

public class ObjectsPane {

	public ObjectsPane() {
		
		JScrollPane objectsTable = new JScrollPane();
		objectsTable.setBounds(260, 10, 1350, 270);
		objectsTable.setBorder(BorderFactory.createTitledBorder("Выбраные объекты"));
		SenderCommands.f.getContentPane().add(objectsTable);
		objectsTable.setViewportView(QueryToObjects.table);
	}

}
