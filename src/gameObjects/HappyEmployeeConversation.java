package gameObjects;

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
			t.changeText("~P50~~Cwhite~WOW TODAYS A GREAT DAY I BET THERES NOTHING YOU COULD POSSIBLE DO TO RUIN THIS DAY");
		}
		
		if (t.isEmpty()) {
			
			if (conversationState == 4) {
				fadeOut = true;
			}
			
			if (conversationState == 3) {
				t.pushString("~Cwhite~DANG");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/happyGuyCodecIdle.txt"));
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~YEAH NOT A SINGLE THING");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/happyGuyCodecTalk.txt"));
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~REALLY?  NOT A SINGLE THING");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/happyGuyCodecIdle.txt"));
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
