package client.model.buildings;

import client.engine.renderPrimitives.Rectangle;
import client.model.statics.*;

import java.awt.*;

/**
 * Class representing an Archer Tower.
 * 
 * @author 6177000
 * @see DefensiveBuilding
 */
public final class ArcherTower extends DefensiveBuilding {
	
	/**
	 * Calls the DefensiveBuilding constructor, as well as sets the area, creates the rectangle representing an Archer Tower, and a rectangle 
	 * representing the Archer Tower while it's upgrading.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see DefensiveBuilding
	 */
	public ArcherTower(int xPos, int yPos) {
		super(xPos, yPos);
		setArea(25,25);
		Rectangle rect = new Rectangle(xPos(), yPos(), width(), height(), Color.yellow);
		setDefaultRect(rect);
		Rectangle upgradingRect = new Rectangle(xPos(), yPos(), width(), height(), Color.gray);
		setUpgradingRect(upgradingRect);
	}

	@Override
	public float attackStrength() {
		return AttackStrength.ARCHER_TOWER[level() - 1];
	}

	@Override
	public int attackRadius() {
		return AttackRadius.ARCHER_TOWER[level() - 1];
	}

	@Override
	public float attackSpeed() {
		return AttackSpeed.ARCHER_TOWER[level() - 1];
	}
	
	@Override
	public float maxHP() {
		return MaxHealthPoints.ARCHER_TOWER[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.ARCHER_TOWER;
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.ARCHER_TOWER[level - 1];
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.ARCHER_TOWER[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.ARCHER_TOWER[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.ARCHER_TOWER[level - 1];
	}

	@Override
	public String getName() {
		return "Archer Tower";
	}

	@Override
	public Object clone() {
		ArcherTower a = new ArcherTower(xPos(),yPos());
		a.setLevel(level());
		return a;
	}
}
