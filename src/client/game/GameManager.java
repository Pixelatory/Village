package client.game;

import client.controller.Controller;
import client.engine.AbstractGame;
import client.engine.GameContainer;
import client.engine.Renderer;
import client.model.Model;
import client.model.village.Village;
import client.utility.GameState;
import client.view.View;
import server.Client;

/**
 * Entire client.game functionality found here.<br>
 * Controls when the sounds, inputs, rendering are used.
 * 
 * @author 6177000
 *
 */
public class GameManager extends AbstractGame {
	private Controller controller;
	private Model model;
	private View view;
	private Client client;
	
	// Used for combatant training bar
	
	public GameManager(Client client) {
		this.client = client;
		while(client.getVillageinfo() == null);
		Village village = GameState.load(client.getVillageinfo());
		this.model = new Model(village);
		this.controller = new Controller();
		this.view = new View();
	}
	
	float imageTileRate = 0;
	
	/**
	 * Every frame, update what's going on.
	 */
	@Override
	public void update(GameContainer gc, float dt) {
		controller.update(gc,model,view,dt);
		UpdateServer s = new UpdateServer(client,model.getVillage());
		s.start();
	}

	private class UpdateServer extends Thread {
		private Client client;
		private Village village;
		public UpdateServer(Client client, Village village) {
			this.client = client;
			this.village = village;
		}

		public void run() {
			client.sendMessageToServer(GameState.toJSONString(village));
		}
	}

	/**
	 * Every frame, render what's going on to the Window, using Renderer.
	 * @see client.engine.Window
	 * @see client.engine.Renderer
	 */
	@Override
	public void render(GameContainer gc, Renderer r) {
		view.render(gc,r,model);
	}
}
