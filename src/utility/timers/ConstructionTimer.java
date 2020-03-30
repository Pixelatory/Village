package utility.timers;

import engine.audio.Sound;
import model.buildings.Building;
import model.village.Village;
import utility.GameState;

import java.util.TimerTask;

public class ConstructionTimer extends TimerTask {
    Village village;
    Building building;

    public ConstructionTimer(Building building, Village village) {
        this.village = village;
        this.building = building;
    }
    @Override
    public void run() {
        new Sound("/sounds/building_finished.wav").play();
        building.setUpgrading(false);
        GameState.save(village);
    }
}
