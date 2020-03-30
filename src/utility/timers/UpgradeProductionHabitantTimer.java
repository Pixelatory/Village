package utility.timers;

import engine.audio.Sound;
import model.buildings.ProductionBuilding;
import model.habitants.ProductionHabitant;
import model.village.Village;
import utility.GameState;

import java.util.TimerTask;

public class UpgradeProductionHabitantTimer extends TimerTask {
    ProductionBuilding building;
    ProductionHabitant habitant;
    Village village;

    public UpgradeProductionHabitantTimer(ProductionBuilding building, ProductionHabitant habitant, Village village) {
        this.building = building;
        this.habitant = habitant;
        this.village = village;
    }

    @Override
    public void run() {
        new Sound("/sounds/building_finished.wav").play();
        habitant.performUpgrade();
        building.setTraining(false);
        GameState.save(village);
    }
}
