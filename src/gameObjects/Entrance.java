package gameObjects;

import java.util.ArrayList;

import engine.GameCode;
import engine.GameObject;
import engine.ObjectHandler;
import engine.Sprite;

public class Entrance extends GameObject {
	
	public Entrance (Sprite spr) {
		setSprite (spr);
	}

	@Override
	public void frameEvent () {
		if (this.isColliding(GameCode.getLevel().player)) {
			ArrayList<ArrayList<GameObject>> allObs = ObjectHandler.getChildrenByName("GameObject");
			
			for (int i = 0; i < allObs.size(); i++) {
				for (int j = 0; j <allObs.get(i).size(); j++) {
					allObs.get(i).get(j).forget();
				}
				
			}
			Level inside = new InsideMcDonalds ();
			GameCode.currentLevel = inside;
			inside.load ();

		}
	}
	
}
