package equipmentType;

import tableObjects.QueryToObjects;

public class CheckRSU {

	public CheckRSU() {
		
		for (int i=0; i<QueryToObjects.model.getRowCount(); i++) {
			Integer type = Integer.parseInt(QueryToObjects.model.getValueAt(i, 8).toString());
			boolean di = (Boolean) QueryToObjects.model.getValueAt(i, 0);
			if (di == true) {
				if (type == 1) {
					System.out.println("RSU");										
				}	 
				else if (type == 0) {
					System.out.println("UZSK - remove");	
					QueryToObjects.model.setValueAt(false, i, 0);												 										
				}
				else {
					System.out.println("Which equipment?!");					
				}
			}
			else {
				System.out.println("Not checked");
			}
		}
		QueryToObjects.model.fireTableDataChanged();
	}
	

}
