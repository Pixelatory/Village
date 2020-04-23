package client.model.habitants;

import client.model.statics.GoldCosts;
import client.model.statics.MaxLevels;
import client.model.statics.ProductionAmounts;
import client.model.statics.UpgradeTimes;

/**
 * Class representing a Gold Miner.
 * 
 * @author 6177000
 * @see Miner
 *
 */
public final class GoldMiner extends Miner {

	public GoldMiner() {
		super();
		this.name = "Gold Miner";
	}

	@Override
	public int maxLevel() {
		return MaxLevels.GOLD_MINER;
	}

	@Override
	public int productionAmount() {
		return ProductionAmounts.GOLD_MINER[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.GOLD_MINER[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.GOLD_MINER[level - 1];
	}

}
