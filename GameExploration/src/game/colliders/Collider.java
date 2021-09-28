package game.colliders;

import java.awt.Rectangle;

import game.*;

public abstract class Collider {
	
	private Vector2 position;
	
	public abstract Boolean collide(Vector2 point);
	public abstract Boolean collide(Vector2 from, Vector2 to);
	public abstract Boolean collide(Rectangle box);
	
	
}
