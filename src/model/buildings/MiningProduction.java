package model.buildings;

import model.habitants.Miner;

/**
 * Class that divides production model.buildings into separate parts, where the subclasses of this are for miners specifically.
 * 
 * @author 6177000
 * @see ProductionBuilding
 */
public abstract class MiningProduction<E extends Miner> extends ProductionBuilding<E> {
	/**
	 * Calls the ProductionBuilding constructor.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see ProductionBuilding
	 */
	public MiningProduction(int xPos, int yPos) {
		super(xPos, yPos);
	}
}
