package game.colliders;

import game.Entity;
import game.Vector2;
import game.components.Component;

public class Hitbox extends Component {
	
	private BoxCollider collider;
	private Vector2 offset;
	
	
	public Hitbox(Entity entity, float width, float height, Vector2 offset, Boolean isActive)
	{
		this.entity = entity;
		this.isActive = isActive;
		isVisible = false;
		this.offset = offset;
		collider = new BoxCollider(entity.position.x - offset.x, 
				                   entity.position.y - offset.y,
				                   width,
				                   height);
	}
	
	public Hitbox(Entity entity, float width, float height) {
		this(entity, width, height, new Vector2(0, 0), true);
	}
	
	public Hitbox(Entity entity) {
		this(entity, 0, 0);
	}
	
	@Override
	public void update() {
		collider.setLeft(entity.position.x - offset.x);
		collider.setTop(entity.position.y - offset.y);
		
	}
	
	public Boolean collide(Vector2 point) {
		return this.collider.collide(point);
	}
	
	public Boolean collide(Hitbox hitbox) {
		return this.collider.collide(hitbox);
	}
	
	public Boolean collide(Entity entity) {
		return this.collide(entity);
	}
	
	public BoxCollider getCollider() {
		return this.collider;
	}
	
	public float getWidth() {
		return collider.width;
	}
	
	public void setWidth(float width) {
		collider.width = width;
	}
	
	public float getHeight() {
		return collider.height;
	}
	
	public void setHeight(float height) {
		collider.height = height;
	}
	
	public void setDimensions(Vector2 dim) {
		setWidth(dim.x);
		setHeight(dim.y);
	}

	@Override
	public void render() {
		
	}
	
	@Override
	public String toString() {
		return "X: " + this.collider.x + " " + 
			   "Y: " + this.collider.y + " " +
		       "Width: " + this.collider.width + " " + 
		       "Height: " + this.collider.height;
	}

}
