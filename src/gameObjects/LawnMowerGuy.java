package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class LawnMowerGuy extends TalkableNPC{

	boolean moveLeft = true;
	boolean dead = false;
	
	public LawnMowerGuy () {
		this.setSprite(new Sprite ("resources/sprites/lawnmower guy.txt"));
		this.conversation = new LawnMowerConversation();
		this.getAnimationHandler().setFrameTime(100);
	}
	
	@Override
	public void frameEvent() {
		if (!dead) {
			super.frameEvent();
		}
		if (!talking && !fadeOut && !fadeIn && !dead) {
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
			if (this.isColliding("Rock") && !dead) {
				Explosion e = new Explosion (5);
				e.declare();
				e.setCenterX(this.getX());
				e.setCenterY(this.getY());
				e.setRenderPriority(101);
				this.getCollisionInfo().getCollidingObjects().get(0).forget();
				this.getAnimationHandler().setFrameTime(0);
				dead = true;
			}
		}
	}
	
	public class Lawnmower extends GameObject {
		public Lawnmower () {
		}
		
		public void breakMower () {
			this.breakToFragments("lawnmowerFragment", 5,7,4,10);
		}
	}
}
