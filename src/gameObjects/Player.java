package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

public class Player extends GameObject {
	
	public Player () {
		setSprite (new Sprite ("resources/sprites/player.png"));
		this.setHitboxAttributes (16, 16);
	}
	
	@Override
	public void frameEvent () {
		double xprev = getX ();
		double yprev = getY ();
		if (keyDown ('W')) {
			setY (getY () - 3);
		}
		if (keyDown ('A')) {
			setX (getX () - 3);
		}
		if (keyDown ('S')) {
			setY (getY () + 3);
		}
		if (keyDown ('D')) {
			setX (getX () + 3);
		}
		if (GameCode.getLevel ().isColliding (this)) {
			setX (xprev);
			setY (yprev);
		}
	}

}
