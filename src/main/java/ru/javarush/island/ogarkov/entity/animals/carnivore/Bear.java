package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.annotations.ItemData;
import ru.javarush.island.ogarkov.settings.Setting;

@ItemData(
        name = "Медведь",
        maxWeight = 500,
        maxCount = 5,
        maxSpeed = 2,
        maxFood = 80,
        icon = "/ogarkov/carnivore/bear.png"
)
public class Bear extends CarnivoreAnimal {

    public Bear() {
        chanceToReproduce = Setting.LOWER_CHANCE_TO_REPRODUCE;
    }
}
