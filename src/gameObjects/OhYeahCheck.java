package gameObjects;

import engine.GameCode;

public class OhYeahCheck extends TalkableNPC{

	public OhYeahCheck () {
		conversation = new KoolAidConversation();
	}
	
	@Override
	public void frameEvent () {
		if (SmokingGuy.mad && LawnMowerGuy.isBlownUp() && TrashGuy.isMad() && !GameCode.getLevel().player.isBlackListed()) {
			GameCode.getLevel().player.blackList();
			fadeOut = true;
		}
	}
}
