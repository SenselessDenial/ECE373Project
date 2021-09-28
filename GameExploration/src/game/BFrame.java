package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class BFrame extends JFrame {
	
	private Color bgColor;
	public boolean isClosing = false;
	
	public BFrame() {
		addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	isClosing = true;
	        	Logger.Log("Window is closing!");
	            Game.exit();
	        }
	    });
		
		this.getContentPane().setBackground(new Color(255, 100, 125));
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		toFront();
		requestFocus();
		
	}
	
	public BFrame(Screen screen) {
		this();
		addScreen(screen);

	}
	
	public void addScreen(Screen screen) {
		this.getContentPane().add(screen);
		pack();
	}
	
	
	

}
