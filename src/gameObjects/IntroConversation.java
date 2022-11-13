package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class IntroConversation extends CodecConversation {
	int conversationState = 0;
	int conversationTimer = 0;

	
	public IntroConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecIdle.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/wendysEmpTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~A300~~Cwhite~OK JIMMY, HERE'S THE SITREP.");
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/IntroCodec1.wav");
			
		}
		
		if (t.isEmpty()) {
			if (conversationState == 6) {
				fadeOut = true;
			}
			if (conversationState == 5) {
				t.pushString("~Cwhite~GOOD LUCK ON YOUR MISSION!");
				t.advanceText();
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/IntroCodec6.wav");
				conversationState = 6;
			}
			
			if (conversationState == 4) {
				t.pushString("~Cwhite~MAKE SURE YOU DON'T GET CAUGHT BY ANYONE INSIDE THE MICKEY DEES.");
				t.advanceText();
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/IntroCodec5.wav");
				conversationState = 5;
			}
		
			if (conversationState == 3) {
				t.pushString("~P35~~Cwhite~YOUR OBJECTIVE WILL BE TO INFILTRATE THE MICKEY DEES AND JAM THE ICE CREAM MACHINE.");
				t.advanceText();
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/IntroCodec4.wav");
				conversationState = 4;
			}
			
			if (conversationState == 2) {
				t.pushString("~P50~~Cwhite~THIS OPERATION IS ENTIRELY CONFIDENTIAL.");
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/IntroCodec3.wav");
				t.advanceText();
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P25~~Cwhite~SINCE YOU AND I ARE THE ONLY WINDY'S EMPLOYEES LEFT AFTER ALL OF OUR CUSTOMERS AND COWORKERS STARTED WORKING FOR MICKEY DEES...");
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/IntroCodec2.wav");
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

