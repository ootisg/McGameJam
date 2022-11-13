package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import java.awt.event.KeyEvent;

public class Pipe extends GameObject {
	
	boolean inPipe = false;
	
	Player2D realPlayer;
	
	public Pipe () {
		this.setSprite(new Sprite ("resources/sprites/pipe.png"));
		this.useSpriteHitbox();
		this.setRenderPriority(-1);
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D") && !inPipe && GameCode.keyPressed(KeyEvent.VK_ENTER, this)) {
			Player2D player = (Player2D)this.getCollisionInfo().getCollidingObjects().get(0);
			if (!player.isBlackListed()) {
				player.blackList();
				player.hide();
				realPlayer = player;
				inPipe = true;
				this.setSprite(new Sprite ("resources/sprites/pipe hide.png"));
			}
		}
		
		if (inPipe && GameCode.keyPressed(KeyEvent.VK_ENTER, this)) {
			realPlayer.whiteList();
			realPlayer.show();
			inPipe = false;
			this.setSprite(new Sprite ("resources/sprites/pipe.png"));
		}
		
	}

}
