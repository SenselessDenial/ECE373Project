package game;

import game.util.Interfaces;
import game.util.Logger;
import game.util.Vector2;

public final class TestScene2 extends Scene {

	private InteractiveButton pal;
	private Spritesheet sheet;
	private Entity joemama;
	
	public TestScene2() {
		super();
	}
	
	@Override
	public void begin() {
		super.begin();
		
		pal = new InteractiveButton(new Vector2(1000, 100), this, 
				                   new Texture("changebutton.png"),
				                   new Texture("changebutton_pressed.png"),
				                   Interfaces.GoToScene_Test);
		sheet = new Spritesheet(new Texture("rightcurtain.png"), "rightcurtain.xml");
		joemama = new Entity(this);
		joemama.add(sheet.getSprite(0));
		add(pal);
		add(joemama);
		
	}
	
	@Override
	public void update() {
		super.update();
		
		Logger.Log(Game.deltaTime);
	}
	
	@Override
	public void render() {
		super.render();
	}
	
}
