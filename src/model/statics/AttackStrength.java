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
	public static final float[] ARCHER_TOWER = {5.5f,7.5f,9.5f,12.5f,15f};
	public static final float[] CANNON = {7.2f,8.8f,12,15.2f,20};
	
	//Combatant
	public static final float[] ARCHER = {7,9,12,16,20};
	public static final float[] CATAPULT = {22,28,38,48,62};
	public static final float[] KNIGHT = {75,105,135,187.5f,255};
	public static final float[] SOLDIER = {8,11,14,18,23};
}
