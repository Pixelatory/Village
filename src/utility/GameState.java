package utility;

import engine.audio.Sound;
import exceptions.UnknownBuildingException;
import exceptions.UnknownCombatantException;
import model.army.*;
import model.buildings.*;
import model.habitants.GoldMiner;
import model.habitants.IronMiner;
import model.habitants.Lumberman;
import model.habitants.Worker;
import model.resources.Food;
import model.resources.Gold;
import model.resources.Iron;
import model.resources.Wood;
import model.statics.ProductionFrequency;
import model.village.Village;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utility.timers.ProductionBuildingCollectionTimer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {
    public synchronized static void save(Village village) {
        JSONObject obj = new JSONObject();
        obj.append("gold",village.getGold());
        obj.append("iron",village.getIron());
        obj.append("wood",village.getWood());
        obj.append("food",village.getFood());
        obj.append("buildings",village.getBuildings());
        obj.append("combatees",village.getCombatees());

        File file = new File("save.json");

        try(FileWriter fw = new FileWriter(file)) {
            fw.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Village load() {
        File file = new File("save.json");
        JSONObject obj = null;

        try {
            String str = new String(Files.readAllBytes(file.toPath()));
            obj = new JSONObject(str);
        } catch (FileNotFoundException e) {
            System.err.println("village save file not found.");
        } catch (IOException e) {
            System.err.println("Cannot read village save file");
            e.printStackTrace();
        } catch (JSONException e) {
            System.err.println("JSON file is formatted incorrectly");
        }

        Gold gold;
        Iron iron;
        Wood wood;
        Food food;
        ArrayList<Building> buildings = new ArrayList<>();
        ArrayList<Combatant> combatants = new ArrayList<>();
        if(obj != null) {
            Timer timer = new Timer();
            // First get the resources
            gold = new Gold((Integer) obj.getJSONArray("gold").get(0));
            iron = new Iron((Integer) obj.getJSONArray("iron").get(0));
            wood = new Wood((Integer) obj.getJSONArray("wood").get(0));
            food = new Food((Integer) obj.getJSONArray("food").get(0));

            // Now we get the buildings, and set appropriate timers for production buildings, and for buildings that are being upgrade/constructed
            JSONArray buildingsArray = obj.getJSONArray("buildings").getJSONArray(0);
            for(int i=0;i<buildingsArray.length();i++) {
                //System.out.println(buildingsArray.getJSONObject(i).get("name").toString());
                int xPos = new Integer(buildingsArray.getJSONObject(i).getJSONObject("position").get("x").toString());
                int yPos = new Integer(buildingsArray.getJSONObject(i).getJSONObject("position").get("y").toString());

                int level = new Integer(buildingsArray.getJSONObject(i).get("level").toString());

                switch(buildingsArray.getJSONObject(i).get("name").toString()) {
                    case "Village Hall":
                        VillageHall hall = new VillageHall(xPos,yPos);
                        hall.setLevel(level);
                        buildings.add(hall);
                        break;
                    case "Archer Tower":
                        ArcherTower archerTower = new ArcherTower(xPos,yPos);
                        archerTower.setLevel(level);
                        buildings.add(archerTower);
                        break;
                    case "Cannon":
                        Cannon cannon = new Cannon(xPos,yPos);
                        cannon.setLevel(level);
                        buildings.add(cannon);
                        break;
                    case "Iron Mine":
                        IronMine ironMine = new IronMine(xPos,yPos);
                        ironMine.setLevel(level);

                        ArrayList<IronMiner> ironWorkers = new ArrayList<>();

                        JSONArray ironWorkersArray = buildingsArray.getJSONObject(i).getJSONArray("workers");
                        for(int j=0;j<ironWorkersArray.length();j++) {
                            IronMiner im = new IronMiner();
                            im.setLevel(new Integer(ironWorkersArray.getJSONObject(j).get("level").toString()));
                            ironWorkers.add(im);

                            if(ironWorkersArray.getJSONObject(j).get("upgrading").toString().equals("true")) {
                                im.setUpgrading(true);
                                int upgradeTime = Integer.parseInt(ironWorkersArray.getJSONObject(i).get("upgradeTime").toString());
                                im.setUpgradeTime(upgradeTime);

                                int finalI = i;
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        int upgradeTime = ironWorkers.get(finalI).getUpgradeTime();
                                        if(upgradeTime > 0) {
                                            ironWorkers.get(finalI).setUpgradeTime(upgradeTime - 1);
                                        } else {
                                            new Sound("/sounds/building_finished.wav").play();
                                            ironWorkers.get(finalI).setUpgrading(false);
                                            cancel(); // building was successfully upgraded so cancel.
                                        }
                                    }
                                }, 0, 1000);
                            }
                        }

                        ironMine.setWorkers(ironWorkers);
                        buildings.add(ironMine);
                        break;
                    case "Gold Mine":
                        GoldMine goldMine = new GoldMine(xPos,yPos);
                        goldMine.setLevel(level);

                        ArrayList<GoldMiner> goldWorkers = new ArrayList<>();

                        JSONArray goldWorkersArray = buildingsArray.getJSONObject(i).getJSONArray("workers");
                        for(int j=0;j<goldWorkersArray.length();j++) {
                            GoldMiner gm = new GoldMiner();
                            gm.setLevel(new Integer(goldWorkersArray.getJSONObject(j).get("level").toString()));
                            goldWorkers.add(gm);

                            if(goldWorkersArray.getJSONObject(j).get("upgrading").toString().equals("true")) {
                                gm.setUpgrading(true);
                                int upgradeTime = Integer.parseInt(goldWorkersArray.getJSONObject(i).get("upgradeTime").toString());
                                gm.setUpgradeTime(upgradeTime);

                                int finalI = i;
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        int upgradeTime = goldWorkers.get(finalI).getUpgradeTime();
                                        if(upgradeTime > 0) {
                                            goldWorkers.get(finalI).setUpgradeTime(upgradeTime - 1);
                                        } else {
                                            new Sound("/sounds/building_finished.wav").play();
                                            goldWorkers.get(finalI).setUpgrading(false);
                                            cancel(); // building was successfully upgraded so cancel.
                                        }
                                    }
                                }, 0, 1000);
                            }
                        }

                        goldMine.setWorkers(goldWorkers);
                        buildings.add(goldMine);
                        break;
                    case "Lumber Mill":
                        LumberMill lumberMill = new LumberMill(xPos,yPos);
                        lumberMill.setLevel(level);

                        ArrayList<Lumberman> lumbermen = new ArrayList<>();

                        JSONArray lumbermenArray = buildingsArray.getJSONObject(i).getJSONArray("workers");
                        for(int j=0;j<lumbermenArray.length();j++) {
                            Lumberman lm = new Lumberman();
                            lm.setLevel(new Integer(lumbermenArray.getJSONObject(j).get("level").toString()));
                            lumbermen.add(lm);

                            if(lumbermenArray.getJSONObject(j).get("upgrading").toString().equals("true")) {
                                lm.setUpgrading(true);
                                int upgradeTime = Integer.parseInt(lumbermenArray.getJSONObject(i).get("upgradeTime").toString());
                                lm.setUpgradeTime(upgradeTime);

                                int finalI = i;
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        int upgradeTime = lumbermen.get(finalI).getUpgradeTime();
                                        if(upgradeTime > 0) {
                                            lumbermen.get(finalI).setUpgradeTime(upgradeTime - 1);
                                        } else {
                                            new Sound("/sounds/building_finished.wav").play();
                                            lumbermen.get(finalI).setUpgrading(false);
                                            cancel(); // building was successfully upgraded so cancel.
                                        }
                                    }
                                }, 0, 1000);
                            }
                        }

                        lumberMill.setWorkers(lumbermen);
                        buildings.add(lumberMill);
                        break;
                    case "Farm":
                        Farm farm = new Farm(xPos,yPos);
                        farm.setLevel(level);

                        ArrayList<Worker> workers = new ArrayList<>();

                        JSONArray workerArray = buildingsArray.getJSONObject(i).getJSONArray("workers");
                        for(int j=0;j<workerArray.length();j++) {
                            Worker w = new Worker();
                            w.setLevel(new Integer(workerArray.getJSONObject(j).get("level").toString()));
                            workers.add(w);

                            if(workerArray.getJSONObject(j).get("upgrading").toString().equals("true")) {
                                w.setUpgrading(true);
                                int upgradeTime = Integer.parseInt(workerArray.getJSONObject(i).get("upgradeTime").toString());
                                w.setUpgradeTime(upgradeTime);

                                int finalI = i;
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        int upgradeTime = workers.get(finalI).getUpgradeTime();
                                        if(upgradeTime > 0) {
                                            workers.get(finalI).setUpgradeTime(upgradeTime - 1);
                                        } else {
                                            new Sound("/sounds/building_finished.wav").play();
                                            workers.get(finalI).setUpgrading(false);
                                            cancel(); // building was successfully upgraded so cancel.
                                        }
                                    }
                                }, 0, 1000);
                            }
                        }

                        farm.setWorkers(workers);
                        buildings.add(farm);
                        break;
                    default:
                        throw new UnknownBuildingException();
                }

                if(buildingsArray.getJSONObject(i).get("upgrading").toString().equals("true")) {
                    buildings.get(i).setUpgrading(true);
                    int upgradeTime = Integer.parseInt(buildingsArray.getJSONObject(i).get("upgradeTime").toString());
                    buildings.get(i).setUpgradeTime(upgradeTime);
                    int finalI = i;
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            int upgradeTime = buildings.get(finalI).getUpgradeTime();
                            if(upgradeTime > 0) {
                                buildings.get(finalI).setUpgradeTime(upgradeTime - 1);
                            } else {
                                new Sound("/sounds/building_finished.wav").play();
                                buildings.get(finalI).setUpgrading(false);
                                cancel(); // building was successfully upgraded so cancel.
                            }
                        }
                    }, 0, 1000);
                }
            }

            JSONArray combatantsArray = obj.getJSONArray("combatees").getJSONArray(0);
            for(int i=0;i<combatantsArray.length();i++) {
                int xPos = new Integer(combatantsArray.getJSONObject(i).getJSONObject("position").get("x").toString());
                int yPos = new Integer(combatantsArray.getJSONObject(i).getJSONObject("position").get("y").toString());

                Combatant combatant;
                switch(combatantsArray.getJSONObject(i).get("name").toString()) {
                    case "Archer":
                        combatant = new Archer(xPos,yPos);
                        break;
                    case "Catapult":
                        combatant = new Catapult(xPos,yPos);
                        break;
                    case "Soldier":
                        combatant = new Soldier(xPos,yPos);
                        break;
                    case "Knight":
                        combatant = new Knight(xPos,yPos);
                        break;
                    default:
                        throw new UnknownCombatantException();
                }
                combatants.add(combatant);

                if(combatantsArray.getJSONObject(i).get("upgrading").toString().equals("true")) {
                    combatant.setUpgrading(true);
                    int upgradeTime = Integer.parseInt(combatantsArray.getJSONObject(i).get("upgradeTime").toString());
                    combatant.setUpgradeTime(upgradeTime);

                    int finalI = i;
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            int upgradeTime = combatants.get(finalI).getUpgradeTime();
                            if(upgradeTime > 0) {
                                combatants.get(finalI).setUpgradeTime(upgradeTime - 1);
                            } else {
                                new Sound("/sounds/building_finished.wav").play();
                                combatants.get(finalI).setUpgrading(false);
                                cancel(); // building was successfully upgraded so cancel.
                            }
                        }
                    }, 0, 1000);
                }
            }

            Village village = new Village(gold,wood,iron,food,buildings,combatants);

            for(Building b : buildings) {
                if(b instanceof ProductionBuilding) {
                    timer.scheduleAtFixedRate(new ProductionBuildingCollectionTimer((ProductionBuilding) b, gold, iron, wood, food, village)
                            , ProductionFrequency.time * 1000, ProductionFrequency.time * 1000);
                }
            }

            return village;
        }

        return null;
    }

}
