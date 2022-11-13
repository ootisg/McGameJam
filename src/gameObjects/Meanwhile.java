package gameObjects;

import engine.Sprite;

public class Meanwhile extends TalkableNPC {

	int timer = 120;
	
	public Meanwhile () {
		this.setSprite(new Sprite ("resources/sprites/meanwhile.png"));
		this.conversation = new McDaleRevealConversation ();
	}
	
	
	@Override
	public void frameEvent () {
		timer = timer -1;
		if (timer == 0) {
			fadeOut = true;
		}
		if (talking) {
			forget();
		}
	}
}
