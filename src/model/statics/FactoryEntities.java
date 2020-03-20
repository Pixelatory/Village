package model.statics;

import model.army.*;
import model.buildings.*;

/**
 * This is mostly used as a helper class for factories, as they can create stuff easily.
 *
 * @author 6177000
 */
public final class FactoryEntities {

    public static final Building[] buildings = {
            new ArcherTower(0,0),
            new Cannon(0,0),
            new Farm(0,0),
            new GoldMine(0,0),
            new IronMine(0,0),
            new LumberMill(0,0)
    };

    public static final Combatant[] combatants = {
            new Archer(0,0),
            new Catapult(0,0),
            new Knight(0,0),
            new Soldier(0,0)
    };
}
