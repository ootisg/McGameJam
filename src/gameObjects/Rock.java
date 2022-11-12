package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class Rock extends GameObject {
	
	public Rock () {
		setSprite (new Sprite ("resources/sprites/rock.png"));
		this.useSpriteHitbox ();
	}

}
