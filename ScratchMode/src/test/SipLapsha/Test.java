package test.SipLapsha;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

class Test {
  
	public Test(String first, String second) {
	    ProcessBuilder pb = new ProcessBuilder("C:\\Users\\Admin\\workspace\\ScratchMode\\ffmpeg_x64.exe","-i",first,"-codec:a","libmp3lame","-y",second);
	    //ffmpeg -i 12345ster.wav -ac 1 -acodec pcm_u8 12345m.wav
	    Process p;
		try {
			p = pb.start();
			
			new Thread() {
			      public void run() {
			
			        Scanner sc = new Scanner(p.getErrorStream());
			
			        // Find duration
			        Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
			        String dur = sc.findWithinHorizon(durPattern, 0);
			        if (dur == null)
			          throw new RuntimeException("Could not parse duration.");
			        String[] hms = dur.split(":");
			        double totalSecs = Integer.parseInt(hms[0]) * 3600
			                         + Integer.parseInt(hms[1]) *   60
			                         + Double.parseDouble(hms[2]);
			        System.out.println("Total duration: " + totalSecs + " seconds.");
			
			        // Find time as long as possible.
			        Pattern timePattern = Pattern.compile("(?<=time=)[\\d.]*");
			        String match;
			        while (null != (match = sc.findWithinHorizon(timePattern, 0))) {
			          double progress = Double.parseDouble(match) / totalSecs;
			          System.out.printf("Progress: %.2f%%%n", progress * 100);
			        }
			      }
			    }.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    

  }
}