package game.components;

import java.awt.Color;
import java.util.HashMap;

import game.Animation;
import game.Entity;
import game.Game;
import game.util.Logger;
import game.util.Vector2;

// Stores animations for an entity.
public class Sprite extends Component{

	/// Fields
	private HashMap<String, Animation> animations;
	private Animation currAnimation;
	private Vector2 offset;
	private int currFrame;
	private float currTime;
	public String name;
	
	/// Constructors
	public Sprite(Entity entity, Vector2 offset, String name) {
		animations = new HashMap<String, Animation>();
		this.entity = entity;
		currFrame = 0;
		currTime = 0;
		currAnimation = null;
		this.offset = offset;
		this.name = name;
	}
	
	/// Methods
	public void addAnimation(String name, Animation animation) {
		animations.put(name, animation);
		if (currAnimation == null) {
			currAnimation = animations.get(name);
		}
	}
	
	public void setCurrAnimation(String name) {
		if (animations.containsKey(name)) {
			currAnimation = animations.get(name);
		} else {
			Logger.Log("WARNING: Animation not found.");
			currAnimation = null;
		}
	}
	
	public void setFrame(int index) {
		if (index < 0) {
			currFrame = 0;
		}
		else if (index >= currAnimation.size()) {
			index = currAnimation.size() - 1;
		} else {
			currFrame = index;
		}
		currTime = 0;
	}
	
	private Vector2 getRenderPos() {
		return new Vector2(this.entity.getX() - (offset.x + currAnimation.getOffset().x), 
						   this.entity.getY() - (offset.y + currAnimation.getOffset().y));
	}
	
	@Override
	public void update() {
		currTime += Game.deltaTime;
		if (currTime > currAnimation.getTimeDelay()) {
			nextFrame();
			currTime -= currAnimation.getTimeDelay();
		}
	}
	
	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}
	
	private void nextFrame() {
		currFrame += 1;
		if (currAnimation.isLooping) {
			if (currFrame > currAnimation.size() - 1) {
				currFrame = 0;
			}
		} else {
			if (currFrame > currAnimation.size() - 1) {
				currFrame = currAnimation.size() - 1;
			}
		}
	}

	@Override
	public void render() {
		currAnimation.get(currFrame).render(getRenderPos());
		
	}
	
	public Sprite tint(Color color) {
		Sprite temp = new Sprite(this.entity, this.offset, this.name);
		for (var a : animations.keySet()) {
			temp.addAnimation(animations.get(a).name, animations.get(a).tint(color));
		}
		
		return temp;
	}
	
	public Sprite brighten(int value) {
		Sprite temp = new Sprite(this.entity, this.offset, this.name);
		for (var a : animations.keySet()) {
			temp.addAnimation(animations.get(a).name, animations.get(a).brighten(value));
		}
		
		return temp;
	}

}
