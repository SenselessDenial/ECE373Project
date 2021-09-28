package game;

import java.awt.Color;
import java.lang.Math;

public class Calc {
	
	public static Color Multiply(Color c1, Color c2) {
		float r1 = (c1.getRed() / 255.0f);
		float g1 = (c1.getGreen() / 255.0f);
		float b1 = (c1.getBlue() / 255.0f);
		float a1 = (c1.getAlpha() / 255.0f);
		
		float r2 = (c2.getRed() / 255.0f);
		float g2 = (c2.getGreen() / 255.0f);
		float b2 = (c2.getBlue() / 255.0f);
		float a2 = (c2.getAlpha() / 255.0f);
		
		return new Color(r1 * r2, g1 * g2, b1 * b2, a1 * a2);
	}
	
	
	
	
	
}
