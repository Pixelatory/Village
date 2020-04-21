package model.village;

/**
 * An interface which encompasses all the features an object should have if it is upgradable.
 * 
 * @author 6177000
 * @see model.army.Combatant
 * @see model.buildings.Building
 *
 */
public interface Upgradable {

	public void setLevel(int level);

	/**
	 * A function that performs tasks required in order to upgrade this upgradable object.
	 */
	public void performUpgrade();
	
	/**
	 * A getter function that returns a boolean value representing if this upgradable object can be upgraded further.
	 * 
	 * @return boolean value
	 */
	public boolean canUpgrade();
	
	/**
	 * A getter function that returns a boolean value representing if this upgradable object is upgrading.
	 * 
	 * @return boolean value
	 */
	public boolean isUpgrading();
	
	/**
	 * A function that sets whether this upgradable object is currently upgrading.
	 * 
	 * @param b boolean value
	 */
	public void setUpgrading(boolean b);
	
	/**
	 * A getter function that returns an int value representing the level this upgradable object is at.
	 * 
	 * @return int value
	 */
	public int level();
	
	/**
	 * A getter function that returns a float value representing the maximum hp of this upgradable object.
	 * 
	 * @return float value
	 */
	public float maxHP();
	
	/**
	 * A getter function that returns an int value representing the maximum level this upgradable object can reach.
	 * 
	 * @return int value
	 */
	public int maxLevel();
	
	/**
	 * A getter function that returns an int value representing how much this upgradable object costs in iron.
	 * 
	 * @return int value
	 */
	public int ironCost(int level);
	
	/**
	 * A getter function that returns an int value representing how much this upgradable object costs in gold.
	 * 
	 * @return int value
	 */
	public int goldCost(int level);
	
	/**
	 * A getter function that returns an int value representing how much this upgradable object costs in wood.
	 * 
	 * @return int value
	 */
	public int woodCost(int level);
	
	/**
	 * A getter function that returns an int value representing the amount of time this combatant takes to upgrade depending on the level parameter.
	 * 
	 * @param level int value
	 * 
	 * @return int value
	 */
	public int upgradeTime(int level);
}
