package gameObjects;

import engine.Sprite;

public class KoolAidConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public KoolAidConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/smokingCodecTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~P50~~Cwhite~GO AWAY IM ON BREAK");
		}
		
		if (t.isEmpty()) {
			
			if (conversationState == 4) {
				fadeOut = true;
			}
			
			if (conversationState == 3) {
				t.pushString("~Cwhite~MAYBE I SHOULD TELL HIS BOSS?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecIdle.txt"));
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~NONE OF YOUR BUISNES");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecTalk.txt"));
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~HOW LONG HAVE YOU BEEN ON BREAK");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecIdle.txt"));
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
