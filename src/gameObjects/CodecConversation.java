package gameObjects;

import engine.GameObject;
import engine.Sprite;
import engine.Textbox;

public class CodecConversation extends GameObject{

	Converser converser1 = new Converser();
	Converser converser2 = new Converser();
	
	Textbox t = new Textbox ("");
	
	public CodecConversation () {
		converser1.setX(100);
		converser1.setY(100);
		
		converser2.setX(600);
		converser2.setY(100);
		
		t.setX(100);
		t.setY(500);
	}

	public void setConverser1Sprite (Sprite s) {
		converser1.setSprite(s);
	}
	
	public void setConverser2Sprite (Sprite s) {
		converser2.setSprite(s);
	}
	
	public void changeConverser1Charictar (Sprite s) {
		converser1.setSpriteAppear(s);
	}
	
	public void changeConverser2Charictar (Sprite s) {
		converser2.setSpriteAppear(s);
	}
	
	
	@Override
	public void draw () {
		converser1.draw();
		converser2.draw();
		t.draw();
	}
	
	@Override
	public void frameEvent () {
		converser1.frameEvent();
		converser2.frameEvent();
	}
	
	
	public class Converser extends GameObject {
		
		double size = 0;
		
		public static final double SIZE_INCREASE_SPEED = .1;
		
		public Sprite unscaled;
		
		public Converser () {
		
		}
		
		@Override
		public void frameEvent () {
			if (this.getSprite() != null && size != 1.0) {
				this.setSprite(unscaled);
				Sprite.scale(this.getSprite(), (int)(this.getSprite().getWidth() * size), (int)(this.getSprite().getHeight() * size));
				size = size + SIZE_INCREASE_SPEED;
				if (size > 1.0) {
					size = 1.0;
				}
			}
		}
		
		public void setSpriteAppear (Sprite newSprite) {
			size = 0;
			this.setSprite(newSprite);
		}
		
		@Override
		public void setSprite (Sprite newSprite) {
			Sprite toUse = new Sprite (newSprite);
			unscaled = newSprite;
			super.setSprite(toUse);
		}
		
	}
	
}
