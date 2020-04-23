package client.controller;

import client.engine.GameContainer;
import client.model.Model;
import client.model.buildings.*;
import client.view.View;

/**
 * BuildController
 *
 * A client.controller that only applies when the client.game is in a construct building state
 *
 * @author 6177000 (na16dg)
 */
class BuildController {
    protected static void execute(GameContainer gc, Model model, View view) {
        if(Controller.rightClickUp(gc)) { // RIGHT CLICK IN BUILD MODE
            model.setSelectedNewConstruction(null);
            view.getToolbar().setVisible(true);
            view.getClickSound().play();
            return;
        } else if(Controller.leftClickUp(gc)) { // LEFT CLICK IN BUILD MODE
            if(model.getSelectedNewConstruction() != null) { // BUILDING WAS ALREADY SELECTED
                model.getVillage().newConstruction(model.getSelectedNewConstruction());
                model.setSelectedNewConstruction(null);
                view.getToolbar().setVisible(true);
                //GameState.save(model.getVillage());
            } else { // NO BUILDING WAS SELECTED

                if(Controller.mouseInBounds(view.getArcherTowerSymbol())
                && model.getVillage().canConstruct(new ArcherTower(Controller.mouseX, Controller.mouseY))) {
                    model.setSelectedNewConstruction(new ArcherTower(Controller.mouseX, Controller.mouseY));
                    view.getToolbar().setVisible(false);
                    view.getClickSound().play();
                } else if(Controller.mouseInBounds(view.getCannonSymbol())
                && model.getVillage().canConstruct(new Cannon(Controller.mouseX, Controller.mouseY))) {
                    model.setSelectedNewConstruction(new Cannon(Controller.mouseX, Controller.mouseY));
                    view.getToolbar().setVisible(false);
                    view.getClickSound().play();
                } else if(Controller.mouseInBounds(view.getFarmSymbol())) {
                    if(model.getVillage().canConstruct(new Farm(Controller.mouseX, Controller.mouseY))) {
                        model.setSelectedNewConstruction(new Farm(Controller.mouseX, Controller.mouseY));
                        view.getToolbar().setVisible(false);
                        view.getClickSound().play();
                    }
                } else if(Controller.mouseInBounds(view.getGoldMineSymbol())) {
                    if(model.getVillage().canConstruct(new GoldMine(Controller.mouseX, Controller.mouseY))) {
                        model.setSelectedNewConstruction(new GoldMine(Controller.mouseX, Controller.mouseY));
                        view.getToolbar().setVisible(false);
                        view.getClickSound().play();
                    }
                } else if(Controller.mouseInBounds(view.getIronMineSymbol())) {
                    if(model.getVillage().canConstruct(new IronMine(Controller.mouseX, Controller.mouseY))) {
                        model.setSelectedNewConstruction(new IronMine(Controller.mouseX, Controller.mouseY));
                        view.getToolbar().setVisible(false);
                        view.getClickSound().play();
                    }
                } else if(Controller.mouseInBounds(view.getLumbermillSymbol())) {
                    if(model.getVillage().canConstruct(new LumberMill(Controller.mouseX, Controller.mouseY))) {
                        model.setSelectedNewConstruction(new LumberMill(Controller.mouseX, Controller.mouseY));
                        view.getToolbar().setVisible(false);
                        view.getClickSound().play();
                    }
                }
            }
        } else if(model.getSelectedNewConstruction() != null) { // JUST FOR MOVING THE BUILDING AFTER SELECTING IT FOR CONSTRUCTION
            model.getSelectedNewConstruction().setXPos(Controller.mouseX - model.getSelectedNewConstruction().height() / 2);
            model.getSelectedNewConstruction().setYPos(Controller.mouseY - model.getSelectedNewConstruction().width() / 2);
        }
    }
}
