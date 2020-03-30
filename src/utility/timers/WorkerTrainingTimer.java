package utility.timers;

import engine.audio.Sound;
import model.buildings.ProductionBuilding;
import model.village.Village;
import utility.GameState;

import java.util.TimerTask;

public class WorkerTrainingTimer extends TimerTask {

    ProductionBuilding building;
    Village village;

    public WorkerTrainingTimer(ProductionBuilding building, Village village) {
        this.building = building;
        this.village = village;
    }

    @Override
    public void run() {
        new Sound("/sounds/building_finished.wav").play();
        building.addWorker();
        building.setTraining(false);
        GameState.save(village);
    }
}
