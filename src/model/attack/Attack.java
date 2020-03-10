package model.attack;

import java.util.ArrayList;

import model.army.Combatant;
import exceptions.InvalidPlacementException;
import model.generate.Generator;
import model.village.Village;

/**
 * <b>UNIMPLEMENTED</b>
 * 
 * Class that takes care of attacking functionality.
 * 
 * @author 6177000
 *
 */
public abstract class Attack implements Generator<Village> {
	private ArrayList<Combatant> combatants;
	private Village attackedVillage;
	private int attackScore;
	private int lootScore;
	private int lootGained;
	
	public abstract ArrayList<Combatant> generateComparedTo(Village village);
	
	public abstract void placeCombatant(Combatant combatee, int x, int y) throws InvalidPlacementException;
	public abstract int attackScore();
	public abstract int defenceScore();
	public abstract int lootScore();
	public abstract int lootGained();
	public abstract ArrayList<Combatant> getCombatants();
}
