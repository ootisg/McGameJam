package gameObjects;

import engine.GameCode;

public class OhYeahCheck extends TalkableNPC{
	
	public OhYeahCheck () {
		conversation = new KoolAidConversation();
	}
	
	@Override
	public void frameEvent () {
		if (!fadeOut && !talking&& SmokingGuy.mad && LawnMowerGuy.isBlownUp() && TrashGuy.isMad() && !GameCode.getLevel().player.isBlackListed() && OutsideMcDonalds.entr.isBlackListed()) {
			GameCode.getLevel().player.blackList();
			fadeOut = true;
		}
	}

}
