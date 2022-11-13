package gameObjects;

public class EnterMcdonaldsCutscene extends TalkableNPC {

	public EnterMcdonaldsCutscene () {
		this.conversation = new InsideMickeyDeesConversation();
		fadeOut = true;
	}
	
	@Override
	public void frameEvent () {
		if (talking) {
			this.forget();
			return;
		}
		
		
		
	}
	
}
