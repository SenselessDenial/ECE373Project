package game;

public class Renderer {
	
	protected Scene scene;
	public Camera camera;
	
	public Renderer(Scene scene) {
		this.scene = scene;
		camera = new Camera(this);
	}
	
	public void update() {
		camera.update();
	}
	
	public void render() {
		if (scene != null) {
			scene.render();
		}
	}
	
}
