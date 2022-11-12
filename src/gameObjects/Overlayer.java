package gameObjects;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;

public class Overlayer extends GameObject {

	private Rectangle bounds;
	
	private float opacityTimer = 1;
	private float opacitySpeed = 0.1f;
	private float minOpacity = 0.3f;
	
	public Overlayer (Sprite spr, Rectangle bounds) {
		this.bounds = bounds;
		this.setHitboxAttributes (bounds.x, bounds.y, bounds.width, bounds.height);
		setSprite (spr);
		this.setRenderPriority (69);
	}
	
	@Override
	public void frameEvent () {
		if (this.isColliding ("Player")) {
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
		g.drawImage (getSprite ().getFrame (0), 0, 0, null);
	}
	
}
