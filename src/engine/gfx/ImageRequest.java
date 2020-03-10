package engine.gfx;

/**
 * Class which helps with controlling Image z-depth.
 * 
 * @author 6177000
 * @see Image
 */
public class ImageRequest {
	private Image image;
	private int zDepth, offsetX, offsetY;
	
	/**
	 * Sets the class values.
	 * 
	 * @param image Image object
	 * @param zDepth int value
	 * @param offsetX int value
	 * @param offsetY int value
	 */
	public ImageRequest(Image image, int zDepth, int offsetX, int offsetY) {
		this.image = image;
		this.zDepth = zDepth;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	/**
	 * Returns the Image object.
	 *
	 * @return Image object
	 */
	public Image getImage() {
		return image;
	}

	
	/**
	 * Returns the z depth value of the ImageRequest
	 * 
	 * @return int value
	 */
	public int getzDepth() {
		return zDepth;
	}

	/**
	 * Returns the x position of the ImageRequest
	 * 
	 * @return int value
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * Returns the y position of the ImageRequest
	 * 
	 * @return int value
	 */
	public int getOffsetY() {
		return offsetY;
	}
}
