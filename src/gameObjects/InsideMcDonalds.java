package gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;

import engine.Sprite;

public class InsideMcDonalds extends Level {
	
	public static final Sprite coolaidBreakIn = new Sprite ("resources/bg/coolaidman_breakin.png");
	
	private Backgrounder bg;
	
	public InsideMcDonalds () {
		
		collision = new ArrayList<Rectangle> ();
		
	}
	
	@Override
	public void load () {
		
		bg = new Backgrounder (new Sprite ("resources/bg/mcinside.png"));
		player = new Player ();
		
		bg.declare ();
		player.declare ();
		
		//McEmployees
		FryerGuy empA = new FryerGuy ();
		new LineGuy ().declare (190, 202);
		new CoolEmployee ().declare (295, 292);
		new DriveThruGuy ().declare (770, 28);
		new RegisterGuy ().declare (784, 465);
		empA.declare (53, 183);
		
		collision.add (new Rectangle (0, 55, 84, 120));
		collision.add (new Rectangle (0, 180, 49, 188));
		collision.add (new Rectangle (216, 0, 64, 369));
		collision.add (new Rectangle (0, 494, 960, 46));
		collision.add (new Rectangle (543, 0, 80, 62));
		collision.add (new Rectangle (544, 129, 79, 162));
		collision.add (new Rectangle (626, 291, 334, 75));
		collision.add (new Rectangle (544, 291, 79, 75)); //Ice cream machine
		
	}
	
}
