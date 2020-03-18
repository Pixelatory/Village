package engine.renderPrimitives;

import java.awt.Color;

/**
 * Class which encompasses everything a Circle object will need.
 *
 * @author 6177000
 */
public class Circle extends Shape {
    /**
     * Sets the parameter values in the respective instance variables.
     *
     * @param x int value
     * @param y int value
     * @param radius int value
     * @param colour Color object
     */
    public Circle(int x, int y, int radius, Color colour) {
        super(x,y,radius,colour);
    }

    /**
     * Creates a new circle from a given circle.
     *
     * @param circle Circle object
     */
    public Circle(Circle circle) {
        super(circle.getX(),circle.getY(),circle.getRadius(),circle.getColour());
    }
}
