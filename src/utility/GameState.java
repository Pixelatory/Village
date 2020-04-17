package utility;

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

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Timer;

public class GameState {
    public static void save(Village village) {
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

    public static Village load() {
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
            gold = new Gold((Integer) obj.getJSONArray("gold").get(0));
            iron = new Iron((Integer) obj.getJSONArray("iron").get(0));
            wood = new Wood((Integer) obj.getJSONArray("wood").get(0));
            food = new Food((Integer) obj.getJSONArray("food").get(0));

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
                        }

                        farm.setWorkers(workers);
                        buildings.add(farm);
                        break;
                    default:
                        throw new UnknownBuildingException();
                }
            }

            JSONArray combatantsArray = obj.getJSONArray("combatees").getJSONArray(0);
            for(int i=0;i<combatantsArray.length();i++) {
                int xPos = new Integer(combatantsArray.getJSONObject(i).getJSONObject("position").get("x").toString());
                int yPos = new Integer(combatantsArray.getJSONObject(i).getJSONObject("position").get("y").toString());

                switch(combatantsArray.getJSONObject(i).get("name").toString()) {
                    case "Archer":
                        combatants.add(new Archer(xPos,yPos));
                        break;
                    case "Catapult":
                        combatants.add(new Catapult(xPos,yPos));
                        break;
                    case "Soldier":
                        combatants.add(new Soldier(xPos,yPos));
                        break;
                    case "Knight":
                        combatants.add(new Knight(xPos,yPos));
                        break;
                    default:
                        throw new UnknownCombatantException();
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
