package client.model.statics;

/**
 * Contains static integers representing the cost of various things in iron.
 * 
 * @author 6177000
 * @see client.model.habitants.Habitant
 * @see client.model.buildings.Building
 * @see client.model.army.Combatant
 * @see client.model.village.Upgradable
 */
public final class IronCosts {
	// BUILDINGS
	public static final int[] VILLAGE_HALL = {0};
	public static final int[] FARM = {50, 70, 100, 140, 190};
	public static final int[] GOLD_MINE = {50, 70, 100, 140, 190};
	public static final int[] IRON_MINE = {50, 70, 100, 140, 190};
	public static final int[] LUMBER_MILL = {50, 70, 100, 140, 190};
	public static final int[] ARCHER_TOWER = {50,100,200,300,500};
	public static final int[] CANNON = {30, 80, 130, 180, 230};	
	
	// HABITANTS
	public static final int WORKER = 0;
	public static final int MINER = 0;
	
	// ARMY
	public static final int[] SOLDIER = {50, 50, 50, 50, 50};
	public static final int[] ARCHER = {50, 50, 50, 50, 50};
	public static final int[] KNIGHT = {50, 50, 50, 50, 50};
	public static final int[] CATAPULT = {50, 50, 50, 50, 50};
}
