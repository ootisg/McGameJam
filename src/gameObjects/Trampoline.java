package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;

public class Trampoline extends GameObject {
	
	public Trampoline () {
		this.setSprite(new Sprite ("resources/sprites/trampoline.png"));
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D")) {
			Player2D player = (Player2D) this.getCollisionInfo().getCollidingObjects().get(0);
			
			if (player.vy > 0) {
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/boing.wav");
				player.vy = player.vy * -1;
			}
		}
	}

}
