package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class IceCreamMachine extends GameObject {
	
	TutorialText text;
	
	public IceCreamMachine () {
		declare (508, 288);
		declare (544, 291);
		setHitboxAttributes (-36, -3, 118, 107);
		setSprite (new Sprite ("resources/sprites/definitely_broken.png"));
	}
	
	@Override
	public void frameEvent () {
		Player p = (Player)ObjectHandler.getObjectsByName ("Player").get (0);
		boolean isMeterFull = ((InsideMcDonalds)GameCode.getLevel ()).stuffMeter.isFull ();
		if (isMeterFull) {
			if (this.isColliding ("Player")) {
				if (!p.hasTutorialTxt ()) {
					text = new TutorialText (new Sprite ("resources/sprites/j_to_jam.png"));
					p.showTutorialTxt (text);
				}
				if (keyPressed ('J')) {
					Explosion exp = new Explosion (12);
					exp.declare ();
					exp.setCenterX (getCenterX ());
					exp.setCenterY (getCenterY ());
					hide ();
				}
			} else {
				if (p.tutorialTxt == text) {
					p.hideTutorialTxt ();
				}
			}
		}
	}

}
