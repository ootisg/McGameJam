package gameObjects;

import engine.GameCode;
import engine.V2;

public class RegisterGuy extends McdonaldsEmployee {

	boolean alert = false;
	boolean doneAlert = false;
	double targetAng = 0;
	
	double currAng = Math.PI / 2;
	
	public RegisterGuy () {
		this.faceVector = new V2 (0, 1);
		setSprite (downSprite);
	}
	
	@Override
	public void frameEvent () {
		double diffX = getX () - GameCode.getLevel ().player.getX ();
		double diffY = getY () - GameCode.getLevel ().player.getY ();
		if (!alert && Math.hypot (diffX, diffY) < 200) {
			alert = true;
			targetAng = Math.atan2 (diffX, diffY);
			System.out.println ("ALERT!");
		}
		if (alert) {
			currAng += Math.PI / 180;
			faceVector = new V2 ((float)Math.cos (currAng), (float)Math.sin (currAng));
			if (Math.abs (currAng - targetAng) < Math.PI / 30) {
				System.out.println ("EPIC");
			}
		}
		if (doneAlert) {
			
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
	
}
