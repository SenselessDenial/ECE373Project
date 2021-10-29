package game.components;

import java.util.ArrayList;

import game.Entity;
import game.Game;
import game.Texture;
import game.Vector2;
import game.util.Logger;

public class Sprite extends Component{

	private int currFrame;
	private Vector2 offset;
	ArrayList<Texture> textures;
	private float timeDelay;
	private float currTime;
	public Boolean isLooping;
	
	public Sprite(Entity entity, float timeDelay, Vector2 offset, Boolean isLooping) {
		this.entity = entity;
		currFrame = 0;
		currTime = 0;
		this.offset = offset;
		this.timeDelay = timeDelay;
		this.isLooping = isLooping;
		textures = new ArrayList<Texture>();
	}
	
	public Sprite(Entity entity, float timeDelay, Vector2 offset, Boolean isLooping, ArrayList<Texture> textureSet) {
		this(entity, timeDelay, offset, isLooping);
		textures.addAll(textureSet);
	}
	
	private Vector2 getRenderPos() {
		return new Vector2(this.entity.getX() - offset.x, this.entity.getY() - offset.y);
	}
	
	public void setFrame(int index) {
		if (index < 0) {
			currFrame = 0;
		}
		else if (index >= textures.size()) {
			index = textures.size() - 1;
		} else {
			currFrame = index;
		}
		currTime = 0;
	}
	
	public void addTexture(Texture texture) {
		textures.add(texture);
	}
	
	public void addTexture(String filename) {
		addTexture(new Texture(filename));
	}
	
	@Override
	public void update() {
		
		currTime += Game.deltaTime;
		if (currTime > timeDelay) {
			nextFrame();
			currTime -= timeDelay;
		}
	}
	
	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}
	
	private void nextFrame() {
		currFrame += 1;
		if (isLooping) {
			if (currFrame > textures.size() - 1) {
				currFrame = 0;
			}
		} else {
			if (currFrame > textures.size() - 1) {
				currFrame = textures.size() - 1;
		}
		}
	}

	@Override
	public void render() {
		textures.get(currFrame).render(getRenderPos());
	}

}
