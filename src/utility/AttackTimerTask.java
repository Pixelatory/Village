package utility;

import model.army.Combatant;
import model.buildings.DefensiveBuilding;

import java.util.TimerTask;

public abstract class AttackTimerTask extends TimerTask {
    public AttackTimerTask(Combatant c) {

    }

    public AttackTimerTask(DefensiveBuilding b) {

    }
}
