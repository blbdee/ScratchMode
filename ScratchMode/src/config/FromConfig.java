package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import tableObjects.DatabaseFirstConnect;

public class FromConfig {
	public static String fileVoiceDecPath;
	public static String coderPath;
	public static String startRecPath;
	public static String audioRecord;
	public static String portBsk;
	public static String alarmBsk;
	public static String authPass;
	public static String authLog;
	public static String reportPath;
	public static Integer refrashSensors;
	public static String logoPath;
	public static String backgrdPath;
	public static String ffmpegPath;
	public static String portModem;
	public static String dtmfPass;
	public static String dtmfCancel;
	public static String outputFileWav;
	public static String outputFileMp3;
	public static String inputSoundBsk;

	public FromConfig() throws IOException	{ 
		
		String query = "Select * from config";
		List<String> tempConfPath = new ArrayList<String>();
		
        try {
        	Statement st = DatabaseFirstConnect.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs. next()) {         	             	
            	tempConfPath.add(rs.getString(2));
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            System.err.println("First Connect to DB.There are problems with the query " + query);
            e.printStackTrace();
        }
        String pathConf = "";
        
        for(int i=0;i<tempConfPath.size();i++) {
        	pathConf=pathConf+tempConfPath.get(i);
        }
        
		Properties props = new Properties();
		props.load(new FileInputStream(new File(pathConf)));

		ffmpegPath = props.getProperty("ffmpegPath");
		coderPath = props.getProperty("coderPath");
		
		fileVoiceDecPath  = props.getProperty("fileVoiceDecPath");		
		audioRecord = props.getProperty("audioRecord");
		portBsk = props.getProperty("portBsk");
		alarmBsk = props.getProperty("alarmBsk");
		
		portModem = props.getProperty("portModem");
		
		authPass = props.getProperty("authPass");
		authLog = props.getProperty("authLog");
		
		reportPath = props.getProperty("reportPath");	
		
		refrashSensors = Integer.parseInt(props.getProperty("refrashSensors"));
		
		logoPath = props.getProperty("logoPath");		
		backgrdPath  = props.getProperty("backgrdPath");
		
		dtmfPass = props.getProperty("dtmfPass");
		dtmfCancel = props.getProperty("dtmfCancel");
		outputFileWav = props.getProperty("outputFileWav"); 
		outputFileMp3 = props.getProperty("outputFileMp3");
		
		inputSoundBsk = props.getProperty("inputSoundBsk");
		
	}
	
}