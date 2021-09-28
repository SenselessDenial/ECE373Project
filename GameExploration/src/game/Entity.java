package game;

import java.util.Iterator;

public class Entity implements Iterable<Component> {
	public Vector2 position;
	protected ComponentList components;
	private int layer;
	private double depth;
	private Scene scene;
	
	public boolean isActive = true;
	public boolean isVisible = true;
	public boolean isCollidable = true;
	
	public Entity(Vector2 pos, Scene scene) {
		position = pos;
		components = new ComponentList(this);
		added(scene);
		layer = 0;
		depth = 0.0;
	}
	
	public Entity() {
		this(new Vector2(0,0), null);
	}
	
	public Entity(Scene scene) {
		this(new Vector2(0, 0), scene);
	}
	
	public void add(Component component) {
		components.add(component);
	}
	
	public void remove(Component component) {
		components.remove(component);
	}
	
	public void added(Scene scene) {
		this.scene = scene;
	}
	
	public void removed(Scene scene) {
		this.scene = null;
	}
	
	public void update() {
		if (isActive)
			components.update();
	}
	
	public void render() {
		if (isVisible)
			components.render();
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
		scene.setDepth(this);
	}
	
	public double getDepth() {
		return depth;
	}
	
	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	public void bringToFront() {
		setLayer(layer);
	}
	

	@Override
	public Iterator<Component> iterator() {
		return components.iterator();
	}
	

}
