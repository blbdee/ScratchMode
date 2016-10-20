package gui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import tableEvents.QueryToEvent_log;
import tableSensors.QueryToSensor_log;
import mainWindow.SenderCommands;

public class LogsPane {

	public LogsPane() {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(260, 635, 1350, 300);
		SenderCommands.f.getContentPane().add(tabbedPane);
		tabbedPane.setBorder(BorderFactory.createTitledBorder("Журналы"));
		
		JScrollPane logEvents = new JScrollPane();
		logEvents.setViewportBorder(null);
		logEvents.setViewportView(QueryToEvent_log.tableLog);
		tabbedPane.addTab("Журнал событий", null, logEvents, null);	
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JScrollPane logSensor = new JScrollPane();
		logSensor.setViewportBorder(null);
		logSensor.setViewportView(QueryToSensor_log.tableSensor);	
		tabbedPane.addTab("Состояние оконечного оборудования", null, logSensor, null);
		
		
	}

}
