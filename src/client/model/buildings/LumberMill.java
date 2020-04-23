package client.model.buildings;

import client.engine.renderPrimitives.Rectangle;
import client.model.habitants.Lumberman;
import client.model.statics.*;

import java.awt.*;

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
		setArea(25,25);
		Rectangle rect = new Rectangle(xPos(), yPos(), width(), height(), new Color(63,25,0,255));
		setDefaultRect(rect);
		Rectangle upgradingRect = new Rectangle(xPos(), yPos(), width(), height(), Color.gray);
		setUpgradingRect(upgradingRect);
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

	@Override
	public Object clone() {
		LumberMill l = new LumberMill(xPos(),yPos());
		l.setLevel(level());
		return l;
	}
}
