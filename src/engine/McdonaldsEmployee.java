package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class McdonaldsEmployee extends GameObject {

	double [] faceVector = {1,0};
	
	public McdonaldsEmployee () {
		this.setSprite(new Sprite ("resources/sprites/McDonaldsEmployee.png"));
	}

	
	
	@Override
	public void draw() {
		super.draw();
		
		for (int i = (int)this.getX() - 400; i < this.getX() + 400; i = i + 10) {
			for (int j = (int)this.getY() - 400; j < this.getY() + 400; j = j + 10) {
				
				
				
				double xDist = i - this.getX();
				double yDist = j - this.getY();
				
				double length = Math.hypot(xDist, yDist);
				
				double contender1 = (((i/length) * faceVector[0]) + ((j/length) * faceVector[1]));
				double contender2 = Math.cos(Math.toRadians(15));
				
				
				if (contender1 > contender2) {
					
					ColidableVector toPixel = new ColidableVector (new Point ((int)this.getX(),(int)this.getY()), new Point (i,j)); 
					
					ArrayList <Rectangle> walls = GameCode.getLevel().collision;
					boolean hitWall = false;
					
					for (int k = 0; k < walls.size(); k++) {
						LevelWall l = new LevelWall ();
						l.setHitbox(walls.get(k).x, walls.get(k).y, walls.get(k).width, walls.get(k).height);
						if (toPixel.isColliding(l)) {
							hitWall = true;
						}
					}
					if (hitWall) {
						break;
					}
					
					
					//looked at
					Graphics g = RenderLoop.wind.getBufferGraphics();
					g.setColor(new Color (230,225,108,55));
					g.fillRect(i,j,10,10);
					if (toPixel.isColliding(GameCode.getLevel().player)) {
						GameCode.getLevel().player.die();
					}
				}
			}
		}
	}

	public class LevelWall extends GameObject {
		public LevelWall () {
			
		}
	}
}

