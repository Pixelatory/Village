package model.statics;

/**
 * Contains static integers representing the maximum amount of hp various things can have.
 * 
 * @author 6177000
 * @see model.buildings.Building
 * @see model.army.Combatant
 * @see model.village.Upgradable
 */
public final class MaxHealthPoints {
	//Buildings
	//Array of ints/floats, each index referring to the level
	public static final float[] ARCHER_TOWER = {100,200,300,400,500};
	public static final float VILLAGE_HALL = 1500;
	public static final float[] CANNON = {150,250,350,450,550};
	public static final float[] GOLD_MINE = {50,100,150,200,250};
	public static final float[] IRON_MINE = {50,100,150,200,250};
	public static final float[] LUMBERMILL = {50,100,150,200,250};
	public static final float[] FARM = {50,100,150,200,250};


	
	//Combatants
	public static final float[] ARCHER = {100,115,130,145,200};
	public static final float[] SOLDIER = {100,115,130,145,200};
	public static final float[] KNIGHT = {100,115,130,145,200};
	public static final float[] CATAPULT = {100,115,130,145,200};
}
