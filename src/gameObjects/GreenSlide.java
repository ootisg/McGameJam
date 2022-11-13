package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

public class GreenSlide extends GameObject {
	boolean coliding = false;
	public GreenSlide () {
		this.setSprite (new Sprite ("resources/sprites/greenSlide.png"));
		this.useSpriteHitbox();
		this.enablePixelCollisions();
		this.setRenderPriority(-1);
		EndLoader load = new EndLoader ();
		load.declare(1987, 931);
		GameCode.getSoundPlayer().play("resources/sound/McDungeon.wav", 6F);
		
		SoundTrigger trig1 = new SoundTrigger ("resources/sound/Crawling1.wav");
		SoundTrigger trig2 = new SoundTrigger ("resources/sound/Crawling2.wav");
		SoundTrigger trig3 = new SoundTrigger ("resources/sound/Crawling3.wav");
		
		trig1.setHitboxAttributes(5, 20);
		trig1.declare(1565,580);
		
		trig2.setHitboxAttributes(5, 20);
		trig2.declare(730,467);
		
		trig3.setHitboxAttributes(20, 5);
		trig3.declare(1319,392);
		
		
	}

	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D") && !coliding) {
			coliding = true;
			Player2D player = (Player2D)this.getCollisionInfo().getCollidingObjects().get(0);
			if (!player.onGreenSlide) {
				player.dontUseSpriteHitbox();
				player.setSprite(new Sprite ("resources/sprites/daveSlide.txt"));
				player.getAnimationHandler().setFrameTime(100);
				player.onGreenSlide = true;
			}
		}
		
		if (!this.isColliding("Player2D")) {
			coliding = false;
		}
	}
	
}
