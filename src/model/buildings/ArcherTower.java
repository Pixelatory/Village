package model.buildings;

import java.awt.Color;

import engine.renderPrimitives.Rectangle;
import model.statics.AttackRadius;
import model.statics.AttackSpeed;
import model.statics.AttackStrength;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.UpgradeTimes;
import model.statics.WoodCosts;
import utility.Area;

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
		this.area = new Area(25,25);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), Color.yellow);
		this.upgradingRect = new Rectangle(this.rect);
		this.upgradingRect.setColour(Color.gray);
	}

	@Override
	public float attackStrength() {
		return AttackStrength.ARCHER_TOWER[level() - 1];
	}

	@Override
	public float attackRadius() {
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
	
}
