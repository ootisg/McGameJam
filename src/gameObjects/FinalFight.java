package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.Sprite;
import engine.Textbox;

public class FinalFight extends TalkableNPC {
	
	int timer = 0;
	
	jToJam j = new jToJam ();
	
	Textbox t = new Textbox ("~Cwhite~0");
	
	int jamCount = 0;
	
	public FinalFight () {
		this.setSprite(new Sprite ("resources/sprites/finalFight.txt"));
		this.getAnimationHandler().setFrameTime(20);
		conversation = new WinConversation();
		GameCode.getSoundPlayer().stopAll();
		GameCode.getSoundPlayer().play("resources/sound/ParodySong.wav",6F);
		j.declare(600, 100);
		t.declare(710,100);
		t.changeWidth(4);
		t.setRenderPriority(80);
	}
	
	@Override
	public void frameEvent () {
		timer = timer + 1;
		if (timer == 80) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/FinalFight1.wav");
		}
		
		if (timer == 200) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/FinalFight2.wav");
		}
		
		if (timer == 300) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/FinalFight3.wav");
		}
		
		if (timer == 400) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/FinalFight4.wav");
		}
		
		if (timer == 500) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/FinalFight5.wav");
		}
		
		if (timer == 650) {
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/explosion.wav");
			this.getAnimationHandler().setFrameTime(0);
			Explosion e = new Explosion (10);
			e.setCenterX(480);
			e.setCenterY(270);
			e.setRenderPriority(100);
			e.declare();
			this.setSprite(new Sprite("resources/sprites/machineDestroyed.png"));
			j.forget();
			t.forget();
		}
		
		if (timer == 700) {
			fadeOut = true;
		}
		if (talking) {
			this.forget();
		}
		
		
		if (GameCode.keyPressed('J', this)) {
			j.switchFrame();
			jamCount = jamCount + 1;
			t.changeText("~Cwhite~" + Integer.toString(jamCount));
			GameCode.getSoundPlayer().playSoundEffect(2F, "resources/sound/punch.wav");
		}
	}
	
	public class jToJam extends GameObject {
		boolean curFrame = false;
		public jToJam () {
			this.setSprite(new Sprite ("resources/sprites/jtojam.txt"));
			this.setRenderPriority(80);
		}
		
		public void switchFrame () {
			if(!curFrame) {
				this.getAnimationHandler().setAnimationFrame(1);
				curFrame = true;
			} else {
				this.getAnimationHandler().setAnimationFrame(0);
				curFrame = false;
			}
		}
		
	}

}
