package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Creates a basic GUI JFrame window that allows for images and graphics to be drawn onto it.
 * 
 * @author 6177000
 * @see Renderer
 * @see GameContainer
 *
 */
public class Window {
	private JFrame frame;
	private BufferedImage image;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;
	
	/**
	 * Initializes everything.
	 * 
	 * @param gc GameContainer
	 */
	public Window(GameContainer gc) {
		this.image = new BufferedImage(GameContainer.getWidth(), GameContainer.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.canvas = new Canvas();
		Dimension dim = new Dimension((int) (GameContainer.getWidth() * gc.getScale()), (int) (GameContainer.getHeight() * gc.getScale()));
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		
		this.frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	/**
	 * Draw the image into the canvas, and show it using the buffer strategy defined in constructor.
	 */
	public void update() {
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
	}

	public BufferedImage getImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Graphics getG() {
		return g;
	}
}
