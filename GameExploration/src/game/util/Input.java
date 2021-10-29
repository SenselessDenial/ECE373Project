package game.util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import game.*;
import game.enums.*;

public class Input {
	
	public static BKeyListener keyListener;
	public static BMouseListener mouseListener;
	public static Vector2 mousePos = new Vector2(0, 0);
	private static final int NUM_KEYS = 256;
	private static final int NUM_BUTTONS = 8;
	
	private static boolean keyStatesLocked = false;
	private static boolean[] currKeyStates = new boolean[NUM_KEYS];
	private static KeyState[] polledKeyStates = new KeyState[NUM_KEYS];
	
	private static boolean[] currMouseStates = new boolean[NUM_BUTTONS];
	private static MouseState[] polledMouseStates = new MouseState[NUM_BUTTONS];
	
	
	public static void initialize() {
		keyListener = new BKeyListener();
		mouseListener = new BMouseListener();
		
		Arrays.fill(polledKeyStates, KeyState.Up);
		Arrays.fill(polledMouseStates, MouseState.Up);
	}
	
	public static void update() {
		if (Game.instance.scene != null) {
			mousePos.x = MouseInfo.getPointerInfo().getLocation().x - Game.instance.screen.getLocationOnScreen().x + Game.instance.scene.renderer.camera.getPosition().x;
			mousePos.y = MouseInfo.getPointerInfo().getLocation().y - Game.instance.screen.getLocationOnScreen().y + Game.instance.scene.renderer.camera.getPosition().y;		
		}
		
		keyStatesLocked = true;
		
		for (int i = 0; i < NUM_KEYS; i++) {
			switch(polledKeyStates[i]) {
			case Up:
				if (currKeyStates[i] == true)
					polledKeyStates[i] = KeyState.Pressed;
				else
					polledKeyStates[i] = KeyState.Up;
				break;
			case Pressed:
				if (currKeyStates[i] == true)
					polledKeyStates[i] = KeyState.Held;
				else
					polledKeyStates[i] = KeyState.Released;
				break;
			case Held:
				if (currKeyStates[i] == true)
					polledKeyStates[i] = KeyState.Held;
				else
					polledKeyStates[i] = KeyState.Released;
				break;
			case Released:
				if (currKeyStates[i] == true)
					polledKeyStates[i] = KeyState.Pressed;
				else
					polledKeyStates[i] = KeyState.Up;
			}
		}
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			switch(polledMouseStates[i]) {
			case Up:
				if (currMouseStates[i] == true)
					polledMouseStates[i] = MouseState.Pressed;
				else
					polledMouseStates[i] = MouseState.Up;
				break;
			case Pressed:
				if (currMouseStates[i] == true)
					polledMouseStates[i] = MouseState.Held;
				else
					polledMouseStates[i] = MouseState.Released;
				break;
			case Held:
				if (currMouseStates[i] == true)
					polledMouseStates[i] = MouseState.Held;
				else
					polledMouseStates[i] = MouseState.Released;
				break;
			case Released:
				if (currMouseStates[i] == true)
					polledMouseStates[i] = MouseState.Pressed;
				else
					polledMouseStates[i] = MouseState.Up;
				break;
			}
		}
		keyStatesLocked = false;
	}
	
	public static boolean check(Keys key) {
		return (polledKeyStates[key.getValue()] == KeyState.Pressed) 
			|| (polledKeyStates[key.getValue()] == KeyState.Held);
	}
	
	public static boolean pressed(Keys key) {
		return polledKeyStates[key.getValue()] == KeyState.Pressed;
	}
	
	public static boolean released(Keys key) {
		return polledKeyStates[key.getValue()] == KeyState.Released;
	}
	
	public static boolean check(Buttons mouseButton) {
		return (polledMouseStates[mouseButton.getValue()] == MouseState.Pressed) 
			|| (polledMouseStates[mouseButton.getValue()] == MouseState.Held);
	}
	
	public static boolean pressed(Buttons mouseButton) {
		return polledMouseStates[mouseButton.getValue()] == MouseState.Pressed;
	}
	
	public static boolean released(Buttons mouseButton) {
		return polledMouseStates[mouseButton.getValue()] == MouseState.Released;
	}
	
	private enum KeyState {
		Up,
		Pressed,
		Held,
		Released;
	}
	
	private enum MouseState {
		Up,
		Pressed,
		Held,
		Released;
	}
	
	
	
	private static class BKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// Leave blank
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (!keyStatesLocked) {
				currKeyStates[e.getKeyCode()] = true;
			}
			else
				Logger.Log("Key state is locked.");
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (!keyStatesLocked) {
				currKeyStates[e.getKeyCode()] = false;
			}
			else
				Logger.Log("Key state is locked.");
		}
		
	}

	private static class BMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Logger.Log("Mouse Clicked: " + e.getButton());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			currMouseStates[e.getButton()] = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			currMouseStates[e.getButton()] = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
	}
	
	
}