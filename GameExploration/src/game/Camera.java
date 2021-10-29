package game;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import game.colliders.BoxCollider;

public class Camera {
	
	private AffineTransform matrix;
	private AffineTransform scaleMatrix;
	private AffineTransform translateMatrix;
	
	private Vector2 scale;
	private Vector2 origin;
	private float angle;
	
	public Rectangle boundaries;
	private BoxCollider viewport;
	
	private Boolean isUpdated;
	
	private Renderer renderer;
	private Scene scene;
	private Entity target;
	
	
	//viewport / position relationship?
	
	public Camera(int width, int height, Renderer renderer) {
		matrix = new AffineTransform();
		translateMatrix = new AffineTransform();
		scaleMatrix = new AffineTransform();
		
		boundaries = null;
		scale = new Vector2(1, 1);
		angle = 0f;
		viewport = new BoxCollider(0, 0, width, height);
		isUpdated = false;
		origin = new Vector2(viewport.width / 2, viewport.height / 2);
		this.renderer = renderer;
		this.scene = renderer.scene;
		target = null;
	}
	
	public Camera(Renderer renderer) {
		this(Game.width, Game.height, renderer);
	}
	
	public void scale(float scale) {
		this.scale.x *= scale;
		this.scale.y *= scale;
		isUpdated = false;
	}
	
	public void setScale(float scale) {
		this.scale.x = scale;
		this.scale.y = scale;
		isUpdated = false;
	}
	
	public void translate(float x, float y) {
		viewport.x += x;
		viewport.y += y;
		//origin.x += x;
		//origin.y += y;
		isUpdated = false;
	}
	
	public Entity getTarget() {
		return target;
	}
	
	public void setTarget(Entity target) {
		this.target = target;
	}
	
	public void removeTarget() {
		this.target = null;
	}
	
	
	public void rotate(float angle) {
		this.angle -= angle;
		isUpdated = false;
	}
	
	public void updateMatrix() {
		matrix.setToIdentity();
		
		matrix.translate(origin.x, origin.y);
		matrix.scale(scale.x, scale.y);
		matrix.translate(-viewport.x, -viewport.y);
		matrix.translate(-origin.x, -origin.y);
		
		
		isUpdated = true;
	}
	
	public void updateTarget() {
		if (target != null) {
			viewport.setTopLeft(target.position);
		}
	}
	
	public void update() {
		if (isUpdated == false) {
			updateTarget();
			checkBoundaries();
			updateMatrix();
		}
			
		
		//Logger.Log(position.x);
		//Logger.Log(position.y);
	}
	
	public AffineTransform getMatrix() {
		return matrix;
	}
	
	public void setBoundaries(Rectangle boundaries) {
		this.boundaries = boundaries;
	}
	
	public void removeBoundaries() {
		this.boundaries = null;
	}
	
	private void checkBoundaries() {
		if (boundaries != null) {
			if (viewport.getRight() > boundaries.width + boundaries.x) {
				viewport.setRight(boundaries.width + boundaries.x);
			}
			if (viewport.getBottom() > boundaries.y + boundaries.height) {
				viewport.setBottom(boundaries.y + boundaries.height);
			}
			if (viewport.x < boundaries.x) {
				viewport.x = boundaries.x;
			}
			if (viewport.y < boundaries.y) {
				viewport.y = boundaries.y;
			}
		}
		
	}
	
	public Vector2 getPosition() {
		return new Vector2(viewport.x, viewport.y);
	}
	
	public void setViewport(float x, float y, float width, float height) {
		viewport.x = x;
		viewport.y = y;
		viewport.width = width;
		viewport.height = height;
	}
	
	public void setViewport(float width, float height) {
		viewport.width = width;
		viewport.height = height;
	}

}
