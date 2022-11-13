package gameObjects;

import engine.GameCode;

public class PlayMcDungeionCutscene extends TalkableNPC {

	public PlayMcDungeionCutscene () {
		this.conversation = new McDungeonConversation();
		fadeOut = true;
		GameCode.drawRoom = false;
		opacity = .8;
	}
	
	@Override
	public void frameEvent () {
		if (talking) {
			this.forget();
			return;
		}
	}
}
