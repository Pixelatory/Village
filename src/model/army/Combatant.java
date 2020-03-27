package model.army;

import engine.renderPrimitives.Circle;
import model.attack.CombatantDamager;
import model.buildings.Building;
import model.village.Attackable;
import model.village.Movable;
import model.village.Trainable;
import model.village.Upgradable;
import utility.Area;
import utility.Position;

/**
 * An abstract class that encompasses what a Combatant is and does.
 * 
 * @author 6177000
 * @see Catapult
 * @see Archer
 * @see Knight
 * @see Soldier
 */
public abstract class Combatant implements Trainable, Attackable, CombatantDamager<Building>, Upgradable, Movable {
	protected float hp;
	protected Position pos;
	protected int level = 1;
	protected Area area;
	protected Circle circle;
	protected boolean isUpgrading = false;
	protected boolean canAttack = true;
	protected String name;
	protected boolean isPlaced = false;
	
	/**
	 * Sets the initial hp and position values.
	 * 
	 * @param xPos
	 * @param yPos
	 * 
	 * @see model.statics.MaxHealthPoints
	 * @see utility.Position
	 */
	public Combatant(int xPos, int yPos) {
		this.hp = maxHP();
		this.pos = new Position(xPos,yPos);
	}

	public Circle getCircle() {
		return this.circle;
	}
	
	public abstract int upgradeTime(int level);
	
	public void takeDamage(float amount) {
		if (this.hp - amount <= 0)
			this.hp = 0;
		this.hp -= amount;
	}
	
	public void attack(Building building) {
		building.takeDamage(attackStrength());
	}
	
	public int level() {
		return this.level;
	}
	
	public void setUpgrading(boolean b) {
		this.isUpgrading = b;
	}
	
	public boolean canAttack() {
		return canAttack;
	}
	
	public void setCanAttack(boolean value) {
		this.canAttack = value;
	}
	
	public boolean isUpgrading() {
		return isUpgrading;
	}
	
	public void performUpgrade() {
		if(canUpgrade()) {
			this.level++;
		}
	}
	
	public boolean canUpgrade() {
		return level() < maxLevel();
	}
	
	public float HP() {
		return this.hp;
	}
	
	public Position getPosition() {
		return pos;
	}

	public String getName() {
		return this.name;
	}

	public boolean isPlaced() {
		return this.isPlaced;
	}

	public void setPlaced(boolean value) {
		this.isPlaced = value;
	}

	public int xPos() {
		return pos.getX();
	}

	public int yPos() {
		return pos.getY();
	}

	public void setXPos(int xPos) {
		pos.setX(xPos);
		circle.setX(xPos);
	}

	public void setYPos(int yPos) {
		pos.setY(yPos);
		circle.setY(yPos);
	}
}
