package gameObjects;

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
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~I JUST TOOK OUT THE TRASH TO THE TRASHBIN. I HOPE THOSE DUMPSTER DIVERS DON'T TAKE THE TRASH OUT OF THE BIN.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
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
