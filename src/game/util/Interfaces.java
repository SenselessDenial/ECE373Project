package game.util;

import game.Game;
import game.SceneManager;
import game.util.Interfaces.Action;
import gameLogic.GameState;

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
	public static Action GoToGame = () -> { Game.instance.setScene(SceneManager.getScene("test")); };
	public static Action GoToCorkboard = () -> {Game.instance.setScene(SceneManager.getScene("main")); };
	public static Action GoToNonogram = () -> {Game.instance.setScene(SceneManager.getScene("nono")); };
	public static Action GoToMainMenu = () -> {Game.instance.setScene(SceneManager.getScene("mainMenu")); };
	public static Action Win = () -> {Game.instance.setScene(SceneManager.getScene("win"));	};
	
	
	
	public static Action barricadeLeftDoor = () -> {GameState.doorleft.blocked = true; } ;
	public static Action barricadeRightDoor = () -> {GameState.doorright.blocked = true; } ;
	
	public static Action removeBarricadeLeftDoor = () -> {GameState.doorleft.blocked = false; };
	public static Action removeBarricadeRightDoor = () -> {GameState.doorright.blocked = false; };
}
