package game.colliders;

import java.awt.Rectangle;

import game.Vector2;

public class BoxCollider extends Collider {
	
	private Rectangle bounds;
	
	public BoxCollider(Rectangle bounds) {
		this.bounds = bounds;
	}

	@Override
	public Boolean collide(Vector2 point) {
		return (point.x >= bounds.x 
		     && point.y >= bounds.y 
	 		 && point.x <= bounds.x + bounds.width 
			 && point.y <= bounds.y + bounds.height);
	}

	@Override
	public Boolean collide(Vector2 from, Vector2 to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean collide(Rectangle box) {
		return (bounds.x <= box.x + box.width
			 && bounds.y <= box.y + box.height
			 && bounds.x + bounds.width >= box.x
			 && bounds.y + bounds.height >= box.y);
	}
	

}
