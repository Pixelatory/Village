package model.attack;

import model.village.Attackable;

/**
 * An interface which extends the Damager interface, and adds an additional method which is only used for Combatants.
 * 
 * @author 6177000
 *
 * @param <E> extends Attackable
 * 
 * @see Damager
 * @see model.army.Combatant
 */
public interface CombatantDamager<E extends Attackable> extends Damager<E> {
	/**
	 * A getter function that returns the float value of the movement speed of this combatant depending on its level.
	 * 
	 * @return a float value of the movement speed
	 */
	public float movementSpeed();
}
