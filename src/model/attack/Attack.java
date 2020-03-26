package model.attack;

import java.util.ArrayList;

import model.army.Combatant;
import exceptions.InvalidPlacementException;
import model.buildings.Building;
import utility.ComparedGenerator;
import model.village.Village;

/**
 * <b>UNIMPLEMENTED</b>
 * 
 * Class that takes care of attacking functionality.
 * 
 * @author 6177000
 *
 */
public class Attack implements ComparedGenerator<Village> {
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

	public void clearAttackingVillage() {
		this.attackingVillage = null;
	}

	public Village getAttackingVillage() {
		return attackingVillage;
	}

	public Village getDefendingVillage() {
		return defendingVillage;
	}

	public void setDefendingVillage(Village village) {
		for(Building b : village.getBuildings())
			b.setMovable(false);
		this.defendingVillage = village;
	}

	public void clearDefendingVillage() {
		if(defendingVillage != null) {
			for (Building b : defendingVillage.getBuildings())
				b.setMovable(true);
			this.defendingVillage = null;
		}
	}

	public int attackScore() {
		return 0;
	}

	public int lootGained() {
		return 0;
	}
}
