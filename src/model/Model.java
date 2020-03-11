package model;

import model.buildings.Building;
import model.village.Village;

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

    private int mouseX;
    private int mouseY;
    private int mouseClickedX;
    private int mouseClickedY;

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

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
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
