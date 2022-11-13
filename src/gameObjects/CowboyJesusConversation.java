package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class CowboyJesusConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public CowboyJesusConversation () {
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/CowboyJesus.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~P50~~Cwhite~ALRIGHT SONNY, I'LL HELP YOU OUT. TIME TO SADDLE UP.");
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/CowboyJesusCodec1.wav");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 2) {
				fadeOut = true;
				GameCode.getLevel ().player.whiteList ();
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~WHO THE HELL ARE YOU?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/StaticTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/CowboyJesusCodec2.wav");
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
