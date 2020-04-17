package view;

import engine.GameContainer;
import engine.Renderer;
import engine.audio.Sound;
import engine.gfx.Font;
import engine.gfx.Image;
import engine.renderPrimitives.Circle;
import engine.renderPrimitives.Rectangle;
import exceptions.UnknownCombatantException;
import model.Model;
import model.army.*;
import model.buildings.*;
import model.habitants.ProductionHabitant;
import model.village.Village;

import java.awt.*;
import java.util.ArrayList;

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
    private Image attackIcon = new Image("/icons/attack.png", toolbar.getWidth() - 50, toolbar.getY() + 16);

    private Image upgradeTroopIcon = new Image("/icons/upgradeTroop.png", toolbar.getX() + 145, toolbar.getY() + 16);
    private Image trainIcon = new Image("/icons/train.png",toolbar.getX() + 75, toolbar.getY() + 16);
    private Image trainCombatantIcon = new Image("/icons/trainCombatant.png", toolbar.getX() + 75, toolbar.getY() + 16);
    private Image endBattleIcon = new Image("/icons/end-battle.png", toolbar.getX() + toolbar.getWidth() - 60, toolbar.getY() + 16);

    // Used for the build mode toolbar
    private Rectangle archerTowerSymbol = new ArcherTower(toolbar.getX() + 25,toolbar.getY() + 15).getRect();
    private Rectangle cannonSymbol = new Cannon(toolbar.getX() + 100, toolbar.getY() + 15).getRect();
    private Rectangle farmSymbol = new Farm(toolbar.getX() + 175, toolbar.getY() + 15).getRect();
    private Rectangle goldMineSymbol = new GoldMine(toolbar.getX() + 250, toolbar.getY() + 15).getRect();
    private Rectangle ironMineSymbol = new IronMine(toolbar.getX() + 325, toolbar.getY() + 15).getRect();
    private Rectangle lumbermillSymbol = new LumberMill(toolbar.getX() + 400, toolbar.getY() + 15).getRect();

    // Used for the train combatant mode toolbar
    private Circle archerSymbol = new Archer(toolbar.getX() + 25, toolbar.getY() + 30).getCircle();
    private Circle catapultSymbol = new Catapult(toolbar.getX() + 185, toolbar.getY() + 30).getCircle();
    private Circle knightSymbol = new Knight(toolbar.getX() + 75, toolbar.getY() + 30).getCircle();
    private Circle soldierSymbol = new Soldier(toolbar.getX() + 125, toolbar.getY() + 30).getCircle();

    //Sounds
    private Sound clickSound = new Sound("/sounds/button_click.wav");
    private Sound mainMusic = new Sound("/music/main-theme.wav");

    private Sound deployArcher = new Sound("/sounds/archer_deploy.wav");
    private Sound deployKnight = new Sound("/sounds/knight_deploy.wav");
    private Sound deploySoldier = new Sound("/sounds/soldier_deploy.wav");
    private Sound deployCatapult = new Sound("/sounds/catapult_deploy.wav");


    public View() {
        toolbar.setVisible(true);
        setMainMusic("/music/main-theme.wav");
        mainMusic.loop();
    }

    public void render(GameContainer gc, Renderer r, Model m) {
        Village village = m.getVillage();
        Building selectedNewConstruction = m.getSelectedNewConstruction();
        Building selectedForUpgrade = m.getSelectedForUpgrade();
        Combatant selectedForAttackPlacement = m.getSelectedForAttackPlacement();
        boolean buildMode = m.isBuildMode();
        boolean upgradeMode = m.isUpgradeMode();
        boolean trainingMode = m.isTrainingMode();
        boolean attackMode = m.isAttackMode();

        r.setzDepth(0);
        r.drawImage(backgroundImage); // show the background image
        r.setzDepth(1);

        if(!attackMode) {
            for (Building b : village.getBuildings()) { // display all village buildings
                r.drawRect(b.getRect());
                r.drawText(Float.toString(b.HP()), smallFont.getFontImage(), b.xPos(), b.yPos(), Color.black);
            }

            if (selectedNewConstruction != null) { // after selecting a building from building mode, then display it on cursor
                r.setzDepth(2);
                r.drawRect(selectedNewConstruction.getRect());
            }
        }

        if(toolbar.isVisible()
        && !buildMode
        && !upgradeMode
        && !trainingMode
        && !attackMode) { // DEFAULT TOOLBAR
            r.setzDepth(Integer.MAX_VALUE);
            r.drawText("Build", smallFont.getFontImage(), buildIcon.getX() - 2, toolbar.getY() + 1, Color.white);
            r.drawImage(buildIcon);

            r.drawText("Train Army", smallFont.getFontImage(), trainCombatantIcon.getX() - 18, toolbar.getY() + 1, Color.white);
            r.drawImage(trainCombatantIcon);

            r.drawText("Archer: " + countCombatants(m.getCombatees(), "Archer"), smallFont.getFontImage(), getTrainCombatantIcon().getX() + 75, toolbar.getY() + 1, Color.white);
            r.drawText("Knight: " + countCombatants(m.getCombatees(), "Knight"), smallFont.getFontImage(), getTrainCombatantIcon().getX() + 75, toolbar.getY() + 12, Color.white);
            r.drawText("Soldier: " + countCombatants(m.getCombatees(), "Soldier"), smallFont.getFontImage(), getTrainCombatantIcon().getX() + 75, toolbar.getY() + 23, Color.white);
            r.drawText("Catapult: " + countCombatants(m.getCombatees(), "Catapult"), smallFont.getFontImage(), getTrainCombatantIcon().getX() + 75, toolbar.getY() + 34, Color.white);

            if(m.getCombatees().size() > 0) {
                r.drawText("Attack", smallFont.getFontImage(), attackIcon.getX() - 1, toolbar.getY() + 1, Color.white);
                r.drawImage(attackIcon);
            }
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

                if(selectedForUpgrade.level() < selectedForUpgrade.maxLevel())
                    r.drawText(selectedForUpgrade.getName() + " (Level: " + selectedForUpgrade.getLevel() + ")", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY(), Color.white);
                else
                    r.drawText(selectedForUpgrade.getName() + " (Level: MAX)", smallFont.getFontImage(), toolbar.getX() + 200, toolbar.getY(), Color.white);

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
                if(selectedForUpgrade instanceof DefensiveBuilding) {
                    r.drawHollowCircle(new Circle(selectedForUpgrade.xPos() + (selectedForUpgrade.width() / 2),selectedForUpgrade.yPos() + (selectedForUpgrade.height() / 2),((DefensiveBuilding) selectedForUpgrade).attackRadius(),Color.white));
                }
                if(selectedForUpgrade.getLevel() < selectedForUpgrade.maxLevel())
                    r.drawText(selectedForUpgrade.getName() + " (Level: " + selectedForUpgrade.getLevel() + ")", smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY(), Color.white);
                else
                    r.drawText(selectedForUpgrade.getName() + " (Level: MAX)", smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY(), Color.white);

                if(selectedForUpgrade.isUpgrading())
                    r.drawText("Upgrading building...", smallFont.getFontImage(), toolbar.getX() + 75, toolbar.getY() + 12, Color.white);
            }
        }

        if(toolbar.isVisible() && trainingMode) { // TRAINING MODE TOOLBAR
            r.setzDepth(Integer.MAX_VALUE);
            r.drawText("Archer", smallFont.getFontImage(), archerSymbol.getX() - 20, toolbar.getY() + 2, Color.white);
            r.drawCircle(archerSymbol);

            r.drawText("Knight", smallFont.getFontImage(), knightSymbol.getX() - 20, toolbar.getY() + 2, Color.white);
            r.drawCircle(knightSymbol);

            r.drawText("Soldier", smallFont.getFontImage(), soldierSymbol.getX() - 20, toolbar.getY() + 2, Color.white);
            r.drawCircle(soldierSymbol);

            r.drawText("Catapult", smallFont.getFontImage(), catapultSymbol.getX() - 28, toolbar.getY() + 2, Color.white);
            r.drawCircle(catapultSymbol);
        }

        if(attackMode) { // ATTACK MODE TOOLBAR

            // (DRAWING DEFENDING VILLAGE BUILDINGS AND ATTACK TOOLBAR AND COMBATANTS)
            r.setzDepth(Integer.MAX_VALUE);

            if(m.placedACombatant()) {
                r.drawText("End Battle", smallFont.getFontImage(), endBattleIcon.getX() - 16, toolbar.getY() + 2, Color.white);
                r.drawImage(endBattleIcon);
            }

            for (Building b : m.getDefendingVillage().getBuildings()) {
                r.drawRect(b.getRect());
                r.drawText(Float.toString(roundToDecimal(b.HP(),1)), smallFont.getFontImage(), b.xPos(), b.yPos(), Color.black);
            }

            for(Combatant c : m.getPlacedCombatants()) {
                if(c.HP() > 0) {
                    r.drawCircle(c.getCircle());
                    r.drawHollowCircle(new Circle(c.xPos(), c.yPos(), c.attackRadius(), Color.white));
                    r.drawText(Float.toString(roundToDecimal(c.HP(),1)), smallFont.getFontImage(), c.xPos(), c.yPos(), Color.white);
                }
            }

            if(selectedForAttackPlacement != null) {
                r.drawCircle(selectedForAttackPlacement.getCircle());
            }

            int archer = 0;
            int catapult = 0;
            int knight = 0;
            int soldier = 0;

            for (Combatant c : m.getAttackingVillage().getCombatees()) {
                switch(c.getName()) {
                    case "Archer":
                        archer++;
                        break;
                    case "Catapult":
                        catapult++;
                        break;
                    case "Knight":
                        knight++;
                        break;
                    case "Soldier":
                        soldier++;
                        break;
                    default:
                        throw new UnknownCombatantException();
                }
            }

            if(archer > 0) {
                r.drawText("Archer", smallFont.getFontImage(), archerSymbol.getX() - 20, toolbar.getY() + 2, Color.white);
                r.drawCircle(archerSymbol);
                r.drawText(Integer.toString(archer), smallFont.getFontImage(), archerSymbol.getX(), archerSymbol.getY(), Color.white);
            }

            if(knight > 0) {
                r.drawText("Knight", smallFont.getFontImage(), knightSymbol.getX() - 20, toolbar.getY() + 2, Color.white);
                r.drawCircle(knightSymbol);
                r.drawText(Integer.toString(knight), smallFont.getFontImage(), knightSymbol.getX(), knightSymbol.getY(), Color.white);
            }

            if(soldier > 0) {
                r.drawText("Soldier", smallFont.getFontImage(), soldierSymbol.getX() - 20, toolbar.getY() + 2, Color.white);
                r.drawCircle(soldierSymbol);
                r.drawText(Integer.toString(soldier), smallFont.getFontImage(), soldierSymbol.getX(), soldierSymbol.getY(), Color.white);
            }

            if(catapult > 0) {
                r.drawText("Catapult", smallFont.getFontImage(), catapultSymbol.getX() - 28, toolbar.getY() + 2, Color.white);
                r.drawCircle(catapultSymbol);
                r.drawText(Integer.toString(catapult), smallFont.getFontImage(), catapultSymbol.getX(), catapultSymbol.getY(), Color.white);
            }

            r.drawText("Attack Score: " + m.getAttackScore(), smallFont.getFontImage(), catapultSymbol.getX() + 35, toolbar.getY() + 2, Color.white);
            r.drawText("Gold Gained: " + m.getAttackGoldGained(), smallFont.getFontImage(), catapultSymbol.getX() + 35, toolbar.getY() + 14, Color.white);
            r.drawText("Iron Gained: " + m.getAttackIronGained(), smallFont.getFontImage(), catapultSymbol.getX() + 35, toolbar.getY() + 26, Color.white);
            r.drawText("Wood Gained: " + m.getAttackWoodGained(), smallFont.getFontImage(), catapultSymbol.getX() + 35, toolbar.getY() + 38, Color.white);
        }

        r.setzDepth(Integer.MAX_VALUE); // DISPLAYING RESOURCE COUNTS
        r.drawText("Gold: " + village.getGold(), regularFont.getFontImage(), 0, 15, Color.WHITE);
        r.drawText("Iron: " + village.getIron(), regularFont.getFontImage(), 0, 30, Color.WHITE);
        r.drawText("Wood: " + village.getWood(), regularFont.getFontImage(), 0, 45, Color.WHITE);
        r.drawText("Food: " + village.getFood(), regularFont.getFontImage(), 0, 60, Color.WHITE);
        r.setzDepth(Integer.MAX_VALUE - 1);
        r.drawRect(toolbar); // DISPLAYING TOOLBAR
    }

    private float roundToDecimal(float number, int decimal) {
        return (float) (Math.round(number * Math.pow(10,decimal)) / Math.pow(10,decimal));
    }

    private int countCombatants(ArrayList<Combatant> combatees, String name) {
        int count = 0;
        for (Combatant combatee : combatees) {
            if (combatee.getName().equals(name))
                count++;
        }
        return count;
    }

    public Sound getDeployArcherSound() {
        return deployArcher;
    }

    public Sound getDeployKnightSound() {
        return deployKnight;
    }

    public Sound getDeploySoldierSound() {
        return deploySoldier;
    }

    public Sound getDeployCatapultSound() {
        return deployCatapult;
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

    public Image getAttackIcon() {
        return attackIcon;
    }

    public Image getBuildIcon() {
        return buildIcon;
    }

    public Image getUpgradeIcon() {
        return upgradeIcon;
    }

    public Image getUpgradeTroopIcon() {
        return upgradeTroopIcon;
    }

    public Image getTrainIcon() {
        return trainIcon;
    }

    public Image getTrainCombatantIcon() {
        return trainCombatantIcon;
    }

    public Rectangle getArcherTowerSymbol() {
        return archerTowerSymbol;
    }

    public Rectangle getCannonSymbol() {
        return cannonSymbol;
    }

    public Rectangle getFarmSymbol() {
        return farmSymbol;
    }

    public Rectangle getGoldMineSymbol() {
        return goldMineSymbol;
    }

    public Image getEndBattleIcon() {
        return endBattleIcon;
    }

    public Rectangle getIronMineSymbol() {
        return ironMineSymbol;
    }

    public Rectangle getLumbermillSymbol() {
        return lumbermillSymbol;
    }

    public Circle getArcherSymbol() {
        return archerSymbol;
    }

    public Circle getCatapultSymbol() {
        return catapultSymbol;
    }

    public Circle getKnightSymbol() {
        return knightSymbol;
    }

    public Circle getSoldierSymbol() {
        return soldierSymbol;
    }

    public Sound getClickSound() {
        return clickSound;
    }

    public Sound getMainMusic() {
        return mainMusic;
    }

    public void setMainMusic(String path) {
        this.mainMusic = new Sound(path);
    }
}
