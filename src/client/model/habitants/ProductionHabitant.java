package client.model.habitants;

/**
 * An abstract class which encompasses everything a habitant that is in production needs to function.
 * 
 * @author 6177000
 * @see Habitant
 */
public abstract class ProductionHabitant extends Habitant {
	private int upgradeTime;
	private boolean isUpgrading = false;

	public ProductionHabitant() {
		super();
	}

	public int getUpgradeTime() {
		return upgradeTime;
	}

	public void setUpgradeTime(int upgradeTime) {
		this.upgradeTime = upgradeTime;
	}

	public boolean isUpgrading() {
		return isUpgrading;
	}

	public void setUpgrading(boolean upgrading) {
		isUpgrading = upgrading;
	}

	/**
	 * A getter function which returns how much resource this habitant generates per cycle.
	 * 
	 * @return int value
	 */
	public abstract int productionAmount();
	
	public boolean canUpgrade() {
		return level() < maxLevel();
	}
	
	public void performUpgrade() {
		if(canUpgrade())
			this.level++;
	}
	
	public abstract int upgradeTime(int level);
}
