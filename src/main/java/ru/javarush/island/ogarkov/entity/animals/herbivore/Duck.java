package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Утка",
        maxCount = 200,
        maxSpeed = 4,
        maxFood = 0.15,
        icon = "/ogarkov/herbivore/duck.png"
)
public class Duck extends HerbivoreAnimal {
}
