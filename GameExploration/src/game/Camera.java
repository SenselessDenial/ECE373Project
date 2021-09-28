package game;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Camera {
	
	private AffineTransform matrix;
	private AffineTransform scaleMatrix;
	private AffineTransform translateMatrix;
	
	public Rectangle boundaries;
	public Vector2 position;
	private Vector2 scale;
	private Vector2 origin;
	private float angle;
	private Rectangle viewport;
	
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
		position = new Vector2(0, 0);
		angle = 0f;
		viewport = new Rectangle(width, height);
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
	
	public void translate(float x, float y) {
		position.x += x;
		position.y += y;
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
		matrix.translate(-position.x, -position.y);
		matrix.translate(-origin.x, -origin.y);
		
		isUpdated = true;
	}
	
	public void updateTarget() {
		if (target != null) {
			this.position = target.position;
		}
	}
	
	public void update() {
		if (isUpdated == false) {
			updateTarget();
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
			if (position.x < boundaries.x) {
				position.x = boundaries.x;
			}
			if (position.y < boundaries.y) {
				position.y = boundaries.y;
			}
		}
		
	}
	
	private void setTopLeft(Vector2 position) {
		this.position = position;
	}
	
	private void setTopRight(Vector2 position) {
		this.position = new Vector2(position.x - viewport.width, position.y);
	}
	
	
	
	

}
