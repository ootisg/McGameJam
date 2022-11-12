package gameObjects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Player extends GameObject {
	
	public GameObject carrying;
	
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
		if (keyPressed(KeyEvent.VK_ENTER)) {
			if (carrying == null) {
				if (isColliding ("RockSource")) {
					RockSource rs = (RockSource)this.getCollisionInfo ().getCollidingObjects ().get (0);
					Rock r = rs.generateRock ();
					if (r != null) {
						carrying = r;
					}
				} else {
					ArrayList<ArrayList<GameObject>> pickups = new ArrayList<ArrayList<GameObject>> ();
					pickups.add (ObjectHandler.getObjectsByName ("Tras"));
					pickups.add (ObjectHandler.getObjectsByName ("Rock"));
					for (int i = 0; i < pickups.size (); i++) {
						ArrayList<GameObject> currList = pickups.get (i);
						for (int j = 0; j < currList.size (); j++) {
							GameObject curr = currList.get (j);
							double x1 = getX () + hitbox ().width / 2;
							double y1 = getY () + hitbox ().height / 2;
							double x2 = curr.getX () + curr.hitbox ().width / 2;
							double y2 = curr.getY () + curr.hitbox ().height / 2;
							if (Math.sqrt ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < 60) {
								carrying = curr;
								break;
							}
						}
						if (carrying != null) {
							break;
						}
					}
				}
			} else {
				carrying = null;
			}
		}
		if (carrying != null) {
			carrying.setX (getX () + 8 - carrying.hitbox ().width / 2);
			carrying.setY (getY () - carrying.hitbox ().height);
		}
	}

	@Override
	public void draw () {
		super.draw ();
		if (carrying == null && isColliding ("RockSource")) {
			RockSource.getEnterForRocks ().show ();
			if (RockSource.getEnterForRocks ().tooManyRocks ()) {
	 			RockSource.getEnterForRocks ().setX (getX () - 16);
				RockSource.getEnterForRocks ().setY (getY () - 48);
			} else {
	 			RockSource.getEnterForRocks ().setX (getX ());
				RockSource.getEnterForRocks ().setY (getY () - 32);
			}
		} else {
			RockSource.getEnterForRocks ().hide ();
		}
	}
	
}
