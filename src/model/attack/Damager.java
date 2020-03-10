package model.attack;

import model.village.Attackable;

/**
 * An interface which encompasses in general what an objects that damages other objects should be able to do.
 * 
 * @author 6177000
 *
 * @param <E> extends Attackable
 */
public interface Damager<E extends Attackable> {
	
	/**
	 * A getter function that returns a float value representing how strong your model.attack damages.
	 * 
	 * @return float value
	 */
	public float attackStrength();
	
	/**
	 * A getter function that returns a float value representing the radius of your model.attack.
	 * 
	 * @return float value
	 */
	public float attackRadius();
	
	/**
	 * A getter function that returns a float value representing how fast you can model.attack.
	 * 
	 * @return float value
	 */
	public float attackSpeed();
	
	/**
	 * A function that will model.attack an Attackable enemy.
	 * 
	 * @param enemy an Attackable object
	 * 
	 * @see model.village.Attackable
	 */
	public void attack(E enemy);
	
	/**
	 * A function that returns if you can model.attack at this moment or not.
	 * 
	 * @return boolean value
	 */
	public boolean canAttack();
	
	/**
	 * A function that sets if you can model.attack at this moment.
	 * 
	 * @param value true/false
	 */
	public void setCanAttack(boolean value);
}
