package view;

import engine.GameContainer;
import engine.Renderer;
import model.Model;
import model.buildings.Building;
import model.buildings.ProductionBuilding;
import model.habitants.ProductionHabitant;

import java.awt.*;

public final class View {
    public void render(GameContainer gc, Renderer r, Model m) {
        r.setzDepth(0);
        r.drawImage(backgroundImage); // show the background image
        r.setzDepth(1);
        for(Building b : village.getBuildings()) { // display all model.village model.buildings
            r.drawRect(b.getRect());
            r.drawText(Float.toString(b.HP()), smallFont.getFontImage(), b.xPos(), b.yPos(), Color.black);
        }

        if(selectedNewConstruction != null) { // after selecting a building from building mode, then display it on cursor
            r.setzDepth(2);
            r.drawRect(selectedNewConstruction.getRect());
        }

        if(toolbar.isVisible()
                && !buildMode
                && !upgradeMode
                && !trainingMode) { // DEFAULT TOOLBAR
            r.setzDepth(Integer.MAX_VALUE);
            r.drawText("Build", smallFont.getFontImage(), buildIcon.getX() - 2, toolbar.getY() + 1, Color.white);
            r.drawImage(buildIcon);
            r.drawText("Train Army", smallFont.getFontImage(), trainCombatantIcon.getX() - 18, toolbar.getY() + 1, Color.white);
            r.drawImage(trainCombatantIcon);
        }

        if(toolbar.isVisible() && buildMode) { // BUILD MODE TOOLBAR
            r.setzDepth(Integer.MAX_VALUE);
            r.drawRect(archerTowerSymbol);
            r.drawRect(cannonSymbol);
            r.drawRect(farmSymbol);
            r.drawRect(goldMineSymbol);
            r.drawRect(ironMineSymbol);
            r.drawRect(lumbermillSymbol);
            r.drawText("Archer Tower", smallFont.getFontImage(), toolbar.getX(), toolbar.getY(), Color.white);
            r.drawText("Cannon", smallFont.getFontImage(), toolbar.getX() + 90, toolbar.getY(), Color.white);
            r.drawText("Farm", smallFont.getFontImage(), toolbar.getX() + 172, toolbar.getY(), Color.white);
            r.drawText("Gold Mine", smallFont.getFontImage(), toolbar.getX() + 228, toolbar.getY(), Color.white);
            r.drawText("Iron Mine", smallFont.getFontImage(), toolbar.getX() + 308, toolbar.getY(), Color.white);
            r.drawText("Lumbermill", smallFont.getFontImage(), toolbar.getX() + 378, toolbar.getY(), Color.white);
        }

        if(toolbar.isVisible() && upgradeMode) { // UPGRADE MODE TOOLBAR
            r.setzDepth(Integer.MAX_VALUE);
            r.drawImage(upgradeIcon);
            r.drawText("Upgrade", smallFont.getFontImage(), toolbar.getX(), toolbar.getY(), Color.white);

            if(selectedForUpgrade instanceof ProductionBuilding) {
                @SuppressWarnings("rawtypes")
                ProductionBuilding b = (ProductionBuilding) selectedForUpgrade;
                r.drawImage(trainIcon);
                r.drawText("Train", smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY(), Color.white);

                r.drawImage(upgradeTroopIcon);
                r.drawText("Upgrade", smallFont.getFontImage(), toolbar.getX() + 135, toolbar.getY(), Color.white);

                r.drawText(selectedForUpgrade.getName(), smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY(), Color.white);

                if(b.numOfWorkers() < b.workerCapacity())
                    r.drawText("Workers: " + b.numOfWorkers(), smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 12, Color.white);
                else
                    r.drawText("Workers: " + b.numOfWorkers() + " (MAX)", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 12, Color.white);

                if(b.numOfWorkers() > 0) {
                    ProductionHabitant h = b.getLowestLevelWorker();
                    if(h.level() == h.maxLevel())
                        r.drawText("Lowest Worker Level: " + b.getLowestLevelWorker().level() + " (MAX)", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 24, Color.white);
                    else
                        r.drawText("Lowest Worker Level: " + b.getLowestLevelWorker().level(), smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 24, Color.white);
                }

                if(b.isUpgrading())
                    r.drawText("Upgrading building...", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 36, Color.white);

                if(b.isTraining())
                    r.drawText("Training worker...", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY() + 36, Color.white);
            } else {
                r.drawText(selectedForUpgrade.getName(), smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY(), Color.white);
            }
        }

        if(toolbar.isVisible() && trainingMode) {

        }

        r.setzDepth(Integer.MAX_VALUE);
        r.drawText("Gold: " + village.getGold(), regularFont.getFontImage(), 0, 15, Color.WHITE);
        r.drawText("Iron: " + village.getIron(), regularFont.getFontImage(), 0, 30, Color.WHITE);
        r.drawText("Wood: " + village.getWood(), regularFont.getFontImage(), 0, 45, Color.WHITE);
        r.setzDepth(Integer.MAX_VALUE - 1);
        r.drawRect(toolbar);
    }
}
