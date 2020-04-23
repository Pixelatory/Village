package client.model.village;

import client.utility.Position;

public interface Attackable {
	/**
	 * Reduces the hp of this combatant by an amount. If it goes below 0, then hp will just go to 0.
	 * 
	 * @param amount float value representing the amount of damage taken
	 */
	public void takeDamage(float amount);

	public Position getPosition();
}
