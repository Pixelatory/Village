package game;

import controller.Controller;
import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import model.Model;
import view.View;

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
		this.model = new Model();
		this.controller = new Controller();
		this.view = new View();
	}
	
	float imageTileRate = 0;
	
	/**
	 * Every frame, update what's going on.
	 */
	@Override
	public void update(GameContainer gc, float dt) {
		model.update(gc);
		controller.update(gc,model,view,dt);
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
}
