package game;

import game.enums.larryState;
import game.util.Logger;

public class Larry extends Entity {
	private int aggro;
	public larryState location;
	int rollPeriod = 1;
	
	public Larry() {
		super();
		this.location = larryState.AETHER;
		this.aggro = 5;
		
		Logger.Log("LARRY IS BORN");
	}
	
	public Larry(int aggro) {
		this.aggro = aggro;
		this.location = larryState.AETHER;
	}
	
	public void setAggro(int aggro) {
		this.aggro = aggro;
	}
	
	public int getAggro() {
		return this.aggro;
	}
	
	public larryState getLarryLocation() {
		return this.location;
	}
	
	public void setLarryLocation(larryState location) {
		this.location = location;
	}
	
	public void update(){
		
		GameState.larryLocation = this.location;			//UPDATES THE GAMESTATE board with larry's location, allowing other entities to see it easier
		
		int randNum = 0;							//Larry's location and movement is entirely random
		int threshold = 0;						//Movement is determined by his aggro setting. If the random number is under the threshold, Larry will do the more agressive action
	
		int newRoom = 70;	
		
		java.util.Random rand = new java.util.Random();	
		if((Game.instance.rawTimeElapsed) > 10*rollPeriod) {
		Logger.Log(Game.instance.rawTimeElapsed);
			randNum = rand.nextInt(100);							//Larry's location and movement is entirely random
			threshold = this.aggro * 10;							//Movement is determined by his aggro setting. If the random number is under the threshold, Larry will do the more agressive action
			rollPeriod++;
			newRoom = rand.nextInt(59);								//rolls which room Larry will go to if he is at the window
		
		}
		
		
		
		
		switch (this.location){
		
		case AETHER:												//if larry is in starting position
			
			//Logger.Log("Larry is in the Aether");
			
			if(randNum < threshold) {
				Logger.Log("yippe");
				this.location = larryState.WINDOW;					//if the randNum says to move, he will move to the Window. The window sprite will be updated, a musical sting will play
			}														//else, nothing happens
			break;
		case DOORRIGHT:								
			
			//Logger.Log("Larry at the right door");
			
			if(GameState.doorright.blocked == true) {					//if he is at the right door, and the door is blocked, he will only move away when randnum is greater than the threshold
				if (randNum > threshold) {							//this way, Larry will be most aggressive at the door if his aggro is higher
					this.location = larryState.AETHER;
				}
			}
			else if (randNum < threshold) {							//if the door is not held, and the randNum hits under the threshold, Larry will ask the levl to go to the game over screen
				//GameState.larryGotcha = 1; 
			}				
			break;
		case DOORLEFT:
			
			//Logger.Log("Larry is at the left door");
			
			if(GameState.doorleft.blocked == true) {					//same logic as the door		
				if (randNum > threshold) {							
					this.location = larryState.AETHER;
				}
			}
			else if (randNum < threshold) {						
				GameState.loser();
			}		
			break;
		case VENT:
			
			//Logger.Log("Larry is at the vent");
			
			if(GameState.vent.blocked == true) {					//same logic as the door		
				if (randNum > threshold) {							
					this.location = larryState.AETHER;
				}
			}
			else if (randNum < threshold) {						
				GameState.loser(); 
			}
			break;
		case WINDOW:														//Larry is not hostile at the window. He will move to each ingress randomly
			
			//Logger.Log("Larry is in the window");
			
			if(randNum < threshold && GameState.windowright.blocked == false) {		//the player can lower the chances of Larry attacking if they leave the window open					
				if(newRoom < 14) {	
					this.location = larryState.WINDOW;						//each time Larry moves there will be a musical sting
				}
				else if(newRoom < 29) {
					this.location = larryState.WINDOW;
				}
				else if(newRoom < 44) {
					this.location = larryState.WINDOW;
				}
				else {
					this.location = larryState.WINDOW;						//its possible he will give up for a while
				}
			}
			else if (randNum < threshold && GameState.windowright.blocked == true) {
				if(newRoom < 19) {	
					this.location = larryState.VENT;
				}
				else if(newRoom < 39) {
					this.location = larryState.DOORLEFT;
				}
				else {
					this.location = larryState.DOORRIGHT;
				}
			}
			break;
		default:
			this.location = larryState.AETHER;
			break;
			
		}
	}
	

}
