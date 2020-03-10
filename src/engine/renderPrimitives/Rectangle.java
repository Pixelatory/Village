package engine.renderPrimitives;

import java.awt.Color;

/**
 * Class which encompasses everything a Rectangle object will need. 
 * 
 * @author 6177000
 */
public class Rectangle {
	private int x,y,width,height;
	private Color colour;
	private boolean isVisible = true;
	
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
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.colour = colour;
	}
	
	/**
	 * Creates a new rectangle from a given rectangle.
	 * 
	 * @param rect Rectangle object
	 */
	public Rectangle(Rectangle rect) {
		this.x = rect.getX();
		this.y = rect.getY();
		this.width = rect.getWidth();
		this.height = rect.getHeight();
		this.colour = rect.getColour();
	}

	/**
	 * Returns the x position.
	 * 
	 * @return int value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y position.
	 * 
	 * @return int value
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the colour of the rectangle.
	 * 
	 * @return Color object
	 */
	public Color getColour() {
		return colour;
	}

	/**
	 * Returns the width.
	 * 
	 * @return int value
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height.
	 * 
	 * @return int value
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the x value of the rectangle.
	 * 
	 * @param x int value
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y value of the rectangle.
	 * 
	 * @param y int value
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns if the rectangle is visible or not.
	 * 
	 * @return boolean value
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Sets if the rectangle is visible.
	 * 
	 * @param isVisible boolean value
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Sets the width of the rectangle.
	 * 
	 * @param width int value
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of the rectangle.
	 * 
	 * @param height int value
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the colour of the rectangle.
	 * 
	 * @param colour Color object
	 */
	public void setColour(Color colour) {
		this.colour = colour;
	}
}
