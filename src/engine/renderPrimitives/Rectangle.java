package engine.renderPrimitives;

import java.awt.Color;

/**
 * Class which encompasses everything a Rectangle object will need. 
 * 
 * @author 6177000
 */
public class Rectangle extends Shape {

	/**
	 * Sets the parameter values in the respective instance variables.
	 * 
	 * @param x int value
	 * @param y int value
	 * @param width int value
	 * @param height int value
	 * @param colour Color object
	 */
	public Rectangle(int x, int y, int width, int height, Color colour) {
		super(x,y,width,height,colour);
	}
	
	/**
	 * Creates a new rectangle from a given rectangle.
	 * 
	 * @param rect Rectangle object
	 */
	public Rectangle(Rectangle rect) {
		super(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight(),rect.getColour());
	}
}
