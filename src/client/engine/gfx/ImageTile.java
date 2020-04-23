package client.engine.gfx;

/**
 * Class which helps with controlling Image z-depth.
 * 
 * @author 6177000
 * @see Image
 */
public class ImageTile extends Image {
	private int tileWidth, tileHeight;
	
	/**
	 * Calls the Image constructor, and sets the tile width and tile height.
	 * 
	 * @param path string value
	 * @param tileWidth int value
	 * @param tileHeight int value
	 * @param x int value
	 * @param y int value
	 */
	public ImageTile(String path, int tileWidth, int tileHeight, int x, int y) {
		super(path, x, y);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	/**
	 * Gets a specific image tile, specified by the tile x and y values.
	 * 
	 * @param tileX int value
	 * @param tileY int value
	 * @return Image object
	 * @see Image
	 */
	public Image getImageTile(int tileX, int tileY) {		
		int[] p = new int[tileWidth * tileHeight];
		
		for(int y=0;y<tileHeight;y++) {
			for(int x=0;x<tileWidth;x++) {
				p[x + y * tileWidth] = this.getP((x + tileX * tileWidth) + (y + tileY * tileHeight) * this.getWidth());
			}
		}
		
		return new Image(p, tileWidth, tileHeight);
	}

	/**
	 * Returns the tile width.
	 * 
	 * @return int value
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Returns the tile height.
	 * 
	 * @return int value
	 */
	public int getTileHeight() {
		return tileHeight;
	}
	
}
