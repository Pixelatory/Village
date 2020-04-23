package client.engine.renderPrimitives;

import java.awt.*;

/**
 * Class which encompasses everything a Shape object will need.
 *
 * @author 6177000
 */
class Shape {
    private int x,y,width,height,radius;
    private Color colour;
    private boolean isVisible = true;

    public Shape(int x, int y, int width, int height, Color colour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    public Shape(int x, int y, int radius, Color colour) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.colour = colour;
    }

    /**
     * Returns the x position of the shape.
     *
     * @return int value
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the shape.
     *
     * @return int value
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the colour of the shape.
     *
     * @return Color object
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Returns the radius of the shape (mainly used for circles).
     *
     * @return int value
     */
    public int getRadius() {
        if(radius > 0)
            return radius;
        return 0;
    }

    /**
     * Returns the width of the shape.
     *
     * @return int value
     */
    public int getWidth() {
        if(width > 0)
            return width;
        return 0;
    }

    /**
     * Returns the height of the shape.
     *
     * @return int value
     */
    public int getHeight() {
        if(height > 0)
            return height;
        return 0;
    }

    /**
     * Sets the x value of the shape.
     *
     * @param x int value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y value of the shape.
     *
     * @param y int value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns if the shape is visible or not.
     *
     * @return boolean value
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Sets if the shape is visible.
     *
     * @param isVisible boolean value
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Sets the width of the shape.
     *
     * @param width int value
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the shape.
     *
     * @param height int value
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the colour of the shape.
     *
     * @param colour Color object
     */
    public void setColour(Color colour) {
        this.colour = colour;
    }
}
