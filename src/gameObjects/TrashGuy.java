package gameObjects;

import engine.Sprite;

public class TrashGuy extends TalkableNPC {

	public TrashGuy () {
		this.setSprite(new Sprite ("resources/sprites/trashGuy.png"));
		this.conversation = new HappyEmployeeConversation();
		this.setRenderPriority(0);
	}
}