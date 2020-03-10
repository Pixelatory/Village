package model.army;

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

/**
 * Class representing a Soldier.
 * 
 * @author 6177000
 * @see Combatant
 *
 */
public class Soldier extends Combatant {

	/**
	 * Constructor that calls the constructor in combatant.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see Combatant
	 */
	public Soldier(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	public float attackRadius() {
		return AttackRadius.SOLDIER;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.SOLDIER[level() - 1];
	}

	@Override
	public float attackStrength() {
		return AttackStrength.SOLDIER[level() - 1];
	}

	@Override
	public float attackSpeed() {
		return AttackSpeed.SOLDIER[level() - 1];
	}

	@Override
	public float movementSpeed() {
		return MovementSpeed.SOLDIER[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.SOLDIER;
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.SOLDIER[level - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.SOLDIER[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.SOLDIER[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.SOLDIER[level - 1];
	}
}
