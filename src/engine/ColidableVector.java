package engine;


import java.awt.Point;
import java.awt.Rectangle;


public class ColidableVector {
	public Point startPoint;
	public Point endPoint;
	double slope = 0;
	
	public ColidableVector () {
		
	}
	public ColidableVector (Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		slope = ((double)(endPoint.y-startPoint.y))/((double)(endPoint.x-startPoint.x));
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		if (this.endPoint != null) {
			slope = ((double)(endPoint.y-startPoint.y))/((double)(endPoint.x-startPoint.x));
		}
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		if (this.startPoint != null) {
			slope = ((double)(endPoint.y-startPoint.y))/((double)(endPoint.x-startPoint.x));
		}
	}

	
	public boolean isColliding (GameObject obj) {
		
		//deals with vertical lines because they are annoying
		if (Double.isInfinite(slope)) {
			
			if (startPoint.x >= obj.hitbox().x && startPoint.getX() <= obj.hitbox().x + obj.hitbox().getWidth()) {
				if (obj.hitbox().contains(startPoint) || obj.hitbox().contains(endPoint) || (startPoint.y >= obj.getY() && endPoint.y <= obj.getY()) || (startPoint.y <= obj.getY() && endPoint.y >= obj.getY())  )
				return true; //vertical line inside hitbox
			} else {
				return false; //vertical line outside of hitbox
			}
		}
		
		
		Rectangle hitbox = obj.hitbox();
		Rectangle bigBox;
		if (startPoint.x < endPoint.x) {
			if (startPoint.y < endPoint.y) {
				bigBox = new Rectangle (startPoint.x,startPoint.y,endPoint.x  - startPoint.x,endPoint.y - startPoint.y);
			} else {
				bigBox = new Rectangle (startPoint.x, endPoint.y, endPoint.x - startPoint.x, startPoint.y - endPoint.y);
			}
		} else {
			if (startPoint.y < endPoint.y) {
				bigBox = new Rectangle (endPoint.x,startPoint.y,startPoint.x - endPoint.x,endPoint.y - startPoint.y);
			} else {
				bigBox = new Rectangle (endPoint.x, endPoint.y, startPoint.x - endPoint.x, startPoint.y - endPoint.y);
			}
		}
		
		/*
		 * Jeffreys line collision algorithm (yeah I invented it im coming for your ass smithsonian)
		 * break the hitbox into 4 points 
		 * extend the line until it is at the same x as the first point
		 * check if the point is actually on the line segment or if it is just on the full line
		 * if it is actually on the segment it is a real point
		 * collect the other three points
		 * if any two "real points" are on differing sides of the original point (the point gathered from the hitbox) on the y axis then there is a collison
		 * (what we are actually checking in that last step is if there are any real points inbetween hitbox points but we can implement it this way becasue of the way real points are generated on hitboxes)
		 * otherwise there is no colision 
		 * 
		 * 
		 * note DOES NOT work with vertical lines I had to put a bit of code at the top to do those 
		 */
		if (bigBox.intersects(hitbox) || slope == 0) {
			Point pt1 = new Point (hitbox.x,hitbox.y);
			Point pt2 = new Point (hitbox.x+hitbox.width,hitbox.y);
			Point pt3 = new Point (hitbox.x,hitbox.y + hitbox.height);
			Point pt4 = new Point (hitbox.x + hitbox.width, hitbox.y + hitbox.height);
			
			boolean p1Real = false;
			boolean p2Real = false;
			boolean p3Real = false;
			boolean p4Real = false;
			
			
			//extends the line out to the x position of the first point and records the y position of that point
			Point linePoint1 = new Point (pt1.x, (int)(startPoint.y + ((pt1.x - startPoint.x) * slope)));
			
			//checks if the point stored in linePoint1 is actually on the line segment or if it is just on the line
			if (((linePoint1.x >= startPoint.x && linePoint1.x <= endPoint.x) || (linePoint1.x <= startPoint.x && linePoint1.x >= endPoint.x) )) {
				p1Real = true;
			}

			//extends the line out to the x position of the second point and records the y position of that point
			Point linePoint2 = new Point (pt2.x, (int)(startPoint.y + ((pt2.x - startPoint.x) * slope)));
			
			//checks if the point stored in linePoint2 is actually on the line segment or if it is just on the line
			if (((linePoint2.x >= startPoint.x && linePoint2.x <= endPoint.x) || (linePoint2.x <= startPoint.x && linePoint2.x >= endPoint.x) )) {
				p2Real = true;
			}
			
			//extends the line out to the x position of the third point and records the y position of that point
			Point linePoint3 = new Point (pt3.x, (int)(startPoint.y + ((pt3.x - startPoint.x) * slope)));
			
			//checks if the point stored in linePoint3 is actually on the line segment or if it is just on the line
			if (((linePoint3.x >= startPoint.x && linePoint3.x <= endPoint.x) || (linePoint3.x <= startPoint.x && linePoint3.x >= endPoint.x) )) {
				p3Real = true;
			}
			
			//extends the line out to the x position of the forth point and records the y position of that point
			Point linePoint4 = new Point (pt4.x, (int)(startPoint.y + ((pt4.x - startPoint.x) * slope)));
			
			//checks if the point stored in linePoint4 is actually on the line segment or if it is just on the line
			if (((linePoint4.x >= startPoint.x && linePoint4.x <= endPoint.x) || (linePoint4.x <= startPoint.x && linePoint4.x >= endPoint.x) )) {
				p4Real = true;
			}
			
			boolean above = false; //are any of the real points above their orignials
			boolean below = false; //are any of the real points below their originals
			
			//p1 status
			if (p1Real) {
				if (linePoint1.y > pt1.y) {
					above = true;
				} else {
					if (linePoint1.y != pt1.y) {
						below = true;
					} else {
						return true; //colides exactly with the hitbox point
					}
				}
			}
			
			//p2 status
			if (p2Real) {
				if (linePoint2.y > pt2.y) {
					above = true;
				} else {
					if (linePoint2.y != pt2.y) {
						below = true;
					} else {
						return true; //colides exactly with the hitbox point
					}
				}
			}
			
			//p3 status
			if (p3Real) {
				if (linePoint3.y > pt3.y) {
					above = true;
				} else {
					if (linePoint3.y != pt3.y) {
						below = true;
					} else {
						return true; //colides exactly with the hitbox point
					}
				}
			}
			
			//p4 status
			if (p4Real) {
				if (linePoint4.y > pt4.y) {
					above = true;
				} else {
					if (linePoint4.y != pt4.y) {
						below = true;
					} else {
						return true; //colides exactly with the hitbox point
					}
				}
			}
			
			//there is a real point at the x coordinate of one of the points that is above one point but below another therfore this point colides with a hitbox side
			if (above && below) {
				return true;
			}
			
			//this will only happen if there is no real points there is no collision in this case
			if (!above && !below) {
				return false;
			}
			return false; //all real points are either above or below the hitbox points therefore the line itself is either above or below the hitbox
		} 
		return false; 
	}
	
	
	public boolean isColliding (ColidableVector v) {
		/*
		 * Jeffreys line/line collision algorithm (yeah I made another one still coming for you smithsonian)
		 * put both lines in point slope form (y1-y2)=m(x1-x2)
		 * try to find intersection point where y1 = y1 and x1 = x1 in both equations
		 * use that fact to substitute the second line in for x1 then solve for x1
		 * calculate the x that we used earlier
		 * check if that x y coordinate combo is on both line segements
		 * if it is then there is a collision at the point we calcuated (the only point where that particualr colision can happen)
		 * 
		 * otherwise there is no colision 
		 * 
		 * 
		 * note currently a bit buggy with vertical and horizontal slopes
		 * 
		 */
		//if ((v.startPoint.x > this.startPoint.x && v.startPoint.x < this.endPoint.x) || (v.startPoint.x < this.startPoint.x && v.startPoint.x > this.endPoint.x) || (v.endPoint.x > this.startPoint.x && v.endPoint.x < this.endPoint.x) || (v.endPoint.x < this.startPoint.x && v.endPoint.x > this.endPoint.x)) {
			
			double y1; //solving for the point where this is the same in both equations
			int y21 = startPoint.y; // using the startpoint as the other point for this equation\
			int y22 = v.startPoint.y; //the startpoint for the other equation
			
			int x21 = startPoint.x;
			int x22 = v.startPoint.x;
			
			double m1 = slope;
			double m2 = v.slope;
			
			//original equation y1 -y21 = m1((y1-y22)/m2) + x22 -x21) where everything is constant except y1
			double y1RhsCof =  -1*(m1/m2) + 1;
			double RHSGarbage = -(y22 * m1/m2) + x22*m1 -x21*m1 + y21;
			
			y1 = RHSGarbage/y1RhsCof; //at last the fabeled y1 (this is the point the lines are gonna colide on if they are btw)
		
			if (m2 == 0) {
				y1 = y22;
			}
			if (m1 == 0) {
				y1 = y21;
			}
			
			double x1 = ((y1 - y21)/m1) + x21;
			//TODO make vertical lines work better
//			if (Double.isInfinite(m2)) {
//				x1 = x22;
//				y1 = ((x1 - x21)*m1) - y21;
//			}
//			
//			if (Double.isInfinite(m1)) {
//				x1 = x21;
//				y1 = ((x1 - x22)*m2) - y22;
//			}
//			
//			if (Double.isInfinite(m1) && Double.isInfinite(m2)) {
//				if (x21 != x22) {
//					return false;
//				} else {
//					return true; //TODO deal with edge cases with two vertical lines
//				}
//			}
			
			if (m1 == 0) {
				if (m2 != 0) {
					x1 = ((y1 - y22)/m2) + x22;
				} else {
					if (y21 != y22) {
						return false;
					} else {
						return true; //TODO deal with edge case with two horizontal lines not near each other
					}
				}
			}
			
			//if both of these are true the point they will colide on is on the first line
			boolean betweenYs1 = ((startPoint.y >= y1 && endPoint.y <= y1) || (startPoint.y <= y1 && endPoint.y >= y1)); //is the point between the ys of the first line
			boolean betweenXs1 = ((startPoint.x >= x1 && endPoint.x <= x1) || (startPoint.x <= x1 && endPoint.x >= x1)); //is the point between the xs of the first line
			
			//if both of these are true the point they will colide on is on the second line
			boolean betweenYs2 = ((v.startPoint.y >= y1 && v.endPoint.y <= y1) || (v.startPoint.y <= y1 && v.endPoint.y >= y1)); //is the point between the ys of the second line
			boolean betweenXs2 = ((v.startPoint.x >= x1 && v.endPoint.x <= x1) || (v.startPoint.x <= x1 && v.endPoint.x >= x1)); //is the point between the xs of the second line
		
			if (betweenYs1 && betweenXs1 && betweenYs2 && betweenXs2) {
				
				return true; //the point that the lines are gonna colide on is on both lines therfore the lines colided
			}
			return false;
		//} 
		//return false;
	}
	
	/**
	 * @return the x coordinate of a point on the line that is at a specific y
	 * returns -1 if point is not on line segment
	 * returns -2 if its a horizontal line 
	 */
	public double getXof(double corispondingY) {
		//System.out.println(slope + ", " + startPoint.x + ", " + startPoint.y + ", " + endPoint.x + ", " + endPoint.y);
		if (slope == 0) {
			return -2;
		}
		if (Double.isInfinite(slope)) {
			//check if its on the line
			if ((corispondingY >= startPoint.y && corispondingY <= endPoint.y) || (corispondingY <= startPoint.y && corispondingY >= endPoint.y)) {
				return startPoint.x;
			} else {
				return -1;
			}
			
		}
		
		double x = (startPoint.x + (corispondingY - startPoint.y)/slope); //calculates x pos using slope
		
		
		
		if ((x >= startPoint.x && x <= endPoint.x) || (x <= startPoint.x && x >= endPoint.x)) {
			//System.out.println("start " + startPoint.x + " end " + endPoint.x + " x " + x + " corsisponding y " + corispondingY + " slope " + slope);

			return x;
		}
		return -1;
	}
	
	public double getSlope() {
		return slope;
	}
	
	@Override
	public String toString() {
		return ("Start: "  +startPoint + "End: " +endPoint);
	}
}
