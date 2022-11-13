package gameObjects;

import engine.GameCode;
import engine.GameObject;

public class SoundTrigger extends GameObject {
	
	String fileName;
	
	public SoundTrigger (String sound) {
		fileName = sound;
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D")) {
			GameCode.getSoundPlayer().playSoundEffect(6F, fileName);
			this.forget();
		}
	}

}
