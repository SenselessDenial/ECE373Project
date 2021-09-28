package game;

import java.awt.Color;

import game.enums.Keys;

public class TestScene extends Scene {
	
	private Entity dummy;
	private Entity dummy2;
	private Picture dummyImage;
	
	public TestScene() {
		super();
	}
	
	@Override
	public void begin() {
		dummy = new Entity();
		dummy2 = new Entity();
		dummyImage = new Picture("testimage2.png");
		dummy.add(dummyImage);
		add(dummy);
		
		Logger.Log(dummy.getLayer());
	}
	
	@Override
	public void update() {
		super.update();
		
		if (Input.pressed(Keys.U)) {
			renderer.camera.scale(2);
		}
		if (Input.pressed(Keys.Y)) {
			renderer.camera.scale(0.5f);
		}
		if (Input.check(Keys.I)) {
			renderer.camera.rotate((float)Math.PI / 16);
		}
		
		if (Input.check(Keys.Left)) {
			renderer.camera.translate(-2, 0);
		}
		if (Input.check(Keys.Up)) {
			renderer.camera.translate(0, -2);
		}
		if (Input.check(Keys.Right)) {
			renderer.camera.translate(2, 0);
		}
		if (Input.check(Keys.Down)) {
			renderer.camera.translate(0, 2);
		}
		
		
		
	}
	
	
	
	
	
}
