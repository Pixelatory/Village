package client.model.statics;

/**
 * Contains static integers representing the amount of time it takes to upgrade various things.
 * 
 * @author 6177000
 * @see client.model.buildings.Building
 * @see client.model.army.Combatant
 * @see client.model.habitants.Habitant
 * @see client.model.village.Upgradable
 */
public final class UpgradeTimes {
	// All times here are ints, represented as seconds
	
	//Buildings
	public static final int[] ARCHER_TOWER = {5,5,5,5,5};
	public static final int[] CANNON = {5,5,5,5,5};
	public static final int[] GOLD_MINE = {5,5,5,5,5};
	public static final int[] IRON_MINE = {5,5,5,5,5};
	public static final int[] LUMBERMILL = {5,5,5,5,5};
	public static final int[] FARM = {5,5,5,5,5};
	
	//Habitants
	public static final int[] GOLD_MINER = {5,5,5,5,5};
	public static final int[] WORKER = {5,5,5,5,5};
	public static final int[] IRON_MINER= {5,5,5,5,5};
	public static final int[] LUMBERMEN = {5,5,5,5,5};

	//Combatants
	public static final int[] ARCHER = {5, 10, 10, 10, 10};
	public static final int[] SOLDIER = {5, 10, 10, 10, 10};
	public static final int[] CATAPULT = {5, 10, 10, 10, 10};
	public static final int[] KNIGHT = {5, 10, 10, 10, 10};

	
}
