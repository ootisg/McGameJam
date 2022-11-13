package gameObjects;

import engine.V2;

public class LineGuy extends McdonaldsEmployee {

	int timer = 0;
	
	public LineGuy () {
		this.getAnimationHandler ().setFrameTime (0);
	}
	
	@Override
	public void frameEvent () {
		if (timer == 60) {
			faceVector = new V2 (0, -1);
		}
		if (timer >= 80) {
			faceVector = new V2 (1, 0);
			timer = 0;
		}
		timer++;
	}
	
}
