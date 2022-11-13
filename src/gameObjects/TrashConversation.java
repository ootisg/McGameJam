package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class TrashConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public TrashConversation () {
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/trashCodecIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			t.changeText("~P50~~Cwhite~HEY, WHAT ARE YOU DOING STANDING OUT HERE?");
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/TrashCodec1.wav");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 3) {
				fadeOut = true;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~TIME FOR ME TO GET DEVIOUS.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecIdle.txt"));
				conversationState = 3;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/TrashCodec3.wav");
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~I JUST TOOK THE TRASH OUT TO THE TRASHCAN. I REALLY HOPE THOSE DUMPSTER DIVERS DON'T TAKE IT OUT LIKE THEY USUALLY DO.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/TrashCodec2.wav");
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
