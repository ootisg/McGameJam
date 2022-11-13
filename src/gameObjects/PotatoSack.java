package gameObjects;

import java.awt.event.KeyEvent;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

public class PotatoSack extends GameObject {

	TutorialText text;
	
	public PotatoSack () {
		setSprite (new Sprite ("resources/sprites/potatoes.png"));
		setHitboxAttributes (0, 0, 32, 32);
	}
	
	@Override
	public void frameEvent () {
		Player p = GameCode.getLevel ().player;
		double diffX = getCenterX () - p.getCenterX ();
		double diffY = getCenterY () - p.getCenterY ();
		if (Math.hypot (diffX, diffY) < 40) {
			if (!p.hasTutorialTxt ()) {
				text = new TutorialText (new Sprite ("resources/sprites/g_to_grab.png"));
				p.showTutorialTxt (text);
			}
			if (keyPressed ('G')) {
				((InsideMcDonalds)GameCode.getLevel ()).stuffMeter.fill ();
				p.hideTutorialTxt ();
				forget ();
			}
		} else {
			if (p.tutorialTxt == text) {
				p.hideTutorialTxt ();
			}
		}
	}
	
}
