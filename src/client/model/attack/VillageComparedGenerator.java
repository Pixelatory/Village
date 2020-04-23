package client.model.attack;

import client.model.village.Village;
import client.utility.ComparedGenerator;

/**
 * @author 6177000
 */
public abstract class VillageComparedGenerator implements ComparedGenerator<Village> {

	public abstract Village generateComparedTo(Village village);
	
}
