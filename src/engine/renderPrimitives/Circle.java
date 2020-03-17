package engine.renderPrimitives;

import java.awt.Color;

/**
 * Class which encompasses everything a Circle object will need.
 *
 * @author 6177000
 */
public class Circle extends Shape {
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
    /*public Circle(int x, int y, int width, int height, Color colour) {
        super(x,y,width,height,colour);
    }*/

    /**
     * Sets the parameter values in the respective instance variables.
     *
     * @param x int value
     * @param y int value
     * @param radius int value
     * @param colour Color object
     */
    public Circle(int x, int y, int radius, Color colour) {
        super(x,y,radius * 2,radius * 2,colour);
    }

    /**
     * Creates a new circle from a given circle.
     *
     * @param circle Circle object
     */
    public Circle(Circle circle) {
        super(circle.getX(),circle.getY(),circle.getWidth(),circle.getHeight(),circle.getColour());
    }
}
