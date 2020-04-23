package client.model.buildings;

import client.model.habitants.Worker;

/**
 * Class that divides production client.model.buildings into separate parts, where the subclasses of this are for generating food.
 * 
 * @author 6177000
 * @see ProductionBuilding
 */
public abstract class FoodProduction extends ProductionBuilding<Worker> {
	
	/**
	 * Calls the ProductionBuilding constructor.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see ProductionBuilding
	 */
	public FoodProduction(int xPos, int yPos) {
		super(xPos, yPos);
	}
}
