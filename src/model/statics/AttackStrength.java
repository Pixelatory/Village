package model.statics;

/**
 * Contains static integers representing the model.attack strength of Damagers.
 * @author 6177000
 * @see model.army.Combatant
 * @see model.buildings.DefensiveBuilding
 * @see model.attack.Damager
 */
public final class AttackStrength {
	//Buildings
	public static final float[] ARCHER_TOWER = {5,10,15,20,25};
	public static final float[] CANNON = {5,10,15,20,25};
	
	//Combatant
	public static final float[] ARCHER = {5,7,10,13,15};
	public static final float[] CATAPULT = {10,20,30,40,50};
	public static final float[] KNIGHT = {5,7,10,13,15};
	public static final float[] SOLDIER = {5,7,10,13,15};
}
