package model.attack;

import model.village.Movable;
import model.village.Upgradable;

public interface Generative extends Cloneable, Movable, Upgradable {
    public String getName();
    public Object clone();
}
