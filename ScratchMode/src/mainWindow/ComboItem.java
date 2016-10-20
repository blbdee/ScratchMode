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
    		    "Сообщение #1",
    		    "Сообщение #2",
    		    "Сообщение #23",
    		    "Сообщение #95"
    		};
    	String[] items2 = {
    		    "Сообщение #3",
    		    "Сообщение #4",
    		    "Сообщение #5",
    		    "Сообщение #7"
    		};
    	String[] items3 = {
    		    "Сообщение #6",
    		    "Сообщение #10",
    		    "Сообщение #11",
    		    "Сообщение #12"
    		};
    	String[] items4 = {
    		    "Сообщение #8",
    		    "Сообщение #9"
    		    
    		};
    	String[] items5 = {
    		    "Сообщение #13",
    		    "Сообщение #14",
    		    "Сообщение #15",
    		    "Сообщение #16"
    		};
    	String[] items6 = {
    		    "Сообщение #17",
    		    "Сообщение #18",
    		    "Сообщение #19",
    		    "Сообщение #20",
    		    "Сообщение #21",
    		    "Сообщение #22",
    		    "Сообщение #23",
    		    "Сообщение #24"
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