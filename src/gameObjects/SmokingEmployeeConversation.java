package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class SmokingEmployeeConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public SmokingEmployeeConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/smokingCodecTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~P50~~Cwhite~FUCK OFF, I'M ON BREAK.");
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/SmokerCodec1.wav");
		}
		
		if (t.isEmpty()) {
			
			if (conversationState == 4) {
				GameCode.getSoundPlayer().stopAll();
				fadeOut = true;
			}
			
			if (conversationState == 3) {
				t.pushString("~Cwhite~DAMN, SHE'S GOT SASS. MAYBE I SHOULD GO TELL HER BOSS.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecIdle.txt"));
				conversationState = 4;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/SmokerCodec4.wav");
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~NONE OF YOUR GODDAMN BUSINESS.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecTalk.txt"));
				conversationState = 3;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/SmokerCodec3.wav");
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~HOW LONG HAVE YOU BEEN ON BREAK?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecIdle.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/SmokerCodec2.wav");
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
