package client.model.attack;

import client.model.village.Attackable;

/**
 * An interface which encompasses in general what an objects that damages other objects should be able to do.
 * 
 * @author 6177000
 *
 * @param <E> extends Attackable
 */
public interface Damager<E extends Attackable> {
	
	/**
	 * A getter function that returns a float value representing how strong your client.model.attack damages.
	 * 
	 * @return float value
	 */
	public float attackStrength();
	
	/**
	 * A getter function that returns a float value representing the radius of your client.model.attack.
	 * 
	 * @return float value
	 */
	public int attackRadius();
	
	/**
	 * A getter function that returns a float value representing how fast you can client.model.attack.
	 * 
	 * @return float value
	 */
	public float attackSpeed();
	
	/**
	 * A function that will client.model.attack an Attackable enemy.
	 * 
	 * @param enemy an Attackable object
	 * 
	 * @see client.model.village.Attackable
	 */
	public void attack(E enemy);
	
	/**
	 * A function that returns if you can client.model.attack at this moment or not.
	 * 
	 * @return boolean value
	 */
	public boolean canAttack();
	
	/**
	 * A function that sets if you can client.model.attack at this moment.
	 * 
	 * @param value true/false
	 */
	public void setCanAttack(boolean value);

	/**
	 * A function which will calculate if the enemy is in sight, depending on the radius of the attacker and position of the attackable.
	 *
	 * @param enemy Attackable object
	 * @return boolean value
	 */
	public boolean enemyInSight(E enemy); // to calculate, just calculate hypotenuse length from position, and client.model.attack nearest (if that's the building strategy)
}
