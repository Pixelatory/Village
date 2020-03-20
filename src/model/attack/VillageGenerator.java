package model.attack;

import model.village.Village;
import utility.Generator;

/**
 * <b>NOT IMPLEMENTED</b>
 * @author 6177000
 */
public abstract class VillageGenerator implements Generator<Village> {

	public abstract Village generateComparedTo(Village village);
	
}
