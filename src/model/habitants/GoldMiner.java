package model.habitants;

import model.statics.GoldCosts;
import model.statics.MaxLevels;
import model.statics.ProductionAmounts;
import model.statics.UpgradeTimes;

/**
 * Class representing a Gold Miner.
 * 
 * @author 6177000
 * @see Miner
 *
 */
public final class GoldMiner extends Miner {
	
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
