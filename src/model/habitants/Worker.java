package model.habitants;

import model.statics.GoldCosts;
import model.statics.MaxLevels;
import model.statics.ProductionAmounts;
import model.statics.UpgradeTimes;

/**
 * Class representing a Worker.
 * 
 * @author 6177000
 * @see ProductionHabitant
 *
 */
public final class Worker extends ProductionHabitant {

	@Override
	public int productionAmount() {
		return ProductionAmounts.WORKER[level() - 1];
	}

	@Override
	public int maxLevel() {
		return MaxLevels.WORKER;
	}

	@Override
	public int goldCost(int level) {
		// TODO Auto-generated method stub
		return GoldCosts.WORKER[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.WORKER[level - 1];
	}
}
