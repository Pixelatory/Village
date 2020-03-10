package model.buildings;

import java.awt.Color;

import engine.renderPrimitives.Rectangle;
import model.habitants.Worker;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.UpgradeTimes;
import model.statics.WoodCosts;
import model.statics.WorkerCapacities;
import utility.Area;

/**
 * Class representing a Farm.
 * 
 * @author 6177000
 * @see FoodProduction
 */
public final class Farm extends FoodProduction {

	/**
	 * Calls the FoodProduction constructor, as well as sets the area, creates the rectangle representing a Farm, and a rectangle 
	 * representing the Farm while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see FoodProduction
	 */
	public Farm(int xPos, int yPos) {
		super(xPos, yPos);
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), new Color(100,175,25,255));
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
	}

	@Override
	public int workerCapacity() {
		return WorkerCapacities.FARM[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.FARM;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.FARM[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.FARM[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.FARM[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.FARM[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.FARM[level - 1];
	}

	@Override
	public void addWorker() {
		if(canAddWorker())
			workers.add(new Worker());
	}

	@Override
	public String getName() {
		return "Farm";
	}

	@Override
	public void takeDamage(float amount) {
		// TODO Auto-generated method stub
		
	}
}
