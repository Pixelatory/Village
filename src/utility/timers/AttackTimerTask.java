package utility.timers;

import model.army.Combatant;
import model.buildings.DefensiveBuilding;

import java.util.TimerTask;

public class AttackTimerTask extends TimerTask {
    Combatant c;
    DefensiveBuilding b;

    public AttackTimerTask(Combatant c) {
        this.c = c;
    }

    public AttackTimerTask(DefensiveBuilding b) {
        this.b = b;
    }

    @Override
    public void run() {
        if(c != null)
            c.setCanAttack(true);
        else if (b != null)
            b.setCanAttack(true);
    }
}
