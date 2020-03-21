package model.attack;

import model.village.Village;
import utility.ComparedGenerator;

/**
 * @author 6177000
 */
public abstract class VillageComparedGenerator implements ComparedGenerator<Village> {

	public abstract Village generateComparedTo(Village village);
	
}
