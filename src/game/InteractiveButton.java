package game;

import java.awt.Rectangle;

import game.colliders.BoxCollider;
import game.components.Picture;
import game.enums.Buttons;
import game.util.Input;
import game.util.Logger;
import game.util.Vector2;
import game.util.Interfaces.*;

// Button entity that performs an action when pressed. Needs to be updated to use the Sprite class.
public class InteractiveButton extends Entity {
	
	/// Fields
	public Action action;
	public Picture picture;
	private Texture normal;
	private Texture pressed;
	
	/// Constructors
	public InteractiveButton(Vector2 pos, Scene scene, Texture normal, Texture pressed, Action action) {
		super(pos, scene);
		position = pos;
		this.action = action;
		this.normal = normal;
		this.pressed = pressed;
		this.setPicture(new Picture(this, normal));
		add(picture);
	}
	
	public InteractiveButton(Scene scene, Action action) {
		this(new Vector2(0, 0), scene, null, null, action);
	}
	
	/// Methods
	private void invoke() {
		if (action != null) {
			action.function();
		}
	}
	
	@Override
	public void update() {
		super.update();
		
		if (this.collide(Input.getMousePos()) && Input.check(Buttons.LeftButton)) {
			this.swapTexture(pressed);
		}
		else {
			this.swapTexture(normal);
		}
		
		if (this.collide(Input.getMousePos()) && Input.released(Buttons.LeftButton)) {
			this.invoke();
		}
	}
	
	public void setPicture(Picture picture) {
		this.remove(picture);
		this.picture = picture;
		add(picture);
		
		this.setHitbox(picture.getWidth(), picture.getHeight());
	}
	
	private void swapTexture(Texture texture) {
		if (this.picture.getTexture() != texture && texture != null) {
			this.picture.setTexture(texture);
			setPicture(this.picture);
		}
	}
	
	
}