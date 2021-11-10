package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import game.util.Vector2;

// Holds multiple related textures. 
// Actual swapping of textures takes place in Sprite class.
public class Animation {

	/// Fields
	private ArrayList<Texture> textures;
	private Vector2 offset;
	private float timeDelay;
	public Boolean isLooping;
	public String name;
		
	private Animation() {
		textures = new ArrayList<Texture>();
		offset = new Vector2(0, 0);
		timeDelay = 0f;
		isLooping = false;
		name = "";
	}
	
	public Animation(Vector2 offset, float timeDelay, Boolean isLooping, String name, Collection<Texture> textures) {
		this();
		this.offset = offset;
		this.timeDelay = timeDelay;
		this.isLooping = isLooping;
		this.name = name;
		this.textures.addAll(textures);
	}
	
	public Animation(Vector2 offset, float timeDelay, Boolean isLooping, String name) {
		this();
		this.offset = offset;
		this.timeDelay = timeDelay;
		this.isLooping = isLooping;
		this.name = name;
	}
	
	public void addTexture(Texture texture) {
		textures.add(texture);
	}
	
	
	public int size() {
		return this.textures.size();
	}
	
	public float getTimeDelay() {
		return this.timeDelay;
	}
	
	public void setTimeDelay(float timeDelay) {
		this.timeDelay = timeDelay;
	}
	
	public Texture get(int index) {
		return this.textures.get(index);
	}
	
	public Vector2 getOffset() {
		return this.offset;
	}
	
	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}
	
	public Animation tint(Color color) {
		Animation temp = new Animation(this.offset, this.timeDelay, this.isLooping, this.name);
		for (var t : textures) {
			temp.addTexture(t.tint(color));
		}
		return temp;
	}
	
	public Animation brighten(int value) {
		Animation temp = new Animation(this.offset, this.timeDelay, this.isLooping, this.name);
		for (var t : textures) {
			temp.addTexture(t.brighten(value));
		}
		return temp;
		
	}
	
}
