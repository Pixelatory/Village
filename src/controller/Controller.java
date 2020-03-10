package controller;

import model.buildings.*;
import engine.GameContainer;
import engine.audio.Sound;
import model.Model;

public final class Controller {

    private int mouseX;
    private int mouseY;

    float imageTileRate = 0;
    public void update(GameContainer gc, Model model, float dt) {
        /* Here for reference on how to get keyboard input
		 * if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			sound.play();
		}*/
        imageTileRate += dt * 50;

        if(imageTileRate > 23)
            imageTileRate = 0;

        mouseX = gc.getInput().getMouseX();
        mouseY = gc.getInput().getMouseY();

        SoundPlay sound = file -> new Sound(file).play();

        if(toolbar.isVisible()
                && rightClickUp(gc)) {
            buildMode = false;
            upgradeMode = false;
            trainingMode = false;
            selectedForUpgrade = null;
            selectedNewConstruction = null;
            sound.play("/sounds/button_click.wav");
            return;
        } // RIGHT CLICKING ANYWHERE WHEN TOOLBAR IS VISIBLE

        for(Building b : model.village.getBuildings()) {
            if(b != selectedForUpgrade
                    && mouseInBounds(b)
                    && leftClickDown(gc)) {
                upgradeMode = true;
                buildMode = false;
                trainingMode = false;
                selectedForUpgrade = b;
                mouseClickedX = mouseX - b.xPos();
                mouseClickedY = mouseY - b.yPos();
                sound.play("/sounds/button_click.wav");
                return;
            }
        } // CLICKING ON A BUILDING ON SCREEN

        if(selectedForUpgrade != null
                && !mouseInBounds(toolbar)
                && leftClickDown(gc)) {
            mouseClickedX = mouseX - selectedForUpgrade.xPos();
            mouseClickedY = mouseY - selectedForUpgrade.yPos();
            sound.play("/sounds/button_click.wav");
        } // WHEN MOVING A BUILDING THAT WAS ALREADY CLICKED

        if(selectedForUpgrade != null
                && toolbar.isVisible()
                && leftClickUp(gc)) {
            if(mouseInBounds(upgradeIcon)) {
                sound.play("/sounds/button_click.wav");
                model.village.performUpgrade(selectedForUpgrade);
                return;
            } // CLICKING UPGRADE BUTTON
            else if (selectedForUpgrade instanceof ProductionBuilding
                    && mouseInBounds(trainIcon)) {
                sound.play("/sounds/button_click.wav");
                model.village.trainIndividual(selectedForUpgrade);
                return;
            } // CLICKING TRAIN BUTTON (FOR PRODUCTION BUILDINGS)
            else if (selectedForUpgrade instanceof ProductionBuilding
                    && mouseInBounds(upgradeTroopIcon)) {
                sound.play("/sounds/button_click.wav");
                model.village.performUpgradeHabitant(selectedForUpgrade);
                return;
            } // CLICKING UPGRADE WORKER BUTTON (FOR PRODUCTION BUILDINGS)
        } // UPGRADE MODE

        if(selectedForUpgrade != null
                && mouseY - mouseClickedY + selectedForUpgrade.height() <= toolbar.getY()
                && mouseY - mouseClickedY >= 0
                && mouseX - mouseClickedX >= 0
                && mouseX - mouseClickedX + selectedForUpgrade.width() <= GameContainer.getWidth()
                && upgradeMode
                && holdingLeftClick(gc)
                && !mouseInBounds(toolbar)) {
            selectedForUpgrade.setXPos(mouseX - mouseClickedX);
            selectedForUpgrade.setYPos(mouseY - mouseClickedY);
            return;
        } // HOLDING LEFT CLICK ON BUILDING AFTER SELECTING IT

        if(selectedNewConstruction != null) {
            if(rightClickUp(gc)) { // RIGHT CLICK AFTER SELECTING BUILDING FOR CONSTRUCTION
                selectedNewConstruction = null;
                toolbar.setVisible(true);
                sound.play("/sounds/button_click.wav");
                return;
            } else if(leftClickUp(gc)) { // LEFT CLICK AFTER SELECTING BUILDING FOR CONSTRUCTION
                sound.play("/sounds/button_click.wav");
                model.village.newConstruction(selectedNewConstruction);
                selectedNewConstruction = null;
                toolbar.setVisible(true);
                return;
            } else { // JUST FOR MOVING THE BUILDING AFTER SELECTING IT FOR CONSTRUCTION
                selectedNewConstruction.setXPos(mouseX - selectedNewConstruction.height() / 2);
                selectedNewConstruction.setYPos(mouseY - selectedNewConstruction.width() / 2);
                return;
            }
        } else {
            if(buildMode
                    && leftClickUp(gc)) {
                if(mouseInBounds(archerTowerSymbol)) {
                    sound.play("/sounds/button_click.wav");
                    if(model.village.canConstruct(new ArcherTower(mouseX,mouseY))) {
                        selectedNewConstruction = new ArcherTower(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(cannonSymbol)) {
                    sound.play("/sounds/button_click.wav");
                    if(model.village.canConstruct(new Cannon(mouseX,mouseY))) {
                        selectedNewConstruction = new Cannon(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(farmSymbol)) {
                    sound.play("/sounds/button_click.wav");
                    if(model.village.canConstruct(new Farm(mouseX,mouseY))) {
                        selectedNewConstruction = new Farm(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(goldMineSymbol)) {
                    sound.play("/sounds/button_click.wav");
                    if(model.village.canConstruct(new GoldMine(mouseX,mouseY))) {
                        selectedNewConstruction = new GoldMine(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(ironMineSymbol)) {
                    sound.play("/sounds/button_click.wav");
                    if(model.village.canConstruct(new IronMine(mouseX,mouseY))) {
                        selectedNewConstruction = new IronMine(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(lumbermillSymbol)) {
                    sound.play("/sounds/button_click.wav");
                    if(model.village.canConstruct(new LumberMill(mouseX,mouseY))) {
                        selectedNewConstruction = new LumberMill(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }
            }
        }

        if(toolbar.isVisible()
                && !upgradeMode
                && !trainingMode
                && mouseInBounds(buildIcon)
                && leftClickUp(gc)) {
            sound.play("/sounds/button_click.wav");
            buildMode = true;
        }

        if(toolbar.isVisible()
                && trainingMode
                && !upgradeMode
                && !buildMode) {
        }
        //System.out.println(mouseX + " " + mouseY + " " + buildModeButton.getX() + " " + buildModeButton.getY());
    }
}
