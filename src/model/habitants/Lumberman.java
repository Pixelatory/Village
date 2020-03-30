package model.habitants;

import model.statics.GoldCosts;
import model.statics.MaxLevels;
import model.statics.ProductionAmounts;
import model.statics.UpgradeTimes;

/**
 * Class representing a Lumberman.
 * 
 * @author 6177000
 * @see ProductionHabitant
 *
 */
public final class Lumberman extends ProductionHabitant {
	public Lumberman() {
		super();
		this.name = "Lumberman";
	}

	@Override
	public int maxLevel() {
		return MaxLevels.LUMBERMAN;
	}

	@Override
	public int productionAmount() {
		return ProductionAmounts.LUMBERMAN[level() - 1];
	}

	@Override
	public int goldCost(int level) {
		return GoldCosts.LUMBERMEN[level - 1];
	}

	@Override
	public int upgradeTime(int level) {
		return UpgradeTimes.LUMBERMEN[level - 1];
	}

}
