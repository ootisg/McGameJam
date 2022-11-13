package gameObjects;

import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;

public class PhoneCodecTrigger extends TalkableNPC {
	
	public PhoneCodecTrigger () {
		this.conversation = new DistractionConversation ();
		declare ();
		this.fadeOut = true;
		GameCode.getLevel ().player.blackList ();
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent ();
		if (fadeIn) {
			ArrayList<GameObject> registerGuy = ObjectHandler.getObjectsByName ("RegisterGuy");
			if (registerGuy != null && registerGuy.size () != 0) {
				((RegisterGuy)registerGuy.get (0)).distract ();
			}
		}
	}

}
