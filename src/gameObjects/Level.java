package gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;

import engine.GameObject;

public class Level {
	
	public ArrayList<Rectangle> collision;
	
	public Player player;
	
	public void load () {
		
	}
	
	public void unload () {
		
	}
	
	public boolean isColliding (GameObject obj) {
		return false;
		}

}
