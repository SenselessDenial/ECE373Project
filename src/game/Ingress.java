package game;

import java.util.ArrayList;
import java.util.Collection;

import game.components.Sprite;
import game.enums.larryState;
import game.util.Logger;
import game.util.Vector2;

//describes the door, window, and vent class

public class Ingress extends Entity {
	//Fields
	public boolean blocked;
	public boolean larryPresent;
	public Sprite sprite;	
	public larryState location;
	
	//Constructor
	private Ingress() {
		super();
		blocked = false;
		this.location = larryState.AETHER; 
	}
	
	public Ingress(String name) {
		this();
		
		
		ArrayList<String> filenamesLarry = new ArrayList<String>();
		
		switch (name){
		
		case "windowright":											//to create a new kind of ingress, add to the switch case constructor.
																	//this constructor will load up textures into the entity appropriately, making animations and setting the default sprite
			Logger.Log("a window is born");							//animations have 2 flavors, larry or no larry
			
			filenamesLarry.add("rightwindowlarry1.png");
			
			setUpIngressSprites(new Vector2(80, 50), filenamesLarry, "rightwindowNOlarry1.png");
			
			this.location = larryState.WINDOW;
		
			break;
		case "windowleft":
			break;
		case "doorright":
			break;
		case "doorleft":
			break;
		case "vent":
			break;
		default:
			break;
		}
	}
	
	public void update() {
		super.update();
		
		
		if (GameState.larryLocation == this.location && this.location != larryState.AETHER) {
			this.larryPresent = true;
		}
		
		if (larryPresent)
			this.sprite.setCurrAnimation("larry");
	}
	
	public void updateSprite(Sprite newSprite) {
		this.sprite = newSprite;
	}
	
	public void changeState() {
		if (this.blocked) {
			this.blocked = false;
		}
		else {
			this.blocked = true;
		}
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	public void setUpIngressSprites(Vector2 position, ArrayList<String> filenamesLarry,  String...filenamesNoLarry) {
		Vector2 zero = new Vector2(0, 0);
		ArrayList<Texture> larry = new ArrayList<Texture>();
		ArrayList<Texture> nolarry = new ArrayList<Texture>();
		
		for(String filename : filenamesLarry) {
		larry.add( new Texture(filename));
		}
		
		for(String filename : filenamesNoLarry) {
		nolarry.add( new Texture(filename));
		}
		
		Animation larryAnime = new Animation(zero, 1, false, "larry", larry);
		Animation nolarryAnime = new Animation(zero, 1, false, "nolarry", nolarry);
		
		Sprite windowrightSprite = new Sprite(this, zero, "larry");
		windowrightSprite.addAnimation("nolarry", nolarryAnime);
		windowrightSprite.addAnimation("larry", larryAnime);
		
		windowrightSprite.setCurrAnimation("nolarry");
		
		this.sprite = windowrightSprite;
		add(sprite);
		this.position = position;
	}
}
