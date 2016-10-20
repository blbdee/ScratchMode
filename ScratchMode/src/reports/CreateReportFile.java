package reports;

import java.awt.Desktop;
import  java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;

import tableEvents.LogTableModel;
import tableEvents.QueryToEventLog;
import config.FromConfig;

public class CreateReportFile{
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss");
	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	private static HSSFRow rowtitle;
	private static HSSFRow rowhead;
	private static HSSFRow row;
	public static String reportDateFirstDay;
	public static String reportDateLastDay;
	
	
    public CreateReportFile(String reportDateFirstDay, String reportDateLastDay) {  
    	
    	CreateReportFile.reportDateFirstDay = reportDateFirstDay;
    	CreateReportFile.reportDateLastDay = reportDateLastDay;
    	String name = sdf.format(System.currentTimeMillis());   
    	    	
        try {
            String filename = FromConfig.reportPath+name+".xls" ;
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("FirstSheet"); 
            //String query = "SELECT * FROM event_log where Дата = '"+CreateReportFile.reportDateFirstDay+"'";
            String query = "SELECT * FROM event_log where DATE(Дата) BETWEEN '"+CreateReportFile.reportDateFirstDay+"' AND '"+CreateReportFile.reportDateLastDay+"'";
            getNomen(query);  
            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            desktop.open(new File(filename));
            
        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
    
   public static Vector<Vector<Object>> getNomen(String query) {
    	
        Vector<Vector<Object>> retVector = new Vector<Vector<Object>>();
        try {
        	Statement st = QueryToEventLog.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();           
            for (int i = 1; i <= cols; i++) {
            	LogTableModel.columnNames.addElement(rsmd.getColumnName(i));
            	System.out.println(LogTableModel.columnNames);
            }
            int j = 1;
            
            rowtitle = sheet.createRow((short)0);
            rowtitle.createCell(0).setCellValue("Отчёт работы системы оповещения "+CreateReportFile.reportDateFirstDay+"-"+CreateReportFile.reportDateLastDay);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
            
            rowhead = sheet.createRow((short)2);  
            rowhead.createCell(0).setCellValue("№");
            rowhead.createCell(1).setCellValue("Объект");
            rowhead.createCell(2).setCellValue("Событие");
            rowhead.createCell(3).setCellValue("Результат");
            rowhead.createCell(4).setCellValue("Дата");
            rowhead.createCell(5).setCellValue("Время");
            rowhead.createCell(6).setCellValue("Имя оператора");
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 6000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 4000);
            sheet.setColumnWidth(5, 4000);
            sheet.setColumnWidth(6, 4000);
            while (rs.next()) {
            	            	
            	row = sheet.createRow((short)j+2);            	
                for (int i = 1; i <= cols; i++) {                	                      	                 		
                	row.createCell(i-1).setCellValue(rs.getObject(i).toString());
                }   
                
                j++;             
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            System.err.println("QueryToEventLog.There are problems with the query " + query);
            e.printStackTrace();
        }    	
        return retVector;        
    }


}