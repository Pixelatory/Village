package client.model.statics;

/**
 * Contains static integers representing the radius of Damagers.
 * @author 6177000
 * @see client.model.army.Combatant
 * @see client.model.buildings.DefensiveBuilding
 * @see client.model.attack.Damager
 */
public final class AttackRadius {
	//Buildings
	public static final int[] ARCHER_TOWER = {50,55,60,65,70};
	public static final int[] CANNON = {40,45,50,55,60};
	
	//Combatants
	public static final int ARCHER = 25;
	public static final int KNIGHT = 15;
	public static final int CATAPULT = 50;
	public static final int SOLDIER = 15;
}
