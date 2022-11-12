package gameObjects;

import java.awt.Rectangle;

import engine.GameObject;
import engine.Sprite;

public class RockSource extends GameObject {
	
	private static EnterForRocks rockIndicator = new EnterForRocks ();
	
	private int rockCount = 0;
	private boolean tooManyRocks = false;
	
	public RockSource (Rectangle bounds) {
		this.setHitboxAttributes (bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public static EnterForRocks getEnterForRocks () {
		if (!rockIndicator.declared ()) {
			rockIndicator.declare ();
		}
		return rockIndicator;
	}
	
	public Rock generateRock () {
		if (rockCount < 10) {
			Rock r = new Rock ();
			r.declare (getX (), getY ());
			rockCount++;
		return r;
		} else {
			rockIndicator.setTooManyRocks ();
			tooManyRocks = true;
			return null;
		}
	}
	
	public boolean sourceEmpty () {
		return tooManyRocks;
	}

}
