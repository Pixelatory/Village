package model.habitants;

import model.village.Trainable;

import java.io.Serializable;

/**
 * Abstract class that encompasses everything a habitant should do and have.
 * 
 * @author 6177000
 * @see ProductionHabitant
 *
 */
public abstract class Habitant implements Trainable, Serializable {
	protected int level;
	protected String name;
	
	public Habitant() {
		this.level = 1;
	}

	public int level() {
		return level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public abstract int maxLevel();
	public abstract int goldCost(int level);

	public String getName() {
		return name;
	}
}
