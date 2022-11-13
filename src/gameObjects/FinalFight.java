package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

public class FinalFight extends TalkableNPC {
	
	int timer = 0;
	
	public FinalFight () {
		this.setSprite(new Sprite ("resources/sprites/finalFight.txt"));
		this.getAnimationHandler().setFrameTime(20);
		conversation = new WinConversation();
	}
	
	@Override
	public void frameEvent () {
		timer = timer + 1;
		
		if (timer == 850) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/explosion.wav");
			this.getAnimationHandler().setFrameTime(0);
			Explosion e = new Explosion (10);
			e.setCenterX(480);
			e.setCenterY(270);
			e.setRenderPriority(100);
			e.declare();
			this.setSprite(new Sprite("resources/sprites/machineDestroyed.png"));
		}
		
		if (timer == 900) {
			fadeOut = true;
		}
		if (timer % 5 == 0 && timer < 850) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/punch.wav");
		}
	}
	
	

}
