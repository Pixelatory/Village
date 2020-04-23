package client.model.village;

import client.exceptions.NotEnoughResourcesException;
import client.exceptions.UpgradeMaxLevelException;

public interface Upgrade<E extends Upgradable> {
	public boolean canUpgrade(E e);
	public void performUpgrade(E e) throws NotEnoughResourcesException, UpgradeMaxLevelException;
}
