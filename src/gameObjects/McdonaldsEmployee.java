package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import engine.ColidableVector;
import engine.GameCode;
import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;
import engine.V2;

public class McdonaldsEmployee extends GameObject {

	public static final Sprite upSprite = new Sprite ("resources/sprites/mcdonaldsEmployeeUp.txt");
	public static final Sprite downSprite = new Sprite ("resources/sprites/mcdonaldsEmployeeDown.txt");
	public static final Sprite leftSprite = new Sprite ("resources/sprites/mcdonaldsEmployeeLeft.txt");
	public static final Sprite idleSprite = new Sprite ("resources/sprites/mcdonaldsEmployeeIdle.txt");
	
	public static final V2 upVec = new V2 (0, -1);
	public static final V2 downVec = new V2 (0, 1);
	public static final V2 leftVec = new V2 (-1, 0);
	public static final V2 rightVec = new V2 (1, 0);
	
	ArrayList<PathPoint> path;
	
	int radiusX = 300;
	int radiusY = 300;
	double fov = 30;
	int aiType = 0;
	
	V2 faceVector = new V2 (1, 0);
	boolean direction;
	boolean turning = false;
	PathPoint fromPt = null;
	PathPoint toPt = null;
	ListIterator<PathPoint> pathIter = null;
	double pauseCounter = 0;
	
	double ang = 0;
	
	int currDir = -1;
	
	public McdonaldsEmployee () {
		this.setSprite(new Sprite (leftSprite));
		this.getAnimationHandler ().setFrameTime (100);
		path = new ArrayList<PathPoint> ();
	}

	public float fminf (float a, float b) {
		return a < b ? a : b;
	}
	
	private float fmaxf (float a, float b) {
		return a > b ? a : b;
	}
	
	public boolean collide (V2 from, V2 to, Rectangle r) {
		
		//Include all triangle edge intersections with the screen boundaries
		int i;
		//Find the slope/intecept form
		float m, b;
		if (to.x == from.x) {
			//TODO do I need to include vertical edges?
			//Horizontal edges
			int j;
			for (j = 0; j < 2; j ++) {
				float wy = j == 0 ? r.y : r.y + r.height;
				if (wy > fminf (from.y, to.y) && wy < fmaxf (from.y, to.y)) {
					return true;
				}
			}
		} else if (to.y == from.y) {
			return true;
			/*int j;
			for (j = 0; j < 2; j ++) {
				float wy = j == 0 ? r.y : r.y + r.height;
				if (wy > fminf (from.y, to.y) && wy < fmaxf (from.y, to.y)) {
					return true;
				}
			}*/
		} else {
			//TODO fix weird bug with horizontal lines
			m = (float)((to.y - from.y) / (to.x - from.x)); //m = dx/dy
			b = (float)(from.y - m * from.x); //b = y - mx
			//y = mx + b, (y - b) / m = x;
			float isect;
			//x=1, x=-1
			int j;
			for (j = 0; j < 2; j ++) {
				float wx = (j == 0 ? r.x : r.x + r.width);
				isect = m * wx + b;
				if (isect >= r.y && isect <= r.y + r.height && isect > fminf (from.y, to.y) && isect < fmaxf (from.y, to.y)) {
					return true;
				}
			}
			//y=1, y=-1
			for (j = 0; j < 2; j ++) {
				if (m != 0) {
					float wy = j == 0 ? r.y : r.y + r.height;
					isect = (wy - b) / m;
					if (isect >= r.x && isect <= r.x + r.width && isect > fminf (from.x, to.x) && isect < fmaxf (from.x, to.x)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected int align (int val, int align) {
		val -= val % align;
		return val;
	}
	
	@Override
	public void draw() {
		super.draw();
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
						
						ArrayList <Rectangle> walls = GameCode.getLevel().collision;
						boolean hitWall = false;
						
						for (int k = 0; k < walls.size(); k++) {
							l.setHitbox(walls.get(k).x, walls.get(k).y, walls.get(k).width, walls.get(k).height);
							if (collide (pos, offs2, l.hitbox ())) {
								hitWall = true;
							}
						}
						
						
						//looked at
						if (!hitWall) {
						g.fillRect(i,j,4,4);
						}
						/*if (toPixel.isColliding(GameCode.getLevel().player)) {
							GameCode.getLevel().player.die();
						}*/
					}
				}
			}
			
			
			
		}
	}
	
	@Override
	public void frameEvent () {
		if (pauseCounter <= 0) {
			if (pathIter == null) {
				pathIter = path.listIterator ();
			}
			if (fromPt == null) {
				fromPt = pathIter.next ();
				toPt = pathIter.next ();
			}
			double xDiff = getX () - toPt.x;
			double yDiff = getY () - toPt.y;
			double dist = Math.hypot (xDiff, yDiff);
			ang = Math.atan2 (xDiff, yDiff);
			if (dist < 5) {
				if (aiType == 0) {
					if (direction == false) {
						fromPt = toPt;
						setX (fromPt.x);
						setY (fromPt.y);
						pauseCounter = fromPt.pauseTime;
						if (pathIter.hasNext ()) {
							toPt = pathIter.next ();
							//turning = true;
						} else {
							direction = true;
							pathIter.previous ();
							toPt = pathIter.previous ();
							//turning = true;
						}
					} else {
						fromPt = toPt;
						setX (fromPt.x);
						setY (fromPt.y);
						pauseCounter = fromPt.pauseTime;
						if (pathIter.hasPrevious ()) {
							toPt = pathIter.previous ();
							//turning = true;
						} else {
							direction = false;
							pathIter.next ();
							toPt = pathIter.next ();
							//turning = true;
						}
					}
				} else {
					fromPt = toPt;
					setX (fromPt.x);
					setY (fromPt.y);
					pauseCounter = fromPt.pauseTime;
					if (pathIter.hasNext ()) {
						toPt = pathIter.next ();
					} else {
						pathIter = path.listIterator ();
						toPt = pathIter.next ();
					}
				}
				System.out.println (fromPt + "," + toPt);
			}
			V2 offs = new V2 ((float)(toPt.x - getX ()), (float)(toPt.y - getY ()));
			offs.normalize ();
			float spd = 1;
			setX (getX () + faceVector.x * spd);
			setY (getY () + faceVector.y * spd);
			int dir = -1;
			double largest = -1;
			double[] dots = new double[4];
			dots[0] = faceVector.dot (upVec);
			dots[1] = faceVector.dot (leftVec);
			dots[2] = faceVector.dot (downVec);
			dots[3] = faceVector.dot (rightVec);
 			for (int i = 0; i < 4; i++) {
				if (dots[i] > largest) {
					largest = dots[i];
					dir = i;
				}
			}
 			if (getAnimationHandler ().getFrameTime () == 0) {
 				getAnimationHandler ().setFrameTime (100);
 			}
 			if (currDir != dir) {
 				switch (dir) {
 					case 0:
 						setSprite (upSprite);
 						this.getAnimationHandler ().setFlipHorizontal (false);
 						break;
 					case 1:
 						setSprite (leftSprite);
 						this.getAnimationHandler ().setFlipHorizontal (true);
 						break;
 					case 2:
 						setSprite (downSprite);
 						this.getAnimationHandler ().setFlipHorizontal (false);
 						break;
 					case 3:
 						setSprite (leftSprite);
 						this.getAnimationHandler ().setFlipHorizontal (false);
 						break;
 				}
 			}
			if (!turning && pauseCounter <= 0) {
				faceVector = offs;
			}
		} else {
			pauseCounter -= 1;
			if (pauseCounter < 0) {
				pauseCounter = 0;
			}
			getAnimationHandler ().setAnimationFrame (0);
			getAnimationHandler ().setFrameTime (0);
			/*double currAng = Math.atan2 (faceVector.x, faceVector.y);
			V2 offs = new V2 ((float)(toPt.x - getX ()), (float)(toPt.y - getY ()));
			double toAng = Math.atan2 (offs.x, offs.y);
			System.out.println (Math.abs (currAng - toAng));
			if (Math.abs (currAng - toAng) < Math.PI / 90) {
				turning = false;
			} else {
				ang += currAng - toAng < 0 ? (-Math.PI / 360) : (Math.PI / 360);
				faceVector = new V2 ((float)Math.cos (ang), (float)Math.sin (ang));
			}*/
		}
	}
	
	public class PathPoint {
		public double x;
		public double y;
		public double pauseTime;
		public boolean doTurn;
		
		public PathPoint (double x, double y, double pauseTime, boolean doTurn) {
			this.x = x;
			this.y = y;
			this.pauseTime = pauseTime;
			this.doTurn = doTurn;
		}
		
		@Override
		public String toString () {
			return "[" + x + "," + y + "]";
		}
	}

	public class LevelWall extends GameObject {
		public LevelWall () {
			
		}
	}
}

