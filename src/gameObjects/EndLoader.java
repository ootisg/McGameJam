package gameObjects;

import engine.GameCode;
import engine.GameObject;
import map.Room;

public class EndLoader extends TalkableNPC {
	
	public EndLoader () {
		this.setHitboxAttributes(100, 10);
		this.conversation = new WindysConversation();
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding("Player2D")) {
			if (!this.getCollisionInfo().getCollidingObjects().get(0).isBlackListed()) {
				GameCode.getSoundPlayer().stop();
				this.getCollisionInfo().getCollidingObjects().get(0).blackList();
				fadeOut = true;
				
			}
		}
	}
}
