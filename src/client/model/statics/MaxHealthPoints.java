package client.model.statics;

/**
 * Contains static integers representing the maximum amount of hp various things can have.
 * 
 * @author 6177000
 * @see client.model.buildings.Building
 * @see client.model.army.Combatant
 * @see client.model.village.Upgradable
 */
public final class MaxHealthPoints {
	//Buildings
	//Array of ints/floats, each index referring to the level
	public static final float[] ARCHER_TOWER = {380,420,460,500,540};
	public static final float VILLAGE_HALL = 1500;
	public static final float[] CANNON = {420,470,520,570,620};
	public static final float[] GOLD_MINE = {400,440,480,520,560};
	public static final float[] IRON_MINE = {400,440,480,520,560};
	public static final float[] LUMBERMILL = {400,440,480,520,560};
	public static final float[] FARM = {400,440,480,520,560};


	
	//Combatants
	public static final float[] ARCHER = {20,23,28,33,40};
	public static final float[] SOLDIER = {45,54,65,78,95};
	public static final float[] KNIGHT = {45,54,65,78,95};
	public static final float[] CATAPULT = {300,360,430,520,720};
}
