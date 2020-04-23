package client.controller;

import client.engine.GameContainer;
import client.model.Model;
import client.model.buildings.Building;
import client.model.buildings.ProductionBuilding;
import client.model.habitants.ProductionHabitant;
import client.view.View;

/**
 * UpgradeController
 *
 * A client.controller that only applies when the client.game is in an upgrade building state
 *
 * @author 6177000 (na16dg)
 */
class UpgradeContoller {
    protected static void execute(GameContainer gc, Model model, View view) {
        if(!Controller.mouseInBounds(view.getToolbar())
                && Controller.leftClickDown(gc)) {
            model.setMouseClickedX(Controller.mouseX - model.getSelectedForUpgrade().xPos());
            model.setMouseClickedY(Controller.mouseY - model.getSelectedForUpgrade().yPos());
            view.getClickSound().play();
            return;
        } // WHEN MOVING A BUILDING THAT WAS ALREADY CLICKED

        if(view.getToolbar().isVisible() && Controller.leftClickUp(gc)) {
            if(Controller.mouseInBounds(view.getUpgradeIcon())) {
                view.getClickSound().play();
                model.getVillage().performUpgrade(model.getSelectedForUpgrade());
                //GameState.save(model.getVillage());
                return;
            } // CLICKING UPGRADE BUTTON
            else if (model.getSelectedForUpgrade() instanceof ProductionBuilding
                    && Controller.mouseInBounds(view.getTrainIcon())) {
                view.getClickSound().play();
                model.getVillage().trainHabitant((ProductionBuilding<ProductionHabitant>) model.getSelectedForUpgrade());
                //GameState.save(model.getVillage());
                return;
            } // CLICKING TRAIN BUTTON (FOR PRODUCTION BUILDINGS)
            else if (model.getSelectedForUpgrade() instanceof ProductionBuilding
                    && Controller.mouseInBounds(view.getUpgradeTroopIcon())) {
                view.getClickSound().play();
                model.getVillage().performUpgradeHabitant((ProductionBuilding) model.getSelectedForUpgrade());
                //GameState.save(model.getVillage());
                return;
            } // CLICKING UPGRADE WORKER BUTTON (FOR PRODUCTION BUILDINGS)
        } // UPGRADE TOOLBAR

        if(Controller.mouseY - Controller.mouseClickedY + model.getSelectedForUpgrade().height() <= view.getToolbar().getY()
                && Controller.mouseY - Controller.mouseClickedY >= 0
                && Controller.mouseX - Controller.mouseClickedX >= 0
                && Controller.mouseX - Controller.mouseClickedX + model.getSelectedForUpgrade().width() <= GameContainer.getWidth()
                && !Controller.mouseInBounds(view.getToolbar())) {

            if(Controller.holdingLeftClick(gc)) {
                boolean overlap = false;
                for (Building b : model.getVillage().getBuildings()) {
                    overlap = Controller.overlap(b, model.getSelectedForUpgrade());
                }
                if (!overlap) {
                    model.getSelectedForUpgrade().setXPos(Controller.mouseX - Controller.mouseClickedX);
                    model.getSelectedForUpgrade().setYPos(Controller.mouseY - Controller.mouseClickedY);
                }
                return;
            }
        } // HOLDING LEFT CLICK (AND DRAGGING) ON BUILDING AFTER SELECTING IT
    }
}
