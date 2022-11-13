package gameObjects;

import gameObjects.McdonaldsEmployee.PathPoint;

public class FryerGuy extends McdonaldsEmployee {
	
	public FryerGuy () {
		path.add (new PathPoint (53, 183, 60, false));
		path.add (new PathPoint (53, 338, 60, false));
		fov = 20;
	}

}
