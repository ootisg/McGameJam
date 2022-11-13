package gameObjects;

import java.awt.event.KeyEvent;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class OverlayGuide extends GameObject {
	
	public OverlayGuide () {
		setSprite (new Sprite ("resources/sprites/level_2_overlay.png"));
		declare (0, 0);
		hide ();
	}
	
	@Override
	public void frameEvent () {
		if (keyPressed ('Q')) {
			show ();
			ObjectHandler.pause (true);
		}
	}
	
	@Override
	public void pausedEvent () {
		if (keyPressed ('Q')) {
			hide ();
			ObjectHandler.pause (false);
		}
	}

}
