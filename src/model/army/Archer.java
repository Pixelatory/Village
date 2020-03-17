package model.army;

import engine.renderPrimitives.Rectangle;
import model.statics.AttackRadius;
import model.statics.AttackSpeed;
import model.statics.AttackStrength;
import model.statics.GoldCosts;
import model.statics.IronCosts;
import model.statics.MaxHealthPoints;
import model.statics.MaxLevels;
import model.statics.MovementSpeed;
import model.statics.UpgradeTimes;
import model.statics.WoodCosts;
import utility.Area;

import java.awt.*;

/**
 * Class representing an Archer.
 * 
 * @author 6177000
 * @see Combatant
 *
 */
public class Archer extends Combatant {
	
	/**
	 * Constructor that calls the constructor in combatant.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see Combatant
	 */
	public Archer(int xPos, int yPos) {
		super(xPos, yPos);
		this.area = new Area(10,10);
		this.rect = new Rectangle(pos.getX(), pos.getY(), area.getWidth(), area.getHeight(), new Color(138, 94, 0,255));
	}

	@Override
	public float attackRadius() {
		return AttackRadius.ARCHER;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.ARCHER[level() - 1];
	}

	@Override
	public float attackStrength() {
		return AttackStrength.ARCHER[level() - 1];
	}

	@Override
	public float attackSpeed() {
		return AttackSpeed.ARCHER[level() - 1];
	}

	@Override
	public float movementSpeed() {
		return MovementSpeed.ARCHER[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.ARCHER;
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.ARCHER[level - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.ARCHER[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.ARCHER[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.ARCHER[level - 1];
	}
}
