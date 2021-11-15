package game;

import java.awt.Rectangle;


import game.Entity;
import game.Game;
import game.InteractiveButton;
import game.Scene;
import game.Texture;
import game.components.Picture;
import game.util.Interfaces;
import game.util.Logger;
import game.util.Vector2;
import gameLogic.NonogramCell;

// A template used for making scenes.
// do NOT inherit from this class. Instead copy the contents and add on to it.
public final class MainMenu extends Scene {
	
	private InteractiveButton newGame;
	private InteractiveButton loadGame;
	private InteractiveButton settings;
	private InteractiveButton exitGame;
	private Entity larryContainer;
	private NonogramCell testNonogram;
	private Picture larrySheet;
	private Picture background;
	private Entity bgEntity;

	public MainMenu() {
		super();
	}
	
	@Override
	public void begin() {
		super.begin();
		
		Picture button = new Picture("changebutton.png");
		newGame = new InteractiveButton(new Vector2(90, 250), this, new Texture("startbutton.png"), new Texture("startbutton.png"), Interfaces.GoToGame);
		loadGame = new InteractiveButton(new Vector2(90, 300), this, new Texture("loadbutton.png"), new Texture("loadbutton.png"), Interfaces.GoToGame);
		settings = new InteractiveButton(new Vector2(500, 250), this, new Texture("settingsbutton.png"), new Texture("settingsbutton.png"), Interfaces.GameExit);
		exitGame = new InteractiveButton(new Vector2(500, 300), this, new Texture("exitbutton.png"), new Texture("exitbutton.png"), Interfaces.GameExit);
		larrySheet = new Picture("titlescreen1.png");
		larryContainer = new Entity(0, 0);
		Logger.Log(larrySheet.getWidth());
		Logger.Log(Game.getWidth());
		larryContainer.add(larrySheet);
		//bgEntity = new Entity(0, 0);
		//background = new Picture("testroom.png");
		//bgEntity.add(background);
		//add(bgEntity);
		add(larryContainer);
		add(newGame);
		add(loadGame);
		add(settings);
		add(exitGame);
		this.renderer.camera.setBoundaries(new Rectangle(0, 0, 1280, 0));
        this.renderer.camera.setScale(2.75f);
	}
	
	@Override
	public void update() {
		super.update();
		// super.update() already updates entities. add any additional update here.
	}
	
	@Override
	public void render() {
		super.render();
		// don't touch unless you specifically need to add something.
	}
	
}
