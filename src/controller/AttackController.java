package controller;

import engine.GameContainer;
import model.Model;
import model.army.Combatant;
import model.buildings.*;
import utility.GameState;
import utility.Position;
import utility.timers.AttackTimerTask;
import view.View;

import java.util.ArrayList;

/**
 * AttackController
 *
 * A controller that only applies when the game is in an attack village state
 *
 * @author 6177000 (na16dg)
 */
class AttackController {
    protected static void execute(GameContainer gc, Model model, View view) {
        /*
         First, check if a building is in radius of a combatant, if so, then attack, otherwise move closer

         Secondly, check if a combatant is in radius of a building, if so, then attack
        */

        // First part:
        for(Combatant c : model.getPlacedCombatants()) {
            if(c.HP() > 0) {
                boolean didAttack = false;

                for (Building b : model.getDefendingVillage().getBuildings()) { // check if a building is in attack radius of combatant
                    if (c.enemyInSight(b) && b.HP() > 0) {
                        didAttack = true;
                        if (c.canAttack()) {
                            Controller.timer.schedule(new AttackTimerTask(c), (long) (c.attackSpeed() * 1000));
                            c.setCanAttack(false);
                            c.attack(b);
                            if(b.HP() <= 0) {
                                if(b instanceof GoldMine) {
                                    int goldGained = (model.getDefendingVillage().getGold()) / model.getDefendingVillage().getBuildings().size();
                                    model.getDefendingVillage().decreaseGold(goldGained);
                                    model.getAttackingVillage().increaseGold(goldGained);
                                } else if(b instanceof IronMine) {
                                    int ironGained = (model.getDefendingVillage().getIron()) / model.getDefendingVillage().getBuildings().size();
                                    model.getDefendingVillage().decreaseIron(ironGained);
                                    model.getAttackingVillage().increaseIron(ironGained);
                                } else if(b instanceof LumberMill) {
                                    int woodGained = (model.getDefendingVillage().getWood()) / model.getDefendingVillage().getBuildings().size();
                                    model.getDefendingVillage().decreaseWood(woodGained);
                                    model.getAttackingVillage().increaseWood(woodGained);
                                }
                            }
                        }
                        break;
                    }
                }

                if (!didAttack) { // If this combatant didn't attack cause not in range, then move to closest building
                    Position closestBuildingPosition = null;
                    for (Building b : model.getDefendingVillage().getBuildings()) {
                        if (b.HP() > 0) {
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
                    if (closestBuildingPosition != null) {
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
        } // END OF COMBATANT ATTACKING HERE

        for(Building b : model.getDefendingVillage().getBuildings()) { // START ATTACKING OF DEFENSIVE BUILDINGS HERE
            if(b instanceof DefensiveBuilding && b.HP() > 0) {
                DefensiveBuilding b2 = (DefensiveBuilding) b;
                for (Combatant c : model.getPlacedCombatants()) {
                    if (b2.enemyInSight(c) && c.HP() > 0) {
                        if (b2.canAttack() && !b2.isUpgrading()) {
                            Controller.timer.schedule(new AttackTimerTask(b2) {
                                @Override
                                public void run() {
                                    b2.setCanAttack(true);
                                }
                            }, (long) (b2.attackSpeed() * 1000));
                            b2.setCanAttack(false);
                            b2.attack(c);
                        }
                        break;
                    }
                }
            }
        } // END OF DEFENSIVE BUILDING ATTACKING HERE

        if(Controller.leftClickUp(gc) && model.getSelectedForAttackPlacement() == null) {

            ArrayList<Combatant> combatees = model.getAttackingVillage().getCombatees();

            if(Controller.mouseInBounds(view.getArcherSymbol())) {
                for (Combatant c : combatees) {
                    if (c.getName().equals("Archer") && !c.isUpgrading()) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            } else if (Controller.mouseInBounds(view.getSoldierSymbol())) {
                for(Combatant c : combatees) {
                    if(c.getName().equals("Soldier") && !c.isUpgrading()) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            } else if (Controller.mouseInBounds(view.getCatapultSymbol())) {
                for(Combatant c : combatees) {
                    if(c.getName().equals("Catapult") && !c.isUpgrading()) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            } else if (Controller.mouseInBounds(view.getKnightSymbol())) {
                for(Combatant c : combatees) {
                    if(c.getName().equals("Knight") && !c.isUpgrading()) {
                        model.setSelectedForAttackPlacement(c);
                        view.getClickSound().play();
                        return;
                    }
                }
            }
        } // SELECTING COMBATANT FROM TOOLBAR


        if(model.getSelectedForAttackPlacement() != null) {
            if(Controller.leftClickUp(gc) && Controller.mouseY < view.getToolbar().getY()) {
                ArrayList<Combatant> combatees = model.getAttackingVillage().getCombatees();
                Combatant placedCombatant = model.getSelectedForAttackPlacement();

                model.placeCombatant(placedCombatant);
                combatees.remove(placedCombatant);

                for(Combatant c : combatees) { // This is for when we place it, instead of clicking on the toolbar again we'll just get the next combatant
                    if(c.getName().equals(model.getSelectedForAttackPlacement().getName()) && !c.isUpgrading()) {
                        model.setSelectedForAttackPlacement(c);
                        break;
                    }
                }

                switch(placedCombatant.getName()) {
                    case "Archer":
                        view.getDeployArcherSound().play();
                        break;
                    case "Soldier":
                        view.getDeploySoldierSound().play();
                        break;
                    case "Knight":
                        view.getDeployKnightSound().play();
                        break;
                    case "Catapult":
                        view.getDeployCatapultSound().play();
                        break;
                }

                if(model.getSelectedForAttackPlacement() == placedCombatant)
                    model.setSelectedForAttackPlacement(null);

                GameState.save(model.getVillage());
                return;
            } else if (Controller.rightClickUp(gc)) {
                model.setSelectedForAttackPlacement(null);
                view.getClickSound().play();
                return;
            } else {
                model.getSelectedForAttackPlacement().setXPos(Controller.mouseX);
                model.getSelectedForAttackPlacement().setYPos(Controller.mouseY);
            }
        } // CONTROLLING SELECTED FOR ATTACK PLACEMENT, WHETHER

        if(view.getToolbar().isVisible()
        && Controller.mouseInBounds(view.getEndBattleIcon())
        && Controller.leftClickUp(gc)) {
            model.endAttack();
            GameState.save(model.getVillage());
            view.getClickSound().play();
        } // ENDING THE BATTLE
    }

    private int goldGained(Building b, int totalGold, int numOfBuildings) {
        if(b instanceof GoldMine)
            return (totalGold / 5) / numOfBuildings;
        return 0;
    }

    private int ironGained(Building b, int totalIron, int numOfBuildings) {
        if(b instanceof IronMine)
            return (totalIron / 5) / numOfBuildings;
        return 0;
    }

    private int woodGained(Building b, int totalWood, int numOfBuildings) {
        if(b instanceof LumberMill)
            return (totalWood / 5) / numOfBuildings;
        return 0;
    }

    private int foodGained(Building b, int totalFood, int numOfBuildings) {
        if(b instanceof Farm)
            return (totalFood / 5) / numOfBuildings;
        return 0;
    }
}
