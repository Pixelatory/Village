package client.utility;

import java.awt.Color;

/**
 * Performs Alpha Blending.<br>
 * Using the formula: C = A(a/255) + B(1-(a/255))<br>
 * Where C is the result colour,<br>
 * A is the foreground value,<br>
 * B is the background value,<br>
 * a is the alpha value.
 * 
 * @author nicka
 *
 */
public class AlphaBlend {
	
	interface AlphaCalc {
		int calc(int front, int back, int factor);
	}
	
	/**
	 * Blends two colours depending on their alpha value.
	 * 
	 * @param frontColour Color object
	 * @param backColour Color object
	 * @param alpha int value
	 * @return Color object
	 */
	public static Color blend(Color frontColour, Color backColour, int alpha) {		
		int frontRed = frontColour.getRed();
		int frontGreen = frontColour.getGreen();
		int frontBlue = frontColour.getBlue();
		
		int backRed = backColour.getRed();
		int backGreen = backColour.getGreen();
		int backBlue = backColour.getBlue();
		
		
		AlphaCalc colour = (front, back, factor) -> (int) (front * (factor / 255f)) + (int) (back * (1 - (factor / 255f)));
		
		int red = colour.calc(frontRed, backRed, alpha);
		int green = colour.calc(frontGreen, backGreen, alpha);
		int blue = colour.calc(frontBlue, backBlue, alpha);
		
		return new Color(red,green,blue);
	}
}
