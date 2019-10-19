import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * A class used to provide sound output for the SimpleBoardGame framework. An object is created by providing a
 * viable string that translates into a filetype in the “.wav” format. The sound is then played. 
 */

public class GameSound {
	
	public GameSound(String soundFile){
		
		if(soundFile != null){
			
			File file = new File(soundFile);
	
			try{
			    Clip clip = AudioSystem.getClip();
			    clip.open(AudioSystem.getAudioInputStream(file));
			    clip.start();
			    Thread.sleep(clip.getMicrosecondLength()/100000);
			}
			catch(Exception e){
				System.out.println("soundFile is non existing!");
			}
		}
	}

}
