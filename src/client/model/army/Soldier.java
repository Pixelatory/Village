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
		this.area = new Area(5,5);
		this.circle = new Circle(xPos,yPos,area.getWidth(),new Color(145, 0, 197,255));
		this.name = "Soldier";
	}

	@Override
	public int attackRadius() {
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

	@Override
	public Object clone() {
		Soldier s = new Soldier(xPos(), yPos());
		s.setLevel(level());
		return s;
	}
}
