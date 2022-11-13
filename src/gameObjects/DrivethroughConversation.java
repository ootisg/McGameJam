package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class DrivethroughConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public DrivethroughConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/peterCodecTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~A300~~P30~~Cwhite~DING, FRIES ARE DONE. DING, FRIES ARE DONE. DING, FRIES ARE DONE. DING, FRIES ARE DONE.");
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/ding_fries_are_done.wav");
			
		}
		
		if (t.isEmpty()) {
			
			if (conversationState == 4) {
				fadeOut = true;
			}
		
			if (conversationState == 3) {
				t.pushString("~P32~~Cwhite~WOULD YOU LIKE AN APPLE PIE WITH THAT?~A1000~  WOULD YOU LIKE AN APPLE PIE WITH THAT?");
				t.advanceText();
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/apple pie.wav");
				conversationState = 4;
			}
			
			if (conversationState == 2) {
				t.pushString("~Cwhite~I WORK AT MCDEES MAKING FLAME BOILED BIG MACS.  I WEAR PAPER HATS.");
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/I work at mcdees.wav");
				t.advanceText();
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~I GOTTA RUN, I GOTTA RUN, I GOTTA RUN, I GOTTA RUN.");
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/I gotta run.wav");
				t.advanceText();
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
