package model.buildings;

import java.awt.Color;

import engine.renderPrimitives.Rectangle;
import model.habitants.Lumberman;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.UpgradeTimes;
import model.statics.WoodCosts;
import model.statics.WorkerCapacities;
import utility.Area;

/**
 * Class representing a Lumber Mill.
 * 
 * @author 6177000
 * @see ProductionBuilding
 */
public final class LumberMill extends ProductionBuilding<Lumberman> {

	/**
	 * Calls the ProductionBuilding constructor, as well as sets the area, creates the rectangle representing a Lumber Mill, and a rectangle 
	 * representing the Lumber Mill while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see ProductionBuilding
	 */
	public LumberMill(int xPos, int yPos) {
		super(xPos, yPos);
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), new Color(63,25,0,255));	
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
	}

	@Override
	public int workerCapacity() {
		return WorkerCapacities.LUMBER_MILL[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.LUMBER_MILL;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.LUMBERMILL[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.LUMBER_MILL[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.LUMBER_MILL[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.LUMBER_MILL[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.LUMBERMILL[level - 1];
	}

	@Override
	public void addWorker() {
		if(canAddWorker())
			workers.add(new Lumberman());
	}

	@Override
	public String getName() {
		return "Lumber Mill";
	}
}
