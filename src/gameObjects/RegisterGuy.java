package gameObjects;

import engine.GameCode;
import engine.Sprite;
import engine.V2;

public class RegisterGuy extends McdonaldsEmployee {

	public static final Sprite noticed = new Sprite ("resources/sprites/noticed.png");
	
	boolean alert = false;
	boolean doneAlert = false;
	boolean returning = false;
	double targetAng = 0;
	V2 alertVec;
	
	int timer = 0;
	
	double currAng = Math.PI / 2;
	
	public RegisterGuy () {
		this.faceVector = new V2 (0, 1);
		setSprite (downSprite);
	}
	
	@Override
	public void frameEvent () {
		double diffX = GameCode.getLevel ().player.getX () - getX ();
		double diffY = GameCode.getLevel ().player.getY () - getY ();
		if (!alert && !doneAlert && Math.hypot (diffX, diffY) < 320) {
			alert = true;
			targetAng = Math.atan2 (diffX, diffY);
			alertVec = new V2 ((float)diffX, (float)diffY);
			alertVec.normalize ();
		}
		if (alert) {
			faceVector = new V2 ((float)Math.cos (currAng), (float)Math.sin (currAng));
			V2 diffVec = new V2 ((float)diffX, (float)diffY);
			diffVec.normalize ();
			if (alertVec.dot (faceVector) > .95) {
				if (timer <= 0) {
					timer = 20;
				} else {
					timer--;
					if (timer <= 0) {
						alert = false;
						doneAlert = true;
						timer = 0;
					}
				}
			} else {
				currAng += Math.PI / 60;
			}
		}
		if (doneAlert) {
			if (Math.hypot (diffX, diffY) > 30 && timer <= 0) {
				timer = 60;
			}
			if (timer > 0) {
				timer -= 1;
				if (timer == 0) {
					doneAlert = false;
					returning = true;
				}
			}
		}
		if (returning) {
			currAng -= Math.PI / 120;
			faceVector = new V2 ((float)Math.cos (currAng), (float)Math.sin (currAng));
			if (faceVector.dot (new V2 (0, 1)) > .99) {
				returning = false;
			}
		}
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
	}
	
	@Override
	public void draw () {
		super.draw ();
		if (alert) {
			noticed.draw ((int)getX (), (int)getY () - 32);
		}
	}
	
}
