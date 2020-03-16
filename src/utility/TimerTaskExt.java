package utility;

import model.buildings.ProductionBuilding;
import model.resources.Food;
import model.resources.Gold;
import model.resources.Iron;
import model.resources.Wood;

import java.util.TimerTask;

/**
 * Used for passing parameters into a timer task for when a building generates model.resources.<br>
 * We need parameters for the building, and the various model.resources we can get.
 * 
 * @author 6177000
 *
 */
public abstract class TimerTaskExt extends TimerTask {
	
	public TimerTaskExt(ProductionBuilding building, Gold gold, Iron iron, Wood wood, Food food) {

	}

}
