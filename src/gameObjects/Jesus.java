package gameObjects;

import engine.Sprite;

public class Jesus extends TalkableNPC {
	
	public Jesus () {
		declare (633, 524);
		setHitboxAttributes (24, 24);
		setSprite (new Sprite ("resources/sprites/poster.png"));
		conversation = new CowboyJesusConversation ();
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent ();
		if (talking) {
			forget ();
		}
	}

}
