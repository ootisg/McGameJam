package gameObjects;

import engine.Sprite;

public class CodecConversation1 extends CodecConversation{
	
	int conversationState = 0;
	int conversationTimer = 0;

	
	public CodecConversation1 () {
		
	}
	
	@Override
	public void frameEvent () {
		super.frameEvent();
		
		if (conversationState == 0) {
			conversationState = 1;
			this.changeConverser1Charictar(new Sprite ("resources/sprites/wendys_employee.png"));
			this.changeConverser2Charictar(new Sprite ("resources/sprites/wendys_employee.png"));
			t.changeText("~P50~~Cwhite~TEST CODEC MESSAGE");
		}
		
		if (t.isEmpty()) {
			if (conversationState == 1) {
				t.pushString("~Cwhite~OH SHIT SOMETHING ABOUT MY ASS CHEEKS");
				t.advanceText();
				this.changeConverser2Charictar(new Sprite ("resources/sprites/wendys_employee.png"));
				conversationState = 2;
			}
		}
	}

}
