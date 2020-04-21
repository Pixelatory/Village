package model.buildings;

import engine.renderPrimitives.Rectangle;
import model.statics.*;

import java.awt.*;

/**
 * Class representing a Village Hall.
 * 
 * @author 6177000
 * @see Building
 */
public final class VillageHall extends Building {
	
	/**
	 * Calls the Building constructor, as well as sets the area, creates the rectangle representing a Village Hall, and a rectangle 
	 * representing the Village Hall while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see Building
	 */
	public VillageHall(int xPos, int yPos) {
		super(xPos, yPos);
		setArea(25,25);
		Rectangle rect = new Rectangle(xPos(), yPos(), width(), height(), Color.white);
		setDefaultRect(rect);
		Rectangle upgradingRect = new Rectangle(xPos(), yPos(), width(), height(), Color.gray);
		setUpgradingRect(upgradingRect);
	}
	
	@Override
	public float maxHP() {
		return MaxHealthPoints.VILLAGE_HALL;
	}

	@Override
	public int maxLevel() {
		return MaxLevels.VILLAGE_HALL;
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.VILLAGE_HALL[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.VILLAGE_HALL[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.VILLAGE_HALL[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return 0;
	}

	@Override
	public String getName() {
		return "Village Hall";
	}

	@Override
	public Object clone() {
		VillageHall v = new VillageHall(xPos(),yPos());
		v.setLevel(level());
		return v;
	}
}
