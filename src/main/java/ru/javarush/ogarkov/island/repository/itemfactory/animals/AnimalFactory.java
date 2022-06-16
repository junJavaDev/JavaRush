package ru.javarush.ogarkov.island.repository.itemfactory.animals;

import ru.javarush.ogarkov.island.entity.animals.Animal;
import ru.javarush.ogarkov.island.entity.animals.herbivore.HerbivoreAnimal;
import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class AnimalFactory extends AbstractFactory {
    @Override
    public Animal createItem() {
        created.incrementAndGet();
        return (Animal) getRandomFactory(Items.ANIMAL).createItem();
    }
}
