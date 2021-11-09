package game.util;

import game.Game;
import game.SceneManager;
import game.util.Interfaces.Action;

// Stores general use interfaces such as Action and Predicate.
public class Interfaces {
	
	/// Interfaces
	public interface Action {
		void function();
	}
	
	public interface Predicate<T> {
		boolean function(T item);
	}
	
	
	
	/// Static prefabs
	public static Action GameExit = () -> { Game.exit(); };
	public static Action GoToScene_Test = () -> { Game.instance.setScene(SceneManager.getScene("test")); };
	
	
	
}
