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
	CodecText text = new CodecText();
	CodecCenterpeice center = new CodecCenterpeice();
	
	double opacity = 1.0;
	
	boolean fadeIn = true;
	boolean fadeOut = false;
	
	public CodecConversation () {
		converser1.setX(110);
		converser1.setY(168);
		converser1.getAnimationHandler().setFrameTime(100);
		
		converser2.setX(580);
		converser2.setY(168);
		converser2.getAnimationHandler().setFlipHorizontal(true);
		converser2.getAnimationHandler().setFrameTime(100);
		
		text.setX(250);
		text.setY(40);
		
		t.setX(110);
		t.setY(410);
		t.changeWidth(45);
		t.changeHeight(8);
		
		center.setX(360);
		center.setY(168);
		
		
		//t.changeBoxVisability();
		
		
		this.setRenderPriority(100);
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
	
	public void cleanUp() {
		fadeOut = true;
	}
	
	@Override
	public void draw () {
		cb.draw();
		center.draw();
		converser1.draw();
		converser2.draw();
		t.draw();
		text.draw();
		
		if (fadeIn) {
			opacity = opacity - .03;
			if (opacity < 0) {
				opacity = 0;
				fadeIn = false;
			}
			Graphics g = RenderLoop.wind.getBufferGraphics();
			g.setColor(new Color (0,0,0,(int)(opacity*255)));
			g.fillRect(0,0,960,540);
		}
		
		if (fadeOut) {
			opacity = opacity + .03;
			if (opacity > 1) {
				opacity = 1;
				this.forget();
				this.reset();
			}
			Graphics g = RenderLoop.wind.getBufferGraphics();
			g.setColor(new Color (0,0,0,(int)(opacity*255)));
			g.fillRect(0,0,960,540);
		}
		
		
	}
	
	public void reset()
	{
		fadeIn = true;
		fadeOut = false;
		
		t = new Textbox ("~P50~");
		 
		converser1 = new Converser();
		converser2 = new Converser();
		
		converser1.setX(110);
		converser1.setY(168);
		converser1.getAnimationHandler().setFrameTime(100);
		
		converser2.setX(580);
		converser2.setY(168);
		converser2.getAnimationHandler().setFlipHorizontal(true);
		converser2.getAnimationHandler().setFrameTime(100);
		
		t.setX(110);
		t.setY(410);
		t.changeWidth(45);
		t.changeHeight(8);
		
		text = new CodecText();
		text.setX(250);
		text.setY(40);
		
		CodecCenterpeice center = new CodecCenterpeice();
		center.setX(300);
		center.setY(168);
		
	}
	@Override
	public void frameEvent () {
		converser1.frameEvent();
		converser2.frameEvent();
		t.frameEvent();
		text.frameEvent();
	}
	
	public class CodecBackground extends GameObject {
		public CodecBackground() {
			this.setSprite(new Sprite ("resources/sprites/codecBackground.png"));
		}
	}
	
	public class CodecCenterpeice extends GameObject {
		public CodecCenterpeice() {
			this.setSprite(new Sprite ("resources/sprites/codecCenterpiece.txt"));
			this.getAnimationHandler().setFrameTime(500);
		}
	}
	
	public class CodecText extends GameObject {
		
		int timer = 0;
	
		public CodecText() {
			this.setSprite(new Sprite ("resources/sprites/codec text.png"));
		}
		
		@Override
		public void frameEvent () {
			timer = timer + 1;
			if (timer == 30) {
				timer = 0;
				if (this.isVisable()) {
					this.hide();
				} else {
					this.show();
				}
			}
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
