package model.buildings;

import engine.renderPrimitives.Rectangle;
import model.habitants.Worker;
import model.statics.*;

import java.awt.*;

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
		setArea(25,25);
		Rectangle rect = new Rectangle(xPos(), yPos(), width(), height(), new Color(100,175,25,255));
		setDefaultRect(rect);
		Rectangle upgradingRect = new Rectangle(xPos(), yPos(), width(), height(), Color.gray);
		setUpgradingRect(upgradingRect);
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
	public Object clone() {
		Farm f = new Farm(xPos(),yPos());
		f.setLevel(level());
		return f;
	}
}
