package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class KoolAidConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public KoolAidConversation () {
	
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec1.wav");
			t.changeText("~A600~~P50~~Cwhite~OKAY, I WAS ABLE TO INCONVENIENCE 3 MICKEY DEES EMPLOYEES. WHAT NOW?");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 7) {
				fadeOut = true;
			}
			if (conversationState == 6) {
				t.pushString("~P30~~Cwhite~I HAVE SECURED SIGHT ON THE ENTRANCE ON THE NORTH SIDE OF THE BUILDING. THANKS KOOL-AID MAN!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/koolaidmanIdle.txt"));
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec7.wav");
				conversationState = 7;
			}
			if (conversationState == 5) {
				t.pushString("~Cwhite~OH YEAAAH!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/koolaidmanTalk.txt"));
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec6.wav");
				conversationState = 6;
			}
			if (conversationState == 4) {
				t.pushString("~Cwhite~OH NO!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/lawnmowerCodecTalk.txt"));
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec5.wav");
				conversationState = 5;
			}
			
			if (conversationState == 3) {
				t.pushString("~Cwhite~OH NO!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/smokingCodecTalk.txt"));
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec4.wav");
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~OH NO!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~HOLD ON A SECOND...");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/OhYeahCodec2.wav");
				
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
