package utility.timers;

import model.buildings.*;
import model.habitants.ProductionHabitant;
import model.resources.Food;
import model.resources.Gold;
import model.resources.Iron;
import model.resources.Wood;
import model.village.Village;
import utility.GameState;

import java.util.TimerTask;

/**
 * Used for passing parameters into a timer task for when a building generates model.resources.<br>
 * We need parameters for the building, and the various model.resources we can get.
 * 
 * @author 6177000
 *
 */
public class ProductionBuildingCollectionTimer extends TimerTask {

	ProductionBuilding building;
	Gold gold;
	Iron iron;
	Wood wood;
	Food food;
	Village village;

	public ProductionBuildingCollectionTimer(ProductionBuilding building, Gold gold, Iron iron, Wood wood, Food food, Village village) {
		this.building = building;
		this.gold = gold;
		this.iron = iron;
		this.wood = wood;
		this.food = food;
		this.village = village;
	}

	@Override
	public void run() {
		if(building instanceof GoldMine)
			gold.increase(building.productionAmount());
		else if (building instanceof IronMine)
			iron.increase(building.productionAmount());
		else if (building instanceof LumberMill)
			wood.increase(building.productionAmount());
		else if (building instanceof Farm)
			food.increase(building.productionAmount());
		GameState.save(village);
	}
}
