package client.model.buildings;

import client.engine.renderPrimitives.Rectangle;
import client.model.habitants.IronMiner;
import client.model.statics.*;

import java.awt.*;

/**
 * Class representing an Iron Mine.
 * 
 * @author 6177000
 * @see MiningProduction
 */
public final class IronMine extends MiningProduction<IronMiner> {

	/**
	 * Calls the MiningProduction constructor, as well as sets the area, creates the rectangle representing an Iron Mine, and a rectangle 
	 * representing the Iron Mine while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see MiningProduction
	 */
	public IronMine(int xPos, int yPos) {
		super(xPos, yPos);
		setArea(25,25);
		Rectangle rect = new Rectangle(xPos(), yPos(), width(), height(), new Color(114,110,108,255));
		setDefaultRect(rect);
		Rectangle upgradingRect = new Rectangle(xPos(), yPos(), width(), height(), Color.gray);
		setUpgradingRect(upgradingRect);
	}
	
	@Override
	public int workerCapacity() {
		return WorkerCapacities.IRON_MINE[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.IRON_MINE;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.IRON_MINE[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.IRON_MINE[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.IRON_MINE[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.IRON_MINE[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.IRON_MINE[level - 1];
	}

	@Override
	public void addWorker() {
		if(canAddWorker())
			workers.add(new IronMiner());
	}

	@Override
	public String getName() {
		return "Iron Mine";
	}

	@Override
	public Object clone() {
		IronMine i = new IronMine(xPos(),yPos());
		i.setLevel(level());
		return i;
	}

}
