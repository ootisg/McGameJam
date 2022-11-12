package gameObjects;

import engine.Sprite;

public class HappyEmployee extends TalkableNPC {

	public HappyEmployee () {
		this.setSprite(new Sprite ("resources/sprites/happyEmployee.png"));
		this.conversation = new HappyEmployeeConversation();
		this.setRenderPriority(0);
	}
}
