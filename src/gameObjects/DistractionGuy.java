package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class DistractionGuy extends GameObject {
	
	int waitTimer = 1000;
	int despawnTimer = 0;
	
	public DistractionGuy () {
		
		setSprite (new Sprite ("resources/sprites/distraction_guy_2.png"));
		
		
	}
	
	@Override
	public void frameEvent () {
		if (waitTimer != 0) {
			waitTimer--;
			if (waitTimer == 0) {
				setSprite (new Sprite ("resources/sprites/distraction_guy.txt"));
				this.getAnimationHandler ().setFrameTime (100);
				despawnTimer = 150;
			}
		}
		if (despawnTimer != 0) {
			setY (getY () + 1);
			despawnTimer--;
			if (despawnTimer == 0) {
				forget ();
			}
		}
	}

}
