package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class PeterFreindConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public PeterFreindConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/peterFriendCodecTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~A200~~P20~~Cwhite~WHATEVER YOU DO DONT TRY TO ORDER FROM THE DRIVE THROUGH");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 2) {
				GameCode.getSoundPlayer().stopAll();
				fadeOut = true;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~WORST MISTAKE OF MY LIFE");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/peterFriendCodecTalk.txt"));
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
