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
		this.area = new Area(7,7);
		this.circle = new Circle(xPos,yPos,area.getWidth(),new Color(197, 60, 0,255));
		this.name = "Catapult";
	}

	@Override
	public float attackStrength() {
		return AttackStrength.CATAPULT[level() - 1];
	}

	@Override
	public int attackRadius() {
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

	@Override
	public Object clone() {
		Catapult c = new Catapult(xPos(),yPos());
		c.setLevel(level());
		return c;
	}
}
