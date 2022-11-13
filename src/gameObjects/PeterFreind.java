package gameObjects;

import engine.Sprite;

public class PeterFreind extends TalkableNPC{

	public PeterFreind () {
		this.setSprite(new Sprite ("resources/sprites/peterFriend.png"));
		this.conversation = new PeterFreindConversation ();
	}
	
}
