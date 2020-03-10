package model.statics;

/**
 * Contains static integers representing the maximum level various things can have.
 * 
 * @author 6177000
 * @see model.buildings.Building
 * @see model.army.Combatant
 * @see model.habitants.Habitant
 * @see model.village.Upgradable
 */
public final class MaxLevels {
	// BUILDINGS
	public static final int FARM = 5;
	public static final int GOLD_MINE = 5;
	public static final int IRON_MINE = 5;
	public static final int LUMBER_MILL = 5;
	public static final int ARCHER_TOWER = 5;
	public static final int CANNON = 5;
	public static final int VILLAGE_HALL = 1;
	
	// HABITANTS
	public static final int WORKER = 5;
	public static final int GOLD_MINER = 5;
	public static final int IRON_MINER = 5;
	public static final int LUMBERMAN = 5;
	
	// ARMY
	public static final int SOLDIER = 0;
	public static final int ARCHER = 5;
	public static final int KNIGHT = 0;
	public static final int CATAPULT = 0;
}
