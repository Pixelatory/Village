package client.model.attack;

import client.exceptions.EvolutionPopulateException;
import client.model.army.Combatant;
import client.model.buildings.Building;
import client.model.statics.FactoryEntities;
import client.model.village.Village;
import client.utility.ComparedGenerator;
import client.utility.ai.evolutionary.BuildingEvolution;

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
	public ArrayList<Building> generateComparedTo(Village village) {
		BuildingEvolution b;
		try {
			b = new BuildingEvolution(village.getBuildings().size(), FactoryEntities.buildings, village.getBuildings());
		} catch (EvolutionPopulateException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<Building> br = b.process();
		return br;
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

	public float attackScore() {
		float count = 0f;

		for(Building b : defendingVillage.getBuildings()) {
			if(b.HP() <= 0)
				count++;
		}

		return (count / defendingVillage.getBuildings().size()) * 100;
	}

	public int woodGained() {
		return originalWoodQuantity - defendingVillage.getWood();
	}

	public int goldGained() {
		return originalGoldQuantity - defendingVillage.getGold();
	}

	public int ironGained() {
		return originalIronQuantity - defendingVillage.getIron();
	}
}
