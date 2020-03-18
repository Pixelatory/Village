package model;

import engine.GameContainer;
import model.army.Combatant;
import model.buildings.Building;
import model.village.Village;

import java.util.ArrayList;

/**
 * Contains the model of the game.<br>
 *
 * Various data which is used by the Controller and View (MVC), is found here.
 *
 * @author 6177000
 * @see view.View
 * @see controller.Controller
 *
 */
public class Model {
    // Instance variables
    private boolean buildMode = false;
    private boolean upgradeMode = false;
    private boolean trainingMode = false;

    private Village village;
    private Building selectedNewConstruction = null;
    private Building selectedForUpgrade = null;

    private int mouseX = 0;
    private int mouseY = 0;
    private int mouseClickedX = 0;
    private int mouseClickedY = 0;

    public Model() {
        village = new Village();
    }

    public void update(GameContainer gc) {
        mouseX = gc.getInput().getMouseX();
        mouseY = gc.getInput().getMouseY();
    }

    /*
        Setter/Getter functions
     */
    public boolean isUpgradeMode() {
        return upgradeMode;
    }

    public void setUpgradeMode(boolean upgradeMode) {
        this.upgradeMode = upgradeMode;
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

    public boolean isBuildMode() {
        return buildMode;
    }

    public void setBuildMode(boolean buildMode) {
        this.buildMode = buildMode;
    }
}
