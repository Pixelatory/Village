package client.utility.timers;

import client.engine.audio.Sound;
import client.model.army.Combatant;
import client.model.village.Village;

import java.util.TimerTask;

public class CombatantTrainingTimer extends TimerTask {

    Combatant combatant;
    Village village;

    public CombatantTrainingTimer(Village village, Combatant combatant) {
        this.combatant = combatant;
        this.village = village;
    }

    @Override
    public void run() {
        int upgradeTime = combatant.getUpgradeTime();
        if (upgradeTime > 0) {
            combatant.setUpgradeTime(upgradeTime - 1);
        } else {
            new Sound("/sounds/building_finished.wav").play();
            combatant.setUpgrading(false);
            //GameState.save(village);
            cancel(); // building was successfully upgraded so cancel.
        }
    }
}
