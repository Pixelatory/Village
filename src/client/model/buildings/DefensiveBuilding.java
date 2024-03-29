package client.model.buildings;

import client.model.army.Combatant;
import client.model.attack.Damager;
import client.model.village.Attackable;
import client.utility.Position;

public abstract class DefensiveBuilding extends Building implements Damager<Combatant>, Attackable {
	
	protected boolean canAttack = true;

	public DefensiveBuilding(int xPos, int yPos) {
		super(xPos, yPos);
	}
	
	public boolean enemyInSight(Combatant enemy) {
		Position enemyPosition = enemy.getPosition();

		int xPos = xPos() + width();
		int yPos = yPos() + height();
		
		if(enemyPosition != null && Math.hypot(Math.abs(enemyPosition.getX() - xPos), Math.abs(enemyPosition.getY() - yPos)) <= attackRadius()) {
			return true;
		}
		
		return false;
	}
	
	public void attack(Combatant enemy) {
		enemy.takeDamage(attackStrength());
	}
	
	public boolean canAttack() {
		return this.canAttack;
	}
	
	public void setCanAttack(boolean value) {
		this.canAttack = value;
	}
}
