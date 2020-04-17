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
	private int originalWoodQuantity;
	private int originalIronQuantity;
	private int originalGoldQuantity;
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

		originalWoodQuantity = village.getWood();
		originalGoldQuantity = village.getGold();
		originalIronQuantity = village.getIron();
	}

	public void clearDefendingVillage() {
		if(defendingVillage != null) {
			for (Building b : defendingVillage.getBuildings())
				b.setMovable(true);
			this.defendingVillage = null;
		}

		originalIronQuantity = 0;
		originalGoldQuantity = 0;
		originalWoodQuantity = 0;
	}

	public void addPlacedCombatant(Combatant combatant) {
		placedCombatants.add(combatant);
	}

	public ArrayList<Combatant> getPlacedCombatants() {
		return placedCombatants;
	}

	public void clearPlacedCombatants() {
		placedCombatants.clear();
	}

	public int attackScore() {
		int count = 0;

		for(Building b : defendingVillage.getBuildings()) {
			if(b.HP() == 0)
				count++;
		}

		return count / defendingVillage.getBuildings().size();
	}

	public int woodGained() {
		return defendingVillage.getWood() - originalWoodQuantity;
	}

	public int goldGained() {
		return defendingVillage.getGold() - originalGoldQuantity;
	}

	public int ironGained() {
		return defendingVillage.getIron() - originalIronQuantity;
	}
}
