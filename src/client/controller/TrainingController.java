package client.controller;

import client.engine.GameContainer;
import client.model.Model;
import client.model.army.Archer;
import client.model.army.Catapult;
import client.model.army.Knight;
import client.model.army.Soldier;
import client.view.View;

/**
 * TrainingController
 *
 * A client.controller that only applies when the client.game is in a training combatant state
 *
 * @author 6177000 (na16dg)
 */
class TrainingController {
    protected static void execute(GameContainer gc, Model model, View view) {
        if(Controller.leftClickUp(gc)) {
            if (Controller.mouseInBounds(view.getArcherSymbol())) {
                model.getVillage().newIndividual(new Archer(0, 0));
                //GameState.save(model.getVillage());
                return;
            }

            if (Controller.mouseInBounds(view.getSoldierSymbol())) {
                model.getVillage().newIndividual(new Soldier(0, 0));
                //GameState.save(model.getVillage());
                return;
            }

            if (Controller.mouseInBounds(view.getCatapultSymbol())) {
                model.getVillage().newIndividual(new Catapult(0, 0));
                //GameState.save(model.getVillage());
                return;
            }

            if (Controller.mouseInBounds(view.getKnightSymbol())) {
                model.getVillage().newIndividual(new Knight(0, 0));
                //GameState.save(model.getVillage());
                return;
            }
        }
    }
}
