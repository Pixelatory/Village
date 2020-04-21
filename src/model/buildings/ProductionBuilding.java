package model.buildings;

import engine.renderPrimitives.Rectangle;
import model.habitants.ProductionHabitant;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * General abstract class that encompasses everything a building should do/have if it contains workers, and generates model.resources.
 * 
 * @param <E> extends ProductionHabitant
 * @author 6177000
 * @see Building
 * @see model.habitants.ProductionHabitant
 * @see MiningProduction
 * @see FoodProduction
 */
public abstract class ProductionBuilding<E extends ProductionHabitant> extends Building implements Serializable {

	protected ArrayList<E> workers = new ArrayList<>();
	protected boolean isTraining = false;
	
	/**
	 * Calls the Building constructor.
	 * 
	 * @param xPos int value
	 * @param yPos int value
	 * @see Building
	 */
	public ProductionBuilding(int xPos, int yPos) {
		super(xPos, yPos);
	}

	/**
	 * A getter function which returns the maximum amount of workers this production building can hold at once.
	 * 
	 * @return int value
	 */
	public abstract int workerCapacity(); // max number of workers allowed on the building at once
	
	/**
	 * A function which will add a worker automatically to the end of the worker list.
	 */
	public abstract void addWorker();
	
	@Override
	public Rectangle getRect() {
		if(isTraining() || isUpgrading())
			return getUpgradingRect();
		
		return getDefaultRect();
	}
	
	/**
	 * A function which searches through the worker list and will return the lowest level worker.
	 * 
	 * @return A worker of the type ProductionHabitant
	 * @see ProductionHabitant
	 */
	public E getLowestLevelWorker() { 
		if(numOfWorkers() > 0) {
			E lowest = workers.get(0);
			
			for(E m : workers) {
				if(m.level() <= lowest.level())
					lowest = m;
			}
		
			return lowest;
		}
		return null;
	}
	
	/**
	 * A function which returns if you can add a worker to the production building.
	 * 
	 * @return boolean value
	 */
	public boolean canAddWorker() {
		return numOfWorkers() < workerCapacity();
	}
	
	/**
	 * A function which removes the worker from the list and returns it.
	 * 
	 * @param num index of worker in list
	 * @return A worker of the type ProductionHabitant
	 */
	public E removeWorker(int num) {
		return workers.remove(num);
	}
	
	/**
	 * A getter function which returns the number of workers already in the production building.
	 * 
	 * @return int value
	 */
	public int numOfWorkers() {
		return workers.size();
	}
	
	/**
	 * A function which gets the worker from the list.
	 * 
	 * @param num index of worker in list
	 * @return A worker of the type ProductionHabitant
	 */
	public E getWorker(int num) {
		return workers.get(num);
	}
	
	/**
	 * A function which searches for a worker from the list and returns it.
	 * 
	 * @param worker of the type ProductionHabitant
	 * @return A worker of the type ProductionHabitant, null if nothing
	 */
	public E getWorker(E worker) {
		for(E e : workers) {
			if (e == worker) {
				return e;
			}
		}
		
		return null;
	}
	
	/**
	 * A function which returns the total production amount of all workers currently working in the production building.
	 * 
	 * @return int value
	 */
	public int productionAmount() {
		int sum = 0;
		for(E e : workers) {
			sum += e.productionAmount();
		}
		return sum;
	}
	
	/**
	 * A function which returns if this production building is currently training a new worker.
	 * 
	 * @return boolean value
	 */
	public boolean isTraining() {
		return isTraining;
	}

	/**
	 * A function which sets if this production building is currently training a new worker.
	 * 
	 * @param isTraining boolean value
	 */
	public void setTraining(boolean isTraining) {
		this.isTraining = isTraining;
	}

	public ArrayList<E> getWorkers() {
		return workers;
	}

	public void setWorkers(ArrayList<E> workers) {
		this.workers = workers;
	}
}
