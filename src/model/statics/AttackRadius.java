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
	public static final float[] ARCHER_TOWER = {10,20,30,40,50};
	public static final float[] CANNON = {5,10,15,20,25};
	
	//Combatants
	public static final float ARCHER = 5;
	public static final float KNIGHT = 2;
	public static final float CATAPULT = 50;
	public static final float SOLDIER = 15;
}
