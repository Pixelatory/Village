package model.buildings;

import java.awt.Color;

import engine.renderPrimitives.Rectangle;
import model.habitants.GoldMiner;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.UpgradeTimes;
import model.statics.WoodCosts;
import model.statics.WorkerCapacities;
import utility.Area;

/**
 * Class representing a Gold Mine.
 * 
 * @author 6177000
 * @see MiningProduction
 */
public final class GoldMine extends MiningProduction<GoldMiner> {
	
	/**
	 * Calls the MiningProduction constructor, as well as sets the area, creates the rectangle representing a Gold Mine, and a rectangle 
	 * representing the Gold Mine while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see MiningProduction
	 */
	public GoldMine(int xPos, int yPos) {
		super(xPos, yPos);
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), new Color(255,196,44,255));
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
	}
	
	@Override
	public int workerCapacity() {
		return WorkerCapacities.GOLD_MINE[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.GOLD_MINE;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.GOLD_MINE[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.GOLD_MINE[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.GOLD_MINE[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.GOLD_MINE[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.GOLD_MINE[level - 1];
	}

	@Override
	public void addWorker() {
		if(canAddWorker()) {
			workers.add(new GoldMiner());
		}
	}

	@Override
	public String getName() {
		return "Gold Mine";
	}
}
