package model.village;

import exceptions.NotEnoughResourcesException;
import exceptions.UpgradeMaxLevelException;

public interface Upgrade<E extends Upgradable> {
	public boolean canUpgrade(E e);
	public void performUpgrade(E e) throws NotEnoughResourcesException, UpgradeMaxLevelException;
}
