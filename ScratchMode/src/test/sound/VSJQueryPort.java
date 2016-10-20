package test.sound;
import java.util.*;

import javax.sound.sampled.*;
import javax.sound.sampled.Port.Info;
public class VSJQueryPort {
    
    public static void main(String[] args) throws Exception {
    	probePort();
    }
    
    public static void probePort() throws Exception {
    	
    	ArrayList<Mixer.Info> mixerInfos = new ArrayList<Mixer.Info>( Arrays.asList( AudioSystem.getMixerInfo()));
    	Line.Info portInfo = new Line.Info(Port.class);
    	for (Mixer.Info mixerInfo: mixerInfos) {
    		
    		Mixer mixer = AudioSystem.getMixer(mixerInfo);
	    	System.out.println(mixer.getMixerInfo());
	    	
	    	if (mixer.isLineSupported(portInfo)) {
	        	// found a Port Mixer
	        	disp("Found mixer: " + mixerInfo.getName());
	        	disp("\t" + mixerInfo.getDescription());
	        	disp("Source Line Supported:");
	        	ArrayList<Line.Info> srcInfos = new ArrayList<Line.Info>( Arrays.asList( mixer.getSourceLineInfo()));
	        	
	        	for (Line.Info srcInfo: srcInfos) {
	        		//Port.Info pi = disp("\t" + pi.getName() + ", " + (pi.isSource()? "source" : "target"));
	        		showControls(mixer.getLine( srcInfo));
	        	} // of for Line.Info
	        	
	        	disp("Target Line Supported:");
	        	ArrayList<Line.Info> targetInfos = new ArrayList<Line.Info> ( Arrays.asList( mixer.getTargetLineInfo()));
	        	
	        	for (Line.Info targetInfo: targetInfos) {
	        		Port.Info pi = (Port.Info) targetInfo;
	        		disp("\t" + pi.getName() + ", " + (pi.isSource()? "source" : "taget"));
	        		showControls(mixer.getLine( targetInfo));
	        	}
	        }   	    	 // of if
    	        	// (mixer.isLineSupported)
    	} // of for (Mixer.Info)
    }
    
    private static void showControls( Line inLine) throws Exception {
    	    // must open the line to get
    	    // at controls
    	    inLine.open();
    	    disp("\t\tAvailable controls:");
    	    ArrayList<Control> ctrls = new ArrayList<Control>( Arrays.asList( inLine.getControls()));
    	    for (Control ctrl: ctrls) {
    	    	disp( "\t\t\t" + ctrl.toString());
    	    	if (ctrl instanceof CompoundControl) {
    	    		CompoundControl cc = ((CompoundControl) ctrl);
    	    		ArrayList<Control> ictrls = new ArrayList<Control>( Arrays.asList( cc.getMemberControls()));
    	    		for(Control ictrl : ictrls)
    	    			disp("\t\t\t\t" + ictrl.toString());
    	    		} // of if (ctrl instanceof)
    	    	} // of for(Control ctrl)
    	    	inLine.close();
    	    }
    private static void disp(String msg) {
    	System.out.println(msg);    	
    }



}