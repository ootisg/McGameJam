package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import map.Room;

import java.awt.event.KeyEvent;

public class Player2D extends GameObject {
	
	double vy = 0;
	
	double vx = 0;
	
	boolean onGround = true;
	
	public boolean onBlueSlide = false;
	
	public boolean onRedSlide = false;
	
	public boolean onGreenSlide = false;
	

	public static final Sprite WALK = new Sprite ("resources/sprites/daveWalkSide.txt");
	public static final Sprite IDLE = new Sprite ("resources/sprites/daveIdle.txt");
	
	
	public Player2D() {
		this.setSprite(IDLE);
		this.useSpriteHitbox();
		this.getAnimationHandler().setFrameTime(100);
	}
	
	@Override
	public void onDeclare () {
		Room.setView((int)this.getX() - 480 ,(int)this.getY() - 270);
	}
	
	@Override
	public void frameEvent () {
	
		
		
		
		if (!onBlueSlide && !onRedSlide && !onGreenSlide) {
			if (keyDown ('D')) {
				this.getAnimationHandler().setFlipHorizontal(false);
				this.setSprite(WALK);
				if (this.goX(this.getX() + 4)) {
					Room.setView(Room.getViewXAcurate() + 4,Room.getViewYAcurate());
				}
			}
			
			if (keyDown ('A')) {
				this.getAnimationHandler().setFlipHorizontal(true);
				this.setSprite(WALK);
				if (this.goX(this.getX() - 4)) {
					Room.setView(Room.getViewXAcurate() - 4,Room.getViewYAcurate());
				}
			}
			
			if ((!keyDown ('A') && !keyDown ('D')) ) {
				this.setSprite(IDLE);
			}
			
			if (keyDown(KeyEvent.VK_SPACE) && onGround) {
				vy = -8;
				onGround = false;
			}
			
			
			if (!this.goY(this.getY() + vy)) {
				if (vy > 0) {
					onGround = true;
				}
				vy = 0;
			} else {
				Room.setView(Room.getViewXAcurate(),(int) (Room.getViewYAcurate() + vy));
				if (vy < 15) {
					vy = vy + .3;
				}
			}
		} else {
			if (onBlueSlide) {
				if (!this.goX(this.getX() - 4)){
					useSpriteHitbox();
					onBlueSlide = false;
				} else {
					Room.setView(Room.getViewXAcurate() - 4,Room.getViewYAcurate());
				}
				
				if (!this.goY(this.getY() + 3)) {
					useSpriteHitbox();
					onBlueSlide = false;
				} else {
					Room.setView(Room.getViewXAcurate(),Room.getViewYAcurate() + 3);
				}
				
			} else if (onRedSlide) {
				
				if (!this.goX(this.getX() + 6)){
					useSpriteHitbox();
					onRedSlide = false;
				} else {
					Room.setView(Room.getViewXAcurate() + 6,Room.getViewYAcurate());
				}
				
				if (!this.goY(this.getY() + 2)) {
					useSpriteHitbox();
					onRedSlide = false;
				} else {
					Room.setView(Room.getViewXAcurate(),Room.getViewYAcurate() + 2);
				}
			} else {
				if (!this.goX(this.getX() + 12)){
					useSpriteHitbox();
					onGreenSlide = false;
				} else {
					Room.setView(Room.getViewXAcurate() + 12,Room.getViewYAcurate());
				}
				this.goY(this.getY() + 1);
//				if (!this.goY(this.getY() + 1)) {
//					onGreenSlide = false;
//				} else {
//					Room.setView(Room.getViewXAcurate(),Room.getViewYAcurate() + 1);
//				}
			}
		}
		
		
				
	}
	
	@Override
	public void draw() {
		super.draw();
	}
	
	public void die () {
		this.blackList();
		this.hide();
		
		
		this.setX(1653);
		this.setY(385);
		Room.setView((int)this.getX() - 480 ,(int)this.getY() - 270);
		
		LevelThreeGameOverScreen screen = new LevelThreeGameOverScreen();
		screen.playMcdonaldsJingle();
		
		screen.declare(screen.getX(),screen.getY());
	}
}
