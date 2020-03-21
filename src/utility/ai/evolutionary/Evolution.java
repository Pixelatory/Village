package utility.ai.evolutionary;

import exceptions.EvolutionPopulateException;
import model.attack.Generative;

/**
 * An evolutionary algorithm (used mainly for generation).
 *
 * @param <E>
 * @author 6177000
 */
public class Evolution<E extends Generative> {
    private int generation;
    private Population<E> pop;

    public Evolution(int size, E[] entities) throws EvolutionPopulateException {
        generation = 0;
        pop = new Population<>(size, entities);
    }
}
