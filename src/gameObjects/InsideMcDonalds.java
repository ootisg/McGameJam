package gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class InsideMcDonalds extends Level {
	
	public static final Sprite coolaidBreakIn = new Sprite ("resources/bg/coolaidman_breakin.png");
	
	EnterMcdonaldsCutscene e = new EnterMcdonaldsCutscene();
	
	private Backgrounder bg;
	public StuffMeter stuffMeter;
	public OverlayGuide ol;
	
	private PotatoSack potato;
	private Mop mop;
	private Jam jam;
	private IceCreamGrabbable iceCream;
	private IceCreamMachine toJam;
	private McPhone mcPhone;
	private Jesus jesus;
	
	public InsideMcDonalds () {
		
		collision = new ArrayList<Rectangle> ();
		lightBlockers = new ArrayList<Rectangle> ();
		
		e.declare();
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
		potato = new PotatoSack ();
		potato.declare (8, 8);
		mop = new Mop ();
		mop.declare (571, 46);
		jam = new Jam ();
		jam.declare (927, 505);
		iceCream = new IceCreamGrabbable ();
		iceCream.declare (639, 299);
		toJam = new IceCreamMachine ();
		mcPhone = new McPhone ();
		jesus = new Jesus ();
		
		collision.add (new Rectangle (0, 55, 84, 120)); //Grill
		collision.add (new Rectangle (0, 180, 49, 188)); //Fryer
		collision.add (new Rectangle (216, 0, 64, 369)); //Line
		collision.add (new Rectangle (0, 494, 960, 4)); //Counter
		collision.add (new Rectangle (543, 0, 80, 62)); //Small thing
		collision.add (new Rectangle (544, 129, 79, 162)); //Wall A
		collision.add (new Rectangle (626, 291, 334, 75)); //Wall B
		collision.add (new Rectangle (544, 291, 79, 75)); //Ice cream machine
		
		lightBlockers.add (new Rectangle (0, 55, 84, 120)); //Grill
		lightBlockers.add (new Rectangle (0, 494, 960, 4)); //Counter
		lightBlockers.add (new Rectangle (543, 0, 80, 62)); //Small thing
		lightBlockers.add (new Rectangle (544, 129, 79, 162)); //Wall A
		lightBlockers.add (new Rectangle (626, 291, 334, 75)); //Wall B
		lightBlockers.add (new Rectangle (544, 291, 79, 75)); //Ice cream machine
		
		stuffMeter = new StuffMeter ();
		ol = new OverlayGuide ();
		
	}
	
	@Override
	public void unload () {
		ArrayList<ArrayList<GameObject>> objs = ObjectHandler.getChildrenByName ("McdonaldsEmployee");
		for (int i = 0; i < objs.size (); i++) {
			ArrayList<GameObject> curr = objs.get (i);
			for (int j = 0; j < curr.size (); j++) {
				curr.get (j).forget ();
			}
		}
		ArrayList<GameObject> distractionGuy = ObjectHandler.getObjectsByName ("DistractionGuy");
		if (distractionGuy != null && distractionGuy.size () != 0) {
			distractionGuy.get (0).forget ();
		}
		player.forget ();
		bg.forget ();
		collision = new ArrayList<Rectangle> ();
		lightBlockers = new ArrayList<Rectangle> ();
		stuffMeter.forget ();
		
		potato.forget ();
		jam.forget ();
		mop.forget ();
		iceCream.forget ();
		toJam.forget ();
		mcPhone.forget ();
		jesus.forget ();
		
	}
	
}
