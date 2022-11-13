package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class DistractionConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public DistractionConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/DistractionCodec1.wav");
			t.changeText("~A200~~P20~~Cwhite~EMERGENCY, CALLING FOR BACKUP: I NEED A DISTRACTION AT THE FRONT DESK!");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 4) {
				fadeOut = true;
			}
			if (conversationState == 3) {
				t.pushString("~A500~~P17~~Cwhite~CAN I GET ~HUHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH~N" + "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH~N" + "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH~N" + "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH~N" + "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH~N");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/DistractionCodec4.wav");
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~HELLO SIR, WHAT WOULD YOU LIKE TO ORDER TODAY?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/DistractionCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~OK JIMMY JAM, YOU'VE GOT IT!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/DistractionCodec2.wav");
				
			}
		}
	}
	
	@Override
	public void reset () {
		super.reset();
		conversationState = 0;
		conversationTimer = 0;
	}

}
