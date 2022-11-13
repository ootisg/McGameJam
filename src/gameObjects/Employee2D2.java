package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.RenderLoop;
import engine.Sprite;
import engine.V2;
import gameObjects.McdonaldsEmployee.LevelWall;
import map.Room;

public class Employee2D2 extends McdonaldsEmployee {
	
	SpriteBitch b;
	
	int timer = 0;
	boolean moveRight = true;
	Player2D player;
	
	public Employee2D2 () {
		b = new SpriteBitch ();
		this.setSprite(leftSprite);
		b.getAnimationHandler().setFrameTime(100);
		b.getAnimationHandler().setFlipHorizontal(false);
		this.faceVector = rightVec;
		this.radiusX = 200;
	}
	
	@Override
	public void frameEvent () {
		if (player == null) {
			player = (Player2D)ObjectHandler.getObjectsByName("Player2D").get(0);
		}
		
		if (moveRight) {
			this.setX(this.getX() + 3);
		} else {
			this.setX(this.getX() - 3);
		}
		timer = timer + 1;
		if (timer == 160) {
			timer = 0;
			if (this.faceVector.equals(leftVec)) {
				this.faceVector = rightVec;
			} else {
				this.faceVector = leftVec;
			}
			b.getAnimationHandler().setFlipHorizontal(!b.getAnimationHandler().flipHorizontal());
			moveRight = !moveRight;
		}
	}
	
	@Override
	public void setSprite (Sprite s) {
		super.setSprite(s);
		if (b != null) {
			b.setSprite(s);
		}
	}
	
	@Override
	public void draw() {
		b.setX(this.getX());
		b.setY(this.getY());
		b.draw();
		Graphics g = RenderLoop.wind.getBufferGraphics();
		g.setColor(new Color (230,225,108,55));
		V2 pos = new V2 ((float)getX (), (float)getY ());
		
	
		
		for (int i = align ((int)this.getX() - radiusX, 4); i < align ((int)this.getX() + radiusY, 4); i = i + 4) {
			for (int j = align ((int)this.getY() - radiusX, 4); j < align ((int)this.getY() + radiusY, 4); j = j + 4) {
				
				double xOffs = i - this.getX();
				double yOffs = j - this.getY();
				if (xOffs * xOffs + yOffs * yOffs <= radiusX * radiusX) {
				
					faceVector.normalize ();
					V2 offs = new V2 ((float)xOffs, (float)yOffs);
					V2 offs2 = new V2 ((float)xOffs, (float)yOffs);
					offs2 = offs2.add (pos);
					offs.normalize ();
					faceVector.normalize ();
					
					double contender1 = (faceVector.dot (offs));
					double contender2 = Math.cos(Math.toRadians(fov));
					
					
					
					if (contender1 > contender2) {
						
						//ColidableVector toPixel = new ColidableVector (new Point ((int)this.getX(),(int)this.getY()), new Point (i,j)); 
						g.fillRect(i - Room.getViewX(),j - Room.getViewY(),4,4);
						
						try {
							if (collide (pos, offs2, player.hitbox()) && !player.isBlackListed()) {
								player.die();
							}
						} catch (NullPointerException e) {
							
						}
						
						//looked at
						/*if (toPixel.isColliding(GameCode.getLevel().player)) {
							GameCode.getLevel().player.die();
						}*/
					}
				}
			}
			
			
			
		}
	}
	
	public class SpriteBitch extends GameObject {
		public SpriteBitch () {
			
		}
	}

}
