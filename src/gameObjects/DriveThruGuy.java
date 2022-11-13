package gameObjects;

public class DriveThruGuy extends McdonaldsEmployee {
	
	public DriveThruGuy () {
		path.add (new PathPoint (770, 28, 200, false));
		path.add (new PathPoint (770, 250, 200, false));
	}

}
