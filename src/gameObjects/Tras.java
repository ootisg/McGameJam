package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Tras extends GameObject implements Carryable {
	
	public Tras () {
		setSprite (new Sprite ("resources/sprites/tras.png"));
		this.setHitboxAttributes (0, 0, 32, 32);
	}

}
