package utility.ai.genetic;

public class GA<E> {
    private int generation;
    private Population pop;

    public GA(int size) {
        generation = 0;
        pop = new Population(size);
    }
}
