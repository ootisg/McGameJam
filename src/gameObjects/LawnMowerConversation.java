package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class LawnMowerConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public LawnMowerConversation () {
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/lawnmowerCodecIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~A700~~P40~~Cwhite~WOW, YOU'RE IN A GOOD MOOD, AREN'T YOU?");
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/MowerCodec1.wav");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 5) {
				fadeOut = true;
			}
			if (conversationState == 4) {
				t.pushString("~A300~~Cwhite~ALRIGHT, IT'S TIME TO JAM THAT MOWER.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/lawnmowerCodecIdle.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/MowerCodec5.wav");
				conversationState = 5;
			}
			if (conversationState == 3) {
				t.pushString("~P60~~Cwhite~I'VE BEEN WAITING TO MOW THIS GRASS FOR A FEW WEEKS NOW.");
				t.advanceText();
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/MowerCodec4.wav");
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~P60~~Cwhite~I RAN IT INTO A ROCK AND THE WHOLE THING COMBUSTED!");
				t.advanceText();
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/MowerCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P60~~Cwhite~YEAH, I JUST GOT MY LAWNMOWER BACK FROM THE SHOP!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/lawnmowerCodecTalk.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/MowerCodec2.wav");
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
