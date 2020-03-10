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

}
