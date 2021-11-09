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
import game.util.Draw;
import game.util.Input;
import game.util.Interfaces;
import game.util.Logger;
import game.util.Vector2;

public class TestScene extends Scene {
	
	private Entity dummy;
	private Entity dummy2;
	private Picture dummyImage;
	private InteractiveButton button;
	Clip clip = null;
	FloatControl gControl;
	int a;
	
	
	
	
	public TestScene() {
		super();
	}
	
	@Override
	public void begin() {
		super.begin();
		this.renderer.camera.setViewport(2000, 1200);
		
		dummy = new Entity(0, 0);
		dummy2 = new Entity();
		dummyImage = new Picture("testroom.png");
		dummy.add(dummyImage);
		add(dummy);
		button = new InteractiveButton(new Vector2(600,600), this, new Texture("exitbutton.png"), 
				                       new Texture("exitbutton_pressed.png"), Interfaces.GameExit);
		add(button);
		
		
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
		gControl.setValue(0f);
		//Logger.Log((float) Math.pow(10f, gControl.getValue() / 20f));
		//Logger.Log(gControl.getValue());
		clip.start();
		clip.loop(clip.LOOP_CONTINUOUSLY);
	
		
		this.renderer.camera.setBoundaries(new Rectangle(0, 0, 2400, 1600));
	}
	
	@Override
	public void update() {
		super.update();
		
		Logger.Log(this.renderer.camera);
		
		
		
		if (Input.pressed(Keys.U)) {
			renderer.camera.setScale(4);
		}
		if (Input.pressed(Keys.H)) {
			this.renderer.camera.setViewport(1000, 800);
		}
		if (Input.pressed(Keys.G)) {
			clip.start();
		}
		
		if (Input.check(Keys.Q)) {
			button.position.x += 2;
			a += 1;
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
		
	}
	
	@Override
	public void render() {
		super.render();
	}
		
}
