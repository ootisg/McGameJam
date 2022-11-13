package gameObjects;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;
import map.Room;

public class PipeOverlayer extends GameObject {

	private Rectangle bounds;
	
	private float opacityTimer = 1;
	private float opacitySpeed = 0.1f;
	private float minOpacity = 0.6f;
	
	public PipeOverlayer () {
		
	}
	@Override
	public void onDeclare () {
		Rectangle bounds = new Rectangle ((int)this.getX(),(int)this.getY(),528,272);
		this.bounds = bounds;
		this.setHitbox (bounds.x, bounds.y, bounds.width, bounds.height);
		setSprite (new Sprite ("resources/bg/pipeOverlay.png"));
		this.setRenderPriority (69);
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding ("Player") || this.isColliding("Player2D")) {
			opacityTimer -= opacitySpeed;
			if (opacityTimer < minOpacity) {
				opacityTimer = minOpacity;
			}
		} else {
			opacityTimer += opacitySpeed;
			if (opacityTimer > 1) {
				opacityTimer = 1;
			}
		}
	}
	
	@Override
	public void draw () {
		AlphaComposite composite = AlphaComposite.getInstance (AlphaComposite.SRC_OVER, opacityTimer);
		Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics ();
		g.setComposite (composite);
		g.drawImage (getSprite ().getFrame (0), (int)this.getX() - Room.getViewX(), (int)this.getY() - Room.getViewY(), null);
		
	}
	
}
