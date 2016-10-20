package commands.send.playerCmd;

import javax.media.bean.playerbean.MediaPlayer;

public class PlayerCommand {
	
	public static MediaPlayer PlayerCommand;
	
	public PlayerCommand(String path){		
		
		PlayerCommand = new MediaPlayer();
		System.out.println(path);
		PlayerCommand.setMediaLocation("file:///" + path); 
		PlayerCommand.setPlaybackLoop(false);//Повтор видео
		PlayerCommand.prefetch ();//предварительная обработка плеера (без неё плеер не появится)
		//добавляем на фрейм 
		//add(player);
		//PlayerCommand.start (); //- сразу запустить плеер
		
	}
	
	/*public static void main(String []args){
		PlayerCmd ve = new PlayerCmd("C:\\1.wav");
	}*/
}

