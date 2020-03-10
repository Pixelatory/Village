package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Basic game container.<br>
 * Has a window, renders to window, updates the game, and controls input.
 * 
 * @author 6177000
 * @see GameContainer
 * @see KeyListener
 * @see MouseListener
 * @see MouseMotionListener
 * @see MouseWheelListener
 *
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private GameContainer gc;
	
	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];
	
	private final int NUM_BUTTONS = 6;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];
	
	private int mouseX, mouseY;
	private int scroll;
	
	/**
	 * Initializes the mouse position and scroll wheel movement, sets the GameContainer that the input
	 * is attached to, and adds a keylistener, mouselistener, mousemotionlistener, and mousewheel listener to it.
	 * 
	 * @param gc GameContainer object
	 */
	public Input(GameContainer gc) {
		this.gc = gc;
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
		
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
	}
	
	/**
	 * Updates the input values.
	 */
	public void update() {
		for(int i=0;i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
		}
		
		for(int i=0;i < NUM_BUTTONS; i++) {
			buttonsLast[i] = buttons[i];
		}
		
		scroll = 0;
	}
	
	/**
	 * Given a keycode, if the key is down returns true, else false.
	 * 
	 * @param keyCode int value
	 * @return boolean value
	 */
	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}
	
	/**
	 * Given a keycode, returns true if it isn't down in the current frame, but it was down in the previous one.
	 * 
	 * @param keyCode int value
	 * @return boolean value
	 */
	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	/**
	 * Given a keycode, returns true if it is down in the current frame, but it was up in the previous one.
	 * 
	 * @param keyCode int value
	 * @return boolean value
	 */
	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	// CHECKING MOUSE BUTTONS
	
	/**
	 * Given a buttoncode, if the button is down returns true, else false.
	 * 
	 * @param buttonCode int value
	 * @return boolean value
	 */
	public boolean isButton(int buttonCode) {
		return buttons[buttonCode];
	}
	
	/**
	 * Given a buttoncode, returns true if it isn't down in the current frame, but it was down in the previous one.
	 * 
	 * @param keyCode int value
	 * @return boolean value
	 */
	public boolean isButtonUp(int buttonCode) {
		return !buttons[buttonCode] && buttonsLast[buttonCode];
	}
	
	/**
	 * Given a buttoncode, returns true if it is down in the current frame, but it was up in the previous one.
	 * 
	 * @param buttonCode int value
	 * @return boolean value
	 */
	public boolean isButtonDown(int buttonCode) {
		return buttons[buttonCode] && !buttonsLast[buttonCode];
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int)(e.getX() / gc.getScale());
		mouseY = (int)(e.getY() / gc.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int)(e.getX() / gc.getScale());
		mouseY = (int)(e.getY() / gc.getScale());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the x position of the mouse.
	 * 
	 * @return int value
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * Returns the y position of the mouse.
	 * 
	 * @return int value
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * Returns the mouse scroll wheel value.
	 * 
	 * @return int value
	 */
	public int getScroll() {
		return scroll;
	}
}
