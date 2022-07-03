package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Гусеница",
        maxWeight = 0.01,
        maxCount = 1000,
        maxFood = 0.01,
        icon = "/ogarkov/herbivore/caterpillar.png"
)
public class Caterpillar extends HerbivoreAnimal {
}
