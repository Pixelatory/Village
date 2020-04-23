package client.model.attack;

import client.model.village.Movable;
import client.model.village.Upgradable;

public interface Generative extends Cloneable, Movable, Upgradable {
    public String getName();
    public Object clone();
}
