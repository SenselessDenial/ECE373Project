package game;

public abstract class Component {
	
	public boolean isVisible;
	public boolean isActive;
	private Entity entity;
	

	
	public Component(boolean isVisible, boolean isActive, Entity entity) {
		this.isVisible = isVisible;
		this.isActive = isActive;
		this.entity = entity;
	}
	
	public Component(boolean isVisible, boolean isActive) {
		this(isVisible, isActive, null);
	}
	
	public Component() {
		this(true, true, null);
	}
	
	public abstract void update();
	public abstract void render();
	
	public void added(Entity entity) {
		this.entity = entity;
	}
	
	public void removed(Entity entity) {
		this.entity = null;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void removeSelf() {
		if (entity != null)
			this.entity = null;
	}
		
}
