package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class EnterForRocks extends GameObject {
	
	public static final Sprite pickupRock = new Sprite ("resources/sprites/enterforrocks.png");
	public static final Sprite noMoreRock = new Sprite ("resources/sprites/easteregg.png");
	
	private boolean tooMany = false;
	
	public EnterForRocks () {
		setSprite (pickupRock);
		this.setRenderPriority (100);
	}
	
	public boolean tooManyRocks () {
		return tooMany;
	}
	
	public void setTooManyRocks () {
		tooMany = true;
		setSprite (noMoreRock);
	}

}
