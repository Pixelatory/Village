package utility.ai.evolutionary;

import engine.GameContainer;
import exceptions.EvolutionPopulateException;
import model.attack.Generative;
import utility.Factory;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An evolutionary algorithm (used mainly for generation).
 *
 * @author 6177000
 */
public class BuildingEvolution<E extends Generative> {
    private int generation;
    private Population<E> pop;
    private E[] entities;
    private ArrayList<E> compareTo;
    private int size;

    public BuildingEvolution(int size, E[] entities, ArrayList<E> compareTo) throws EvolutionPopulateException {
        this.size = size;
        this.entities = entities;
        pop = new Population<>(size, entities);
        this.compareTo = compareTo;
    }

    /**
     * Follows the general format of a genetic algorithm.
     * increase generation
     * select a new population from older one for reproduction
     * recombine genes of the pairs of parents
     * mutate the new mated population
     * evaluate the new individuals
     * @return Arraylist of generated Buildings
     */
    public ArrayList<E> process() {
        int fitness = evaluate(pop.getPopulation());
        while(fitness < evaluate(compareTo)) {
            generation++;
            Population<E> nextGeneration = new Population<>();

            while(nextGeneration.size() < size) { // Make sure next generation is same size as last
                // START WITH TOURNAMENT SELECTION
                ArrayList<E> possibleParent1 = new ArrayList<>(); // randomly select 3 potential chromosomes to be parents of next population
                ArrayList<E> possibleParent2 = new ArrayList<>();

                /*
                    Tried to diversify by ensuring that the same parents cannot be in both
                    possibleparent arrays at the same time. This will help crossover actually working
                    as it's intended.
                 */
                while(possibleParent1.size() < 3 && possibleParent2.size() < 3) {
                    int rand1 = ThreadLocalRandom.current().nextInt(pop.size());
                    int rand2 = ThreadLocalRandom.current().nextInt(pop.size());
                    E parent1 = pop.getChromosome(rand1);
                    E parent2 = pop.getChromosome(rand2);
                    if(rand1 != rand2 && !possibleParent1.contains(parent2) && !possibleParent2.contains(parent1)) {
                        possibleParent1.add(parent1);
                        possibleParent2.add(parent2);
                    }
                }

                // Here, select the best possible parents from the two parent pools
                E bestParent1 = null;
                int bestParent1Fitness = Integer.MIN_VALUE;
                E bestParent2 = null;
                int bestParent2Fitness = Integer.MIN_VALUE;
                for(int i=0;i<possibleParent1.size();i++) { // out of the 3 random chromosomes, pick the best parents for reproducing next generation
                    int tmpEval = evaluate(possibleParent1.get(i));
                    if(tmpEval > bestParent1Fitness) {
                        bestParent1Fitness = tmpEval;
                        bestParent1 = possibleParent1.get(i);
                    }

                    tmpEval = evaluate(possibleParent2.get(i));
                    if(tmpEval > bestParent2Fitness) {
                        bestParent2Fitness = tmpEval;
                        bestParent2 = possibleParent2.get(i);
                    }
                }

                /*
                    CROSSOVER APPLICATION
                    The method I chose to get this done is to randomly choose between the building types,
                    as one parent can a different building than the other. Afterwards, randomly choose between
                    the x and y positions of the 2 parents, and the level of them as well.
                    This will determine the kid that these two buildings will have.
                    Kinda scary but I mean, we all know two buildings can't reproduce (hopefully)

                    Crossover gets applied every time to each chromosome
                 */
                E child;
                int choice = ThreadLocalRandom.current().nextInt(2); // setting the building type
                if(choice == 1)
                    child = bestParent1;
                else
                    child = bestParent2;

                choice = ThreadLocalRandom.current().nextInt(2); // setting x pos
                if(choice == 1)
                    child.setXPos(bestParent1.xPos());
                else
                    child.setYPos(bestParent2.xPos());

                choice = ThreadLocalRandom.current().nextInt(2); // setting y pos
                if(choice == 1)
                    child.setYPos(bestParent1.yPos());
                else
                    child.setYPos(bestParent2.yPos());

                choice = ThreadLocalRandom.current().nextInt(2); // setting level
                if(choice == 1)
                    child.setLevel(bestParent1.level());
                else
                    child.setLevel(bestParent2.level());

                /*
                    MUTATION HERE
                    The method I thought about doing this is to
                        1. change the building type to something random
                        2. change the x position to something random
                        3. change the y position to something random
                        4. increment the level by one
                        5. decrement the level by one

                    Mutation has a 5% chance of occurring per chromosome
                 */
                if(ThreadLocalRandom.current().nextFloat() < 0.05) {
                    choice = ThreadLocalRandom.current().nextInt(5);
                    if(choice == 0) {
                        Factory<E> f = new Factory<>();
                        E tmp = f.create(entities);
                        tmp.setLevel(child.level());
                        tmp.setXPos(child.xPos());
                        tmp.setYPos(child.yPos());
                        child = tmp;
                    } else if(choice == 1) {
                        child.setXPos(ThreadLocalRandom.current().nextInt(100,GameContainer.getWidth() - 100));
                    } else if(choice == 2) {
                        child.setYPos(ThreadLocalRandom.current().nextInt(100,GameContainer.getHeight() - 100));
                    } else if(choice == 3 || child.level() == 1) {
                        child.setLevel(child.level() + 1);
                    } else if(choice == 4 || child.level() == child.maxLevel()) {
                        child.setLevel(child.level() - 1);
                    }
                }
                nextGeneration.addChromosome(child);
            }
            pop = nextGeneration;
            fitness = evaluate(pop.getPopulation());
        }
        return pop.getPopulation();
    }

    private int evaluate(E e) {
        return e.level();
    }

    private int evaluate(ArrayList<E> pop) {
        int sum = 0;
        for(int i=0;i<pop.size();i++) {
            sum += evaluate(pop.get(i));
        }
        return sum;
    }
}
