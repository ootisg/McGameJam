package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class StuffMeter extends GameObject {
	
	int fullness = 0;
	
	public StuffMeter () {
		setSprite (new Sprite ("resources/sprites/stuff_meter.txt"));
		getAnimationHandler ().setFrameTime (0);
		declare (356, 3);
	}
	
	public void fill () {
		fullness++;
		if (fullness > 4) {
			fullness = 4;
		}
	}
	
	public boolean isFull () {
		return fullness == 4;
	}
	
	@Override
	public void draw () {
		if (fullness <= 4) {
			getAnimationHandler ().setAnimationFrame (fullness);
		}
		super.draw ();
	}

}
