package engine;

/**
 * Abstract class that represents basic game function.
 * 
 * @author 6177000
 *
 */
public abstract class AbstractGame {
	/**
	 * Updates the game every frame.
	 * 
	 * @param gc GameContainer object
	 * @param dt delta time (float)
	 * 
	 * @see GameContainer
	 */
	public abstract void update(GameContainer gc, float dt);
	
	/**
	 * Renders primitives or graphics every frame.
	 * @param gc GameContainer object
	 * @param r Renderer object
	 * @see GameContainer
	 * @see Renderer
	 */
	public abstract void render(GameContainer gc, Renderer r);
}
