package gameObjects;

import engine.GameCode;
import engine.ObjectHandler;

public class EnterMcdonaldsCutscene extends TalkableNPC {

	public EnterMcdonaldsCutscene () {
		this.conversation = new InsideMickeyDeesConversation();
		fadeOut = true;
	}
	
	@Override
	public void frameEvent () {
		GameCode.getLevel ().player.blackList ();
		if (talking) {
			this.forget();
			return;
		}
		
		
		
	}
	
}
