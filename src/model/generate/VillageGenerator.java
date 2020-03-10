package model.generate;

import model.village.Village;

/**
 * <b>NOT IMPLEMENTED</b>
 * @author 6177000
 */
public abstract class VillageGenerator implements Generator<Village> {

	public abstract Village generateComparedTo(Village village);
	
}
