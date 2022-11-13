package gameObjects;

import engine.GameCode;
import engine.Sprite;

public class McDoubleCrossConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public McDoubleCrossConversation () {
	
	}
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec1.wav");
			t.changeText("~A200~~P20~~Cwhite~MISSION ACCOMPLISHED: I WAS ABLE TO JAM THE MICKEY DEES ICE CREAM MACHINE.");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 9) {
				fadeOut = true;
			}
			if (conversationState == 8) {
				t.pushString("~P10~~Cwhite~~H" + "NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO~N" + "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO~N" + "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO~N" + "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO~N");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec7.wav");
				conversationState = 9;
			}
			if (conversationState == 7) {
				t.pushString("~P50~~Cwhite~ICE CREAM MACHINES, SON.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec9.wav");
				conversationState = 8;
			}
			if (conversationState == 6) {
				t.pushString("~P50~~Cwhite~WHY WOULD YOU DO THIS!? WHAT WAS YOUR MOTIVE?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpIdle.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec8.wav");
				conversationState = 7;
			}
			if (conversationState == 5) {
				t.pushString("~P50~~Cwhite~THAT'S RIGHT JIMMY, THIS IS A MCDOUBLECROSS!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec6.wav");
				conversationState = 6;
			}
			if (conversationState == 4) {
				t.pushString("~P17~~Cwhite~MISSION CONTROL, I'VE BEEN HAD!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecIdle.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec5.wav");
				conversationState = 5;
			}
			if (conversationState == 3) {
				t.pushString("~P17~~Cwhite~~H" + "FREEZE!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/trashCodecTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec4.wav");
				conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~Cwhite~WAIT, YOU DON'T MEAN...");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P50~~Cwhite~GOOD JOB, NOW THAT'S ONE LESS THREAT. NOW, TO GET RID OF ONE LAST VARIABLE IN THE EQUATION...");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/McDoubleCrossCodec2.wav");
				
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
