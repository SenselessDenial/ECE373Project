package gameLogic;

import java.util.ArrayList;

import game.CorkboardTest;
import game.Entity;
import game.Scene;
import game.Texture;
import game.components.Sprite;
import game.enums.Buttons;
import game.enums.Keys;
import game.util.Input;
import game.util.Logger;
import game.util.Vector2;


public class Clue extends Entity{
	
	private Sprite sprite;
	private ArrayList<Clue> currentConnections;
	public ArrayList<Clue> correctConnections;
	private boolean dragged;
	private boolean pinned;
	private CorkboardTest corkboardScene;
	private String name;

	
	////Constructors////
	
	public Clue() {
		this.dragged = false;
		this.sprite = new Sprite();
		this.currentConnections = new ArrayList<Clue>();
		this.correctConnections = new ArrayList<Clue>();
		
	}
	
	public Clue(Scene scene, ArrayList<Clue> clues) {
		this();
		this.correctConnections = clues;
		this.scene = scene;
		this.corkboardScene = (CorkboardTest) scene;
		
	}
	
	public Clue(Scene scene, String filename) {
		this();
		this.sprite = new Sprite(new Texture(filename));
		add(this.sprite);
		this.setHitbox(sprite.getCurrAnimation().get(0).getWidth(), sprite.getCurrAnimation().get(0).getHeight());
		this.scene = scene;
		this.corkboardScene = (CorkboardTest) scene;
	}
	
	public Clue(Scene scene, String filename, ArrayList<Clue> clues) {
		this(scene, filename);
		this.correctConnections = clues;
	}
	
	public Clue(Scene scene, String filename, Vector2 position) {
		this(scene, filename);
		this.position = position;
	}
	
	
	//////////////////////
	
	public void addSprite(Sprite sprite) {
		this.sprite = sprite;
		add(this.sprite);
		this.setHitbox(sprite.getCurrAnimation().get(0).getWidth(), sprite.getCurrAnimation().get(0).getHeight());
		
	}
	
	public void checkSolution() {
		int correctCount = 0;
		
		for(Clue clue : this.correctConnections) {
			if(this.currentConnections.contains(clue))
				correctCount++;
		}
		
		if(correctCount == this.correctConnections.size() ) {
			Logger.Log("Correct Solution Found!");
		}
	}
	
	public void update() {
		super.update();
		this.checkSolution();
		///////CHANGELOG
		/*
		 * 11/11/2021 - click and drag implemented. Unstable, if the mouse is released at the wrong time it does not stop holding the clue
		 * BUG - if two clues are moved to overlap eachother they snap into the same hitbox and cannot be seperated
		 * 		- potential fixes: have sprites postion move with the mouse offset from the sprite
		 * 							have a function that moves a clues position to front when it is clicked on and only allow 
		 * 							clues on the topmost layer to be moved
		 * 
		 * 11/12/2021 - solution checking implemented. As expected, because the connection establishing is unstable due to a click being
		 * 				longer than a refresh, the solution tracking is also unstable. I dont want to implement a full flip flop to arbitrarily
		 * 				delay another check for drag but it might be necessary?
		 */
		
		if(this.dragged == true) {												//movement of the clue depends on if its being dragged, pinned, or free falling
			this.position = Input.getMousePos().subtract(new Vector2(10,10)) ;	//If its dragged, the clue will move with the cursor
			
			if(Input.check(Keys.Space)) {										//while its dragged, if the space bar is pressed the clue will be pinned in place
				this.dragged = false;
				this.pinned = true;
			}
		}

		if (this.collide(Input.getMousePos()) && Input.check(Buttons.LeftButton)) {	//if the user clicks and holds on an image with the left button, it will be dragged
			Logger.Log(this.dragged);
				this.dragged = true;
				this.pinned = false;												//the clue will no longer be pinned if the user moves it again
		}	
		else {
			if(this.dragged == false && (this.position.y < (1080 - 80) / 4) && this.pinned == false )
				this.position = this.position.add(new Vector2(0,5));				//while not pinned, the clue will fall to the bottom of the corkboard
		}
		
		if (this.collide(Input.getMousePos()) && Input.released(Buttons.LeftButton)) {
			this.dragged = false;													//when the left button is released, the image is no longer dragged
		}
		
		////////////PUZZLE SOLVING SECTION////////////////
		
		if (this.collide(Input.getMousePos()) && Input.check(Buttons.RightButton)) {
			if(this.corkboardScene.selectedClue == null)											//if no clue is selected to be connected
				this.corkboardScene.selectedClue = this;
			else if(this.corkboardScene.selectedClue != this) {
				if(!this.currentConnections.contains(this.corkboardScene.selectedClue)) {
					this.currentConnections.add(this.corkboardScene.selectedClue);
					this.corkboardScene.selectedClue.currentConnections.add(this);
					Logger.Log("clue is connected to clue");
				}
				else {
					this.currentConnections.remove(this.corkboardScene.selectedClue);
					this.corkboardScene.selectedClue.currentConnections.remove(this);
					Logger.Log("clue is removed from clue");
				}
				
			}
		
				
		}
		
		
	}
	

}
