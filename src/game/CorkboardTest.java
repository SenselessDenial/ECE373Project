package game;

import java.util.ArrayList;

import game.components.Picture;
import game.util.Interfaces;
import game.util.Vector2;
import gameLogic.Clue;

// A template used for making scenes.
// do NOT inherit from this class. Instead copy the contents and add on to it.
public final class CorkboardTest extends Scene {

	/// add entity declarations and additional things here
	
	public Clue selectedClue;
	public ArrayList<Clue> answerKey;
	
	public CorkboardTest() {
		super();
		// do not touch!
	}
	
	@Override
	public void begin() {
		super.begin();
		// add entity initiations, setup, etc.
		// add ALL entities to scene using this.add(entity)
		
		Entity mainscreen = new Entity();
		Picture mainscreenimage = new Picture("corkboard.png");
		
		mainscreen.add(mainscreenimage);
		
		
		Clue clue = new Clue(this, "sampleclue.png");
		Clue gunclue = new Clue(this, "sampleclue2.png", new Vector2(1920/4, 0));
		
		clue.correctConnections.add(gunclue);
		gunclue.correctConnections.add(clue);
		
		InteractiveButton backButton = new InteractiveButton(new Vector2(0, 0), this, new Texture("backbutton.png"),
				new Texture("backbutton.png"), Interfaces.GoToGame	);

		
		
		add(mainscreen);
		add(clue);
		add(gunclue);
		add(backButton);
		
		
		this.renderer.camera.setScale(3);
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
