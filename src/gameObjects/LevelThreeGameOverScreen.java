package gameObjects;

import java.util.Random;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;
import map.Room;

public class LevelThreeGameOverScreen extends GameObject {
	
	int gameOverTimer = 0;
	
	public LevelThreeGameOverScreen () {
		this.setSprite(new Sprite("resources/sprites/gameOver.png"));
		this.setX(Room.getViewXAcurate());
		this.setY(Room.getViewYAcurate());
		this.setRenderPriority(1000);
		GameCode.getSoundPlayer().stopAll();
	}
	
	public void playMcdonaldsJingle () {
		GameCode.getSoundPlayer().stop();
		GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McCursed.wav");
	}
	
	public void playDeathLine () {
		Random r = new Random ();
		GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/GameOver" + (r.nextInt(3) + 4) + ".wav");
	}
	

	@Override
	public void frameEvent () {
		gameOverTimer = gameOverTimer + 1;
		
		if (gameOverTimer == 200) {
			playDeathLine();
		}
		
		
		if (gameOverTimer == 300) {
			forget();
			gameOverTimer = 0;
			ObjectHandler.getObjectsByName("Player2D").get(0).whiteList();
			ObjectHandler.getObjectsByName("Player2D").get(0).show();
		}
	}
	
}
