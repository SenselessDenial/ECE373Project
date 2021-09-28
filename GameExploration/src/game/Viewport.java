package game;

import java.awt.Rectangle;

import game.colliders.BoxCollider;

//TODO Implement this in camera class

//Describes a rectangle in real world space.
public class Viewport {
	
	private Rectangle bounds;
	
	
	public Viewport(int x, int y, int width, int height) {
		bounds = new Rectangle(x, y, width, height);
	}
	
	public Viewport(int width, int height) {
		this(0, 0, width, height);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getPosition() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	public int getX() {
		return bounds.x;
	}
	
	public int getY() {
		return bounds.y;
	}
	
	public int getWidth() {
		return bounds.width;
	}
	
	public int getHeight() {
		return bounds.height;
	}
	
	public int getLeft() {
		return bounds.x;
	}
	
	public int getRight() {
		return bounds.x + bounds.width;
	}
	
	public int getTop() {
		return bounds.y;
	}
	
	public int getBottom() {
		return bounds.y + bounds.height;
	}
	
	public Vector2 getTopLeft() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	public Vector2 getTopRight() {
		return new Vector2(bounds.x + bounds.width, bounds.y);
	}
	
	public Vector2 getBottomLeft() {
		return new Vector2(bounds.x, bounds.y + bounds.height);
	}
	
	public Vector2 getBottomRight() {
		return new Vector2(bounds.x + bounds.width, bounds.y + bounds.height);
	}
	
	public Vector2 getCenter() {
		return new Vector2((float)bounds.width / 2, (float)bounds.height / 2);
	}
	
	
}
