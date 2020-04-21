package utility.ai.evolutionary;

import exceptions.EvolutionPopulateException;
import model.attack.Generative;
import utility.Factory;

import java.util.ArrayList;

public class Population<E extends Generative> {
    ArrayList<E> pop;
    Factory<E> factory;
    E[] entities;

    public Population(int size, E[] entities) throws EvolutionPopulateException {
        this.entities = entities;
        pop = new ArrayList<>();
        factory = new Factory<>();

        for(int i=0;i<size;i++)
            addChromosome();
    }

    public Population() {
        pop = new ArrayList<>();
    }

    public E getChromosome(int index) {
        return pop.get(index);
    }

    public void setChromosome(int index, E obj) {
        pop.set(index,obj);
    }

    public void setChromosome(int index) {
        pop.set(index,factory.create(entities));
    }

    public void addChromosome() {
        pop.add(factory.create(entities));
    }

    public void addChromosome(E e) {
        pop.add(e);
    }

    public int size() {
        return pop.size();
    }

    public ArrayList<E> getPopulation() {
        return pop;
    }
}
