package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class TrashConversation2 extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public TrashConversation2 () {
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/trashCodecTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/TrashCodec4.wav");
			t.changeText("~P50~~Cwhite~FUCK YOU MAN!");
			
		}
		
		if (t.isEmpty()) {
			if (conversationState == 1) {
				GameCode.getSoundPlayer().stopAll();
				fadeOut = true;
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
