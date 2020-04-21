package utility;

import exceptions.UnknownBuildingException;
import exceptions.UnknownCombatantException;
import model.army.*;
import model.buildings.*;

public final class NameToObject {
    public static Building building(String name) {
        switch(name) {
            case "Archer Tower":
                return new ArcherTower(0,0);
            case "Cannon":
                return new Cannon(0,0);
            case "Gold Mine":
                return new GoldMine(0,0);
            case "Iron Mine":
                return new IronMine(0,0);
            case "Lumbermill":
                return new LumberMill(0,0);
            case "Farm":
                return new Farm(0,0);
            case "Village Hall":
                return new VillageHall(0,0);
            default:
                throw new UnknownBuildingException();
        }
    }

    public static Combatant combatant(String name) {
        switch(name) {
            case "Archer":
                return new Archer(0,0);
            case "Catapult":
                return new Catapult(0,0);
            case "Knight":
                return new Knight(0,0);
            case "Soldier":
                return new Soldier(0,0);
            default:
                throw new UnknownCombatantException();
        }
    }
}
