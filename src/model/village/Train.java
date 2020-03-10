package model.village;

import exceptions.NoStructureException;
import exceptions.NotEnoughResourcesException;

public interface Train<E extends Trainable> {
	public void newIndividual(E e) throws NotEnoughResourcesException, NoStructureException;
	public boolean canTrain(E e);
}
