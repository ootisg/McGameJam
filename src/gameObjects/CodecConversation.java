package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;
import engine.Textbox;

public class CodecConversation extends GameObject{

	Converser converser1 = new Converser();
	Converser converser2 = new Converser();
	
	Textbox t = new Textbox ("~P50~");
	
	CodecBackground cb = new CodecBackground ();
	double opacity = 1.0;
	
	public CodecConversation () {
		converser1.setX(110);
		converser1.setY(128);
		
		converser2.setX(580);
		converser2.setY(128);
		converser2.getAnimationHandler().setFlipHorizontal(true);
		
		t.setX(110);
		t.setY(350);
		t.changeWidth(45);
		t.changeHeight(8);
		
		//t.changeBoxVisability();
		
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
		cb.draw();
		converser1.draw();
		converser2.draw();
		t.draw();
		
		if (opacity > 0) {
			opacity = opacity - .03;
			if (opacity < 0) {
				opacity = 0;
			}
			Graphics g = RenderLoop.wind.getBufferGraphics();
			g.setColor(new Color (0,0,0,(int)(opacity*255)));
			g.fillRect(0,0,960,540);
		}
	}
	
	@Override
	public void frameEvent () {
		converser1.frameEvent();
		converser2.frameEvent();
		t.frameEvent();
	}
	
	public class CodecBackground extends GameObject {
		public CodecBackground() {
			this.setSprite(new Sprite ("resources/sprites/codecBackground.png"));
		}
	}
	
	public class Converser extends GameObject {
		
		double size = 0;
		
		public static final double SIZE_INCREASE_SPEED = .1;
		
		public Sprite unscaled;
		
		public Converser () {
		
		}
		
		@Override
		public void frameEvent () {
			if (this.getSprite() != null && size != 1.0 && size != 0) {
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
