package tableSensors;

import java.util.ArrayList;

public class GetSingleSensor {
	
	private static Integer start;
	private static Integer end;
	public static ArrayList<String> sensorList = new ArrayList<String>();
	public static String sensorStr;

	public GetSingleSensor(Integer start, Integer end, StringBuffer sb) {

				
		GetSingleSensor.start = start;
		GetSingleSensor.end = start+end;
						
        char[] arr = new char[GetSingleSensor.end - GetSingleSensor.start];
        sb.getChars(GetSingleSensor.start, GetSingleSensor.end, arr, 0);
        
        for(int i=0;i<arr.length;i++) {
        	
        	sensorStr = sensorStr + Character.toString(arr[i]);       	 
        	      	
        }
        
        sensorList.add(sensorStr);
        
	}

}
