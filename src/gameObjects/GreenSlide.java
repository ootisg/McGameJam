package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class GreenSlide extends GameObject {
	boolean coliding = false;
	public GreenSlide () {
		this.setSprite (new Sprite ("resources/sprites/greenSlide.png"));
		this.useSpriteHitbox();
		this.enablePixelCollisions();
		this.setRenderPriority(-1);
	}

	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D") && !coliding) {
			coliding = true;
			Player2D player = (Player2D)this.getCollisionInfo().getCollidingObjects().get(0);
			if (!player.onGreenSlide) {
			
				player.onGreenSlide = true;
			}
		}
		
		if (!this.isColliding("Player2D")) {
			coliding = false;
		}
	}
	
}
