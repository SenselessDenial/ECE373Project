package gameLogic;

import java.util.ArrayList;

import game.Game;
import gameLogic.Ingress;
import game.SceneManager;
import game.enums.larryState;
import java.util.HashMap;

///////// CHANGE LOG //////////

/*
 * 11/10/2021 class created. 
 *
 * 11/13/2021 
 *   added hashmap for solutions
 *   added nightSetup function (incomplete) that sets the puzzle for each night and resets the Ingresses
 */

////////////////////////////////

public class GameState {
	
	public static HashMap<Integer, ArrayList<ArrayList<Integer>>> solutions;
	public static Ingress windowright;
	public static Ingress doorright;
	public static Ingress windowleft;
	public static Ingress doorleft;
	public static Ingress vent;
	public static int larryGotcha;
	public static int nightNumber;
	public static larryState larryLocation;
	public static Nonogram puzzle;
	public static ArrayList<ArrayList<Integer>> solution;
	
	public GameState() {
		
		
		
	}
	
	public static void initialize() {
		//sets up night 1
		
		GameState.windowleft = new Ingress("windowleft");
		GameState.windowright = new Ingress("windowright");
		GameState.doorleft = new Ingress("doorleft");
		GameState.doorright = new Ingress("doorright");
		GameState.vent = new Ingress("vent");
		
		//TODO, integrate a solution for each night
		//solutions = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
		//getSolutions();
		
		// Delete after integrating multiple nights
		ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
		//PLACEHOLDER 5x5 PUZZLE (ALL 1's = SOLUTION)
		for (int  i = 0; i < 5; i++) {
			solution.add(new ArrayList<Integer>());
			for (int j = 0; j < 5; j++) {
				solution.get(i).add(1);
			}
		}
		
		puzzle = new Nonogram(5, solution);
		larryGotcha = 0;
	}
	
	// getSolutions is for when we have multiple solutions to update 
	/*private static void getSolutions() {
		//fills in the solutions hashmap with our set solutions 
		//contained in a text file in the content folder.
		
		ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
		//PLACEHOLDER 5x5 PUZZLE (ALL 1's = SOLUTION)
		for (int  i = 0; i < 5; i++) {
			solution.add(new ArrayList<Integer>());
			for (int j = 0; j < 5; j++) {
				solution.get(i).add(1);
			}
		}
	}*/
	
	public static void nightSetup() {
		GameState.reset();
		puzzle = new Nonogram(10, solutions.get(GameState.nightNumber));
		larryLocation = larryState.AETHER;
	}
	
	public static void winner() {
		Game.instance.setScene(SceneManager.getScene("win"));
		nightNumber += 1;
	}
	
	public static void loser() {
		larryGotcha = 1;
		
		Game.instance.setScene(SceneManager.getScene("lose"));
		reset();
	}
	
	
	public static void reset() {
		windowright.blocked = false;
		doorright.blocked = false;
		windowleft.blocked = false;
		doorleft.blocked = false;
		vent.blocked = false;
		
		larryGotcha = 0;
		
		
		puzzle.reset();
		
	}

}
