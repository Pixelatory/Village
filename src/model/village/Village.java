package model.village;

import engine.audio.Sound;
import model.army.Combatant;
import model.buildings.*;
import model.habitants.ProductionHabitant;
import model.resources.Food;
import model.resources.Gold;
import model.resources.Iron;
import model.resources.Wood;
import model.statics.ProductionFrequency;
import utility.TimerTaskExt;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Village extends Guard implements Construct, Serializable {
	private ArrayList<Building> buildings;
	private Gold gold;
	private Iron iron;
	private Wood wood;
	private Food food;
	private ArrayList<Combatant> combatees;
	private int combatantTimer = 0;
	private Timer timer = new Timer();
	
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
			
			if(building instanceof ProductionBuilding) {
				@SuppressWarnings("rawtypes")
				ProductionBuilding tmp = (ProductionBuilding) building;
				timer.scheduleAtFixedRate(new TimerTaskExt(tmp, gold, iron, wood, food) {
					@Override
					public void run() {
						if(tmp instanceof GoldMine)
							gold.increase(tmp.productionAmount());
						else if (tmp instanceof IronMine)
							iron.increase(tmp.productionAmount());
						else if (tmp instanceof LumberMill)
							wood.increase(tmp.productionAmount());
						else if (tmp instanceof Farm)
							food.increase(tmp.productionAmount());
					}
				}, ProductionFrequency.time * 1000, ProductionFrequency.time * 1000);
			}

			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					new Sound("/sounds/building_finished.wav").play();
					building.setUpgrading(false);
				}
			}, building.upgradeTime(building.level())*1000);
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
			combatantTimer += combatant.upgradeTime(combatant.level()) * 1000;
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					new Sound("/sounds/building_finished.wav").play();
					combatees.add(combatant);
				}
			}, combatantTimer);
		}
	} // Creating a new individual in the village (new combatant)
	
	public boolean canTrainIndividual(ProductionBuilding<ProductionHabitant> building) {
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
	
	public void trainIndividual(ProductionBuilding<ProductionHabitant> building) {
		if(canTrainIndividual(building)) {
			new Sound("/sounds/building_construct.wav").play();
			building.addWorker();
			ProductionHabitant h = building.removeWorker(building.numOfWorkers() - 1);
			gold.decrease(h.goldCost(h.level()));
			building.setTraining(true);
			
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					new Sound("/sounds/building_finished.wav").play();
					building.addWorker();
					building.setTraining(false);
				}
			}, h.upgradeTime(h.level()) * 1000);
		}
	}
	
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
			
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					new Sound("/sounds/building_finished.wav").play();
					building.performUpgrade();
					building.setUpgrading(false);
				}
			}, building.upgradeTime(building.level())*1000);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public boolean canUpgradeHabitant(ProductionBuilding building) {
		ProductionHabitant h = building.getLowestLevelWorker();
		
		if(h == null) // this means there are no model.habitants in this production building, so no you cannot upgrade something that doesn't exist
			return false;
		
		return (h.canUpgrade()
		&& h.goldCost(h.level() + 1) <= getGold()
		&& !building.isTraining()
		&& !building.isUpgrading());
	}
	
	@SuppressWarnings("rawtypes")
	public void performUpgradeHabitant(ProductionBuilding building) {
		if(canUpgradeHabitant(building)) {
			ProductionHabitant h = building.getLowestLevelWorker();
			new Sound("/sounds/building_construct.wav").play();
			gold.decrease(h.goldCost(h.level() + 1));
			building.setTraining(true);
			
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					new Sound("/sounds/building_finished.wav").play();
					h.performUpgrade();
					building.setTraining(false);
				}
			}, h.upgradeTime(h.level() + 1) * 1000);
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
	
	public int popSize() {
		return 0;
	}
	
	public int numInArmy() {
		return combatees.size();
	}
	
	public int numOfBuildings() {
		return buildings.size();
	}

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
