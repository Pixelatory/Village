package model.attack;

import model.army.Combatant;
import model.buildings.Building;
import model.village.Village;
import utility.ComparedGenerator;

import java.util.ArrayList;

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
	private int lootGained;
	private ArrayList<Combatant> placedCombatants = new ArrayList<>();

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

	public void addPlacedCombatant(Combatant combatant) {
		placedCombatants.add(combatant);
	}

	public ArrayList<Combatant> getPlacedCombatants() {
		return placedCombatants;
	}

	public int attackScore() {
		return attackScore;
	}

	public int lootGained() {
		return lootGained;
	}
}
