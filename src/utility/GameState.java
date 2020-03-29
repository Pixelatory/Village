package utility;

import model.army.Combatant;
import model.buildings.Building;
import model.resources.Food;
import model.resources.Gold;
import model.resources.Iron;
import model.resources.Wood;
import model.village.Village;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class GameState {
    public static void save(Village village) {
        JSONObject obj = new JSONObject();
        obj.append("gold",village.getGold());
        obj.append("iron",village.getIron());
        obj.append("wood",village.getWood());
        obj.append("food",village.getFood());
        obj.append("buildings",village.getBuildings());
        obj.append("combatees",village.getCombatees());


        //System.out.println(obj.toString());

        ArrayList<Building> buildings;
        JSONArray array = obj.getJSONArray("buildings");
        buildings = (ArrayList<Building>) array.get(0);
        for(Building b : buildings) {
           // System.out.println(b.getName());
        }

        File file = new File("okay.json");

        try(FileWriter fw = new FileWriter(file)) {
            fw.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Village load() {
        File file = new File("okay.json");
        JSONObject obj = null;

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = new String(Files.readAllBytes(file.toPath()));
            obj = new JSONObject(str);
        } catch (FileNotFoundException e) {
            System.err.println("village save file not found.");
        } catch (IOException e) {
            System.err.println("Cannot read village save file");
            e.printStackTrace();
        }

        Gold gold;
        Iron iron;
        Wood wood;
        Food food;
        ArrayList<Building> buildings;
        ArrayList<Combatant> combatants;
        if(obj != null) {
            System.out.println(obj.get("gold").toString());
            gold = new Gold((Integer) obj.getJSONArray("gold").get(0));
            iron = new Iron((Integer) obj.getJSONArray("iron").get(0));
            wood = new Wood((Integer) obj.getJSONArray("wood").get(0));
            food = new Food((Integer) obj.getJSONArray("food").get(0));

            JSONArray buildingsArray = obj.getJSONArray("buildings");
            for(int i=0;i<buildingsArray.length();i++) {
                System.out.println(buildingsArray.getJSONObject(0).get("name").toString());
            }
            System.out.println(gold.getQuantity());
            System.out.println(obj.toString());
        }

        return null;
    }

}
