package engine.gfx;

/**
 * Class which loads an Image representing a Font.
 * @author 6177000
 * @see Image
 */
public class Font {
	
	/**
	 * Standard font.
	 */
	public static final Font STANDARD = new Font("/fonts/font-2.png");
	
	private Image fontImage;
	private int[] offsets;
	private int[] widths;
	
	/**
	 * Given the path, will load the image which represents the font.
	 * 
	 * @param path string value
	 */
	public Font(String path) {
		fontImage = new Image(path,0,0);
		offsets = new int[256];
		widths = new int[256];
		
		int unicode = 0;
		
		for(int i=0;i<fontImage.getWidth();i++) {
			if(fontImage.getP()[i] == 0xff0000ff) { // blue pixel means start of letter
				offsets[unicode] = i;
			}
			
			if(fontImage.getP()[i] == 0xffffff00) { // yellow pixel means end of letter
				widths[unicode] = i - offsets[unicode];
				unicode++;
			}
		}
	}

	/**
	 * Returns the Image that represents the Font.
	 * 
	 * @return an Image
	 */
	public Image getFontImage() {
		return fontImage;
	}

	/**
	 * Returns the offsets of each unicode letter in the font image.
	 * 
	 * @return int array
	 */
	public int[] getOffsets() {
		return offsets;
	}

	/**
	 * Returns the widths of the letters in the font image.
	 * 
	 * @return int array
	 */
	public int[] getWidths() {
		return widths;
	}
}
