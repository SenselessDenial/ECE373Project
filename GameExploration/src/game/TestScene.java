package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.colliders.BoxCollider;
import game.colliders.Hitbox;
import game.components.Picture;
import game.components.Sprite;
import game.enums.Keys;
import game.util.Interfaces.*;
import game.util.Input;
import game.util.Logger;

public class TestScene extends Scene {
	
	private Entity dummy;
	private Entity dummy2;
	private Picture dummyImage;
	private BoxCollider leftWall;
	private BoxCollider rightWall;
	private BoxCollider topWall;
	private BoxCollider bottomWall;
	private InteractiveButton button;
	private Entity evilChris;
	private Sprite sprite;
	Clip clip = null;
	FloatControl gControl;
	
	public TestScene() {
		super();
	}
	
	@Override
	public void begin() {
		this.renderer.camera.setViewport(2000, 1200);
		
		dummy = new Entity(1920 / 2, 1080 / 2);
		dummy2 = new Entity();
		dummyImage = new Picture("testpicross.png");
		dummy.add(dummyImage);
		add(dummy);
		button = new InteractiveButton(new Vector2(0,0), new Texture("exitbutton.png"), 
				                       new Texture("exitbutton_pressed.png"), InteractiveButton.GameExit);
		add(button);
		evilChris = new Entity(new Vector2(60, 60), this);
		sprite = new Sprite(evilChris, 0.5f, new Vector2(0,0), false);
		sprite.addTexture("chris.png");
		sprite.addTexture("evilchris.png");
		evilChris.add(sprite);
		add(evilChris);
		
		leftWall = new BoxCollider(0, 0, Game.instance.screen.getWidth() / 5, Game.instance.screen.getHeight());
		rightWall = new BoxCollider(0, 0, Game.instance.screen.getWidth() / 5, Game.instance.screen.getHeight());
		rightWall.setTopRight(new Vector2(Game.instance.screen.getWidth(), 0));
		
		
		
		String soundName = Game.contentPath + "rainloop.wav";    
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gControl = (FloatControl) clip.getControl(FloatControl.Type.PAN);
		gControl.setValue(-1f);
		//Logger.Log((float) Math.pow(10f, gControl.getValue() / 20f));
		Logger.Log(gControl.getValue());
		clip.start();
		clip.loop(clip.LOOP_CONTINUOUSLY);
	
		
		this.renderer.camera.setBoundaries(new Rectangle(0, 0, 2400, 1600));
	}
	
	@Override
	public void update() {
		super.update();
		
		
		if (Input.pressed(Keys.U)) {
			renderer.camera.setScale(4);
		}
		if (Input.pressed(Keys.H)) {
			clip.stop();
		}
		if (Input.pressed(Keys.G)) {
			clip.start();
		}
		
		if (Input.pressed(Keys.Q)) {
		}
		
		if (Input.pressed(Keys.Escape)) {
			Game.exit();
		}
		
		if (Input.check(Keys.A)) {
			renderer.camera.translate(-4, 0);
		} 
		if (Input.check(Keys.D)) {
			renderer.camera.translate(4, 0);
		} 
		if (Input.check(Keys.W)) {
			renderer.camera.translate(0, -4);
		}
		if (Input.check(Keys.S)) {
			renderer.camera.translate(0, 4);
		}
		
//		if (leftWall.collide(Input.mousePos) && !Input.check(Keys.D)) {
//			renderer.camera.translate(-20, 0);
//		}
//		
//		if (rightWall.collide(Input.mousePos) && !Input.check(Keys.A)) {
//			renderer.camera.translate(20, 0);
//		}
		
		
		
	}
	
	
	
	
	
}
