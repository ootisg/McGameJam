package gameObjects;

import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;
import map.Room;

public class WindysConversation extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public WindysConversation () {
	
		this.setRenderPriority(1000000);
	}
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/daveCodecTalk.txt"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
			converser2.getAnimationHandler().setFlipHorizontal(false);
			GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys1.wav");
			t.changeText("~A200~~Cwhite~IM READY FOR PAYBACK");
			Room.setView(0,0);	
		}
		
		if (t.isEmpty()) {
			if (conversationState == 7) {
				fadeOut = true;
				FinalFight f = new FinalFight ();
				f.declare();
			}
			if (conversationState == 6) {
				GameCode.drawRoom = false;
				
				ArrayList<ArrayList<GameObject>> allObs = ObjectHandler.getChildrenByName("GameObject");
				
				for (int i = 0; i < allObs.size(); i++) {
					for (int j = 0; j <allObs.get(i).size(); j++) {
						if (!allObs.get(i).get(j).equals(this)) {
							allObs.get(i).get(j).forget();
						}
					}
					
				}
				
				t.pushString("~Cwhite~YOU CAN'T FIRE ME! I ONLY WORK FOR THE SIDE OF JUSTICE! YOUR ICE CREAM MACHINE IS NEXT!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpIdle.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys7.wav");
				conversationState = 7;
			}
			if (conversationState == 5) {
				t.pushString("~Cwhite~YOU BROKE INTO THE MICKEY DEES DURING YOUR SHIFT!? THAT'S IT, YOU'RE FIRED!");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys6.wav");
				conversationState = 6;
			}
			if (conversationState == 4) {
				t.pushString("~P40~~Cwhite~SNEAKING INTO THE MICKEY DEES AND JAMMING THE ICE CREAM MACHINE UNDER YOUR LIEGE, DUH."); //TODO: Reread the line
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpIdle.txt")); //TODO
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys5.wav");
				conversationState = 5;
			}
			if (conversationState == 3) {
				 t.pushString("~P40~~Cwhite~YOU WERE SUPPOSED TO BE WORKING YOUR SHIFT 4 HOURS AGO! WHERE WERE YOU?");
					t.advanceText();
					this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
					this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt")); //TODO
					GameCode.getSoundPlayer().stopAll();
					GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys4.wav");
					conversationState = 4;
			}
			if (conversationState == 2) {
				t.pushString("~P30~~Cwhite~ROTTING IN THAT CELL YOU PUT ME IN, YOU MONSTER.");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecTalk.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpIdle.txt"));
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys3.wav");
				conversationState = 3;
			}
			if (conversationState == 1) {
				t.pushString("~P50~~Cwhite~JIMMY JAM, WHERE THE HELL HAVE YOU BEEN!?");
				t.advanceText();
				this.setConverser1Sprite(new Sprite ("resources/sprites/daveCodecIdle.txt"));
				this.setConverser2Sprite(new Sprite ("resources/sprites/WendysEmpTalk.txt"));
				conversationState = 2;
				GameCode.getSoundPlayer().stopAll();
				GameCode.getSoundPlayer().playSoundEffect(6F, "resources/sound/Windys2.wav");
				
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
