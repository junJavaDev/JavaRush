package ru.javarush.island.ogarkov.entity.animals.carnivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Лиса",
        maxWeight = 8,
        maxCount = 30,
        maxSpeed = 2,
        maxFood = 2,
        icon = "/ogarkov/carnivore/fox.png"
)
public class Fox extends CarnivoreAnimal {
}
