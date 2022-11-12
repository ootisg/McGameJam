package gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import engine.GameObject;
import engine.Sprite;

public class OutsideMcDonalds extends Level {

	private Backgrounder bg;
	private Overlayer ol;
	private Player player;
	
	private ArrayList<Rectangle> collision;
	
	public OutsideMcDonalds () {
		collision = new ArrayList<Rectangle> ();
	}
	
	@Override
	public void load () {
		
		bg = new Backgrounder (new Sprite ("resources/bg/mcbackground.png"));
		ol = new Overlayer (new Sprite ("resources/bg/mcoverlay.png"), new Rectangle (111, 62, 673, 380));
		player = new Player ();
		
		bg.declare ();
		ol.declare ();
		player.declare ();
		
		collision.add (new Rectangle (207, 82, 494, 291));
		collision.add (new Rectangle (0, 191, 69, 128));
		
		new Tras ().declare (24, 274);
		new Tras ().declare (24, 252);
		new Tras ().declare (24, 230);
		new Tras ().declare (24, 208);
		
		new RockSource (new Rectangle (289, 373, 170, 58)).declare ();
		
	}
	
	@Override
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
