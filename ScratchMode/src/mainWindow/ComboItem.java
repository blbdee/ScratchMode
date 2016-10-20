package mainWindow;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboItem {
   
	private String[] items;
	
    public ComboItem(int q, int w, int e, int r, int num, String title1) {
    	
    	String[] items1 = {
    		    "��������� #1",
    		    "��������� #2",
    		    "��������� #23",
    		    "��������� #95"
    		};
    	String[] items2 = {
    		    "��������� #3",
    		    "��������� #4",
    		    "��������� #5",
    		    "��������� #7"
    		};
    	String[] items3 = {
    		    "��������� #6",
    		    "��������� #10",
    		    "��������� #11",
    		    "��������� #12"
    		};
    	String[] items4 = {
    		    "��������� #8",
    		    "��������� #9"
    		    
    		};
    	String[] items5 = {
    		    "��������� #13",
    		    "��������� #14",
    		    "��������� #15",
    		    "��������� #16"
    		};
    	String[] items6 = {
    		    "��������� #17",
    		    "��������� #18",
    		    "��������� #19",
    		    "��������� #20",
    		    "��������� #21",
    		    "��������� #22",
    		    "��������� #23",
    		    "��������� #24"
    		};
    	

    	if (num==1) { items = items1;}
    	else if (num==2) { items = items2;}
    	else if (num==3) { items = items3;}
    	else if (num==4) { items = items4;}
    	else if (num==5) { items = items5;}
    	else if (num==6) { items = items6;}
    	
    	
        JComboBox<String> comboBox_5 = new JComboBox<String>(items); //items
		comboBox_5.setBounds(q, w, e, r);
		comboBox_5.setRenderer(new MyComboBoxRenderer(title1));
		SenderCommands.scrollPane.add(comboBox_5);
    }

    
}

@SuppressWarnings("serial")
class MyComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
		
    private String _title;

    public MyComboBoxRenderer(String title1)
    {
        _title = title1;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus)
    {
        if (index == -1 && value == null) setText(_title);
        else setText(value.toString());
        return this;
    }
}