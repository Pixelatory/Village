package view;

import engine.GameContainer;
import engine.Renderer;
import engine.audio.Sound;
import engine.gfx.Font;
import engine.gfx.Image;
import engine.renderPrimitives.Rectangle;
import model.Model;
import model.buildings.*;
import model.habitants.ProductionHabitant;
import model.village.Village;

import java.awt.*;

public final class View {
    // Fonts
    private Font regularFont = new Font("/fonts/font-2.png");
    private Font smallFont = new Font("/fonts/font-3.png");

    // toolbar
    private Rectangle toolbar = new Rectangle(0,GameContainer.getHeight() - 50, GameContainer.getWidth(), 50, Color.gray);

    // Images
    private Image backgroundImage = new Image("/grass.png",0,0);
    private Image buildIcon = new Image("/icons/build.png", toolbar.getX() + 5, toolbar.getY() + 16);
    private Image upgradeIcon = new Image("/icons/upgrade.png", toolbar.getX() + 5, toolbar.getY() + 16);

    private Image upgradeTroopIcon = new Image("/icons/upgradeTroop.png", toolbar.getX() + 145, toolbar.getY() + 16);
    private Image trainIcon = new Image("/icons/train.png",toolbar.getX() + 75, toolbar.getY() + 16);
    private Image trainCombatantIcon = new Image("/icons/trainCombatant.png", toolbar.getX() + 75, toolbar.getY() + 16);

    // Used for the build mode toolbar
    private Rectangle archerTowerSymbol = new ArcherTower(toolbar.getX() + 25,toolbar.getY() + 15).getRect();
    private Rectangle cannonSymbol = new Cannon(toolbar.getX() + 100, toolbar.getY() + 15).getRect();
    private Rectangle farmSymbol = new Farm(toolbar.getX() + 175, toolbar.getY() + 15).getRect();
    private Rectangle goldMineSymbol = new GoldMine(toolbar.getX() + 250, toolbar.getY() + 15).getRect();
    private Rectangle ironMineSymbol = new IronMine(toolbar.getX() + 325, toolbar.getY() + 15).getRect();
    private Rectangle lumbermillSymbol = new LumberMill(toolbar.getX() + 400, toolbar.getY() + 15).getRect();

    //Sounds
    private Sound clickSound = new Sound("/sounds/button_click.wav");
    private Sound mainMusic = new Sound("/music/main-theme.wav");

    private int mouseX;
    private int mouseY;

    public View() {
        toolbar.setVisible(true);
        mainMusic.loop();
    }

    public void render(GameContainer gc, Renderer r, Model m) {
        Village village = m.getVillage();
        Building selectedNewConstruction = m.getSelectedNewConstruction();
        Building selectedForUpgrade = m.getSelectedForUpgrade();
        boolean buildMode = m.isBuildMode();
        boolean upgradeMode = m.isUpgradeMode();
        boolean trainingMode = m.isTrainingMode();

        r.setzDepth(0);
        r.drawImage(backgroundImage); // show the background image
        r.setzDepth(1);

        for(Building b : village.getBuildings()) { // display all village buildings
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

        if(toolbar.isVisible() && trainingMode) { // TRAINING MODE TOOLBAR

        }

        r.setzDepth(Integer.MAX_VALUE); // DISPLAYING RESOURCE COUNTS
        r.drawText("Gold: " + village.getGold(), regularFont.getFontImage(), 0, 15, Color.WHITE);
        r.drawText("Iron: " + village.getIron(), regularFont.getFontImage(), 0, 30, Color.WHITE);
        r.drawText("Wood: " + village.getWood(), regularFont.getFontImage(), 0, 45, Color.WHITE);
        r.drawText("Food: " + village.getFood(), regularFont.getFontImage(), 0, 60, Color.WHITE);
        r.setzDepth(Integer.MAX_VALUE - 1);
        r.drawRect(toolbar);
    }

    public Font getRegularFont() {
        return regularFont;
    }

    public void setRegularFont(Font regularFont) {
        this.regularFont = regularFont;
    }

    public Font getSmallFont() {
        return smallFont;
    }

    public void setSmallFont(Font smallFont) {
        this.smallFont = smallFont;
    }

    public Rectangle getToolbar() {
        return toolbar;
    }

    public void setToolbar(Rectangle toolbar) {
        this.toolbar = toolbar;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Image getBuildIcon() {
        return buildIcon;
    }

    public void setBuildIcon(Image buildIcon) {
        this.buildIcon = buildIcon;
    }

    public Image getUpgradeIcon() {
        return upgradeIcon;
    }

    public void setUpgradeIcon(Image upgradeIcon) {
        this.upgradeIcon = upgradeIcon;
    }

    public Image getUpgradeTroopIcon() {
        return upgradeTroopIcon;
    }

    public void setUpgradeTroopIcon(Image upgradeTroopIcon) {
        this.upgradeTroopIcon = upgradeTroopIcon;
    }

    public Image getTrainIcon() {
        return trainIcon;
    }

    public void setTrainIcon(Image trainIcon) {
        this.trainIcon = trainIcon;
    }

    public Image getTrainCombatantIcon() {
        return trainCombatantIcon;
    }

    public void setTrainCombatantIcon(Image trainCombatantIcon) {
        this.trainCombatantIcon = trainCombatantIcon;
    }

    public Rectangle getArcherTowerSymbol() {
        return archerTowerSymbol;
    }

    public void setArcherTowerSymbol(Rectangle archerTowerSymbol) {
        this.archerTowerSymbol = archerTowerSymbol;
    }

    public Rectangle getCannonSymbol() {
        return cannonSymbol;
    }

    public void setCannonSymbol(Rectangle cannonSymbol) {
        this.cannonSymbol = cannonSymbol;
    }

    public Rectangle getFarmSymbol() {
        return farmSymbol;
    }

    public void setFarmSymbol(Rectangle farmSymbol) {
        this.farmSymbol = farmSymbol;
    }

    public Rectangle getGoldMineSymbol() {
        return goldMineSymbol;
    }

    public void setGoldMineSymbol(Rectangle goldMineSymbol) {
        this.goldMineSymbol = goldMineSymbol;
    }

    public Rectangle getIronMineSymbol() {
        return ironMineSymbol;
    }

    public void setIronMineSymbol(Rectangle ironMineSymbol) {
        this.ironMineSymbol = ironMineSymbol;
    }

    public Rectangle getLumbermillSymbol() {
        return lumbermillSymbol;
    }

    public void setLumbermillSymbol(Rectangle lumbermillSymbol) {
        this.lumbermillSymbol = lumbermillSymbol;
    }

    public Sound getClickSound() {
        return clickSound;
    }

    public void setClickSound(Sound clickSound) {
        this.clickSound = clickSound;
    }

    public Sound getMainMusic() {
        return mainMusic;
    }

    public void setMainMusic(Sound mainMusic) {
        this.mainMusic = mainMusic;
    }
}
