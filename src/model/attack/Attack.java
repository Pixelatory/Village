package model.attack;

import java.util.ArrayList;

import model.army.Combatant;
import exceptions.InvalidPlacementException;
import model.buildings.Building;
import utility.Generator;
import model.village.Village;
import utility.ai.genetic.Evolution;

/**
 * <b>UNIMPLEMENTED</b>
 * 
 * Class that takes care of attacking functionality.
 * 
 * @author 6177000
 *
 */
public class Attack implements Generator<Village> {
	private Village attackingVillage;
	private Village defendingVillage;
	private int attackScore;
	private int lootScore;
	private int lootGained;

	/**
	 * Generating a list of combatants compared to another villages buildings.
	 *
	 * @param village Village object
	 * @return arraylist of combatants
	 */
	public ArrayList<Combatant> generateComparedTo(Village village) {
		return null;
	}

	public void setAttackingVillage(Village village) {
		this.attackingVillage = village;
	}

	public void setDefendingVillage(Village village) {
		this.defendingVillage = village;
	}
	
	public abstract void placeCombatant(Combatant combatee, int x, int y) throws InvalidPlacementException;
	public abstract int attackScore();
	public abstract int defenceScore();
	public abstract int lootScore();
	public abstract int lootGained();
	public abstract ArrayList<Combatant> getCombatants();
}
