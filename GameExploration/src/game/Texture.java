package game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Texture {
	
	private BufferedImage texture;
	private Vector2 offset;
	
	public Texture() {
		texture = null;
		offset = new Vector2(0, 0);
	}
	
	public Texture(int width, int height) {
		this();
		texture = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public Texture(String filename) {
		this();
		setTexture(filename);
	}
	
	public void setTexture(String filename) {
		try {
			texture = ImageIO.read(new File(Game.contentPath + filename));
		}
		catch(Exception e) {
			Logger.Log("Texture file could not be found.");
			texture = null;
		}
	}
	
	public int getWidth() {
		return texture.getWidth();
	}
	
	public int getHeight() {
		return texture.getHeight();
	}
	
	public void setOffset(float x, float y) {
		offset = new Vector2(x, y);
	}
	
	public Vector2 getOffset() {
		return offset;
	}
	
	public void render(float x, float y) {
		Draw.g2d.drawImage(texture, (int)(x-offset.x), (int)(y-offset.y), null);
	}
	
	public void render() {
		render(0, 0);
	}
	
	public void render(Vector2 pos) {
		render(pos.x, pos.y);
	}
	
	public void renderBorder(Vector2 pos) {
		render(pos);
		getBorder().render(pos);
	}
	
	public void renderBorder(float x, float y) {
		renderBorder(new Vector2(x, y));
	}
	
	public void renderBorder() {
		renderBorder(new Vector2(0,0));
	}
	
	public Texture tint(Color color) {
		Texture temp = new Texture(this.getWidth(), this.getHeight());
		
		for (int x = 0; x < temp.getWidth(); x++) 
			for (int y = 0; y < temp.getHeight(); y++) 
				temp.texture.setRGB(x, y, Calc.Multiply(new Color(this.texture.getRGB(x, y), true), color).getRGB());
				
		return temp;
	}
	
	private void setColor(int x, int y, Color color) {
		this.texture.setRGB(x, y, color.getRGB());
	}
	
	private Color getColor(int x, int y) {
		return new Color(this.texture.getRGB(x, y), true);
	}
	
	public Texture clone() {
		Texture temp = new Texture(this.getWidth(), this.getHeight());
		
		for (int x = 0; x < temp.getWidth(); x++) 
			for (int y = 0; y < temp.getHeight(); y++) 
				temp.texture.setRGB(x, y, this.texture.getRGB(x, y));
		
		return temp;
	}
	
	
	
	public Texture getBorder(Color color) {
		Texture temp = new Texture(this.getWidth(), this.getHeight());
		
		for (int x = 0; x < temp.getWidth(); x++) 
			for (int y = 0; y < temp.getHeight(); y++)
				if (this.getColor(x, y).getAlpha() != 0)
					for (int i = -1; i <= 1; i++)
						for (int j = -1; j <= 1; j++)
							if (Math.abs(i+j) == 1)
								if (x+i >= 0 && x+i < this.getWidth() && y+j >= 0 && y+j < this.getHeight()) {
									if (this.getColor(x+i, y+j).getAlpha() == 0)
										temp.setColor(x, y, color);	
								}
								else {
									temp.setColor(x, y, color);
								}	
		return temp;
	}
	
	public Texture getBorder() {
		return getBorder(Color.black);
	}
	
	public void foo() {
		
	}
	
}
