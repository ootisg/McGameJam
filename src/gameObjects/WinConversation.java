package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class WinConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public WinConversation () {
	
	}
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Win1.wav");
			t.changeText("~A200~~P20~~Cwhite~NOOO MY ICE CREAM MACHINE! WHAT HAVE YOU DONE!? WE'RE BOTH FUCKED NOW!");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 3) {
				fadeOut = true;
			}

			if (conversationState == 2) {
				t.pushString("~Cwhite~I HAVE A FAMILY OF FOUR, YOU DICK! HOW CAN I SUPPLY FOR THEM NOW!?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Win3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P50~~Cwhite~~A500~.~A500~.~A500~.~A500~.AND THAT'S WHY THEY CALL ME JIMMY JAM: DELIVERER OF JUSTICE! I'M OUTTA HERE!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Win2.wav");
				
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
