package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class HappyEmployeeConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public HappyEmployeeConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/happyGuyCodecTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/HappyCodec1.wav");
			t.changeText("~A750~~P50~~Cwhite~TODAY'S A GREAT DAY!~NI BET THERE'S NOTHING YOU COULD POSSIBLY DO TO RUIN THIS DAY!");
		}
		
		if (t.isEmpty()) {
			
			if (conversationState == 4) {
				GameCode.getSoundPlayer().stopAll();
				fadeOut = true;
			}
			
			if (conversationState == 3) {
				t.pushString("~Cwhite~~A225~.~A225~.~A225~.~A225~DANG.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/happyGuyCodecIdle.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/HappyCodec4.wav");
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~YEAH, NOT A SINGLE THING.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/happyGuyCodecTalk.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/HappyCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~REALLY? NOT A SINGLE THING?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/happyGuyCodecIdle.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/HappyCodec2.wav");
				conversationState = 2;
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
