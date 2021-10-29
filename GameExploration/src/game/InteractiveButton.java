package game;

import java.awt.Rectangle;

import game.colliders.BoxCollider;
import game.components.Picture;
import game.enums.Buttons;
import game.util.Input;
import game.util.Logger;
import game.util.Interfaces.*;

public class InteractiveButton extends Entity {
	public Action action;
	public Picture picture;
	private Texture normal;
	private Texture pressed;
	
	public InteractiveButton(Vector2 pos, Texture normal, Texture pressed, Action action) {
		this.action = action;
		this.normal = normal;
		this.pressed = pressed;
		this.setPicture(new Picture(normal));
		add(picture);
	}
	
	public InteractiveButton(Action action) {
		this(new Vector2(0, 0), null, null, action);
	}
	
	private void invoke() {
		if (action != null) {
			action.function();
		}
	}
	
	@Override
	public void update() {
		Logger.Log(this.position.x);
		Logger.Log(this.hitbox);
		
		
		if (this.collide(Input.mousePos) && Input.check(Buttons.LeftButton)) {
			this.swapTexture(pressed);
		}
		else {
			this.swapTexture(normal);
		}
		
		if (this.collide(Input.mousePos) && Input.released(Buttons.LeftButton)) {
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
	
	public static Action GameExit = () -> { Game.exit(); };
}
