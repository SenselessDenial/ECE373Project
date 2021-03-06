package game;

import java.awt.Rectangle;

import game.colliders.BoxCollider;
import game.components.Picture;
import game.components.Sprite;
import game.enums.Buttons;
import game.util.Input;
import game.util.Logger;
import game.util.Vector2;
import game.util.Interfaces.*;

// Button entity that performs an action when pressed. Needs to be updated to use the Sprite class.
public class DoorButton extends Entity {
	
	/// Fields
	public Action onPress;
	public Action onRelease;
	
	/// Constructors
	public DoorButton(Vector2 pos, Scene scene, Action action1, Action action2) {
		super(pos, scene);
		position = pos;
		this.onPress = action1;
		this.onRelease = action2;
		
	}
	
	
	public DoorButton(Scene scene, Action action) {
		this(new Vector2(0, 0), scene, null, action);
	}
	
	public DoorButton(Vector2 pos, Scene scene, Action action1, Action action2, float width, float height) {
		super(pos, scene);
		position = pos;
		this.onPress = action1;
		this.onRelease = action2;
		this.setHitbox(width, height);
	}
	
	/// Methods
	
	@Override
	public void update() {
		super.update();
		
		if (this.collide(Input.getMousePos()) && Input.check(Buttons.LeftButton)) {
			this.onPress.function();
		}
		if (this.collide(Input.getMousePos()) && Input.released(Buttons.LeftButton)) {
			this.onRelease.function();
		}
		
	}
	
	
}