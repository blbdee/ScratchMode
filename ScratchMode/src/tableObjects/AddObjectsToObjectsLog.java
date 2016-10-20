package tableObjects;

import gui.ChooseLocationPane;
import java.util.ArrayList;
import java.util.List;

public class AddObjectsToObjectsLog {
	
	public static void AddObjects () {		
		
		List<String> typeList = new ArrayList<String>();		
		typeList.clear();
		TableModel.butUncheckAll.doClick();
		
		for (int i = 0; i<ChooseLocationPane.checkBoxes.size(); i++) {
						            		
			if (ChooseLocationPane.checkBoxes.get(i).isSelected()) {
								
				if(typeList.isEmpty()) {  
					typeList.add("����� = "+"'"+ChooseLocationPane.result.get(i)+"'");			            				
				}
				else {
					typeList.add(" or ����� = "+"'"+ChooseLocationPane.result.get(i)+"'");
				}			            					            			
			}		            									
		}
		
		String query = "";
		for(int j = 0; j<typeList.size(); j++) {
			query = query + typeList.get(j);
			System.out.println(query);
		}
		
		if (typeList.isEmpty()) {
			QueryToObjects.model.setTableData(QueryToObjects.baza.getNomen("SELECT * FROM objects where ����� = '�����'"));	
			QueryToObjects.model.getTableData();
			QueryToObjects.model.fireTableDataChanged();
		}
		else {
			QueryToObjects.model.setTableData(QueryToObjects.baza.getNomen("SELECT * FROM objects where " + query));	
			QueryToObjects.model.getTableData();
			QueryToObjects.model.fireTableDataChanged();  
		}
	
	}

}
