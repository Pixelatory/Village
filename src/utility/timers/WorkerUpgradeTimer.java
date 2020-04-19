package utility.timers;

import engine.audio.Sound;
import model.buildings.ProductionBuilding;
import model.habitants.ProductionHabitant;
import model.village.Village;
import utility.GameState;

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
            GameState.save(village);
            cancel(); // building was successfully upgraded so cancel.
        }
    }
}
