package gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import engine.GameObject;
import engine.Sprite;

public class OutsideMcDonalds extends Level {

	private Backgrounder bg;
	private Overlayer ol;
	
	LawnMowerGuy guy = new LawnMowerGuy();
	HappyEmployee guy2 = new HappyEmployee();
	DrivethroughLoudspeaker speaker = new DrivethroughLoudspeaker();
	TrashGuy trash = new TrashGuy();
	SmokingGuy smoke = new SmokingGuy ();
	Window wind = new Window ();
	OhYeahCheck check = new OhYeahCheck ();
	
	public static final Sprite coolaidBreakIn = new Sprite ("resources/bg/coolaidman_breakin.png");
	
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
		check.declare();
		
		Entrance entr = new Entrance (coolaidBreakIn);
		entr.declare (254, 45);
		entr.setHitboxAttributes (87, 37);
		
		guy.declare(690, 505);
		guy2.declare(500,375);
		speaker.declare();
		trash.declare(180, 220);
		smoke.declare(400, 20);
		wind.declare(560, 76);
		
		collision.add (new Rectangle (207, 82, 494, 291));
		collision.add (new Rectangle (0, 191, 69, 128));
		//collision.add (new Rectangle (676, 2, 20, 37));
		//254 45 87 37
		
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
