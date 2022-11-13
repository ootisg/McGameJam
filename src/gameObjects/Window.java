package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Textbox;

import java.awt.event.KeyEvent;

public class Window extends GameObject{

	Textbox t = new Textbox("~Cwhite~YOU ALERTED THE MANAGER OF THE SMOKERS MISBEHAVIOR");
	
	public Window () {
		this.setHitboxAttributes(0,0,54, 5);
		this.adjustHitboxBorders();
	}
	
	
	@Override
	public void frameEvent () {
		if (this.isColliding(GameCode.getLevel().player) && !t.declared() && GameCode.keyPressed(KeyEvent.VK_ENTER, this) && SmokingGuy.haveBeenTalkedToo()) {
			t.declare(100, 200);
			SmokingGuy.makeMad();
			GameCode.getLevel().player.blackList();
		}
		
		if (t.declared() && GameCode.keyPressed(KeyEvent.VK_ENTER, this)) {
			t.forget();
			GameCode.getLevel().player.whiteList();
		}
	}
	
	
}
