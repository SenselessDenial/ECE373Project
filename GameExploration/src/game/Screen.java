package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Screen extends Canvas {
	
	private Screen() {
		addKeyListener(Input.keyListener);
		addMouseListener(Input.mouseListener);
		setFocusable(true);
	}
	
	public Screen(Dimension preferredSize) {
		this();
		setPreferredSize(preferredSize);
	}
	
	public Screen(int width, int height) {
		this();
		setPreferredSize(width, height);
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height));
	}
	
	public void setPreferredSize(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
	}
	
	@Override
	public void paint(Graphics g) {
		//Leave blank
	}
	
	@Override
	public void repaint() {
		//Leave blank
	}
	
	
	
	
}
