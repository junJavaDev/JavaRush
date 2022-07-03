package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Кролик",
        maxWeight = 2,
        maxCount = 150,
        maxSpeed = 2,
        maxFood = 0.45,
        icon = "/ogarkov/herbivore/rabbit.png"
)
public class Rabbit extends HerbivoreAnimal {
}
