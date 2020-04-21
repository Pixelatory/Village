package controller;

import engine.GameContainer;
import engine.gfx.Image;
import engine.renderPrimitives.Circle;
import engine.renderPrimitives.Rectangle;
import model.Model;
import model.army.Combatant;
import model.buildings.Building;
import utility.GameState;
import view.View;

import java.awt.event.MouseEvent;
import java.util.Timer;

public final class Controller {

    protected static int mouseX;
    protected static int mouseY;
    protected static int mouseClickedX;
    protected static int mouseClickedY;
    protected static Timer timer = new Timer();

    float imageTileRate = 0;
    public void update(GameContainer gc, Model model, View view, float dt) {
        boolean buildMode = model.isBuildMode();
        boolean upgradeMode = model.isUpgradeMode();
        boolean trainingMode = model.isTrainingMode();
        boolean attackMode = model.isAttackMode();
        Building selectedForUpgrade = model.getSelectedForUpgrade();
        Building selectedNewConstruction = model.getSelectedNewConstruction();
        Combatant selectedForAttackPlacement = model.getSelectedForAttackPlacement();
        Rectangle toolbar = view.getToolbar();
        mouseX = gc.getInput().getMouseX();
        mouseY = gc.getInput().getMouseY();
        mouseClickedX = model.getMouseClickedX();
        mouseClickedY = model.getMouseClickedY();

        /* Here for reference on how to get keyboard input
		 * if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			sound.play();
		}*/

        /*imageTileRate += dt * 50;

        if(imageTileRate > 23)
            imageTileRate = 0;*/


        for(Building b : model.getVillage().getBuildings()) {
            if(b != selectedForUpgrade
            && !attackMode
            && mouseInBounds(b)
            && leftClickDown(gc)) {
                model.setSelectedForUpgrade(b);
                model.setUpgradeMode(true);
                model.setTrainingMode(false);
                model.setBuildMode(false);
                model.setMouseClickedX(mouseX - b.xPos());
                model.setMouseClickedY(mouseY - b.yPos());
                view.getClickSound().play();
                GameState.save(model.getVillage());
                return;
            }
        } // CLICKING ON A BUILDING ON SCREEN TO ACTIVATE UPGRADE MODE

        if(toolbar.isVisible()
                && rightClickUp(gc)) {
            model.setTrainingMode(false);
            model.setBuildMode(false);
            model.setUpgradeMode(false);
            model.setSelectedNewConstruction(null);
            model.setSelectedForUpgrade(null);
            model.setSelectedForAttackPlacement(null);
            view.getClickSound().play();
            return;
        } // RIGHT CLICKING ANYWHERE WHEN TOOLBAR IS VISIBLE

        if(model.isAttackMode()) {
            AttackController.execute(gc,model,view);
            return;
        } else if (selectedForUpgrade != null && upgradeMode) {
            UpgradeContoller.execute(gc,model,view);
            return;
        } else if (selectedNewConstruction != null || buildMode) {
            BuildController.execute(gc,model,view);
            return;
        } else if(trainingMode) {
            TrainingController.execute(gc,model,view);
            return;
        }

        if(toolbar.isVisible()
        && !buildMode
        && !upgradeMode
        && !trainingMode
        && !attackMode
        && mouseInBounds(view.getBuildIcon())
        && leftClickUp(gc)) {
            view.getClickSound().play();
            model.setBuildMode(true);
            return;
        } // CLICKING ON BUILD BUTTON

        if(toolbar.isVisible()
        && !trainingMode
        && !upgradeMode
        && !buildMode
        && !attackMode
        && mouseInBounds(view.getTrainCombatantIcon())
        && leftClickUp(gc)) {
            view.getClickSound().play();
            model.setTrainingMode(true);
            return;
        } // CLICKING ON TRAIN BUTTON

        if(toolbar.isVisible()
        && !trainingMode
        && !upgradeMode
        && !buildMode
        && !attackMode
        && model.getCombatees().size() > 0
        && mouseInBounds(view.getAttackIcon())
        && leftClickUp(gc)) {
            boolean atLeastOneNotUpgrading = false;
            for(Combatant c : model.getCombatees()) {
                if(!c.isUpgrading()) {
                    atLeastOneNotUpgrading = true;
                    break;
                }
            }

            if(atLeastOneNotUpgrading) {
                view.getClickSound().play();
                model.setAttackMode(true);
                model.startAttack();
            }
        } // CLICKING ON ATTACK BUTTON
    }

    protected static boolean mouseInBounds(int x, int y, int width, int height) {
        if(mouseX >= x
        && mouseX <= width + x
        && mouseY >= y
        && mouseY <= height + y)
            return true;
        return false;
    }

    protected static boolean mouseInBounds(Image image) {
        if(image.isVisible())
            return mouseInBounds(image.getX(), image.getY(), image.getWidth(), image.getHeight());
        return false;
    }

    protected static boolean mouseInBounds(Rectangle rect) {
        if(rect.isVisible())
            return mouseInBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        return false;
    }

    protected static boolean mouseInBounds(Circle circle) {
        if(Math.sqrt(Math.pow(mouseX - circle.getX(), 2) + Math.pow(mouseY - circle.getY(), 2)) <= circle.getRadius())
            return true;
        return false;
    }

    protected static boolean mouseInBounds(Building building) {
        Rectangle rect = building.getRect();
        return mouseInBounds(rect);
    }

    private boolean overlap(Rectangle rect1, Rectangle rect2) {
        if(rect1.getX() > rect2.getX() + rect2.getWidth() || rect2.getX() > rect1.getX() + rect1.getWidth())
            return false;

        if(rect1.getY() < rect2.getY() + rect2.getHeight() || rect2.getY() < rect1.getY() + rect1.getHeight())
            return false;

        return true;
    }

    protected static boolean overlap(Building b1, Building b2) {
        if(b1.xPos() > b2.xPos() + b2.width() || b2.xPos() > b1.xPos() + b1.width())
            return false;

        if(b1.yPos() < b2.yPos() + b2.height() || b2.yPos() < b1.yPos() + b1.height())
            return false;

        return true;
    }

    protected static boolean leftClickUp(GameContainer gc) {
        return gc.getInput().isButtonUp(MouseEvent.BUTTON1);
    }

    protected static boolean leftClickDown(GameContainer gc) {
        return gc.getInput().isButtonDown(MouseEvent.BUTTON1);
    }

    protected static boolean holdingLeftClick(GameContainer gc) {
        return gc.getInput().isButton(MouseEvent.BUTTON1);
    }

    protected static boolean rightClickUp(GameContainer gc) {
        return gc.getInput().isButtonUp(MouseEvent.BUTTON3);
    }
}
