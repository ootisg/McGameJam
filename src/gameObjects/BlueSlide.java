package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class BlueSlide extends GameObject {
	boolean coliding = false;
	public BlueSlide () {
		this.setSprite (new Sprite ("resources/sprites/blueSlide.png"));
		this.useSpriteHitbox();
		this.enablePixelCollisions();
		this.setRenderPriority(-1);
	}

	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D") && !coliding) {
			coliding = true;
			Player2D player = (Player2D)this.getCollisionInfo().getCollidingObjects().get(0);
			if (!player.onBlueSlide) {
				player.dontUseSpriteHitbox();
				player.onBlueSlide = true;
				player.setSprite(new Sprite ("resources/sprites/daveSlide.txt"));
				player.getAnimationHandler().setFrameTime(100);
			}
		}
		
		if (!this.isColliding("Player2D")) {
			coliding = false;
		}
	}
	
}
