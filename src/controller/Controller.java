package controller;

import engine.GameContainer;
import engine.gfx.Image;
import engine.renderPrimitives.Circle;
import engine.renderPrimitives.Rectangle;
import model.Model;
import model.army.*;
import model.buildings.*;
import model.habitants.ProductionHabitant;
import utility.Position;
import view.View;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public final class Controller {

    private int mouseX;
    private int mouseY;
    private int mouseClickedX;
    private int mouseClickedY;
    Timer timer = new Timer();

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
        mouseX = model.getMouseX();
        mouseY = model.getMouseY();
        mouseClickedX = model.getMouseClickedX();
        mouseClickedY = model.getMouseClickedY();

        /* Here for reference on how to get keyboard input
		 * if(gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			sound.play();
		}*/

        imageTileRate += dt * 50;

        if(imageTileRate > 23)
            imageTileRate = 0;

        if(attackMode) {
            // First, check if a building is in radius of a combatant, if so, then attack, otherwise move closer
            // Secondly, check if a combatant is in radius of a building, if so, then attack

            // First part:
            for(Combatant c : model.getPlacedCombatants()) {
                boolean didAttack = false;

                for(Building b : model.getDefendingVillage().getBuildings()) { // check if a building is in attack radius of combatant
                    if(c.enemyInSight(b) && b.HP() > 0) {
                        didAttack = true;
                        if(c.canAttack()) {
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    c.setCanAttack(true);
                                }
                            }, (long) (c.attackSpeed() * 1000));
                            c.setCanAttack(false);
                            c.attack(b);
                        }
                        break;
                    }
                }

                if(!didAttack) { // If this combatant didn't attack cause not in range, then move to closest building
                    Position closestBuildingPosition = null;
                    for(Building b : model.getDefendingVillage().getBuildings()) {
                        if(b.HP() > 0) {
                            int xPos = b.xPos() + (b.width() / 2);
                            int yPos = b.yPos() + (b.height() / 2);

                            if (closestBuildingPosition == null) {
                                closestBuildingPosition = new Position(xPos, yPos);
                                System.out.println(b.getName());
                            } else if (Math.hypot(xPos - c.xPos(), yPos - c.yPos()) <=
                                    Math.hypot(closestBuildingPosition.getX() - c.xPos(), closestBuildingPosition.getY() - c.yPos())) {
                                System.out.println(b.getName());
                                closestBuildingPosition = new Position(xPos, yPos);
                            }
                        }
                    }
                    if(closestBuildingPosition != null) {
                        int xPos = closestBuildingPosition.getX();
                        int yPos = closestBuildingPosition.getY();

                        if (xPos < c.xPos()) {
                            c.setXPos(c.xPos() - 1);
                        } else if (xPos > c.xPos()) {
                            c.setXPos(c.xPos() + 1);
                        }

                        if (yPos < c.yPos()) {
                            c.setYPos(c.yPos() - 1);
                        } else if (yPos > c.yPos()) {
                            c.setYPos(c.yPos() + 1);
                        }
                    }

                }
            }
        } // FOR ATTACKING OF COMBATANTS AND BUILDINGS AND MOVING COMBATANTS TO BUILDINGS

        for(Building b : model.getVillage().getBuildings()) {
            if(b != selectedForUpgrade
            && !attackMode
            && !buildMode
            && !trainingMode
            && mouseInBounds(b)
            && leftClickDown(gc)) {
                model.setUpgradeMode(true);
                model.setBuildMode(false);
                model.setTrainingMode(false);
                model.setSelectedForUpgrade(b);
                model.setMouseClickedX(mouseX - b.xPos());
                model.setMouseClickedY(mouseY - b.yPos());
                view.getClickSound().play();
                return;
            }
        } // CLICKING ON A BUILDING ON SCREEN

        if(selectedForUpgrade != null
        && !mouseInBounds(toolbar)
        && leftClickDown(gc)) {
            model.setMouseClickedX(mouseX - selectedForUpgrade.xPos());
            model.setMouseClickedY(mouseY - selectedForUpgrade.yPos());
            view.getClickSound().play();
            return;
        } // WHEN MOVING A BUILDING THAT WAS ALREADY CLICKED

        if(selectedForUpgrade != null
        && !attackMode
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
        && !attackMode
        && holdingLeftClick(gc)
        && !mouseInBounds(toolbar)) {

            boolean overlap = false;
            for(Building b : model.getVillage().getBuildings()) {
                overlap = overlap(b,selectedForUpgrade);
            }
            if(!overlap) {
                selectedForUpgrade.setXPos(mouseX - mouseClickedX);
                selectedForUpgrade.setYPos(mouseY - mouseClickedY);
            }
            return;
        } // HOLDING LEFT CLICK (AND DRAGGING) ON BUILDING AFTER SELECTING IT

        if(selectedNewConstruction != null) { // BUILD MODE, AND BUILDING WAS ALREADY SELECTED
            if(rightClickUp(gc)) { // RIGHT CLICK AFTER SELECTING BUILDING FOR CONSTRUCTION
                model.setSelectedNewConstruction(null);
                toolbar.setVisible(true);
                view.getClickSound().play();
                return;
            } else if(leftClickUp(gc)) { // LEFT CLICK AFTER SELECTING BUILDING FOR CONSTRUCTION
                view.getClickSound().play();
                model.getVillage().newConstruction(selectedNewConstruction);
                model.setSelectedNewConstruction(null);
                toolbar.setVisible(true);
                return;
            } else { // JUST FOR MOVING THE BUILDING AFTER SELECTING IT FOR CONSTRUCTION
                selectedNewConstruction.setXPos(mouseX - selectedNewConstruction.height() / 2);
                selectedNewConstruction.setYPos(mouseY - selectedNewConstruction.width() / 2);
                return;
            }
        } else if(!attackMode
        && !trainingMode
        && !upgradeMode) {
            if(buildMode
            && leftClickUp(gc)) {
                if(mouseInBounds(view.getArcherTowerSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new ArcherTower(mouseX,mouseY))) {
                        model.setSelectedNewConstruction(new ArcherTower(mouseX,mouseY));
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getCannonSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new Cannon(mouseX,mouseY))) {
                        model.setSelectedNewConstruction(new Cannon(mouseX, mouseY));
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getFarmSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new Farm(mouseX,mouseY))) {
                        model.setSelectedNewConstruction(new Farm(mouseX, mouseY));
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getGoldMineSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new GoldMine(mouseX,mouseY))) {
                        model.setSelectedNewConstruction(new GoldMine(mouseX, mouseY));
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getIronMineSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new IronMine(mouseX,mouseY))) {
                        model.setSelectedNewConstruction(new IronMine(mouseX, mouseY));
                        toolbar.setVisible(false);
                        return;
                    }
                }

                if(mouseInBounds(view.getLumbermillSymbol())) {
                    view.getClickSound().play();
                    if(model.getVillage().canConstruct(new LumberMill(mouseX,mouseY))) {
                        model.setSelectedNewConstruction(new LumberMill(mouseX, mouseY));
                        toolbar.setVisible(false);
                        return;
                    }
                }
            }
        } // BUILD MODE, BUT NO BUILDING SELECTED

        if(trainingMode
        && !upgradeMode
        && !buildMode
        && !attackMode
        && leftClickUp(gc)) {
            if(mouseInBounds(view.getArcherSymbol())) {
                model.getVillage().newIndividual(new Archer(0,0));
                return;
            }

            if(mouseInBounds(view.getSoldierSymbol())) {
                model.getVillage().newIndividual(new Soldier(0,0));
                return;
            }

            if(mouseInBounds(view.getCatapultSymbol())) {
                model.getVillage().newIndividual(new Catapult(0,0));
                return;
            }

            if(mouseInBounds(view.getKnightSymbol())) {
                model.getVillage().newIndividual(new Knight(0,0));
                return;
            }
        } // COMBATANT TRAINING TOOLBAR

        if(attackMode && leftClickUp(gc) && selectedForAttackPlacement == null) {

            ArrayList<Combatant> combatees = model.getAttackingVillage().getCombatees();

            if(mouseInBounds(view.getArcherSymbol())) {
                for (Combatant c : combatees) {
                    if (c.getName().equals("Archer")) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            } else if (mouseInBounds(view.getSoldierSymbol())) {
                for(Combatant c : combatees) {
                    if(c.getName().equals("Soldier")) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            } else if (mouseInBounds(view.getCatapultSymbol())) {
                for(Combatant c : combatees) {
                    if(c.getName().equals("Catapult")) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            } else if (mouseInBounds(view.getKnightSymbol())) {
                for(Combatant c : combatees) {
                    if(c.getName().equals("Knight")) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            }
        } // SELECTING COMBATANT FROM TOOLBAR

        if(attackMode && selectedForAttackPlacement != null) {
            if(leftClickUp(gc) && mouseY < toolbar.getY()) {
                ArrayList<Combatant> combatees = model.getAttackingVillage().getCombatees();
                Combatant placedCombatant = model.getSelectedForAttackPlacement();

                model.placeCombatant(placedCombatant);
                combatees.remove(placedCombatant);

                for(Combatant c : combatees) { // This is for when we place it, instead of clicking on the toolbar again we'll just get the next combatant
                    if(c.getName().equals(model.getSelectedForAttackPlacement().getName())) {
                        model.setSelectedForAttackPlacement(c);
                        break;
                    }
                }

                if(model.getSelectedForAttackPlacement() == placedCombatant)
                    model.setSelectedForAttackPlacement(null);


                view.getClickSound().play();
                return;
            } else if (rightClickUp(gc)) {
                model.setSelectedForAttackPlacement(null);
                view.getClickSound().play();
                return;
            } else {
                selectedForAttackPlacement.setXPos(mouseX);
                selectedForAttackPlacement.setYPos(mouseY);
                return;
            }
        } // CONTROLLING SELECTED FOR ATTACK PLACEMENT

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
        && mouseInBounds(view.getAttackIcon())
        && leftClickUp(gc)) {
            view.getClickSound().play();
            model.setAttackMode(true);
            model.startAttack();
            return;
        } // CLICKING ON ATTACK BUTTON

        if(toolbar.isVisible()
        && rightClickUp(gc)) {
            model.setBuildMode(false);
            model.setUpgradeMode(false);
            model.setTrainingMode(false);
            model.setSelectedNewConstruction(null);
            model.setSelectedForUpgrade(null);
            view.getClickSound().play();
            return;
        } // RIGHT CLICKING ANYWHERE WHEN TOOLBAR IS VISIBLE
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

    private boolean mouseInBounds(Circle circle) {
        if(Math.sqrt(Math.pow(mouseX - circle.getX(), 2) + Math.pow(mouseY - circle.getY(), 2)) <= circle.getRadius())
            return true;
        return false;
    }

    private boolean mouseInBounds(Building building) {
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

    private boolean overlap(Building b1, Building b2) {
        if(b1.xPos() > b2.xPos() + b2.width() || b2.xPos() > b1.xPos() + b1.width())
            return false;

        if(b1.yPos() < b2.yPos() + b2.height() || b2.yPos() < b1.yPos() + b1.height())
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
