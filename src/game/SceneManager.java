package game;

import java.util.ArrayList;
import java.util.HashMap;

import game.util.Logger;

public class SceneManager {
	
	private static HashMap<String, Scene> scenes;
	private static Scene defaultScene;
	
	///STORE SCENES HERE!///
	private static TestScene t;
	private static TestScene2 t2;
	private static SceneExamine e;
	private static CorkboardTest m;
	private static MainMenu menu;
	private static WinScene w;
	private static NonogramScene n;
	private static Pause p;
	private static Lose l;
	
	/////STOP STORING//////
	
	public static void initialize() {
		scenes = new HashMap<String, Scene>();
		defaultScene = null;
		
		///INSTANTIATE SCENES HERE!///
		t = new TestScene();
		t2 = new TestScene2();
		e = new SceneExamine();
		m = new CorkboardTest();
		menu = new MainMenu();
		w = new WinScene();
		n = new NonogramScene();
		p = new Pause();
		l = new Lose();
		
		///ADD SCENES HERE!///
		addScene(t, "test");
		addScene(t2, "test2");
		addScene(e, "ex");
		addScene(m, "main");
		addScene(menu, "mainMenu");
		addScene(w, "win");
		addScene(n, "nono");
		addScene(p, "pause");
		addScene(l, "lose");
		
		setDefault("mainMenu");
	}
	
	public static void addScene(Scene scene, String key) {
		if (scenes.isEmpty()) {
			defaultScene = scene;
		}
		scenes.put(key, scene);
		
	}
	
	public static Scene getScene(String key) {
		if (scenes.containsKey(key)) {
			return scenes.get(key);
		} 
		else {
			Logger.Log("Requested key not paired to scene. Default scene returned.");
			return defaultScene;
		}
	}
	public static Scene getDefault() {
		return defaultScene;
	}
	
	public static void setDefault(String key) {
		if (scenes.containsKey(key) ) {
			defaultScene = scenes.get(key);
		}
	}

}
