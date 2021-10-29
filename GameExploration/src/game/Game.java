package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import game.util.BFrame;
import game.util.Draw;
import game.util.Input;
import game.util.Logger;
import game.util.Screen;

public class Game {
	
	public BFrame bFrame;
	public Screen screen;
	public final static String directoryPath = System.getProperty("user.dir");
	public final static String contentPath = directoryPath + "\\content" + "/";
	private static String title; 
	private Boolean isRunning;
	private final double DESIRED_FPS = 60.0;
	public static double deltaTime = 0.0;
	int fps = 0;
	
	public static Game instance;
	public static int width;
	public static int height;
	
	public Scene scene;
	public Scene nextScene;
	
	/////////TEST/////////
	
	TestScene t;
	
	///////END TEST///////
	
	
	public Game() {
		instance = this;
		isRunning = true;
		
		
		//Sets the application to use the actual dimensions in pixels, and ignore the resolution of the user.
		System.setProperty("sun.java2d.uiScale", "1");
		
		Input.initialize();
		Draw.initialize();
		
		
		screen = new Screen(1920, 1080);
		bFrame = new BFrame(screen);
		bFrame.setVisible(true);
		
		width = screen.getWidth();
		height = screen.getHeight();
		
		setTitle("FPS: " + fps);
		
		scene = null;
		nextScene = null;
		
		//////////TEST//////////
		
		t = new TestScene();
		setScene(t);
		
		//////////ENDTEST///////
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		if (scene == null) {
			this.scene = scene;
		} else {
			nextScene = scene;
		}
	}
	
	public void updateScenes() {
		if (nextScene != null) {
			if (scene != null) {
				scene.end();
			}
			scene = nextScene;
			if (scene != null) {
				scene.begin();
			}
			nextScene = null;
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long currTime;
		double frameDuration = 1 / DESIRED_FPS;
		long timer = System.currentTimeMillis();
		double delta = 0;
		
		while(isRunning) {
			if (bFrame.isClosing) {
				break;
			}
			
			
			currTime = System.nanoTime();
			delta += (currTime - lastTime) / (double)1000000000;
			lastTime = currTime;
			
			if (delta >= frameDuration) {
				deltaTime = delta;
				update();
				fps++;
				delta -= frameDuration;
			}
			
			render();
			
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				setTitle("FPS: " + fps);
				fps = 0;
			}
			
		}
		
		isRunning = false;
	}
	
	private void update() {
		Input.update();
		
		if (scene != null) {
			scene.update();
		}
		
		updateScenes();
	}
	
	private void render() {
		BufferStrategy bs = screen.getBufferStrategy();
		if (bs == null) {
			screen.createBufferStrategy(3);
			return;
		}
		
		Draw.g2d = (Graphics2D)bs.getDrawGraphics();
		Draw.g2d.clearRect(0, 0, screen.getWidth(), screen.getHeight());
		////////////////////RENDER///////////////////
		
		if (scene != null) {
			Draw.g2d.setTransform(scene.renderer.camera.getMatrix());
			scene.renderer.render();
		}
		
		////////////////////////////////////////////
		Draw.g2d.dispose();
		bs.show();
	}
	
	public static void exit() {
		Logger.Log("Game is exiting!");
		System.exit(0);
	}
	
	private void setTitle(String title) {
		this.title = title;
		if (bFrame != null) {
			bFrame.setTitle(title);
		}
	}
	

}
