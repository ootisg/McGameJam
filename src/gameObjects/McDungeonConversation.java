package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class McDungeonConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public McDungeonConversation () {
	
	}
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/StaticTalk.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDungeonCodec1.wav");
			t.changeText("~A200~~Cwhite~THIS IS JIMMY JAM, MISSION LOG: DAY WHATEVER. I'VE BEEN STUCK IN THE MCDUNGEON FOR I DON'T EVEN KNOW HOW LONG.");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 5) {
				GameCode.getSoundPlayer().stopAll();
				fadeOut = true;
				GameCode.getSoundPlayer().play("resources/sound/McDungeon.wav", 6F);
				GameCode.drawRoom = true;
				conversationState = 6;
			}
			if (conversationState == 4) {
				t.pushString("~Cwhite~OH, I'VE GOT IT! I CAN CRAWL THROUGH THESE HOLES. THIS WILL GET ME OUT OF HERE!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/StaticTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDungeonCodec5.wav");
				conversationState = 5;
			}
			if (conversationState == 3) {
				 t.pushString("~Cwhite~THERE HAS TO BE SOME WAY OUT OF HERE...");
					t.advanceText();
					this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
					this.setConverser2Sprite(new Sprite ("resources/sprites/StaticTalk.txt")); //TODO
					GameCode.getSoundPlayer().stopAll();
					GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDungeonCodec4.wav");
					conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~I NEED TO RECOLLECT MY THOUGHTS...");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/StaticTalk.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDungeonCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P50~~Cwhite~HEY MISTER, YOU'VE ONLY BEEN HERE FOR 25 MINUTES.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDungeonCodec2.wav");
				
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
