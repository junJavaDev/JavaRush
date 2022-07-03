package ru.javarush.island.ogarkov.entity.plant;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.settings.Setting;
public abstract class Plant extends Organism {

    public Plant() {
        lifeLength = Setting.PLANT_LIFE_LENGTH;
        chanceToReproduce = Setting.PLANT_CHANCE_TO_REPRODUCE;
    }

}
