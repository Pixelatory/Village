package model.statics;

/**
 * Contains static integers representing the radius of Damagers.
 * @author 6177000
 * @see model.army.Combatant
 * @see model.buildings.DefensiveBuilding
 * @see model.attack.Damager
 */
public final class AttackRadius {
	//Buildings
	public static final int[] ARCHER_TOWER = {45,50,55,60,65};
	public static final int[] CANNON = {40,45,50,55,60};
	
	//Combatants
	public static final int ARCHER = 10;
	public static final int KNIGHT = 5;
	public static final int CATAPULT = 50;
	public static final int SOLDIER = 5;
}
