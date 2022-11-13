package engine;

import java.util.ArrayList;

import gameObjects.Backgrounder;
import gameObjects.Level;
import gameObjects.OutsideMcDonalds;
import gameObjects.Overlayer;
import gameObjects.Player;
import gameObjects.CodecConversation1;
import gameObjects.Dave;
import gameObjects.InsideMcDonalds;
import gameObjects.LawnMowerGuy;
import map.Room;



public class GameCode {
	
	static int veiwX;
	static int veiwY;
	
	static Level currentLevel;
	

	static ArrayList <Asker> askers = new ArrayList <Asker> ();
	
	static SoundPlayer s;


	public static void testBitch () {
		
		
	}
	
	public static void beforeGameLogic () {
		
	}

	public static void afterGameLogic () {
		
	}

	public static void init () {
<<<<<<< HEAD
		//Level outside = new OutsideMcDonalds ();
		//currentLevel = outside;
		//outside.load ();
=======
		Level outside = new InsideMcDonalds ();
		currentLevel = outside;
		outside.load ();
>>>>>>> b15d33b3c87a87217fb4133f06e1092a1919d443
		ObjectHandler.addSearchPackage("gameObjects");
		
		Room.loadRoom ("resources/mapdata/mcDungeon.tmj");
	
		
		
		//Level outside = new OutsideMcDonalds ();
		//currentLevel = outside;
		//outside.load ();
		s = new SoundPlayer();
		
		
		//CodecConversation1 test = new CodecConversation1();
		//test.declare();
		
//		McdonaldsEmployee m = new McdonaldsEmployee ();
//		m.declare(000,100);
		
//		Dave d = new Dave ();
//		d.declare(100, 200);

	}
		
	public static void resetGame () {
	
		
	}
	
	public static SoundPlayer getSoundPlayer () {
		return s;
	}
	
	public static void gameLoopFunc () {
		
		ObjectHandler.callAll();
		
		 for (int i = 0; i < askers.size(); i++) {
		    	for (int j = 0; j < askers.get(i).getKeys().size(); j++) {
		    		if (!GameLoop.getInputImage().keyDown(askers.get(i).heldKeys.get(j))) {
		    			askers.get(i).getKeys().remove(j);
		    			j--;
		    		}
		    	}
		    }
		
	}
	
	  public static void removeAsker(GameObject asker) {
		  Asker toAsk = getAsker(asker);
		  askers.remove(toAsk);
	  }
	  
	  public static boolean keyCheck(int keyCode, GameObject whosAsking) {
			boolean returnValue = GameLoop.getInputImage().keyDown(keyCode);
		    
			Asker asking = getAsker(whosAsking);
			
			if (returnValue) {
				
				asking.getKeys().add(keyCode);
			}
			
			
			return returnValue;
		  }
		
		public static Asker getAsker (GameObject whosAsking) {
		
			Asker asking = null;
			
			boolean foundAsker = false;
			
			for (int i = 0; i < askers.size(); i++) {
				if (askers.get(i).isAsker(whosAsking)) {
					asking = askers.get(i);
					foundAsker = true;
					break;
				}
			}
			
			if (!foundAsker) {
				askers.add(new Asker(whosAsking));
				asking = askers.get(askers.size() -1);
			}
			
			return asking;
		}
		  
		  public static boolean keyPressed(int keyCode, GameObject whosAsking) {
			boolean returnValue = GameLoop.getInputImage().keyPressed(keyCode);
			
			Asker asking = getAsker(whosAsking);
			
			if (returnValue && !asking.getKeys().contains(keyCode)) {
				asking.getKeys().add(keyCode);
				return returnValue;
			} else {
				return false;
			}
			
			
		  }
		  
		  public static boolean keyReleased(int keyCode) {
		    return GameLoop.getInputImage().keyReleased(keyCode);
		  }
	
	
	public static void renderFunc () {
		Room.render();
		ObjectHandler.renderAll();
	}
	
	public static void beforeRender() {
		
	}
	
	public static void afterRender()
	{
		
	}
		
	public static int getResolutionX() {
		return RenderLoop.wind.getResolution()[0];
	}
	public static int getResolutionY() {
		return RenderLoop.wind.getResolution()[1];
	}
	
	public static int getViewX() {
		return veiwX;
	}



	public static void setViewX(int newVeiwX) {
		veiwX = newVeiwX;
	}



	public static int getViewY() {
		return veiwY;
	}



	public static void setViewY(int newVeiwY) {
		veiwY = newVeiwY;
	}
	
	public static Level getLevel () {
		return currentLevel;
	}



	
}
