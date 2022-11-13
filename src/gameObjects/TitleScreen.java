package gameObjects;

import engine.GameObject;
import engine.Sprite;
import java.awt.event.KeyEvent;

public class TitleScreen extends TalkableNPC {
	
	public TitleScreen () {
		this.setSprite(new Sprite ("resources/sprites/titleScreen.png"));
		conversation = new IntroConversation ();
	}

	@Override
	public void frameEvent () {
		if (keyDown (KeyEvent.VK_ENTER) && !fadeOut) {
			fadeOut = true;
		}
		
		if (talking) {
			this.forget();
		}
	}
	
}
