package utility.timers;

import engine.audio.Sound;
import model.army.Combatant;
import model.village.Village;
import utility.GameState;

import java.util.TimerTask;

public class CombatantTrainingTimer extends TimerTask {

    Combatant combatant;
    Village village;

    public CombatantTrainingTimer(Combatant combatant, Village village) {
        this.combatant = combatant;
        this.village = village;
    }

    @Override
    public void run() {
        new Sound("/sounds/building_finished.wav").play();
        village.getCombatees().add(combatant);
        GameState.save(village);
    }
}
