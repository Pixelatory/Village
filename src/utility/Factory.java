package utility;

import java.util.Random;

public class Factory<E> {

    Random rand = new Random();

    public E create(E[] entities) {
        int randNum = rand.nextInt(entities.length);
        return entities[randNum];
    }
}
