package game;

import java.awt.Rectangle;
import java.util.ArrayList;


import game.Entity;
import game.Scene;
import game.Texture;
import game.components.Picture;
import game.util.Interfaces;
import game.util.Vector2;
import gameLogic.GameState;
import gameLogic.NonogramCell;

// A template used for making scenes.
// do NOT inherit from this class. Instead copy the contents and add on to it.
public final class NonogramScene extends Scene {

	/// add entity declarations and additional things here
	private ArrayList<ArrayList<NonogramCell>> nonogramCells;
	private Picture computerBackground;
	private Entity bgEntity;
	
	public NonogramScene() {
		super();
	}
	
	@Override
	public void begin() {
		super.begin();
		// add entity initiations, setup, etc.
		// add ALL entities to scene using this.add(entity)
		computerBackground = new Picture("corkboard.png");
		bgEntity = new Entity();
		bgEntity.add(computerBackground);
		add(bgEntity);
		nonogramCells = new ArrayList<ArrayList<NonogramCell>>();
		for (int i = 0; i < GameState.puzzle.size; i++) {
			nonogramCells.add(new ArrayList<NonogramCell>());
			for (int j = 0; j < GameState.puzzle.size; j = j + 1) {
				nonogramCells.get(i).add(new NonogramCell(new Vector2(200 + (50 * j), 70 + (50 * i)), this, new Texture("unmarked.png"), new Texture("checked.png"), new Texture("marked.png")));
				nonogramCells.get(i).get(j).swapTexture(GameState.puzzle.getCellState(i, j));
				add(nonogramCells.get(i).get(j));
			}
		}
		
		
		InteractiveButton backButton = new InteractiveButton(new Vector2(0 , 0), this, new Texture("backbutton.png"),
				new Texture("backbutton.png"), Interfaces.GoToGame	);

		add(backButton);
		
		
		this.renderer.camera.setBoundaries(new Rectangle(0, 0, 1280, 0));
        this.renderer.camera.setScale(3f);
	}
	
	@Override
	public void update() {
		super.update();
		// super.update() already updates entities. add any additional update here.
		for (int i = 0; i < GameState.puzzle.size; i++) {
			for (int j = 0; j < GameState.puzzle.size; j++) {
				GameState.puzzle.changeCellState(i, j, nonogramCells.get(i).get(j).getState());
			}
		}
		if (GameState.puzzle.checkSolved()) {
			Interfaces.Win.function();
		}
	}
	
	@Override
	public void render() {
		super.render();
		// don't touch unless you specifically need to add something.
	}
}
