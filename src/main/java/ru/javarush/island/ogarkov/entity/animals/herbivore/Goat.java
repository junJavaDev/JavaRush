package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;


@ItemData(
        name = "Коза",
        maxWeight = 60,
        maxCount = 140,
        maxSpeed = 3,
        maxFood = 10,
        icon = "/ogarkov/herbivore/goat.png"
)
public class Goat extends HerbivoreAnimal {
}
