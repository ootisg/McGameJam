package gameObjects;

import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class McPhone extends GameObject {
	
	TutorialText text;
	
	public McPhone () {
		declare (18, 428);
	}

	@Override
	public void frameEvent () {
		Player p = GameCode.getLevel ().player;
		double diffX = getCenterX () - p.getCenterX ();
		double diffY = getCenterY () - p.getCenterY ();
		if (Math.hypot (diffX, diffY) < 40) {
			if (!p.hasTutorialTxt ()) {
				text = new TutorialText (new Sprite ("resources/sprites/c_to_call.png"));
				p.showTutorialTxt (text);
			}
			if (keyPressed ('C')) {
				new PhoneCodecTrigger ();
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
