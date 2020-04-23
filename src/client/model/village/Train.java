package client.model.village;

import client.exceptions.NoStructureException;
import client.exceptions.NotEnoughResourcesException;

public interface Train<E extends Trainable> {
	public void newIndividual(E e) throws NotEnoughResourcesException, NoStructureException;
	public boolean canTrain(E e);
}
