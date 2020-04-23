package client.utility.timers;

import client.engine.audio.Sound;
import client.model.buildings.Building;
import client.model.village.Village;

import java.util.TimerTask;

public class BuildingUpgradeTimer extends TimerTask {

    private Village village;
    private Building building;

    public BuildingUpgradeTimer(Village village, Building building) {
        this.village = village;
        this.building = building;
    }

    @Override
    public void run() {
        int upgradeTime = building.getUpgradeTime();
        if (upgradeTime > 0) {
            building.setUpgradeTime(upgradeTime - 1);
        } else {
            new Sound("/sounds/building_finished.wav").play();
            building.setUpgrading(false);
            building.performUpgrade();
            //GameState.save(village);
            cancel(); // building was successfully upgraded so cancel.
        }
    }
}
