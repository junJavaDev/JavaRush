package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Овца",
        maxWeight = 70,
        maxCount = 140,
        maxSpeed = 3,
        maxFood = 15,
        icon = "/ogarkov/herbivore/sheep.png"
)
public class Sheep extends HerbivoreAnimal {
}
