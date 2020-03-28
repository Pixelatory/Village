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
	public static final float[] ARCHER_TOWER = {.5f,.45f,.4f,.35f,.3f};
	public static final float[] CANNON = {.8f,.75f,.7f,.65f,.6f};
	
	//Combatants
	public static final float[] ARCHER = {1f,.95f,.9f,.85f,.8f};
	public static final float[] SOLDIER = {1f,.95f,.9f,.85f,.8f};
	public static final float[] CATAPULT = {2f,1.95f,1.9f,1.85f,1.8f};
	public static final float[] KNIGHT = {1.5f,1.45f,1.4f,1.35f,1.3f};
}
