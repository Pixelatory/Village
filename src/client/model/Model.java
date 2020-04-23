package client.model;

import client.model.army.Combatant;
import client.model.attack.Attack;
import client.model.buildings.Building;
import client.model.village.Village;

import java.util.ArrayList;
import java.util.Random;

/**
 * Contains the client.model of the client.game.<br>
 *
 * Various data which is used by the Controller and View (MVC), is found here.
 *
 * @author 6177000
 * @see client.view.View
 * @see client.controller.Controller
 *
 */
public class Model {
    // Instance variables
    private boolean trainingMode = false;
    private boolean attackMode = false;
    private boolean buildMode = false;
    private boolean upgradeMode = false;

    private Village village;
    private Building selectedNewConstruction = null;
    private Building selectedForUpgrade = null;
    private Combatant selectedForAttackPlacement = null;

    private int mouseX = 0;
    private int mouseY = 0;
    private int mouseClickedX = 0;
    private int mouseClickedY = 0;

    private Random rand = new Random();

    private boolean placedACombatant = false;

    private Attack attack = new Attack();

    public Model() {
        village = new Village();
    }

    public Model(Village village) {
        this.village = village;
    }

    /*
        Setter/Getter functions
     */
    public boolean placedACombatant() {
        return placedACombatant;
    }

    public boolean isTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(boolean trainingMode) {
        this.trainingMode = trainingMode;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public ArrayList<Combatant> getCombatees() {return village.getCombatees();}

    public Building getSelectedNewConstruction() {
        return selectedNewConstruction;
    }

    public void setSelectedNewConstruction(Building selectedNewConstruction) {
        this.selectedNewConstruction = selectedNewConstruction;
    }

    public void setSelectedForAttackPlacement(Combatant c) {
        selectedForAttackPlacement = c;
    }

    public Combatant getSelectedForAttackPlacement() {
        return selectedForAttackPlacement;
    }

    public Building getSelectedForUpgrade() {
        return selectedForUpgrade;
    }

    public void setSelectedForUpgrade(Building selectedForUpgrade) {
        this.selectedForUpgrade = selectedForUpgrade;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseClickedX() {
        return mouseClickedX;
    }

    public void setMouseClickedX(int mouseClickedX) {
        this.mouseClickedX = mouseClickedX;
    }

    public int getMouseClickedY() {
        return mouseClickedY;
    }

    public void setMouseClickedY(int mouseClickedY) {
        this.mouseClickedY = mouseClickedY;
    }

    public boolean isAttackMode() {
        return attackMode;
    }

    public void setAttackMode(boolean value) {
        attackMode = value;
    }

    public Village getAttackingVillage() {
        return attack.getAttackingVillage();
    }

    public Village getDefendingVillage() {
        return attack.getDefendingVillage();
    }

    public void startAttack() {
        Village generatedVillage = new Village(attack.generateComparedTo(village));
        generatedVillage.increaseGold(100000);
        generatedVillage.increaseWood(100000);
        generatedVillage.increaseIron(100000);
        attack.setDefendingVillage(new Village(attack.generateComparedTo(village)));
        attack.setAttackingVillage(village);
    }

    public boolean isBuildMode() {
        return buildMode;
    }

    public void setBuildMode(boolean buildMode) {
        this.buildMode = buildMode;
    }

    public boolean isUpgradeMode() {
        return upgradeMode;
    }

    public void setUpgradeMode(boolean upgradeMode) {
        this.upgradeMode = upgradeMode;
    }

    public void endAttack() {
        getAttackingVillage().increaseIron((int) (getAttackScore() * 10));
        getAttackingVillage().increaseWood((int) (getAttackScore() * 10));
        getAttackingVillage().increaseGold((int) (getAttackScore() * 10));
        attack.clearAttackingVillage();
        attack.clearDefendingVillage();
        attack.clearPlacedCombatants();
        placedACombatant = false;
        selectedForAttackPlacement = null;
        attackMode = false;
    }

    public void placeCombatant(Combatant combatant) {
        attack.addPlacedCombatant(combatant);
        placedACombatant = true;
    }

    public float getAttackScore() {
        return attack.attackScore();
    }

    public int getAttackWoodGained() {
        return attack.woodGained();
    }

    public int getAttackIronGained() {
        return attack.ironGained();
    }

    public int getAttackGoldGained() {
        return attack.goldGained();
    }

    public ArrayList<Combatant> getPlacedCombatants() {
        return attack.getPlacedCombatants();
    }
}
