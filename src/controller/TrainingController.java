package controller;

import engine.GameContainer;
import model.Model;
import model.army.Archer;
import model.army.Catapult;
import model.army.Knight;
import model.army.Soldier;
import utility.GameState;
import view.View;

/**
 * TrainingController
 *
 * A controller that only applies when the game is in a training combatant state
 *
 * @author 6177000 (na16dg)
 */
class TrainingController {
    protected static void execute(GameContainer gc, Model model, View view) {
        if(Controller.leftClickUp(gc)) {
            if (Controller.mouseInBounds(view.getArcherSymbol())) {
                model.getVillage().newIndividual(new Archer(0, 0));
                GameState.save(model.getVillage());
                return;
            }

            if (Controller.mouseInBounds(view.getSoldierSymbol())) {
                model.getVillage().newIndividual(new Soldier(0, 0));
                GameState.save(model.getVillage());
                return;
            }

            if (Controller.mouseInBounds(view.getCatapultSymbol())) {
                model.getVillage().newIndividual(new Catapult(0, 0));
                GameState.save(model.getVillage());
                return;
            }

            if (Controller.mouseInBounds(view.getKnightSymbol())) {
                model.getVillage().newIndividual(new Knight(0, 0));
                GameState.save(model.getVillage());
                return;
            }
        }
    }
}
