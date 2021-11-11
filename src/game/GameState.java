package game;

import java.util.ArrayList;

import game.enums.larryState;


///////// CHANGE LOG //////////

/*
 * 11/10/2021 class created. 
 *
 */

////////////////////////////////

public class GameState {
	
	public static Ingress windowright;
	public static Ingress doorright;
	public static Ingress windowleft;
	public static Ingress doorleft;
	public static Ingress vent;
	public static int larryGotcha;
	public static larryState larryLocation;
	
	public GameState() {
		
		
		
	}
	
	public static void initialize() {
		//GameState.windowleft = new Ingress("windowright", SceneManager.getScene("ex"));
		GameState.windowright = new Ingress("windowright");
		//GameState.doorleft = new Ingress();
		//GameState.doorright = new Ingress();
		//GameState.vent = new Ingress();
		
		GameState.larryGotcha = 0;
	}
	
	
	
	public static void loser() {
		GameState.larryGotcha = 1;
		
		Game.instance.setScene(SceneManager.getScene("lose"));
	}
	
	
	public void reset() {
		windowright.blocked = false;
		doorright.blocked = false;
		windowleft.blocked = false;
		doorleft.blocked = false;
		vent.blocked = false;
		
		larryGotcha = 0;
		
	}

}
