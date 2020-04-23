package client.model.habitants;

import client.model.statics.GoldCosts;
import client.model.statics.MaxLevels;
import client.model.statics.ProductionAmounts;
import client.model.statics.UpgradeTimes;

/**
 * Class representing an Iron Miner.
 * 
 * @author 6177000
 * @see Miner
 *
 */
public final class IronMiner extends Miner {

	public IronMiner() {
		super();
		this.name = "Iron Miner";
	}
	
	@Override
	public int maxLevel() {
		return MaxLevels.IRON_MINER;
	}

	@Override
	public int productionAmount() {
		return ProductionAmounts.IRON_MINER[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.IRON_MINER[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.IRON_MINER[level - 1];
	}

}
