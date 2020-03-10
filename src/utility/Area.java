package utility;

/**
 * Class which encompasses everything that takes up an area, what it needs.
 * 
 * @author 6177000
 *
 */
public class Area {
	
	int width, height;
	
	public Area(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getArea() {
		return height * width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
