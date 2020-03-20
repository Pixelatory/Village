package utility.ai.genetic;

/**
 * A genetic algorithm (used mainly for generation).
 *
 * @param <E>
 * @author 6177000
 */
public class Evolution<E> {
    private int generation;
    private Population<E> pop;

    public Evolution(int size) {
        generation = 0;
        pop = new Population<>(size);
    }


}
