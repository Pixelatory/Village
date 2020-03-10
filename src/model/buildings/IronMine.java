package model.buildings;

import java.awt.Color;

import engine.renderPrimitives.Rectangle;
import model.habitants.IronMiner;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.UpgradeTimes;
import model.statics.WoodCosts;
import model.statics.WorkerCapacities;
import utility.Area;

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
		super(xPos,yPos);
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), new Color(114,110,108,255));
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
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

}
