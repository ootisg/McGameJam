package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Dave extends GameObject{

	public static final int SPEED = 5;
	
	public Dave () {
		this.setSprite(new Sprite ("resources/sprites/Dave (Main Character).png"));
	}
	
	@Override
	public void frameEvent () {
		if (keyDown ('W')) {
			this.setY(this.getY() - SPEED);
		}
		
		if (keyDown ('S')) {
			this.setY(this.getY() + SPEED);
		}
		
		if (keyDown ('A')) {
			this.setX(this.getX() - SPEED);
		}
	
		if (keyDown ('D')) {
			this.setX(this.getX() + SPEED);
		}	
	}
	
}
