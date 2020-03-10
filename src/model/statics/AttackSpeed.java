package model.statics;

/**
 * Contains static integers representing the model.attack speed of Damagers.
 * @author 6177000
 * @see model.army.Combatant
 * @see model.buildings.DefensiveBuilding
 * @see model.attack.Damager
 */
public final class AttackSpeed {
	//Buildings
	public static final float[] ARCHER_TOWER = {5,4,3,2,1};
	public static final float[] CANNON = {7,6,5,4,3};
	
	//Combatants
	public static final float[] ARCHER = {2.5f,2.25f,2,1.75f,1.5f};
	public static final float[] SOLDIER = {2.5f,2.25f,2,1.75f,1.5f};
	public static final float[] CATAPULT = {2.5f,2.25f,2,1.75f,1.5f};
	public static final float[] KNIGHT = {2.5f,2.25f,2,1.75f,1.5f};
}
