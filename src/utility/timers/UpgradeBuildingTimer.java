package utility.timers;

import engine.audio.Sound;
import model.buildings.Building;
import model.buildings.ProductionBuilding;
import model.habitants.ProductionHabitant;
import model.village.Village;
import utility.GameState;

import java.util.TimerTask;

public class UpgradeBuildingTimer extends TimerTask {

    Building building;
    Village village;

    public UpgradeBuildingTimer(Building building, Village village) {
        this.building = building;
        this.village = village;
    }

    @Override
    public void run() {
        new Sound("/sounds/building_finished.wav").play();
        building.performUpgrade();
        building.setUpgrading(false);
        GameState.save(village);
    }
}
