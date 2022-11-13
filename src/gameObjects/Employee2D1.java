package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;
import engine.V2;
import gameObjects.McdonaldsEmployee.LevelWall;
import map.Room;

public class Employee2D1 extends McdonaldsEmployee {
	
	SpriteBitch b;
	
	public Employee2D1 () {
		b = new SpriteBitch ();
		this.setSprite(leftSprite);
		this.radiusX = 200;
	}
	
	@Override
	public void frameEvent () {

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
		
	
		
		LevelWall l = new LevelWall ();
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
						if (!collide (pos, offs2, new Rectangle ((int)this.getX() + 40, (int)this.getY() - 30, 100, 5)) && !collide (pos, offs2, new Rectangle ((int)this.getX() + 40, (int)this.getY() + 50, 100, 5)) ) {
							g.fillRect(i - Room.getViewX(),j - Room.getViewY(),4,4);
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
