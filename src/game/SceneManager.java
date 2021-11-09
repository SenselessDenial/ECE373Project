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
	
	/////STOP STORING//////
	
	public static void initialize() {
		scenes = new HashMap<String, Scene>();
		defaultScene = null;
		
		///INSTANTIATE SCENES HERE!///
		t = new TestScene();
		t2 = new TestScene2();
		
		///ADD SCENES HERE!///
		addScene(t, "test");
		addScene(t2, "test2");
		
		setDefault("test2");
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
