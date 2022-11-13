package gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import engine.GameObject;

public class Level {
	
	public ArrayList<Rectangle> collision;
	public ArrayList<Rectangle> lightBlockers;
	
	public Player player;
	
	public void load () {
		
	}
	
	public void unload () {
		
	}
	
	public boolean isColliding (GameObject obj) {
		Iterator<Rectangle> checktangles = collision.iterator ();
		while (checktangles.hasNext ()) {
			if (obj.hitbox ().intersects (checktangles.next ())) {
				return true;
			}
		}
		return false;
	}

}
