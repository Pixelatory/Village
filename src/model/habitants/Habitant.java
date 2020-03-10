package model.habitants;

import model.village.Trainable;

/**
 * Abstract class that encompasses everything a habitant should do and have.
 * 
 * @author 6177000
 * @see ProductionHabitant
 *
 */
public abstract class Habitant implements Trainable {
	protected int level;
	
	public Habitant() {
		this.level = 1;
	}

	public int level() {
		return level;
	}
	
	public abstract int maxLevel();
	public abstract int goldCost(int level);
}
