package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Удав",
        maxWeight = 15,
        maxCount = 30,
        maxSpeed = 1,
        maxFood = 3,
        icon = "/ogarkov/carnivore/boa.png"
)
public class Boa extends CarnivoreAnimal {
}
