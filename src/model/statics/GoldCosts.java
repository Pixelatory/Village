package model.statics;

/**
 * Contains static integers representing the cost of various things in gold.
 * 
 * @author 6177000
 * @see model.habitants.Habitant
 * @see model.buildings.Building
 * @see model.army.Combatant
 * @see model.village.Upgradable
 */
public final class GoldCosts {
	// BUILDINGS
	public static final int[] VILLAGE_HALL = {0};
	public static final int[] FARM = {50, 70, 100, 140, 190};
	public static final int[] GOLD_MINE = {50, 70, 100, 140, 190};
	public static final int[] IRON_MINE= {50, 70, 100, 140, 190};
	public static final int[] LUMBER_MILL = {50, 70, 100, 140, 190};
	public static final int[] ARCHER_TOWER = {50,100,200,300,500};
	public static final int[] CANNON = {30, 80, 130, 180, 230};
	
	// HABITANTS
	public static final int[] WORKER = {10,50,100,150,200};
	public static final int[] GOLD_MINER = {10,50,100,150,200};
	public static final int[] IRON_MINER = {10,50,100,150,200};
	public static final int[] LUMBERMEN = {10,50,100,150,200};


	
	// ARMY
	public static final int[] SOLDIER = {50, 50, 50, 50, 50};
	public static final int[] ARCHER = {50, 50, 50, 50, 50};
	public static final int[] KNIGHT = {50, 50, 50, 50, 50};
	public static final int[] CATAPULT = {50, 50, 50, 50, 50};
}
