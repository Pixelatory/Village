package model.buildings;

import java.awt.Color;

import engine.renderPrimitives.Rectangle;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.WoodCosts;
import utility.Area;

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
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), Color.white);
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
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
	public void setLevel(int level) {
		this.level = level;
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
}
