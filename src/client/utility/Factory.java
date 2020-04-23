package client.utility;

import client.engine.GameContainer;
import client.model.attack.Generative;

import java.util.concurrent.ThreadLocalRandom;

public class Factory<E extends Generative> {

    public E create(E[] entities) {
        int randNum = ThreadLocalRandom.current().nextInt(entities.length);
        E entity = (E) entities[randNum].clone();
        entity.setXPos(ThreadLocalRandom.current().nextInt(100,GameContainer.getWidth() - 100));
        entity.setYPos(ThreadLocalRandom.current().nextInt(100,GameContainer.getHeight() - 100));
        entity.setLevel(ThreadLocalRandom.current().nextInt(entity.maxLevel()) + 1);
        return entity;
    }
}
