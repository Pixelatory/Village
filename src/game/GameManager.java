package game;

import controller.Controller;
import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import model.Model;
import model.army.Archer;
import model.buildings.ArcherTower;
import model.village.Village;
import utility.GameState;
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
		Village village = GameState.load();
		if(village == null) {
			village = new Village();
			this.model = new Model(village);
		} else
			this.model = new Model(village);
		this.controller = new Controller();
		this.view = new View();

		GameState.save(village);
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
