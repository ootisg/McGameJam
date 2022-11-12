package gameObjects;

import engine.Sprite;

public class LawnMowerGuy extends TalkableNPC{

	boolean moveLeft = true;
	
	public LawnMowerGuy () {
		this.setSprite(new Sprite ("resources/sprites/lawnmower guy.txt"));
		this.conversation = new LawnMowerConversation();
		this.getAnimationHandler().setFrameTime(100);
	}
	
	@Override
	public void frameEvent() {
		super.frameEvent();
		if (!talking && !fadeOut && !fadeIn) {
			if (moveLeft) {
				this.setX(this.getX() - 1);
				if (this.getX() < 60) {
					moveLeft = false;
					this.getAnimationHandler().setFlipHorizontal(true);
				}
			} else {
				this.setX(this.getX() + 1);
				if (this.getX() > 660) {
					moveLeft = true;
					this.getAnimationHandler().setFlipHorizontal(false);
				}
			}
		}
	}
}
