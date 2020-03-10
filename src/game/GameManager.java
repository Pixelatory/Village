package game;

import model.buildings.*;
import controller.Controller;
import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.audio.Sound;
import engine.gfx.Font;
import engine.gfx.Image;
import engine.renderPrimitives.Rectangle;
import model.habitants.ProductionHabitant;
import model.Model;
import view.View;
import model.village.Village;

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
	
	private Font regularFont = new Font("/fonts/font-2.png");
	private Font smallFont = new Font("/fonts/font-3.png");
	private Village village;
	private Building selectedNewConstruction = null;
	private Building selectedForUpgrade = null;
	private boolean buildMode = false;
	private boolean upgradeMode = false;
	private boolean trainingMode = false;
	private int mouseX;
	private int mouseY;
	
	// In order to accurately move the model.buildings without it "jumping" to the center of them, just get where you clicked them at
	private int mouseClickedX;
	private int mouseClickedY;
	
	private Rectangle toolbar = new Rectangle(0,GameContainer.getHeight() - 50, GameContainer.getWidth(), 50, Color.gray);

	// Images
	private Image backgroundImage = new Image("/grass.png",0,0);
	private Image buildIcon = new Image("/icons/build.png", toolbar.getX() + 5, toolbar.getY() + 16);
	private Image upgradeIcon = new Image("/icons/upgrade.png", toolbar.getX() + 5, toolbar.getY() + 16);
	private Image upgradeTroopIcon = new Image("/icons/upgradeTroop.png", toolbar.getX() + 145, toolbar.getY() + 16);
	private Image trainIcon = new Image("/icons/train.png",toolbar.getX() + 75, toolbar.getY() + 16);
	private Image trainCombatantIcon = new Image("/icons/trainCombatant.png", toolbar.getX() + 75, toolbar.getY() + 16);
	
	// Used for the build bar
	private Rectangle archerTowerSymbol = new ArcherTower(toolbar.getX() + 25,toolbar.getY() + 15).getRect();
	private Rectangle cannonSymbol = new Cannon(toolbar.getX() + 100, toolbar.getY() + 15).getRect();
	private Rectangle farmSymbol = new Farm(toolbar.getX() + 175, toolbar.getY() + 15).getRect();
	private Rectangle goldMineSymbol = new GoldMine(toolbar.getX() + 250, toolbar.getY() + 15).getRect();
	private Rectangle ironMineSymbol = new IronMine(toolbar.getX() + 325, toolbar.getY() + 15).getRect();
	private Rectangle lumbermillSymbol = new LumberMill(toolbar.getX() + 400, toolbar.getY() + 15).getRect();

	Controller controller;
	Model model;
	View view;
	
	// Used for combatant training bar
	
	public GameManager() {
		village = new Village();
		toolbar.setVisible(true);
		new Sound("/music/main-theme.wav").loop();

		this.controller = new Controller();
		this.model = new Model();
		this.view = new View();
	}
	
	float imageTileRate = 0;
	
	interface BuildingOperation {
		void operation(Building b);
	}
	
	interface SoundPlay {
		void play(String file);
	}
	
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
		r.setzDepth(0);
		r.drawImage(backgroundImage); // show the background image
		r.setzDepth(1);
		for(Building b : village.getBuildings()) { // display all model.village model.buildings
			r.drawRect(b.getRect());
			r.drawText(Float.toString(b.HP()), smallFont.getFontImage(), b.xPos(), b.yPos(), Color.black);
		}
		
		if(selectedNewConstruction != null) { // after selecting a building from building mode, then display it on cursor
			r.setzDepth(2);
			r.drawRect(selectedNewConstruction.getRect());
		}
		
		if(toolbar.isVisible() 
		&& !buildMode
		&& !upgradeMode
		&& !trainingMode) { // DEFAULT TOOLBAR
			r.setzDepth(Integer.MAX_VALUE);
			r.drawText("Build", smallFont.getFontImage(), buildIcon.getX() - 2, toolbar.getY() + 1, Color.white);
			r.drawImage(buildIcon);
			r.drawText("Train Army", smallFont.getFontImage(), trainCombatantIcon.getX() - 18, toolbar.getY() + 1, Color.white);
			r.drawImage(trainCombatantIcon);
		}
		
		if(toolbar.isVisible() && buildMode) { // BUILD MODE TOOLBAR
			r.setzDepth(Integer.MAX_VALUE);
			r.drawRect(archerTowerSymbol);
			r.drawRect(cannonSymbol);
			r.drawRect(farmSymbol);
			r.drawRect(goldMineSymbol);
			r.drawRect(ironMineSymbol);
			r.drawRect(lumbermillSymbol);
			r.drawText("Archer Tower", smallFont.getFontImage(), toolbar.getX(), toolbar.getY(), Color.white);
			r.drawText("Cannon", smallFont.getFontImage(), toolbar.getX() + 90, toolbar.getY(), Color.white);
			r.drawText("Farm", smallFont.getFontImage(), toolbar.getX() + 172, toolbar.getY(), Color.white);
			r.drawText("Gold Mine", smallFont.getFontImage(), toolbar.getX() + 228, toolbar.getY(), Color.white);
			r.drawText("Iron Mine", smallFont.getFontImage(), toolbar.getX() + 308, toolbar.getY(), Color.white);
			r.drawText("Lumbermill", smallFont.getFontImage(), toolbar.getX() + 378, toolbar.getY(), Color.white);
		}
		
		if(toolbar.isVisible() && upgradeMode) { // UPGRADE MODE TOOLBAR
			r.setzDepth(Integer.MAX_VALUE);
			r.drawImage(upgradeIcon);
			r.drawText("Upgrade", smallFont.getFontImage(), toolbar.getX(), toolbar.getY(), Color.white);
			
			if(selectedForUpgrade instanceof ProductionBuilding) {
				@SuppressWarnings("rawtypes")
				ProductionBuilding b = (ProductionBuilding) selectedForUpgrade;
				r.drawImage(trainIcon);
				r.drawText("Train", smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY(), Color.white);
				
				r.drawImage(upgradeTroopIcon);
				r.drawText("Upgrade", smallFont.getFontImage(), toolbar.getX() + 135, toolbar.getY(), Color.white);
				
				r.drawText(selectedForUpgrade.getName(), smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY(), Color.white);
				
				if(b.numOfWorkers() < b.workerCapacity())
					r.drawText("Workers: " + b.numOfWorkers(), smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 12, Color.white);
				else
					r.drawText("Workers: " + b.numOfWorkers() + " (MAX)", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 12, Color.white);
				
				if(b.numOfWorkers() > 0) {
					ProductionHabitant h = b.getLowestLevelWorker();
					if(h.level() == h.maxLevel())
						r.drawText("Lowest Worker Level: " + b.getLowestLevelWorker().level() + " (MAX)", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 24, Color.white);
					else
						r.drawText("Lowest Worker Level: " + b.getLowestLevelWorker().level(), smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 24, Color.white);
				}
				
				if(b.isUpgrading())
					r.drawText("Upgrading building...", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 36, Color.white);
				
				if(b.isTraining())
					r.drawText("Training worker...", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 36, Color.white);
			} else {
				r.drawText(selectedForUpgrade.getName(), smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY(), Color.white);
			}
		}
		
		if(toolbar.isVisible() && trainingMode) {
			
		}
		
		r.setzDepth(Integer.MAX_VALUE);
		r.drawText("Gold: " + village.getGold(), regularFont.getFontImage(), 0, 15, Color.WHITE);
		r.drawText("Iron: " + village.getIron(), regularFont.getFontImage(), 0, 30, Color.WHITE);
		r.drawText("Wood: " + village.getWood(), regularFont.getFontImage(), 0, 45, Color.WHITE);
		r.setzDepth(Integer.MAX_VALUE - 1);
		r.drawRect(toolbar);
		
		/* this is here as extra to help me reference stuff like drawing images, rectangles, and tiled images. Just for the future.
		r.setzDepth(4);
		r.drawRect(100, 100, 50, 50, new Color(255, 0, 0, 255));
		r.setzDepth(3);
		r.drawImage(marioGhost, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		r.setzDepth(4);
		r.drawImage(mario, 100, 100);
		r.setzDepth(0);
		r.drawImageTile(image, gc.getInput().getMouseX()-16, gc.getInput().getMouseY()-16,(int) imageTileRate % 5,(int)(imageTileRate / 5) % 5);*/
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