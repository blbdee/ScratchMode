package ffmpeg;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import tableObjects.TableModel;
import config.FromConfig;

public class CoderTfop {
	
	private Process p;
	private Scanner sc;
	
	public CoderTfop(String inputFile, String outputFile) {
		
		ProcessBuilder pb = new ProcessBuilder(FromConfig.ffmpegPath,"-i",inputFile,"-ac","1","-acodec","pcm_u8","-ar","22050","-y",outputFile);
		//ProcessBuilder pb = new ProcessBuilder(FromConfig.ffmpegPath,"-i",inputFile,"-codec:a","libmp3lame","-y",outputFile);
		try {
			p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread() {
		      public void run() {
		
		        sc = new Scanner(p.getErrorStream());
		
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
	}

}
