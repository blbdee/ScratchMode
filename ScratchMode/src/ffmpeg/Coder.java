package ffmpeg;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import buttons.SelectWav;
import config.FromConfig;
import commands.send.CheckBoxGroup;

public class Coder {
	public static String second;
	
  public static void startCoder() throws IOException {
	
	String first = SelectWav.path;
	System.out.println("Path to file before decode "+ first);	
	second = FromConfig.coderPath;
	
    ProcessBuilder pb = new ProcessBuilder(FromConfig.ffmpegPath,"-i",first,"-ac","1","-acodec","pcm_alaw","-ar","15000","-y",second); 
    final Process p = pb.start();

    new Thread() {
	    private Scanner sc;
	
		public void run() {
	
		        sc = new Scanner(p.getErrorStream());
		
		        // Find duration
		        Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
		        //System.out.println(durPattern);
		        String dur = sc.findWithinHorizon(durPattern, 0);
		        System.out.println("Total time: "+dur);// время
		        if (dur == null) {
		          throw new RuntimeException("Could not parse duration.");
		        }
		        /*String[] hms = dur.split(":");//разбили слова двоеточием
		        for (int i=0; i<hms.length; i++) {
		        	System.out.println(hms[i]);
		        }
		        double totalSecs = Integer.parseInt(hms[0]) * 3600
		                         + Integer.parseInt(hms[1]) *   60
		                         + Double.parseDouble(hms[2]);
		        System.out.println("Total duration: " + totalSecs + " seconds.");
		        System.out.println(totalSecs);//время трека в секундах*/
		
		        
		        //Pattern timePattern = Pattern.compile("(?<=time=)[\\d.]*");
		        //System.out.println("timePat= "+timePattern);
		        //String match = sc.findWithinHorizon(timePattern, 0);
		        //System.out.println("match= "+match);
		        //while (match != null) {
		        //  double progress = Double.parseDouble(match) / totalSecs;
		        //  System.out.printf("Progress: %.2f%%%n", progress * 100);
		        //}
	        
	      }
    }.start();   
   

  }

}