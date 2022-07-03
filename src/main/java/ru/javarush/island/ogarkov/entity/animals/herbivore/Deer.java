package ru.javarush.island.ogarkov.entity.animals.herbivore;

import ru.javarush.island.ogarkov.annotations.ItemData;

@ItemData(
        name = "Олень",
        maxWeight = 300,
        maxCount = 20,
        maxSpeed = 4,
        maxFood = 50,
        icon = "/ogarkov/herbivore/deer.png"
)
public class Deer extends HerbivoreAnimal {
}
