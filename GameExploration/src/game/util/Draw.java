package game.util;

import java.awt.Graphics2D;
import game.*;
import game.enums.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Draw {
	
	public static Graphics2D g2d;
	public static Texture testImage;
	public static Texture testImage2;
	
	
	public static void initialize() {
		testImage = new Texture("testimage.png");
		testImage2 = new Texture("testimage2.png");
	}
	
	
	
}
