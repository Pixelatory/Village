package engine;

import java.awt.Color;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import engine.gfx.Font;
import engine.gfx.Image;
import engine.gfx.ImageRequest;
import engine.gfx.ImageTile;
import engine.renderPrimitives.Rectangle;
import utility.AlphaBlend;

/**
 * Basic Renderer.<br>
 * Controls drawing of graphics and primitives, as well as the z-depth for these.
 * 
 * @author 6177000
 * @see GameContainer
 * @see Image
 * @see ImageTile
 * @see Font
 * @see Rectangle
 *
 */
public class Renderer {
	private Font font = Font.STANDARD;
	private int pixelWidth, pixelHeight;
	private int[] p;
	private int[] zBuffer;
	private ArrayList<ImageRequest> imageQueue = new ArrayList<>();
	
	private int zDepth = 0;
	private boolean processing = false;
	
	/**
	 * Sets the pixel width and height, as well as obtains the pixel data on the window.
	 * Also, initializes the z-level buffer.
	 * 
	 * @param gc GameContainer object
	 */
	public Renderer(GameContainer gc) {
		pixelWidth = GameContainer.getWidth();
		pixelHeight = GameContainer.getHeight();
		p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		zBuffer = new int[p.length];
	}
	
	/**
	 * Clear the render.
	 */
	public void clear() {
		for(int i=0;i<p.length;i++) {
			p[i] = 0;
			zBuffer[i] = 0;
		}	
	}
	
	/**
	 * Used for Image z-depth processing. Sorts the ImageRequest queue, and then processes the elements according to their z-depth.
	 */
	public void process() {
		processing = true;
		
		// Anonymous class that will sort ImageRequest objects by their z value
		Collections.sort(imageQueue, new Comparator<ImageRequest> () {
			@Override
			public int compare(ImageRequest ir1, ImageRequest ir2) {
				if(ir1.getzDepth() < ir2.getzDepth())
					return -1;
				else if (ir1.getzDepth() > ir2.getzDepth())
					return 1;
				return 0;
			}
			
		});
		
		for(ImageRequest ir : imageQueue) {
			setzDepth(ir.getzDepth());
			drawImage(ir.getImage());
		}
		imageQueue.clear();
		
		processing = false;
	}
	
	/**
	 * Sets an individual pixel value depending on the x and y coordinate, as well as the colour value.
	 * 
	 * @param x int value
	 * @param y int value
	 * @param value int value
	 */
	public void setPixel(int x, int y, int value) {
		int alpha = (value >> 24) & 0xff;
		
		// don't try to render if the pixels aren't on the screen, or is opaque
		if((x<0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0)
			return;
		
		int index = x + y * pixelWidth;
		
		if(zBuffer[index] > zDepth)
			return;
		
		zBuffer[index] = zDepth;
		
		if(alpha == 255) {
			p[index] = value;
		} else {
			int pixelColour = p[index];
			Color frontColour = new Color((value >> 16) & 0xff, (value >> 8) & 0xff, value & 0xff);
			Color backColour = new Color((pixelColour >> 16) & 0xff, (pixelColour >> 8) & 0xff, pixelColour & 0xff);
					
			Color colour = AlphaBlend.blend(frontColour, backColour, alpha);
			
			p[index] = (colour.getRed() << 16 | colour.getGreen() << 8 | colour.getBlue());
		}
		
		
	}
	
	/**
	 * Sets an individual pixel value depending on the x and y coordinate, as well as the colour value.
	 * 
	 * @param x int value
	 * @param y int value
	 * @param value Colour object
	 */
	public void setPixel(int x, int y, Color color) {
		setPixel(x,y,color.getRGB());
	}
	
	/**
	 * Draws an image.
	 * 
	 * @param image Image object
	 */
	public void drawImage(Image image) {
		if(image.isVisible() && !processing) {
			imageQueue.add(new ImageRequest(image, zDepth, image.getX(), image.getY()));
			return;
		}
		
		RenderCheck rc = new RenderCheck(image.getX(), image.getY(), image.getWidth(), image.getHeight());
		if(rc.isNoRender())
			return;
		
		for(int y=rc.getNewY();y<rc.getNewHeight();y++) {
			for(int x=rc.getNewX();x<rc.getNewWidth();x++) {
				setPixel(x + image.getX(), y + image.getY(), image.getP(x + y * image.getWidth()));
			}
		}
	}
	
	/**
	 * Draws a string of text on the screen depending on the given fontImage.
	 * 
	 * @param text string value
	 * @param fontImage Image object
	 * @param offsetX int value
	 * @param offsetY int value
	 * @param colour Color object
	 */
	public void drawText(String text, Image fontImage, int offsetX, int offsetY, Color colour) {
		if(fontImage == null)
			fontImage = font.getFontImage();
		
		int offset = 0;
		
		for(int i=0;i<text.length();i++) {
			int unicode = text.codePointAt(i);
			
			for(int y=0; y < fontImage.getHeight();y++) {
				 for(int x=0;x<font.getWidths()[unicode];x++) {
					 if(font.getFontImage().getP((x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()) == 0xffffffff) { // white pixels are the actual letter
						 setPixel(x + offsetX + offset, y + offsetY, colour);
					 }
				 }
			}
			
			offset += font.getWidths()[unicode];
		}
	}
	
	/**
	 * Draws an image tile depending on the x and y coord of the tile.
	 * 
	 * @param image ImageTile object
	 * @param tileX int value
	 * @param tileY int value
	 */
	public void drawImageTile(ImageTile image, int tileX, int tileY) {
		if(image.isVisible() && !processing) {
			imageQueue.add(new ImageRequest(image.getImageTile(tileX, tileY), zDepth, image.getX(), image.getY()));
			return;
		}
		
		RenderCheck rc = new RenderCheck(image.getX(), image.getY(), image.getTileWidth(), image.getTileHeight());
		if(rc.isNoRender())
			return;
		
		for(int y=rc.getNewY();y<rc.getNewHeight();y++) {
			for(int x=rc.getNewX();x<rc.getNewWidth();x++) {
				// if the pixel we have is the bright pink one (full blue and green), then don't print it out, that's a signifier that it's the
				// background of the tileset
				if(image.getP((x + tileX * image.getTileWidth()) + (y + tileY * image.getTileHeight())) * image.getWidth() != 0xffff00ff)
					setPixel(x + image.getX(), y + image.getY(), image.getP((x + tileX * image.getTileWidth()) + (y + tileY * image.getTileHeight()) * image.getWidth()));
			}
		}
	}
	
	/**
	 * Draws a rectangle.
	 * 
	 * @param rect Rectangle object
	 */
	public void drawRect(Rectangle rect) {
		if(!rect.isVisible())
			return;
		
		int offsetX = rect.getX();
		int offsetY = rect.getY();
		int width = rect.getWidth();
		int height = rect.getHeight();
		
		RenderCheck rc = new RenderCheck(offsetX, offsetY, width, height);
		if(rc.isNoRender())
			return;
		
		
		for(int y=rc.getNewY();y<=rc.getNewHeight();y++) {
			for(int x=rc.getNewX();x<=rc.getNewWidth();x++) {
				setPixel(offsetX + x, offsetY + y, rect.getColour());
			}
		}
	}
	
	/**
	 * Local class which checks for redundant rendering (such as off-screen), and will then ignore it completely to stop wasting time.
	 * 
	 * @author 6177000
	 *
	 */
	private class RenderCheck {
		private int newX = 0;
		private int newY = 0;
		private int newWidth = 0;
		private int newHeight = 0;
		private boolean noRender = false;
		
		RenderCheck(int offsetX, int offsetY, int width, int height) {
			this.newWidth = width;
			this.newHeight = height;
			
			if(offsetX < -newWidth ||
			   offsetY < -newHeight ||
			   offsetX >= pixelWidth ||
			   offsetY >= pixelHeight) {
				noRender = true;
				return;
			}
		
			if(offsetX < 0) newX -= offsetX;
			if(offsetY < 0) newY -= offsetY;
			if(offsetX + newWidth >= pixelWidth) newWidth -= newWidth + offsetX - pixelWidth;
			if(offsetY + newHeight >= pixelHeight) newHeight -= newHeight + offsetY - pixelHeight;
		}

		public int getNewX() {
			return newX;
		}

		public int getNewY() {
			return newY;
		}

		public int getNewWidth() {
			return newWidth;
		}

		public int getNewHeight() {
			return newHeight;
		}

		public boolean isNoRender() {
			return noRender;
		}
	}

	/**
	 * Sets the current z-depth of all things that are to be drawn afterwards.
	 * 
	 * param depth int value
	 */
	public void setzDepth(int depth) {
		this.zDepth = depth;
	}
	
	
	/**
	 * Gets the current z-depth.
	 * 
	 * @return int value
	 */
	public int getzDepth() {
		return zDepth;
	}
}
