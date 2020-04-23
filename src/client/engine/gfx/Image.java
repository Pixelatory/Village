package client.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class which loads an Image.
 * @author 6177000
 */
public class Image {
	protected int width,height,x,y;
	private int[] p;
	private boolean isVisible = true;
	
	/**
	 * Given the path, will load the image which represents the font.
	 * 
	 * @param path string value
	 * @param x int value
	 * @param y int value
	 */
	public Image(String path, int x, int y) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
		p = image.getRGB(0, 0, width, height, null, 0, width);
		
		image.flush();
	}
	
	
	/**
	 * Given an array of pixel values, a width, and height, will create an Image from it.
	 * 
	 * @param p int array of pixel values
	 * @param width int value
	 * @param height int value
	 */
	public Image(int[] p, int width, int height) {
		this.p = p;
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the width of the image.
	 * 
	 * @return int value
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the image.
	 * 
	 * @return int value
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the pixel array of the image.
	 * 
	 * @return int array
	 */
	public int[] getP() {
		return p;
	}
	
	/**
	 * Returns a specific pixel value within the pixel array.
	 * 
	 * @param index int value
	 * @return int value
	 */
	public int getP(int index) {
		return p[index];
	}

	/**
	 * Returns a value representing if the Image is visible or not.
	 * 
	 * @return boolean value
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Sets if this Image is visible or not.
	 * 
	 * @param isVisible boolean value
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Returns the x position of the Image.
	 * 
	 * @return int value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x position of the Image.
	 * 
	 * @param x int value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the y position of the Image.
	 * 
	 * @return int value
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y position of the Image.
	 * 
	 * @param y int value
	 */
	public void setY(int y) {
		this.y = y;
	}
}
