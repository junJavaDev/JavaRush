package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Буйвол",
        maxWeight = 700,
        maxSpeed = 3,
        maxFood = 100,
        icon = "/ogarkov/icons/herbivore/buffalo.png"
)
public class Buffalo extends HerbivoreAnimal {
}
