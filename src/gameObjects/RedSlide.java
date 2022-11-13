package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class RedSlide extends GameObject {
	boolean coliding = false;
	public RedSlide () {
		this.setSprite (new Sprite ("resources/sprites/redSlide.png"));
		this.useSpriteHitbox();
		this.enablePixelCollisions();
		this.setRenderPriority(-1);
	}

	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D") && !coliding) {
			coliding = true;
			Player2D player = (Player2D)this.getCollisionInfo().getCollidingObjects().get(0);
			player.dontUseSpriteHitbox();
			player.setSprite(new Sprite ("resources/sprites/daveSlide.txt"));
			player.getAnimationHandler().setFrameTime(100);
			if (!player.onRedSlide) {
				player.onRedSlide = true;
			}
		}
		
		if (!this.isColliding("Player2D")) {
			coliding = false;
		}
	}
	
}
