package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class InsideMickeyDeesConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public InsideMickeyDeesConversation () {
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/InfiltrationCodec1.wav");
			t.changeText("~A500~~P20~~Cwhite~I HAVE INFILTRATED THE MICKEY DEES. WHAT'S NEXT?");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 2) {
				fadeOut = true;
				GameCode.getLevel ().player.whiteList ();
			}
			if (conversationState == 1) {
				t.pushString("~Cwhite~~P20~OKAY, YOUR NEXT STEP IS TO FIND ITEMS THAT WILL ALLOW YOU TO JAM THE MACHINE. LOOK AROUND FOR ANYTHING THAT WILL HELP.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/InfiltrationCodec2.wav");
				
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
