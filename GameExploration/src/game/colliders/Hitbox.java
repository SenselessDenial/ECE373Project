package game.colliders;

import game.Component;
import game.Vector2;

public class Hitbox extends Component {
	
	private Collider collider;
	private Vector2 offset;
	
	public Hitbox(Boolean isActive) {
		this.isActive = isActive;
		isVisible = false;
	}
	
	public Hitbox() {
		this(true);
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
