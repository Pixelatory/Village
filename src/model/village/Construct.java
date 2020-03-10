package model.village;

import model.buildings.Building;
import exceptions.NotEnoughAreaException;
import exceptions.NotEnoughResourcesException;

public interface Construct {
	public void newConstruction(Building building) throws NotEnoughResourcesException, NotEnoughAreaException;
	public boolean canConstruct(Building building);
}
