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
 * Class representing a Catapult.
 * 
 * @author 6177000
 * @see Combatant
 *
 */
public class Catapult extends Combatant {

	/**
	 * Constructor that calls the constructor in combatant.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see Combatant
	 */
	public Catapult(int xPos, int yPos) {
		super(xPos, yPos);
	}

	@Override
	public float attackStrength() {
		return AttackStrength.CATAPULT[level() - 1];
	}

	@Override
	public float attackRadius() {
		return AttackRadius.CANNON[level() - 1];
	}

	@Override
	public float attackSpeed() {
		return AttackSpeed.CATAPULT[level() - 1];
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.CATAPULT[level() - 1];
	}

	@Override
	public float movementSpeed() {
		return MovementSpeed.CATAPULT[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.CATAPULT;
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.CATAPULT[level - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.CATAPULT[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.CATAPULT[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.CATAPULT[level - 1];
	}
}
