package gameObjects;

public class DrivethroughLoudspeaker extends TalkableNPC {

	public DrivethroughLoudspeaker () {
		this.setX(676);
		this.setY(2);
		this.setHitboxAttributes(0,0, 20, 37);
		conversation = new DrivethroughConversation();
	}
	
	
}
