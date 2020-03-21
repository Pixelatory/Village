package model.attack;

import model.village.Attackable;

/**
 * An interface which extends the Damager interface, and adds an additional method which is only used for Defensive Buildings.
 * 
 * @author 6177000
 *
 * @param <E> extends Attackable
 * 
 * @see Damager
 * @see model.buildings.DefensiveBuilding
 */
public interface BuildingDamager<E extends Attackable> extends Damager<E> {
	
	/**
	 * A function which will calculate if the enemy is in sight, depending on the radius of the building and position of the enemy.
	 * 
	 * @param enemy Attackable object
	 * @return boolean value
	 */
	public boolean enemyInSight(E enemy); // to calculate, just calculate hypotenuse length from position, and model.attack nearest (if that's the building strategy)

	public void attack(E enemy);
}
