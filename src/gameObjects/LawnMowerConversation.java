package gameObjects;

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
			t.changeText("~P50~~Cwhite~WOW YOUR IN GOOD MOOD ARENT YOU?");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 2) {
				fadeOut = true;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~YEAH I JUST GOT MY LAWNMOWER BACK FROM THE SHOP!  I RAN IT INTO A ROCK AND THE WHOLE THING BROKE BEEN WAITING TO MOW THIS GRASS FOR A FEW WEEKS NOW");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/lawnmowerCodecTalk.txt"));
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
