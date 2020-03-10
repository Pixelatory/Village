package engine;

import java.awt.Color;

/**
 * Basic game container.<br>
 * Has a window, renders to window, updates the game, and controls input.
 * 
 * @author 6177000
 * @see Window
 * @see Renderer
 * @see AbstractGame
 * @see Input
 * @see Thread
 *
 */
public class GameContainer implements Runnable {
	
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGame game;
	
	private boolean running = false;
	private final double FPS_CAP = 1.0/60.0;
	private static int width = 640, height = 420;
	private float scale = 2f;
	private String title = "Village Game";
	
	/**
	 * Sets the game to the game container.
	 * 
	 * @param game AbstractGame
	 */
	public GameContainer(AbstractGame game) {
		this.game = game;
	}
	
	/**
	 * Starts the thread.
	 */
	public void start() {
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		
		thread = new Thread(this);
		thread.run();
	}
	
	/**
	 * Stops the thread.
	 */
	public void stop() {
		
	}
	
	public void run() {
		running = true;
		boolean render = false;
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int fps = 0;
		int frames = 0;
		
		while(running) {
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= FPS_CAP) {
				unprocessedTime -= FPS_CAP;
				render = true;
				
				game.update(this, (float) FPS_CAP);
				
				input.update();
				
				if(frameTime >= 1) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}
			
			if(render) {
				renderer.clear();
				game.render(this, renderer);
				renderer.process();
				renderer.drawText("fps: " + fps, null, 0, 0, Color.WHITE);
				window.update();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		dispose();
	}
	
	/**
	 * <b>UNIMPLEMENTED</b>
	 * Disposing of stuffs.
	 */
	public void dispose() {
		
	}

	/**
	 * Returns the width of the game.
	 * 
	 * @return int value
	 */
	public static int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the game.
	 * 
	 * @param width int value
	 */
	public void setWidth(int width) {
		GameContainer.width = width;
	}
	
	/**
	 * Returns the height of the game.
	 * 
	 * @return int value
	 */
	public static int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the game.
	 * 
	 * @param height int value
	 */
	public void setHeight(int height) {
		GameContainer.height = height;
	}

	/**
	 * Returns the scaling of the game.
	 * 
	 * @return float value
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Sets the scale of the game.
	 * 
	 * @param scale float value
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}

	/**
	 * Returns the title of the game.
	 * 
	 * @return string value
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the game.
	 * 
	 * @param title string value
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the Window attached to the game.
	 * 
	 * @return Window object
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * Returns the Input attached to the game.
	 * 
	 * @return Input object
	 */
	public Input getInput() {
		return input;
	}
}
