package commands.send.playerCmd;

import javax.media.bean.playerbean.MediaPlayer;

public class PlayerCommand {
	
	public static MediaPlayer PlayerCommand;
	
	public PlayerCommand(String path){		
		
		PlayerCommand = new MediaPlayer();
		System.out.println(path);
		PlayerCommand.setMediaLocation("file:///" + path); 
		PlayerCommand.setPlaybackLoop(false);//������ �����
		PlayerCommand.prefetch ();//��������������� ��������� ������ (��� �� ����� �� ��������)
		//��������� �� ����� 
		//add(player);
		//PlayerCommand.start (); //- ����� ��������� �����
		
	}
	
	/*public static void main(String []args){
		PlayerCmd ve = new PlayerCmd("C:\\1.wav");
	}*/
}

