package game;

import java.util.HashMap;
import java.util.Map;

public class Scene {
	
	public EntityList entities;
	private HashMap<Integer, Double> layerDepths;
	public Renderer renderer;
	
	public Scene() {
		entities = new EntityList(this);
		layerDepths = new HashMap<Integer, Double>();
		renderer = new Renderer(this);
	}
	
	public void begin() {
		
	}
	
	public void end() {
		
	}
	
	public void update() {
		renderer.update();
		entities.update();
	}
	
	public void render() {
		entities.render();
	}
	
	public void add(Entity entity) {
		entities.add(entity);
		setDepth(entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
	}
	
	protected void setDepth(Entity entity) {
		entity.setDepth(findDepth(entity.getLayer()));
		entities.markUnsorted();
	}
	
	private double findDepth(int layer) {
		
		final double theta = 0.00001;
		
		double add = 0;
		
		if (layerDepths.containsKey(layer)) {
			add = layerDepths.get(layer);
			layerDepths.put(layer, layerDepths.get(layer) + theta);
		} else {
			layerDepths.put(layer, theta);
		}
		
		return layer + add;
	}
	
	public int numOfEntities() {
		return entities.size();
	}
	
	public int numOfComponents() {
		int sum = 0;
		
		for (var i : entities) {
			sum += i.components.size();
		}
		
		return sum;
	}
	
	
	

}
