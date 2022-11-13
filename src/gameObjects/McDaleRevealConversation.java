package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class McDaleRevealConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public McDaleRevealConversation () {
	
	}
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/trashCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/ChipHappy1-sheet.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec1.wav");
			t.changeText("~A200~~P20~~Cwhite~SIR, I FINALLY FOUND HIM.");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 7) {
				fadeOut = true;
			}
			if (conversationState == 6) {
				t.pushString("~P75~~Cwhite~AND TO YOU, CHIP MCDALE.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/ChipHappy1.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec7.wav");
				conversationState = 7;
			}
			if (conversationState == 5) {
				t.pushString("~P50~~Cwhite~I AM ABSOLUTELY SURE. BEST OF LUCK TO YOU.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/trashCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/ChipHappy6-sheet.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec6.wav");
				conversationState = 6;
			}
			if (conversationState == 4) {
				t.pushString("~P17~~Cwhite~ARE YOU SURE HE'S THE ONE YOU WANT?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/ChipHappy5-sheet.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec5.wav");
				conversationState = 5;
			}
			if (conversationState == 3) {
				t.pushString("~Cwhite~THE PREPARATIONS ARE READY: MOVE ON TO STAGE TWO. WE FOUND OUR REPLACEMENT FOR CHARLES.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/trashCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/ChipHappy4-sheet.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec4.wav");
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~~P40~THAT'S RIGHT! HE TOOK THE BAIT, WE KNEW HE COULDN'T RESIST INFILTRATING OUR RESTAURANT.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/ChipHappy3-sheet.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P50~~Cwhite~EXCELLENT.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/trashCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/ChipHappy2-sheet.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDaleRevealCodec2.wav");
				
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
