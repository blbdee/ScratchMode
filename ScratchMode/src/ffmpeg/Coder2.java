/*package ffmpeg;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import config.FromConfig;
import commands.send.CheckBoxGroup;

public class Coder2 {
	
	public static String second;
	
	public static void startCoder2() throws IOException {
	  
		String first = CheckBoxGroup.fileVoice;
		System.out.println("Path to file before decode= "+ first);	
		second = FromConfig.coder2Path;
		
	    ProcessBuilder pb = new ProcessBuilder(FromConfig.ffmpegPath,"-i",first,"-ac","1","-acodec","pcm_alaw","-ar","15000","-y",second);
	    final Process p = pb.start();
	
	    new Thread() {
		    private Scanner sc;
			//private String match;
		
			public void run() {
		
		        sc = new Scanner(p.getErrorStream());
		
		        // Find duration
		        Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");		        
		        String dur = sc.findWithinHorizon(durPattern, 0);
		        System.out.println(dur);// время
		        if (dur == null) {
		        	throw new RuntimeException("Could not parse duration.");
		        }		        
		      }		
			
	    }.start();
	}
}*/