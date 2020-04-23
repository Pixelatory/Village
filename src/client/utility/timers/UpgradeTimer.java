package client.utility.timers;

import client.engine.audio.Sound;
import client.model.army.Combatant;
import client.model.buildings.Building;
import client.model.buildings.ProductionBuilding;
import client.model.habitants.ProductionHabitant;
import client.model.village.Village;

import java.util.TimerTask;

public class UpgradeTimer extends TimerTask {
    private final Village village;
    private Building building;
    private ProductionHabitant productionHabitant;
    private Combatant combatant;

    public UpgradeTimer(Village village, Building building) {
        this.village = village;
        this.building = building;
    }

    public UpgradeTimer(Village village, ProductionBuilding<ProductionHabitant> building, ProductionHabitant productionHabitant) {
        this.village = village;
        this.building = building;
        this.productionHabitant = productionHabitant;
    }

    public UpgradeTimer(Village village, Combatant combatant) {
        this.village = village;
        this.combatant = combatant;
    }

    @Override
    public void run() {
        if(productionHabitant == null && combatant == null) {
            int upgradeTime = building.getUpgradeTime();
            if (upgradeTime > 0) {
                building.setUpgradeTime(upgradeTime - 1);
            } else {
                new Sound("/sounds/building_finished.wav").play();
                building.setUpgrading(false);
                //GameState.save(village);
                cancel(); // building was successfully upgraded so cancel.
            }
        } else if(combatant == null) {
            ProductionBuilding tmp = (ProductionBuilding) building;
            int upgradeTime = productionHabitant.getUpgradeTime();
            if(upgradeTime > 0)
                productionHabitant.setUpgradeTime(upgradeTime - 1);
            else {
                new Sound("/sounds/building_finished.wav").play();
                productionHabitant.setUpgrading(false);
                tmp.setTraining(false);
                //GameState.save(village);
                cancel(); // habitant was successfully upgraded so cancel.
            }
        } else {
            int upgradeTime = combatant.getUpgradeTime();
            if(upgradeTime > 0)
                combatant.setUpgradeTime(upgradeTime - 1);
            else {
                new Sound("/sounds/building_finished.wav").play();
                combatant.setUpgrading(false);
                //GameState.save(village);
                cancel(); // combatant was successfully upgraded so cancel
            }
        }
    }
}
