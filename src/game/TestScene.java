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
import game.components.SoundEmitter;
import game.components.Sprite;
import game.enums.Keys;
import game.util.Interfaces.*;
import game.util.Draw;
import game.util.Input;
import game.util.Interfaces;
import game.util.Logger;
import game.util.Vector2;
import gameLogic.GameState;
import gameLogic.Ingress;
import gameLogic.Larry;

public class TestScene extends Scene {
	
	private Entity dummy;
	private Picture dummyImage;
	private Entity listener;
	private InteractiveButton button;
	private SoundEmitter emitter;
	Clip clip = null;
	FloatControl gControl;
	int a;
	BClip miasmajesty;
	BClip wolves;
	private Larry larry;
	
	public TestScene() {
		super();
	}
	
	@Override
	public void begin() {
		super.begin();
		this.renderer.camera.setViewportPixels(1920, 720);
		
		dummy = new Entity(0, 0);
		listener = new Entity(this.renderer.camera.getCenterWorldSpace(), this);
		add(listener);
		dummyImage = new Picture("background.png");
		dummy.add(dummyImage);
		add(dummy);
		button = new InteractiveButton(new Vector2(200,10), this, new Texture("exitbutton.png"), 
				                       new Texture("exitbutton_pressed.png"), Interfaces.GameExit);
		
		InteractiveButton corkboardButton = new InteractiveButton(new Vector2(1920/4 + 50, 50), this, new Texture("invisibleCorkButton.png"),
											new Texture("invisibleCorkButtonPress.png"), Interfaces.GoToCorkboard	);
		
		add(corkboardButton);
		
		
		InteractiveButton puzzleButton = new InteractiveButton(new Vector2(1920/4, 200), this, new Texture("invisibleNonoButton.png"),
				new Texture("invisibleNonoButton.png"), Interfaces.GoToNonogram	);

		add(puzzleButton);
		
		add(button);
		emitter = new SoundEmitter(new Vector2(button.getWidth() / 2, button.getHeight() / 2));
		
		
		larry = new Larry();
		add(larry);
		
		add(GameState.windowright);
		add(GameState.windowleft);
		add(GameState.doorright);
		
		
		Spritesheet sheet = new Spritesheet(new Texture("rightcurtain.png"), "rightcurtain.xml");
		Entity joemama = new Entity(new Vector2(50 , 25), this);
		joemama.add(sheet.getSprite(0));
		add(joemama);
		
		DoorButton leftDoor = new DoorButton(new Vector2(0,0), this, Interfaces.barricadeLeftDoor, Interfaces.removeBarricadeLeftDoor, 130, 360);
		DoorButton rightDoor = new DoorButton(new Vector2(1140,0), this, Interfaces.barricadeRightDoor, Interfaces.removeBarricadeRightDoor, 130, 360);
		
		add(rightDoor);
		
		add(leftDoor);
		
		miasmajesty = new BClip("rainloop.wav");
		wolves = new BClip("wolves.wav");
		miasmajesty.setPan(0f);
		miasmajesty.setVolume(0.6f);
		wolves.setPan(0);
		wolves.setVolume(0.4f);
		emitter.add(miasmajesty);
		emitter.add(wolves);
		emitter.setTarget(listener);
		button.add(emitter);
		emitter.start(1);
		emitter.start(0);
		
		
		
		
		this.renderer.camera.setBoundaries(new Rectangle(0, 0, 1280, 0));
		this.renderer.camera.setScale(2);
	}
	
	@Override
	public void update() {
		super.update();
		
		Logger.Log(GameState.doorleft.blocked);
		
		listener.position = this.renderer.camera.getCenterWorldSpace();
		
		if (Input.pressed(Keys.U)) {
			renderer.camera.setScale(4);
		}
		if (Input.pressed(Keys.H)) {
			emitter.start(1);
		}
		if (Input.pressed(Keys.G)) {
			emitter.stop(1);
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
		
		if(Input.check(Keys.Space)) {
			Game.instance.setScene(SceneManager.getScene("pause"));	
		}
		
	}
	
	@Override
	public void render() {
		super.render();
	}
		
}
