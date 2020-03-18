package model.army;

import engine.renderPrimitives.Circle;
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
 * Class representing a Knight.
 * 
 * @author 6177000
 * @see Combatant
 *
 */
public class Knight extends Combatant {

	/**
	 * Constructor that calls the constructor in combatant.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see Combatant
	 */
	public Knight(int xPos, int yPos) {
		super(xPos, yPos);
		this.area = new Area(2,2);
		this.circle = new Circle(xPos,yPos,area.getWidth(),new Color(0, 197, 119,255));
	}

	@Override
	public float attackRadius() {
		return AttackRadius.KNIGHT;
	}

	@Override
	public float maxHP() {
		return MaxHealthPoints.KNIGHT[level() - 1];
	}

	@Override
	public float attackStrength() {
		return AttackStrength.KNIGHT[level() - 1];
	}

	@Override
	public float attackSpeed() {
		return AttackSpeed.KNIGHT[level() - 1];
	}

	@Override
	public float movementSpeed() {
		return MovementSpeed.KNIGHT[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.KNIGHT;
	}

	@Override
	public int ironCost(int level) {
		return IronCosts.KNIGHT[level - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.KNIGHT[level - 1];
	}

	@Override
	public int woodCost(int level) {
		return WoodCosts.KNIGHT[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.KNIGHT[level - 1];
	}

}
