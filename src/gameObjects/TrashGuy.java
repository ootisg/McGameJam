package gameObjects;

import java.util.ArrayList;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class TrashGuy extends TalkableNPC {

	boolean mad = false;
	
	public TrashGuy () {
		this.setSprite(new Sprite ("resources/sprites/trashGuy.png"));
		this.conversation = new TrashConversation();
		this.setRenderPriority(0);
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();

		if (!mad) {
			ArrayList <GameObject> trashBags = ObjectHandler.getObjectsByName("Tras");
			
			boolean close = true;
			for (int i = 0; i < trashBags.size(); i++) {
				double xDist = trashBags.get(i).getCenterX() - this.getCenterX();
				double yDist = trashBags.get(i).getCenterY() - this.getCenterY();
				
				double length = Math.hypot(xDist, yDist);
				
				if (length > 100) {
					close = false;
					break;
				}
			}
			if (close) {
				mad = true;
				this.conversation = new TrashConversation2();
			}
		}
	}
	
	public boolean isMad () {
		return mad;
	}
}
