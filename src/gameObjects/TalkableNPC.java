package gameObjects;

import engine.GameCode;
import engine.GameObject;
import engine.RenderLoop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class TalkableNPC extends GameObject{

	double opacity = 0;
	
	boolean fadeOut = false;
	
	boolean fadeIn = false;
	
	boolean talking = false;
	
	
	CodecConversation conversation;
	
	public TalkableNPC () {
		this.useSpriteHitbox();
		this.setRenderPriority(70);
	}
	
	@Override
	public void frameEvent () {
	
		if (this.isColliding(GameCode.getLevel().player) && GameCode.keyPressed(KeyEvent.VK_ENTER, this) && !talking && !fadeIn) {
			GameCode.getLevel().player.blackList();
			fadeOut = true;
		}
		
		if (!conversation.declared() && talking) {
			fadeIn = true;
		}
		
		
	}
	
	@Override
	public void draw () {
		super.draw();
		if (fadeIn) {
			opacity = opacity - .03;
			if (opacity < 0) {
				fadeIn = false;
				talking = false;
				GameCode.getLevel().player.whiteList();
				opacity = 0;
			}
		}
		
		if (fadeOut) {
			opacity = opacity + .03;
			if (opacity > 1) {
				fadeOut = false;
				conversation.declare();
				conversation.draw();
				talking = true;
				opacity = 1;
			}
				
		}
		
		if (fadeIn || fadeOut) {
			Graphics g = RenderLoop.wind.getBufferGraphics();
			g.setColor(new Color (0,0,0,(int)(opacity*255)));
			g.fillRect(0,0,960,540);
		}
	}
	
}
