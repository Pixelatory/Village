package controller;

import engine.GameContainer;
import engine.gfx.Image;
import engine.renderPrimitives.Rectangle;
import model.Model;
import model.buildings.*;
import model.habitants.ProductionHabitant;
import view.View;

import java.awt.event.MouseEvent;

public final class Controller {

    private int mouseX;
    private int mouseY;
    private int mouseClickedX;
    private int mouseClickedY;

    float imageTileRate = 0;
    public void update(GameContainer gc, Model model, View view, float dt) {
        boolean buildMode = model.isBuildMode();
        boolean upgradeMode = model.isUpgradeMode();
        boolean trainingMode = model.isTrainingMode();
        Building selectedForUpgrade = model.getSelectedForUpgrade();
        Building selectedNewConstruction = model.getSelectedNewConstruction();
        Rectangle toolbar = view.getToolbar();
        mouseX = model.getMouseX();
        mouseY = model.getMouseY();

        /* Here for reference on how to get keyboard input
		 * if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			sound.play();
		}*/
        imageTileRate += dt * 50;

        if(imageTileRate > 23)
            imageTileRate = 0;

        if(toolbar.isVisible()
                && rightClickUp(gc)) {
            buildMode = false;
            upgradeMode = false;
            trainingMode = false;
            selectedForUpgrade = null;
            selectedNewConstruction = null;
            view.getClickSound().play();
            return;
        } // RIGHT CLICKING ANYWHERE WHEN TOOLBAR IS VISIBLE

        for(Building b : model.getVillage().getBuildings()) {
            if(b != selectedForUpgrade
                    && mouseInBounds(b)
                    && leftClickDown(gc)) {
                upgradeMode = true;
                buildMode = false;
                trainingMode = false;
                selectedForUpgrade = b;
                mouseClickedX = mouseX - b.xPos();
                mouseClickedY = mouseY - b.yPos();
                view.getClickSound().play();
                return;
            }
        } // CLICKING ON A BUILDING ON SCREEN

        if(selectedForUpgrade != null
                && !mouseInBounds(toolbar)
                && leftClickDown(gc)) {
            mouseClickedX = mouseX - selectedForUpgrade.xPos();
            mouseClickedY = mouseY - selectedForUpgrade.yPos();
            view.getClickSound().play();
        } // WHEN MOVING A BUILDING THAT WAS ALREADY CLICKED

        if(selectedForUpgrade != null
                && toolbar.isVisible()
                && leftClickUp(gc)) {
            if(mouseInBounds(view.getUpgradeIcon())) {
                view.getClickSound().play();
                model.getVillage().performUpgrade(selectedForUpgrade);
                return;
            } // CLICKING UPGRADE BUTTON
            else if (selectedForUpgrade instanceof ProductionBuilding
                    && mouseInBounds(view.getTrainIcon())) {
                view.getClickSound().play();
                model.getVillage().trainIndividual((ProductionBuilding<ProductionHabitant>) selectedForUpgrade);
                return;
            } // CLICKING TRAIN BUTTON (FOR PRODUCTION BUILDINGS)
            else if (selectedForUpgrade instanceof ProductionBuilding
                    && mouseInBounds(view.getUpgradeTroopIcon())) {
                view.getClickSound().play();
                model.getVillage().performUpgradeHabitant((ProductionBuilding) selectedForUpgrade);
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
                view.getClickSound().play();
                return;
            } else if(leftClickUp(gc)) { // LEFT CLICK AFTER SELECTING BUILDING FOR CONSTRUCTION
                view.getClickSound().play();
                model.getVillage().newConstruction(selectedNewConstruction);
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
                if(mouseInBounds(view.getArcherTowerSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new ArcherTower(mouseX,mouseY))) {
                        selectedNewConstruction = new ArcherTower(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getCannonSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new Cannon(mouseX,mouseY))) {
                        selectedNewConstruction = new Cannon(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getFarmSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new Farm(mouseX,mouseY))) {
                        selectedNewConstruction = new Farm(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getGoldMineSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new GoldMine(mouseX,mouseY))) {
                        selectedNewConstruction = new GoldMine(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getIronMineSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new IronMine(mouseX,mouseY))) {
                        selectedNewConstruction = new IronMine(mouseX, mouseY);
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getLumbermillSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new LumberMill(mouseX,mouseY))) {
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
                && mouseInBounds(view.getBuildIcon())
                && leftClickUp(gc)) {
            view.getClickSound().play();
            buildMode = true;
        }

        if(toolbar.isVisible()
                && trainingMode
                && !upgradeMode
                && !buildMode) {
        }
    }

    private boolean mouseInBounds(int x, int y, int width, int height) {
        if(mouseX >= x
        && mouseX <= width + x
        && mouseY >= y
        && mouseY <= height + y)
            return true;
        return false;
    }

    private boolean mouseInBounds(Image image) {
        if(image.isVisible())
            return mouseInBounds(image.getX(), image.getY(), image.getWidth(), image.getHeight());
        return false;
    }

    private boolean mouseInBounds(Rectangle rect) {
        if(rect.isVisible())
            return mouseInBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        return false;
    }

    private boolean mouseInBounds(Building building) {
        Rectangle rect = building.getRect();
        return mouseInBounds(rect);
    }

    @SuppressWarnings("unused")
    private boolean overlap(Rectangle rect1, Rectangle rect2) {
        if(rect1.getX() > rect2.getX() + rect2.getWidth() || rect2.getX() > rect1.getX() + rect1.getWidth())
            return false;

        if(rect1.getY() < rect2.getY() + rect2.getHeight() || rect2.getY() < rect1.getY() + rect1.getHeight())
            return false;

        return true;
    }

    private boolean leftClickUp(GameContainer gc) {
        return gc.getInput().isButtonUp(MouseEvent.BUTTON1);
    }

    private boolean leftClickDown(GameContainer gc) {
        return gc.getInput().isButtonDown(MouseEvent.BUTTON1);
    }

    private boolean holdingLeftClick(GameContainer gc) {
        return gc.getInput().isButton(MouseEvent.BUTTON1);
    }

    private boolean rightClickUp(GameContainer gc) {
        return gc.getInput().isButtonUp(MouseEvent.BUTTON3);
    }
}
