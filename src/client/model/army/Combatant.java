package client.model.army;

import client.engine.renderPrimitives.Circle;
import client.model.attack.CombatantDamager;
import client.model.attack.Generative;
import client.model.buildings.Building;
import client.model.village.Attackable;
import client.model.village.Movable;
import client.model.village.Trainable;
import client.model.village.Upgradable;
import client.utility.Area;
import client.utility.Position;

/**
 * An abstract class that encompasses what a Combatant is and does.
 * 
 * @author 6177000
 * @see Catapult
 * @see Archer
 * @see Knight
 * @see Soldier
 */
public abstract class Combatant implements Trainable, Attackable, CombatantDamager<Building>, Upgradable, Movable, Generative {
	protected float hp;
	protected Position pos;
	protected int level = 1;
	protected Area area;
	protected Circle circle;
	protected volatile boolean isUpgrading = false;
	protected boolean canAttack = true;
	protected String name;
	protected boolean isPlaced = false;
	private volatile int upgradeTime;

	/**
	 * Sets the initial hp and position values.
	 * 
	 * @param xPos
	 * @param yPos
	 * 
	 * @see client.model.statics.MaxHealthPoints
	 * @see client.utility.Position
	 */
	public Combatant(int xPos, int yPos) {
		this.hp = maxHP();
		this.pos = new Position(xPos,yPos);
	}

	public synchronized int getUpgradeTime() {
		return upgradeTime;
	}

	public synchronized void setUpgradeTime(int upgradeTime) {
		this.upgradeTime = upgradeTime;
	}

	public Circle getCircle() {
		return this.circle;
	}
	
	public abstract int upgradeTime(int level);
	
	public void takeDamage(float amount) {
		if (this.hp - amount <= 0)
			this.hp = 0;
		else
			this.hp -= amount;
	}

	public boolean enemyInSight(Building enemy) {
		int enemyX = enemy.xPos() + (enemy.width() / 2);
		int enemyY = enemy.yPos() + (enemy.height() / 2);

		if(Math.hypot(Math.abs(enemyX - xPos()), Math.abs(enemyY - yPos())) <= attackRadius()) {
			return true;
		}

		return false;
	}

	public abstract int attackRadius();
	
	public void attack(Building building) {
		building.takeDamage(attackStrength());
	}
	
	public int level() {
		return this.level;
	}
	
	public synchronized void setUpgrading(boolean b) {
		this.isUpgrading = b;
	}
	
	public boolean canAttack() {
		return canAttack;
	}
	
	public void setCanAttack(boolean value) {
		this.canAttack = value;
	}
	
	public synchronized boolean isUpgrading() {
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

	public void setLevel(int level) {
		this.level = level;
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

	public abstract Object clone();
}
