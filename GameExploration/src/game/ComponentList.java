package game;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class ComponentList implements Iterable<Component> {

	private ArrayList<Component> components;
	private ArrayList<Component> toAdd;
	private ArrayList<Component> toRemove;
	private Entity entity;
	private boolean isLocked;
	
	
	protected ComponentList(Entity entity) {
		components = new ArrayList<Component>();
		toAdd = new ArrayList<Component>();
		toRemove = new ArrayList<Component>();
		isLocked = false;
		this.entity = entity;
	}
	
	private void updateLists() {
		
		if (toAdd.size() > 0) {
			for (var comp : toAdd) {
				components.add(comp);
				comp.added(entity);
			}
			toAdd.clear();
		}
		
		if (toRemove.size() > 0) {
			for (var comp : toRemove) {
				components.remove(comp);
				comp.removed(entity);
			}
			toRemove.clear();
		}
	}
	
	public void add(Component component) {
		if (isLocked) {
			if (!components.contains(component) && !toAdd.contains(component))
				toAdd.add(component);
		}
		else {
			if (!components.contains(component)) {
				components.add(component);
				component.added(entity);
			}
		}
	}
	
	public void remove(Component component) {
		if (isLocked) {
			if (components.contains(component) && !toRemove.contains(component))
				toRemove.add(component);
		}
		else {
			if (components.contains(component)) {
				components.remove(component);
				component.removed(entity);
			}
		}
	}
	
	protected void update() {
		isLocked = true;
		updateLists();
		
		for (var c : components) {
			if (c.isActive)
				c.update();
		}
		isLocked = false;
	}
	
	protected void render() {
		isLocked = true;
		updateLists();
		
		for (var c : components) {
			if (c.isVisible)
				c.render();
		}
		
		isLocked = false;
	}
	
	public int size() {
		return components.size();
	}
	
	@Override
	public Iterator<Component> iterator() {
		
		return components.iterator();
	}
}
