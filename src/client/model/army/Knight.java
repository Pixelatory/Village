package client.model.army;

import client.engine.renderPrimitives.Circle;
import client.model.statics.AttackRadius;
import client.model.statics.AttackSpeed;
import client.model.statics.AttackStrength;
import client.model.statics.GoldCosts;
import client.model.statics.IronCosts;
import client.model.statics.MaxHealthPoints;
import client.model.statics.MaxLevels;
import client.model.statics.MovementSpeed;
import client.model.statics.UpgradeTimes;
import client.model.statics.WoodCosts;
import client.utility.Area;

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
		this.area = new Area(5,5);
		this.circle = new Circle(xPos,yPos,area.getWidth(),new Color(0, 197, 119,255));
		this.name = "Knight";
	}

	@Override
	public int attackRadius() {
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

	@Override
	public Object clone() {
		Knight k = new Knight(xPos(),yPos());
		k.setLevel(level());
		return k;
	}
}
