package gameObjects;

import java.util.ArrayList;

import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class SmokingGuy extends TalkableNPC {

	boolean mad = false;
	boolean talkedTo = false;
	
	public SmokingGuy () {
		this.setSprite(new Sprite ("resources/sprites/smokingEmployee.txt"));
		this.conversation = new SmokingEmployeeConversation();
		this.setRenderPriority(0);
		this.getAnimationHandler().setFlipHorizontal(true);
		this.getAnimationHandler().setFrameTime(100);
	}
	
	@Override
	public void frameEvent () {
		if (!mad) {
			super.frameEvent();
		}
		if (talking) {
			talkedTo = true;
		}
	}
	
	public boolean haveBeenTalkedToo() {
		return talkedTo;
	}
	public void makeMad () {
		mad = true;
	}
	
	public boolean isMad () {
		return mad;
	}
}
