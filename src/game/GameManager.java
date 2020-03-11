package game;

import controller.Controller;
import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.audio.Sound;
import engine.gfx.Font;
import engine.gfx.Image;
import engine.renderPrimitives.Rectangle;
import model.Model;
import model.buildings.*;
import view.View;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Entire game functionality found here.<br>
 * Controls when the sounds, inputs, rendering are used.
 * 
 * @author 6177000
 *
 */
public class GameManager extends AbstractGame {
	Controller controller;
	Model model;
	View view;
	
	// Used for combatant training bar
	
	public GameManager() {
		this.controller = new Controller();
		this.model = new Model();
		this.view = new View();
	}
	
	float imageTileRate = 0;
	
	/**
	 * Every frame, update what's going on.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void update(GameContainer gc, float dt) {
		controller.update(gc,model,dt);
	}

	/**
	 * Every frame, render what's going on to the Window, using Renderer.
	 * @see engine.Window
	 * @see engine.Renderer
	 */
	@Override
	public void render(GameContainer gc, Renderer r) {
		view.render(gc,r,model);
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
	
	@SuppressWarnings("unused")
	private boolean overlap(Rectangle rect1, Rectangle rect2) {
		if(rect1.getX() > rect2.getX() + rect2.getWidth() || rect2.getX() > rect1.getX() + rect1.getWidth())
			return false;
		
		if(rect1.getY() < rect2.getY() + rect2.getHeight() || rect2.getY() < rect1.getY() + rect1.getHeight())
			return false;
		
		return true;
	}
	
	private boolean mouseInBounds(int x, int y, int width, int height) {
		if(mouseX >= x 
		&& mouseX <= width + x
		&& mouseY >= y
		&& mouseY <= height + y)
			return true;
		return false;
	}
	
	private boolean mouseInBounds(Building building) {
		return mouseInBounds(building.xPos(), building.yPos(), building.width(), building.height());
	}
	
	private boolean mouseInBounds(Image image) {
		if(image.isVisible())
			return mouseInBounds(image.getX(), image.getY(), image.getWidth(), image.getHeight());
		return false;
	}
	
	private boolean mouseInBounds(Rectangle rect) {
		if(rect.isVisible())
			return mouseInBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		return false;
	}
	
	private boolean leftClickUp(GameContainer gc) {
		return gc.getInput().isButtonUp(MouseEvent.BUTTON1);
	}
	
	private boolean leftClickDown(GameContainer gc) {
		return gc.getInput().isButtonDown(MouseEvent.BUTTON1);
	}
	
	private boolean holdingLeftClick(GameContainer gc) {
		return gc.getInput().isButton(MouseEvent.BUTTON1);
	}
	
	private boolean rightClickUp(GameContainer gc) {
		return gc.getInput().isButtonUp(MouseEvent.BUTTON3);
	}
}
