package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Волк",
        maxWeight = 50,
        maxCount = 30,
        maxSpeed = 3,
        maxFood = 8,
        icon = "/ogarkov/carnivore/wolf.png"
)
public class Wolf extends CarnivoreAnimal {
}
