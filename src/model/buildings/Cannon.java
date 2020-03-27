package model.buildings;

import engine.renderPrimitives.Rectangle;
import model.statics.*;
import utility.Area;

import java.awt.*;

/**
 * Class representing an Cannon.
 * 
 * @author 6177000
 * @see DefensiveBuilding
 */
public final class Cannon extends DefensiveBuilding {

	/**
	 * Calls the DefensiveBuilding constructor, as well as sets the area, creates the rectangle representing a Cannon, and a rectangle 
	 * representing the Cannon while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see DefensiveBuilding
	 */
	public Cannon(int xPos, int yPos) {
		super(xPos, yPos);
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), Color.red);
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
	}
	
	@Override
	public float attackStrength() {
		return AttackStrength.CANNON[level() - 1];
	}

	@Override
	public int attackRadius() {
		return AttackRadius.CANNON[level() - 1];
	}

	@Override
	public float attackSpeed() {
		return AttackSpeed.CANNON[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.CANNON;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.CANNON[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.CANNON[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.CANNON[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.CANNON[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.CANNON[level - 1];
	}

	@Override
	public String getName() {
		return "Cannon";
	}
	
}
