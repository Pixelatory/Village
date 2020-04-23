package client.utility.timers;

import client.engine.audio.Sound;
import client.model.buildings.ProductionBuilding;
import client.model.habitants.ProductionHabitant;
import client.model.village.Village;

import java.util.TimerTask;

public class WorkerTrainingTimer extends TimerTask {

    ProductionBuilding building;
    Village village;
    ProductionHabitant productionHabitant;

    public WorkerTrainingTimer(Village village, ProductionBuilding building, ProductionHabitant productionHabitant) {
        this.building = building;
        this.village = village;
        this.productionHabitant = productionHabitant;
    }

    @Override
    public void run() {
        int upgradeTime = productionHabitant.getUpgradeTime();
        if (upgradeTime > 0) {
            productionHabitant.setUpgradeTime(upgradeTime - 1);
        } else {
            new Sound("/sounds/building_finished.wav").play();
            productionHabitant.setUpgrading(false);
            building.setTraining(false);
            building.addWorker();
            //GameState.save(village);
            cancel(); // worker was successfully trained to close the task
        }
    }
}
