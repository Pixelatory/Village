package client.utility.timers;

import client.engine.audio.Sound;
import client.model.buildings.ProductionBuilding;
import client.model.habitants.ProductionHabitant;
import client.model.village.Village;

import java.util.TimerTask;

public class WorkerUpgradeTimer extends TimerTask {
    ProductionBuilding building;
    ProductionHabitant habitant;
    Village village;

    public WorkerUpgradeTimer(Village village, ProductionBuilding building, ProductionHabitant habitant) {
        this.building = building;
        this.habitant = habitant;
        this.village = village;
    }

    @Override
    public void run() {
        int upgradeTime = habitant.getUpgradeTime();
        if (upgradeTime > 0) {
            habitant.setUpgradeTime(upgradeTime - 1);
        } else {
            new Sound("/sounds/building_finished.wav").play();
            habitant.setUpgrading(false);
            building.setTraining(false);
            habitant.performUpgrade();
            //GameState.save(village);
            cancel(); // building was successfully upgraded so cancel.
        }
    }
}
