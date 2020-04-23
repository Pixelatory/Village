package client.model.village;

import client.model.buildings.Building;
import client.exceptions.NotEnoughAreaException;
import client.exceptions.NotEnoughResourcesException;

public interface Construct {
	public void newConstruction(Building building) throws NotEnoughResourcesException, NotEnoughAreaException;
	public boolean canConstruct(Building building);
}
