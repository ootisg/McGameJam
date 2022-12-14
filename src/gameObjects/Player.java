package gameObjects;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;
import map.Room;

public class Player extends GameObject {
	
	public GameObject carrying;
	public static final Sprite IDLE = new Sprite ("resources/sprites/daveIdle.txt");
	public static final Sprite SIDE = new Sprite ("resources/sprites/daveWalkSide.txt");
	public static final Sprite UP = new Sprite ("resources/sprites/daveUp.txt");
	public static final Sprite DOWN = new Sprite ("resources/sprites/daveDown.txt");
	
	public GameObject tutorialTxt;
	
	public int lastDirection = 0; //0 for right, 1 for left
	
	public Player () {
		setSprite (IDLE);
		this.useSpriteHitbox();
		this.getAnimationHandler().setFrameTime(50);
		setRenderPriority (1);
	}
	
	public void showTutorialTxt (GameObject obj) {
		tutorialTxt = obj;
		tutorialTxt.declare (getX () - 16, getY () - 32);
	}
	
	public void hideTutorialTxt () {
		if (tutorialTxt != null) {
			tutorialTxt.forget ();
			tutorialTxt = null;
		}
	}
	
	public boolean hasTutorialTxt () {
		return tutorialTxt != null;
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
			lastDirection = 1;
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
			lastDirection = 0;
		}
		if (!keyDown ('W') && !keyDown ('S') && !keyDown ('A') && !keyDown ('D') ) {
			if (this.getSprite().equals(SIDE) || this.getSprite().equals(IDLE)) {
				this.setSprite(IDLE);
				this.getAnimationHandler().setFrameTime(100);
			} else {
				this.getAnimationHandler().setFrameTime(0);
			}
		}
		if (GameCode.getLevel() != null && (GameCode.getLevel ().isColliding (this) || !new Rectangle (-80, -80, 1120, 700).intersects (hitbox ()))) {
			setX (xprev);
			setY (yprev);
		}
		if (keyPressed(KeyEvent.VK_SHIFT)) {
			if (carrying == null) {
				if (isColliding ("RockSource") && !((RockSource)this.getCollisionInfo ().getCollidingObjects ().get (0)).sourceEmpty ()) {
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
						if (currList != null) {
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
						}
						if (carrying != null) {
							break;
						}
					}
				}
			} else {
				int frontOffset = lastDirection == 0 ? 24 : -24;
				carrying.setX (getX () + hitbox ().width / 2 - carrying.hitbox ().width / 2 + frontOffset);
				carrying.setY (getY () + hitbox ().height - carrying.hitbox ().height);
				carrying = null;
			}
		}
		if (carrying != null) {
			carrying.setX (getX () + 8 - carrying.hitbox ().width / 2);
			carrying.setY (getY () - carrying.hitbox ().height);
		}
	}
	
	public void die () {
		this.blackList();
		this.hide();
		
		this.setX(0);
		this.setY(0);
		
		
		LevelTwoGameOverScreen screen = new LevelTwoGameOverScreen();
		screen.playMcdonaldsJingle();
		
		screen.declare();
	}

	@Override
	public void draw () {
		super.draw ();
		if (tutorialTxt != null) {
			tutorialTxt.setX (getX () - 16);
			tutorialTxt.setY (getY () - 32);
		}
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
