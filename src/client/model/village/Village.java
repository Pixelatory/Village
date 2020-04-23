package client.model.village;

import client.engine.audio.Sound;
import client.model.army.Combatant;
import client.model.buildings.Building;
import client.model.buildings.ProductionBuilding;
import client.model.buildings.VillageHall;
import client.model.habitants.ProductionHabitant;
import client.model.resources.Food;
import client.model.resources.Gold;
import client.model.resources.Iron;
import client.model.resources.Wood;
import client.model.statics.ProductionFrequency;
import client.utility.timers.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Timer;

public class Village extends Guard implements Construct, Serializable {
	private final ArrayList<Building> buildings;
	private final Gold gold;
	private final Iron iron;
	private final Wood wood;
	private final Food food;
	private final ArrayList<Combatant> combatees;
	private final Timer timer = new Timer();

	public Village() {
		buildings = new ArrayList<>();
		buildings.add(new VillageHall(100,100));
		gold = new Gold();
		wood = new Wood();
		iron = new Iron();
		food = new Food();
		combatees = new ArrayList<>();
	}

	public Village(Village village) {
		buildings = (ArrayList<Building>) village.getBuildings().clone();
		gold = new Gold(village.getGold());
		wood = new Wood(village.getWood());
		iron = new Iron(village.getIron());
		food = new Food(village.getFood());
		combatees = (ArrayList<Combatant>) village.getCombatees().clone();
	}

	public Village(Gold gold, Wood wood, Iron iron, Food food, ArrayList<Building> buildings, ArrayList<Combatant> combatees) {
		this.gold = gold;
		this.wood = wood;
		this.iron = iron;
		this.food = food;
		this.buildings = buildings;
		this.combatees = combatees;
	}

	public Village(ArrayList<Building> buildings) {
		this.buildings = (ArrayList<Building>) buildings.clone();
		buildings.add(new VillageHall(100,100));
		gold = new Gold();
		wood = new Wood();
		iron = new Iron();
		food = new Food();
		combatees = new ArrayList<>();
	}
		
	public boolean canConstruct(Building building) {
			return (building.ironCost(building.level()) <= getIron() 
					&& building.goldCost(building.level()) <= getGold()
					&& building.woodCost(building.level()) <= getWood());
	}
	
	public void newConstruction(Building building) {
		if(canConstruct(building)) {
			new Sound("/sounds/building_construct.wav").play();
			buildings.add(building);
			iron.decrease(building.ironCost(building.level()));
			gold.decrease(building.goldCost(building.level()));
			wood.decrease(building.woodCost(building.level()));
			building.setUpgrading(true);
			building.setUpgradeTime(building.upgradeTime(building.level()));
			
			if(building instanceof ProductionBuilding) {
				@SuppressWarnings("rawtypes")
				ProductionBuilding tmp = (ProductionBuilding) building;
				timer.scheduleAtFixedRate(new ProductionBuildingCollectionTimer(tmp, gold, iron, wood, food, this)
						, ProductionFrequency.time * 1000, ProductionFrequency.time * 1000);
			}

			timer.scheduleAtFixedRate(new BuildingConstructionTimer(this, building), 0, 1000);
		}
	} // Creating a new construction project (new building)
	
	public boolean canTrain(Combatant combatant) {
		return (combatant.ironCost(combatant.level()) <= getIron()
				&& combatant.goldCost(combatant.level()) <= getGold()
				&& combatant.woodCost(combatant.level()) <= getWood());
	}

	public void newIndividual(Combatant combatant) {
		if (canTrain(combatant)) {
			new Sound("/sounds/building_construct.wav").play();
			iron.decrease(combatant.ironCost(combatant.level()));
			gold.decrease(combatant.goldCost(combatant.level()));
			wood.decrease(combatant.woodCost(combatant.level()));

			int highestTime = 0;
			for(Combatant c : combatees) {
				if(c.isUpgrading() && c.getUpgradeTime() > highestTime)
					highestTime = c.getUpgradeTime();
			}
			combatant.setUpgrading(true);
			combatant.setUpgradeTime(highestTime + combatant.upgradeTime(combatant.level()));
			combatees.add(combatant);
			timer.scheduleAtFixedRate(new CombatantTrainingTimer(this,combatant), 0, 1000);
		}
	} // Creating a new individual in the village (new combatant)
	
	public boolean canTrainHabitant(ProductionBuilding<ProductionHabitant> building) {
		ProductionHabitant h;
		
		if(building.canAddWorker()) {
			building.addWorker();
			h = building.removeWorker(building.numOfWorkers() - 1);
		} else
			return false;

		return (building.canAddWorker()
		&& !building.isTraining()
		&& !building.isUpgrading()
		&& h.goldCost(h.level()) <= getGold());
	}
	
	public void trainHabitant(ProductionBuilding<ProductionHabitant> building) {
		if(canTrainHabitant(building)) {
			new Sound("/sounds/building_construct.wav").play();
			building.addWorker();
			ProductionHabitant h = building.removeWorker(building.numOfWorkers() - 1);
			gold.decrease(h.goldCost(h.level()));
			building.setTraining(true);
			h.setUpgrading(true);
			h.setUpgradeTime(h.level());
			
			timer.scheduleAtFixedRate(new WorkerTrainingTimer(this, building, h), 0, 1000);
		}
	} // Creating a new production habitant for a production building
	
	@SuppressWarnings("rawtypes")
	public boolean canUpgrade(Building building) {
		
		if(building instanceof ProductionBuilding
		&& ((ProductionBuilding) building).isTraining())
			return false;
		
		return (building.canUpgrade()
				&& building.ironCost(building.level() + 1) <= getIron() 
				&& building.goldCost(building.level() + 1) <= getGold()
				&& building.woodCost(building.level() + 1) <= getWood()
				&& !building.isUpgrading());
	}
	
	public void performUpgrade(Building building) {
		if(canUpgrade(building)) {
			new Sound("/sounds/building_construct.wav").play();
			iron.decrease(building.ironCost(building.level()));
			gold.decrease(building.goldCost(building.level()));
			wood.decrease(building.woodCost(building.level()));
			building.setUpgrading(true);
			building.setUpgradeTime(building.upgradeTime(building.level()));

			timer.scheduleAtFixedRate(new BuildingUpgradeTimer(this, building), 0, 1000);
		}
	} // upgrading a building
	
	@SuppressWarnings("rawtypes")
	public boolean canUpgradeHabitant(ProductionBuilding building) {
		ProductionHabitant h = building.getLowestLevelWorker();
		
		if(h == null) // this means there are no client.model.habitants in this production building, so no you cannot upgrade something that doesn't exist
			return false;
		
		return (h.canUpgrade()
		&& h.goldCost(h.level() + 1) <= getGold()
		&& !building.isTraining()
		&& !building.isUpgrading()
		&& !h.isUpgrading());
	}
	
	@SuppressWarnings("rawtypes")
	public void performUpgradeHabitant(ProductionBuilding building) {
		if(canUpgradeHabitant(building)) {
			ProductionHabitant h = building.getLowestLevelWorker();
			new Sound("/sounds/building_construct.wav").play();
			gold.decrease(h.goldCost(h.level() + 1));
			building.setTraining(true);
			h.setUpgradeTime(h.upgradeTime(h.level()));
			h.setUpgrading(true);

			timer.scheduleAtFixedRate(new WorkerUpgradeTimer(this, building, h), 0, 1000);
		}
	}

	// Getter Functions
	public int getGold() {
		return gold.getQuantity();
	}
	
	public int getIron() {
		return iron.getQuantity();
	}
	
	public int getWood() {
		return wood.getQuantity();
	}

	public int getFood() { return food.getQuantity(); }

	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	
	public ArrayList<Combatant> getCombatees() {
		return combatees;
	}
	
	public Combatant getCombatant(int idx) {
		return combatees.get(idx);
	}
	
	public Combatant getCombatant(Combatant combatant) {
		for(Combatant i : combatees) {
			if(i.equals(combatant))
				return i;
		}
		return null;
	}

	public void increaseGold(int amount) {
		gold.increase(amount);
	}

	public void decreaseGold(int amount) {
		gold.decrease(amount);
	}

	public void increaseWood(int amount) {
		wood.increase(amount);
	}

	public void decreaseWood(int amount) {
		wood.decrease(amount);
	}

	public void increaseIron(int amount) {
		iron.increase(amount);
	}

	public void decreaseIron(int amount) {
		iron.decrease(amount);
	}

	@Override
	public Duration guardDuration() {
		// TODO Add guard duration
		return null;
	}
	
	@Override
	public boolean isGuarded() {
		// TODO Add is guarded
		return false;
	}
}
