package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

public class Player extends GameObject {
	
	public static final Sprite IDLE = new Sprite ("resources/sprites/daveIdle.txt");
	public static final Sprite SIDE = new Sprite ("resources/sprites/daveWalkSide.txt");
	public static final Sprite UP = new Sprite ("resources/sprites/daveUp.txt");
	public static final Sprite DOWN = new Sprite ("resources/sprites/daveDown.txt");
	
	
	
	public Player () {
		setSprite (IDLE);
		this.useSpriteHitbox();
		this.getAnimationHandler().setFrameTime(50);
	}
	
	@Override
	public void frameEvent () {
		double xprev = getX ();
		double yprev = getY ();
		if (keyDown ('W')) {
			if (!keyDown('A') && !keyDown ('D')) {
				this.setSprite(UP);
				this.getAnimationHandler().setFrameTime(100);
			}
			setY (getY () - 3);
		}
		if (keyDown ('A')) {
			this.setSprite(SIDE);
			this.getAnimationHandler().setFlipHorizontal(true);
			setX (getX () - 3);
			this.getAnimationHandler().setFrameTime(50);
		}
		if (keyDown ('S')) {
			if (!keyDown('A') && !keyDown ('D')) {
				this.setSprite(DOWN);
				this.getAnimationHandler().setFrameTime(100);
			}
			setY (getY () + 3);
		}
		if (keyDown ('D')) {
			this.setSprite(SIDE);
			this.getAnimationHandler().setFlipHorizontal(false);
			setX (getX () + 3);
			this.getAnimationHandler().setFrameTime(50);
		}
		if (!keyDown ('W') && !keyDown ('S') && !keyDown ('A') && !keyDown ('D') ) {
			this.setSprite(IDLE);
			this.getAnimationHandler().setFrameTime(100);
		}
		if (GameCode.getLevel ().isColliding (this)) {
			setX (xprev);
			setY (yprev);
		}
	}
	
	public void die () {
		
	}

}
