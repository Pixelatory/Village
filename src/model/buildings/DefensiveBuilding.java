package model.buildings;

import model.army.Combatant;
import model.attack.BuildingDamager;
import utility.Position;
import model.village.Attackable;

public abstract class DefensiveBuilding extends Building implements BuildingDamager<Combatant>, Attackable {
	
	protected boolean canAttack = true;

	public DefensiveBuilding(int xPos, int yPos) {
		super(xPos, yPos);
	}
	
	public boolean enemyInSight(Combatant enemy) {
		Position enemyPosition = enemy.getPosition();
		
		if(enemyPosition != null && Math.hypot(Math.abs(enemyPosition.getX() - xPos()), Math.abs(enemyPosition.getY() - yPos())) <= attackRadius()) {
			return true;
		}
		
		return false;
	}
	
	public void attack(Combatant enemy) {
		enemy.takeDamage(attackStrength());
	}
	
	public void takeDamage(float amount) {
		if(this.hp - amount > 0)
			this.hp -= amount;
		this.hp = 0;
	}
	
	public boolean canAttack() {
		return this.canAttack;
	}
	
	public void setCanAttack(boolean value) {
		this.canAttack = value;
	}
}
