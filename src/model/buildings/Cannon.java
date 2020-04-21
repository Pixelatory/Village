package model.buildings;

import engine.renderPrimitives.Rectangle;
import model.statics.*;

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
		setArea(25,25);
		Rectangle rect = new Rectangle(xPos(), yPos(), width(), height(), Color.red);
		setDefaultRect(rect);
		Rectangle upgradingRect = new Rectangle(xPos(), yPos(), width(), height(), Color.gray);
		setUpgradingRect(upgradingRect);
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

	@Override
	public Object clone() {
		Cannon c = new Cannon(xPos(),yPos());
		c.setLevel(level());
		return c;
	}
}
